package pt.isel.ls.executioncommands;

import pt.isel.ls.exceptions.ApplicationException;
import pt.isel.ls.http.ExecutionServlet;
import pt.isel.ls.linecommand.model.Command;
import pt.isel.ls.model.Collections;
import pt.isel.ls.model.Review;
import pt.isel.ls.printers.PrintBody;
import pt.isel.ls.printers.Printable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Function;

import static pt.isel.ls.executioncommands.AccessUtils.setValuesOnPreparedStatement;

/**
 * @author Tede Morgado
 *         Created at 25/05/2016
 */
public class GetAllCollectionsWithIDAux implements CommandExecution {

    @Override
    public Printable execute(Connection connection, Command command) throws SQLException, ApplicationException
    {
        String query = "select * from Collections " +
                "inner join (select cid from MovieCollection " +
                    "where MovieID = ?) as mc " +
                "on Collections.CollectionID = mc.cid";
        PreparedStatement ps = connection.prepareStatement(query);
        setValuesOnPreparedStatement(ps, command.getPath().getPathInt("mid"));
        ResultSet rs = ps.executeQuery();
        Collection<Collections> res = new ArrayList<>();
        while (rs.next()) {
            res.add(new Collections(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3)
            ));
        }
        if(res.isEmpty())
            return new PrintBody();
        String[] head = {"Collection id", "Name", "Description"};
        ArrayList<Function<Collections, String>> function = new ArrayList<>();
        function.add(col -> "" + col.getCollectionID());
        function.add(col -> col.getName());
        function.add(col -> col.getDescription());
        ArrayList<String> uri = new ArrayList<>();
        res.forEach(x -> uri.add("http://localhost:"+
                ExecutionServlet.getPort()+"/collections/"+x.getCollectionID()+"/"));
        return new PrintBody(res, head, function, uri);
    }
}
