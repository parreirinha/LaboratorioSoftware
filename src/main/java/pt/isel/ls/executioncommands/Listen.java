package pt.isel.ls.executioncommands;

import pt.isel.ls.exceptions.ApplicationException;
import pt.isel.ls.http.HttpServer;
import pt.isel.ls.linecommand.model.Command;
import pt.isel.ls.printers.PrintMessage;
import pt.isel.ls.printers.Printable;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Dani on 13-05-2016.
 */
public class Listen implements CommandExecution {
    @Override
    public Printable execute(Connection connection, Command command) throws SQLException, ApplicationException {
        try {
            new HttpServer().initServer();
        } catch (Exception e) {
            throw new ApplicationException();
        }
        return new PrintMessage("Server stared."); //TODO not printing this, why?
    }
}
