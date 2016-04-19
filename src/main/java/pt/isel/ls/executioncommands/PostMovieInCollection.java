package pt.isel.ls.executioncommands;

import pt.isel.ls.linecommand.model.Command;
import pt.isel.ls.printers.PrintMensage;
import pt.isel.ls.printers.Printable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * phase 2 - Command 4
 *
 * POST /collections/{cid}/movies/
 * adds a movie to the cid collection, given
 *  mid - the movie unique identifier.
 */
public class PostMovieInCollection implements CommandExecution {
    @Override
    public Printable execute(Connection connection, Command command) throws SQLException {

        int cid = command.getPath().getPathInt("cid");
        int mid = command.getParams().getParamInt("mid");
        String query = "insert into MovieCollection (CID, MovieID) values(?, ?)";
        PreparedStatement ps = connection.prepareStatement(query);
        AccessUtils.setValuesOnPreparedStatement(ps,cid, mid);
        int res = ps.executeUpdate();
        if(res > 0)
            return new PrintMensage("The movie with id = " + mid + " was added with sucess to the collection");
        return new PrintMensage("Could't post the movie in the especified collection");
    }
}
