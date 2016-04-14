package pt.isel.ls.user.io;

import pt.isel.ls.linecommand.mapping.CommandMapper;
import pt.isel.ls.linecommand.model.Command;
import pt.isel.ls.linecommand.process.*;
import pt.isel.ls.database.connection.ConnectionFactory;
import pt.isel.ls.executioncommands.Exit;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Class used to execute the application.
 */
public class Run {
    private Connection conn;

    public void RunApp(String[] args) {
        try {
            Command command = new CommandGetter().getCommand(args);
            conn = new ConnectionFactory().getNewConnection();
            conn.setAutoCommit(false);
            if (command == null)
                exit();

            new Printer().printResult(new CommandMapper()
                    .getExecutionCommandInstance(command)
                    .execute(conn, command.getPath(), command.getParams())
            );


        } catch (SQLException e) {
            tryRollback(conn);
            e.printStackTrace();
        } finally {
          tryClose(conn);
        }
    }

    private void tryClose(Connection conn){
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error: Database Access Error.");
            e.printStackTrace();
            exit();
        }
    }

    private void tryRollback(Connection conn) {
        try {
            conn.rollback();
        } catch (SQLException e) {
            System.out.println("Error: Database Access Error.");
            e.printStackTrace();
            exit();
        }
    }

    private void exit(){
        new Exit().execute();
    }

}


