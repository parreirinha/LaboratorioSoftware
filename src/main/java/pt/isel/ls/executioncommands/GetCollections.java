package pt.isel.ls.executioncommands;

import pt.isel.ls.exceptions.ApplicationException;
import pt.isel.ls.linecommand.model.Command;
import pt.isel.ls.model.Collections;
import pt.isel.ls.printers.PrintGetCollections;
import pt.isel.ls.printers.PrintMensage;
import pt.isel.ls.printers.Printable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import static pt.isel.ls.executioncommands.AccessUtils.*;

/**
 * phase 2 - Command 2
 *
 * GET /collections
 * returns the list of collections, using the insertion order.
 */
public class GetCollections implements CommandExecution {
    @Override
    public Printable execute(Connection connection, Command command) throws SQLException, ApplicationException {

        String query = "select *, " + setClumnRowCountString(null, "CollectionID") +
                " from Collections";
        PreparedStatement ps;
        if (pagingVerification(command)){
            query = concatenateQuearyIfExistsPaging(query, command, "CollectionID");
            ps = connection.prepareStatement(query);
            int[]val = getSkipAndTopValuesToUseInPaging(command);
            setValuesOnPreparedStatement(ps, val[0], val[1]);
        }
        else{ps = connection.prepareStatement(query);}
        ResultSet rs = ps.executeQuery();
        Collection<Collections> res = getCollection(rs);
        if(res.toArray()[0] == "") {
            return new PrintMensage("There are no collections to be return");
        }
        return new PrintGetCollections(res);
    }

    private Collection<Collections> getCollection(ResultSet rs) throws SQLException {
        Collection<Collections> res = new ArrayList<Collections>();
        while (rs.next()) {
            res.add(new Collections(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3)
            ));
        }
        return res;
    }
}
