package pt.isel.ls.commands;

import pt.isel.ls.commandline.model.Parameters;
import pt.isel.ls.commandline.model.Path;
import pt.isel.ls.database.printers.Printable;

import java.sql.Connection;
import java.sql.SQLException;


/**
 * Public interface that give the definition of the method to execute in the commandline line
 * obj[0] Path
 * obj[1] Parameter
 */
public interface CommandExecution {

    public Printable execute(Connection connection, Path path, Parameters parameters) throws SQLException;
}
