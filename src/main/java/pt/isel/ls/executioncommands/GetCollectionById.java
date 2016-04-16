package pt.isel.ls.executioncommands;

import pt.isel.ls.linecommand.model.Parameters;
import pt.isel.ls.linecommand.model.Path;
import pt.isel.ls.printers.Printable;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * phase 2 - Command 3
 *
 * GET /collections/{cid}
 * returns the details for the cid collection, namely all the movies in that collection.
 */
public class GetCollectionById implements CommandExecution {
    @Override
    public Printable execute(Connection connection, Path path, Parameters parameters) throws SQLException {
        return null;
    }
}
