package pt.isel.ls.executioncommands;

import pt.isel.ls.linecommand.model.Command;
import pt.isel.ls.printers.Printable;

import java.sql.Connection;
import java.sql.SQLException;


/**
 * Public interface that give the definition of the method to execute in the linecommand line
 * obj[0] Path
 * obj[1] Parameter
 */
public interface CommandExecution {

    public Printable execute(Connection connection, Command command) throws SQLException;
}

//TODO Não esquecer criar a hierarquia de excepções!!!!
