package pt.isel.ls.executioncommands;

import pt.isel.ls.exceptions.ApplicationException;
import pt.isel.ls.linecommand.model.Command;
import pt.isel.ls.printers.Printable;
import pt.isel.ls.printers.PrintExit;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Dani on 12-04-2016.
 */
public class Exit implements CommandExecution {

    @Override
    public Printable execute(Connection connection, Command command) throws SQLException, ApplicationException {
        System.out.println("Exiting...");
        System.exit(0);
        return new PrintExit();

    }
}
