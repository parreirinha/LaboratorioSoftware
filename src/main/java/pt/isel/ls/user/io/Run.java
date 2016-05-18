package pt.isel.ls.user.io;

import pt.isel.ls.exceptions.ApplicationException;
import pt.isel.ls.linecommand.mapping.CommandMapper;
import pt.isel.ls.linecommand.model.Command;
import pt.isel.ls.linecommand.process.*;
import pt.isel.ls.database.connection.ConnectionFactory;
import pt.isel.ls.printers.Printable;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Class used to execute the application.
 */
public class Run {
    private Connection conn;
    Command command;

    public void RunApp(String[] args) throws ApplicationException {
        try {
            command = new CommandGetter().getCommand(args);
            conn = new ConnectionFactory().getNewConnection();
            conn.setAutoCommit(false);

            identifyOutputType(command, identifyOutputFormat(command, new CommandMapper()
                    .getExecutionCommandInstance(command)
                    .execute(conn, command)));


        } catch (SQLException e) {
            tryRollback(conn);
            throw new ApplicationException();
        } finally {
            tryClose(conn);
        }
    }

    private void tryClose(Connection conn) throws ApplicationException {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            throw new ApplicationException();
        }
    }

    private void tryRollback(Connection conn) throws ApplicationException {
        try {
            conn.rollback();
        } catch (SQLException e) {
            throw new ApplicationException();
        } catch (NullPointerException e1) {
            throw new ApplicationException();
        }
    }

    public static String identifyOutputFormat(Command command, Printable p) {
        String format = command.getHeaders().getHeadersString("accept");
        if (format != null && format.equals("text/plain")) {
            return p.toStringText();
        } else {
            return p.toStringHtml();
        }
    }

    private void identifyOutputType(Command command, String out) throws ApplicationException {
        String filename = command.getHeaders().getHeadersString("file-name");

        if (filename == null) {
            new Printer().printResult(out);
        } else {
            new Writer().writeToFile(out, filename);
        }
    }

}


