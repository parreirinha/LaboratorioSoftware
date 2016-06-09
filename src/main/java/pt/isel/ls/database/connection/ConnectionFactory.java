package pt.isel.ls.database.connection;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;

import static java.lang.System.getenv;

/**
 * Class used as a SQL connection factory.
 */
public class ConnectionFactory {


    private SQLServerDataSource conn = new SQLServerDataSource();
    private final String LS_DB_USER = getenv("dbuser");
    private final String LS_DB_SERVER = getenv("dbserver");
    private final String LS_DB_PW = getenv("dbpassword");

    private void initValues() {
        conn.setPassword(LS_DB_PW);
        conn.setUser(LS_DB_USER);
        conn.setDatabaseName(LS_DB_SERVER);
    }

    public Connection getNewConnection() throws SQLServerException {
        initValues();
        return conn.getConnection();
    }


}
