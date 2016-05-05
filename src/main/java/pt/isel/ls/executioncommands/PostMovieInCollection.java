package pt.isel.ls.executioncommands;

import pt.isel.ls.exceptions.ApplicationException;
import pt.isel.ls.linecommand.model.Command;
import pt.isel.ls.printers.PrintError;
import pt.isel.ls.printers.PrintMessage;
import pt.isel.ls.printers.Printable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * phase 2 - Command 4
 * <p>
 * POST /collections/{cid}/movies/
 * adds a movie to the cid collection, given
 * mid - the movie unique identifier.
 */
public class PostMovieInCollection implements CommandExecution {
    @Override
    public Printable execute(Connection connection, Command command) throws SQLException, ApplicationException {

        int cid = command.getPath().getPathInt("cid");
        int mid = command.getParams().getParamInt("mid");

        if (cid != -1 && mid != -1) {
            String query = "insert into MovieCollection (CID, MovieID) values(?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);
            AccessUtils.setValuesOnPreparedStatement(ps, cid, mid);
            int res = ps.executeUpdate();
            connection.commit();
            if (res > 0) {
                return new PrintMessage("The movie with id = " + mid + " was added with success to the collection");
            }
                return new PrintMessage("Couldn't post the movie in the specified collection");
        }
        String errorString="";
        if(cid == -1)
            errorString += "Error: Invalid collection id.\n";
        if(mid == -1)
            errorString += "Error: Invalid movie id.\n";

        return new PrintError(errorString);
    }
}
