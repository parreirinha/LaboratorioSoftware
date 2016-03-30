package pt.isel.ls.user.io;

import pt.isel.ls.command.mapping.CommandMapper;
import pt.isel.ls.command.model.Command;
import pt.isel.ls.command.process.*;
import pt.isel.ls.database.connection.ConnectionFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class used to execute the application.
 */
public class Run {
    private Connection conn;

<<<<<<< HEAD
    public void RunApp(String[] args) throws SQLException {
        Command c = new CommandGetter().getCommand(args);
=======
    public void RunApp(String[] args) {
        try {
            Command command = new CommandGetter().getCommand(args);
            conn = new ConnectionFactory().connectionFactory();
>>>>>>> 2ad12e18025b8e4514c16d848f5b0583e5165d01

            if (command == null)
                System.exit(-1);

<<<<<<< HEAD
        new Printer().printResultSet((ResultSet) new ExecuteSelection().getCommandExecutor(
                new CommandMapper().getCommandIndex(c)).execute(,
                new ModelFactory().getModel(c)));
=======
            new Printer().printResultSet(
                    (ResultSet) new CommandMapper().getExecutionCommandInstance(command).execute(conn, , command, ));

            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
>>>>>>> 2ad12e18025b8e4514c16d848f5b0583e5165d01
    }
}
