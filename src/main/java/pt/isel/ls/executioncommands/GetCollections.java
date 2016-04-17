package pt.isel.ls.executioncommands;

import pt.isel.ls.linecommand.model.Command;
import pt.isel.ls.printers.Printable;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * phase 2 - Command 2
 *
 * GET /collections
 * returns the list of collections, using the insertion order.
 */
public class GetCollections implements CommandExecution {
    @Override
    public Printable execute(Connection connection, Command command) throws SQLException {
        return null;
    }
}
