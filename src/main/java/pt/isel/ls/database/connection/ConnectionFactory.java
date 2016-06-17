package pt.isel.ls.database.connection;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;

import static java.lang.System.getenv;

/**
 * Class used as a SQL connection factory.
 */
public class ConnectionFactory {


    private SQLServerDataSource conn;
    private final String LS_DB_USER = getenv("dbuser");
    private final String LS_DB_NAME = getenv("dbname");
    private final String LS_DB_PW = getenv("dbpassword");
    private final String LS_DB_SERVER = getenv("dbserver");

    private void initValues() {
        conn = new SQLServerDataSource();
        conn.setServerName(LS_DB_SERVER);
        conn.setDatabaseName(LS_DB_NAME);
        conn.setUser(LS_DB_USER);
        conn.setPassword(LS_DB_PW);
    }

    public Connection getNewConnection() throws SQLServerException {
        initValues();
        return conn.getConnection();
    }


}
