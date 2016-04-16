package pt.isel.ls.executioncommands;

import pt.isel.ls.linecommand.model.Parameters;
import pt.isel.ls.linecommand.model.Path;
import pt.isel.ls.printers.Printable;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * phase 2 - Command 5
 *
 * DELETE /collections/{cid}/movies/{mid}
 * removes the movie mid from the collections cid.
 */
public class DeleteMovieFromCollection implements CommandExecution {
    @Override
    public Printable execute(Connection connection, Path path, Parameters parameters) throws SQLException {
        return null;
    }
}
