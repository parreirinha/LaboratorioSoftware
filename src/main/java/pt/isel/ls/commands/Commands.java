package pt.isel.ls.commands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Collection;


/**
 * Public interface that give the definition of the method to execute in the command line
 */
public interface Commands {

    Connection connection = null;
    PreparedStatement preparedStatement = null;

    public Object execute(Object obj);
}
