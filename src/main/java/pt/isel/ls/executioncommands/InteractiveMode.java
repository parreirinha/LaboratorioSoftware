package pt.isel.ls.executioncommands;

import pt.isel.ls.exceptions.ApplicationException;
import pt.isel.ls.linecommand.model.Command;
import pt.isel.ls.printers.PrintMessage;
import pt.isel.ls.printers.Printable;
import pt.isel.ls.user.io.ContinuousInputOutput;
import pt.isel.ls.user.io.Run;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Dani on 13-04-2016.
 */
public class InteractiveMode implements CommandExecution {
    private Run run;

    public InteractiveMode() {
        this.run = new Run();
    }


    @Override
    public Printable execute(Connection connection, Command command) throws SQLException, ApplicationException {
        System.out.println("Interactive Mode: type commands...");
        new ContinuousInputOutput().start();
        return null;
    }
}
