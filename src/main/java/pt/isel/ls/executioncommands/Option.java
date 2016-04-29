package pt.isel.ls.executioncommands;

import pt.isel.ls.exceptions.ApplicationException;
import pt.isel.ls.linecommand.model.Command;
import pt.isel.ls.printers.PrintOption;
import pt.isel.ls.printers.Printable;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Dani on 12-04-2016.
 */
public class Option implements CommandExecution {

    private final String filename = "src/main/resources/texts/Option.txt";

    @Override
    public Printable execute(Connection connection, Command command) throws SQLException, ApplicationException {
        return new PrintOption(filename);
    }
}
