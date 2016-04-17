package pt.isel.ls.user.io;

import pt.isel.ls.linecommand.mapping.CommandMapper;
import pt.isel.ls.linecommand.model.Command;
import pt.isel.ls.linecommand.process.*;
import pt.isel.ls.database.connection.ConnectionFactory;
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

            new Printer().printResult(new CommandMapper()
                    .getExecutionCommandInstance(command)
                    .execute(conn, command)
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
            if(conn!=null){
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

}


