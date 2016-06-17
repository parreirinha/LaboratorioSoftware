package pt.isel.ls.executioncommands;

import pt.isel.ls.exceptions.ApplicationException;
import pt.isel.ls.linecommand.model.Command;
import pt.isel.ls.linecommand.process.CommandGetter;
import pt.isel.ls.printers.PrintError;
import pt.isel.ls.printers.PrintMessage;
import pt.isel.ls.printers.Printable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

        Integer cid = command.getPath().getPathInt("cid");
        Integer mid = command.getParams().getParamInt("mid");

        if (cid != null && mid != null && cid > 0 && mid > 0 && existsMovie(connection, mid)) {

            int verification = existsInCollection(connection, cid, mid);
            if (verification > 0){
                String[] cmd = {"GET", "/movies/"+verification, ""};
                return new GetMovie().execute(connection, new CommandGetter().getCommand(cmd));
            }

            String query = "insert into MovieCollection (CID, MovieID) values(?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);
            AccessUtils.setValuesOnPreparedStatement(ps, cid, mid);
            int res = ps.executeUpdate();
            connection.commit();
            if (res > 0) {
                command.setLocation("/collections/"+cid+"/");
                return new PrintMessage("The movie with id = " + mid + " was added with success to the collection");
            }
            return new PrintMessage("Couldn't post the movie in the specified collection");
        }
        String errorString="";
        if(cid < 1 || cid == null)
            errorString += "Error: Invalid collection id.\n";
        if(mid == null || mid < 1)
            errorString += "Error: Invalid movie id.\n";
        return new PrintError(errorString);
    }

    private int existsInCollection(Connection conn, int cid, int mid) throws SQLException {
        String q = "select * from MovieCollection where CID = ? and MovieID = ?";
        PreparedStatement ps = conn.prepareStatement(q);
        AccessUtils.setValuesOnPreparedStatement(ps, cid, mid);
        ResultSet rs = ps.executeQuery();
        if(rs.next())
            return rs.getInt(2);
        return 0;
    }

    private boolean existsMovie(Connection conn, int mid) throws SQLException {
        String q = "select * from Movie where MovieID = ?";
        PreparedStatement ps = conn.prepareStatement(q);
        AccessUtils.setValuesOnPreparedStatement(ps, mid);
        ResultSet rs = ps.executeQuery();
        if(rs.next())
            return true;

        mid = 0;
        return false;
    }
}
