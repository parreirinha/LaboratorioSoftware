package pt.isel.ls.user.io;

import pt.isel.ls.command.mapping.CommandMapper;
import pt.isel.ls.command.mapping.ExecuteSelection;
import pt.isel.ls.command.mapping.ModelFactory;
import pt.isel.ls.command.model.Command;
import pt.isel.ls.command.process.*;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Dani on 19-03-2016.
 */
public class Run {

    public void RunApp(String[] args) throws SQLException {
        Command c = new CommandGetter().getCommand(args);

        if(c == null)
            System.exit(-1);

        new Printer().printResultSet((ResultSet) new ExecuteSelection().getCommandExecutor(
                new CommandMapper().getCommandIndex(c)).execute(,
                new ModelFactory().getModel(c)));
    }
}
