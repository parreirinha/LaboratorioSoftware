package pt.isel.ls.executioncommands;

import pt.isel.ls.linecommand.model.Command;
import pt.isel.ls.printers.Printable;

import java.sql.Connection;
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
        return null;
    }
}
