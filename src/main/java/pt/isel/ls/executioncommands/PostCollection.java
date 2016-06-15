package pt.isel.ls.executioncommands;

import pt.isel.ls.exceptions.ApplicationException;
import pt.isel.ls.linecommand.model.Command;
import pt.isel.ls.linecommand.process.CommandGetter;
import pt.isel.ls.printers.*;

import java.sql.*;

/**
 * phase 2 - Command 1
 * <p>
 * POST /collections
 * creates a new collection and returns its identifier, given the following parameters:
 * name - the tag unique name;
 * description - the tag description
 */
public class PostCollection implements CommandExecution {

    @Override
    public Printable execute(Connection connection, Command command) throws SQLException, ApplicationException {

        String collectionName = command.getParams().getParamString("name");
        String description = command.getParams().getParamString("description");
        if (collectionName != null && description != null) {


            int verification = existsCollection(connection, collectionName);
            if (verification > 0){
                String[] cmd = {"GET", "/collections/"+verification, ""};
                return new GetCollectionById().execute(connection, new CommandGetter().getCommand(cmd));
            }

            String query = "insert into Collections (Name, Description) " + "values(?,?)";
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            AccessUtils.setValuesOnPreparedStatement(ps, collectionName, description);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            connection.commit();
            if (rs.next()) {
                int cid = rs.getInt(1);
                command.setLocation("/collections/");
                return new PrintMessage("Collection posted with success, the id of the new collection is " + cid);
            }
            return new PrintMessage("Error inserting new Collection");
        }
        String errorString="";
        if(collectionName == null)
            errorString += "Error: Invalid collection name.\n";
        if(description == null)
            errorString += "Error: Invalid collection description.\n";

        return new PrintError(errorString);
    }


    private int existsCollection(Connection conn, String name) throws SQLException {
        String q = "select * from Collections where Name = ?";
        PreparedStatement ps = conn.prepareStatement(q);
        AccessUtils.setValuesOnPreparedStatement(ps, name);
        ResultSet rs = ps.executeQuery();
        if(rs.next())
            return rs.getInt(1);
        return 0;
    }
}
