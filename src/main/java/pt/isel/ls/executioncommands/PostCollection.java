package pt.isel.ls.executioncommands;

import pt.isel.ls.linecommand.model.Command;
import pt.isel.ls.printers.Printable;

import java.sql.Connection;
import java.sql.SQLException;

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
    public Printable execute(Connection connection, Command command) throws SQLException {
        return null;
    }
}
