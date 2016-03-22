package pt.isel.ls.commands;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;


/**
 * Public interface that give the definition of the method to execute in the command line
 */
public interface Commands {

    public Object execute(Object... obj) throws SQLException;
}
