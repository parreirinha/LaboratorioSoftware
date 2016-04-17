package pt.isel.ls.executioncommands;

import pt.isel.ls.linecommand.model.Command;
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
    public Printable execute(Connection connection, Command command) throws SQLException {
        return null;
    }
}
