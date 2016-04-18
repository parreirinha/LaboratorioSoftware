package pt.isel.ls.executioncommands;

import pt.isel.ls.linecommand.model.Parameters;
import pt.isel.ls.linecommand.model.Path;
import pt.isel.ls.printers.*;

import java.sql.*;

/**
 * phase 2 - Command 1
 *
 * POST /collections
 * creates a new collection and returns its identifier, given the following parameters:
 *      name - the tag unique name;
 *      description - the tag description
 */
public class PostCollection implements CommandExecution {

    @Override
    public Printable execute(Connection connection, Path path, Parameters parameters) throws SQLException {

        String collectionName = parameters.getParamString("name");
        String description = parameters.getParamString("description");
        String query = "insert into Collections (Name, Description) " + "values(?,?)";
        PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        AccessUtils.setValuesOnPreparedStatement(ps, collectionName, description);
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        if(rs.next()){
            int cid = rs.getInt(1);
            return new PrintMensage("Collection posted with success, the id of the new collection is " +cid);
        }
        return new PrintMensage("Error inserting new Collection");
    }
}
