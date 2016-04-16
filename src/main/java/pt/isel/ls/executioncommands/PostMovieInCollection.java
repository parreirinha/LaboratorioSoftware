package pt.isel.ls.executioncommands;

import pt.isel.ls.linecommand.model.Parameters;
import pt.isel.ls.linecommand.model.Path;
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
    public Printable execute(Connection connection, Path path, Parameters parameters) throws SQLException {
        return null;
    }
}
