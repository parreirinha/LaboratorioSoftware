package pt.isel.ls.user.io;

import pt.isel.ls.command.mapping.CommandMapper;
import pt.isel.ls.command.model.Command;
import pt.isel.ls.command.process.*;
import pt.isel.ls.database.connection.ConnectionFactory;

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

            if (command == null)
                exit();

            new Printer(new CommandMapper()
                    .getExecutionCommandInstance(command)
                    .execute(conn, command.getPath(), command.getParams()))
                    .printResult();

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
        System.exit(-1);
    }

}


