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

    public void RunApp(String[] args) {
        try {
            Command command = new CommandGetter().getCommand(args);
            conn = new ConnectionFactory().connectionFactory();

            if (command == null)
                System.exit(-1);

            new Printer().printResultSet(
                    (ResultSet) new CommandMapper().getExecutionCommandInstance(command).execute(conn, command));

            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
