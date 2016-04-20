package pt.isel.ls.user.io;

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

    public void RunApp(String[] args) {
        try {
            command = new CommandGetter().getCommand(args);
            conn = new ConnectionFactory().getNewConnection();
            conn.setAutoCommit(false);

            identifyOutputType(command, identifyOutputFormat(command, new CommandMapper()
                    .getExecutionCommandInstance(command)
                    .execute(conn, command)));

        } catch (SQLException e) {
            tryRollback(conn);
            e.printStackTrace();
        } finally {
            tryClose(conn);
        }
    }

    private void tryClose(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Error: Database Access Error.");
            e.printStackTrace();
        }
    }

    private void tryRollback(Connection conn) {
        try {
            conn.rollback();
        } catch (SQLException e) {
            System.out.println("Error: Database Access Error.");
            e.printStackTrace();
        }
    }

    private String identifyOutputFormat(Command command, Printable p) {
        String format = command.getHeaders().getHeadersString("access");
        if (format.equals("text/plain")) {
            return p.toStringText();

        } else {
            return p.toStringHtml();

        }
    }

    private void identifyOutputType(Command command, String out) {
        String filename = command.getHeaders().getHeadersString("file-name");
        if (filename != null) {
            new Printer().printResult(out);
        } else {
            new Writer().writeToFile(out, "filename");
        }
    }

}


