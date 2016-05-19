package pt.isel.ls.executioncommands;

import pt.isel.ls.exceptions.ApplicationException;
import pt.isel.ls.linecommand.model.Command;
import pt.isel.ls.printers.PrintHomePage;
import pt.isel.ls.printers.Printable;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Dani on 18-05-2016.
 */
public class HttpHomePage implements CommandExecution {
    @Override
    public Printable execute(Connection connection, Command command) throws SQLException, ApplicationException {
        return new PrintHomePage(command.getParams().getParamInt("port"));
    }
}
