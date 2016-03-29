package pt.isel.ls.database.access;

import java.sql.Connection;
import java.sql.SQLException;


/**
 * Public interface that give the definition of the method to execute in the command line
 * obj[0] Path
 * obj[1] Parameter
 */
public interface Commands {

    public Object execute(Connection connection, Object... obj) throws SQLException;
}
