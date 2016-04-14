package pt.isel.ls.executioncommands;

import pt.isel.ls.linecommand.model.Parameters;
import pt.isel.ls.linecommand.model.Path;
import pt.isel.ls.printers.Printable;
import pt.isel.ls.printers.PrintExit;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Dani on 12-04-2016.
 */
public class Exit implements CommandExecution{

    @Override
    public Printable execute(Connection connection, Path path, Parameters parameters) throws SQLException {
        System.exit(-1);
        return new PrintExit();

    }
}
