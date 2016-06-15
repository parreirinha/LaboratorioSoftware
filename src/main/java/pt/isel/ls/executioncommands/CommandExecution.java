package pt.isel.ls.executioncommands;

import pt.isel.ls.exceptions.ApplicationException;
import pt.isel.ls.linecommand.model.Command;
import pt.isel.ls.printers.Printable;

import java.sql.Connection;
import java.sql.SQLException;


/**
 * Public interface that defines the model of execution commands.
 */
public interface CommandExecution {

    public Printable execute(Connection connection, Command command) throws SQLException, ApplicationException;
}

