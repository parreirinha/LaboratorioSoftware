package pt.isel.ls.executioncommands;

import pt.isel.ls.exceptions.ApplicationException;
import pt.isel.ls.http.HttpServer;
import pt.isel.ls.linecommand.model.Command;
import pt.isel.ls.printers.PrintError;
import pt.isel.ls.printers.PrintMessage;
import pt.isel.ls.printers.Printable;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Dani on 13-05-2016.
 */
public class Listen implements CommandExecution {

    public HttpServer server = new HttpServer();

    @Override
    public Printable execute(Connection connection, Command command) throws SQLException, ApplicationException {
        int port = command.getParams().getParamInt("port");

        if (port == -1) {
            return new PrintError("Error: Invalid port");
        }

        try {
            server.initServer(port);
        } catch (Exception e) {
            throw new ApplicationException();
        }

        return new PrintMessage("Server started.");
    }

}
