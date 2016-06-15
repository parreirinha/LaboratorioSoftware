package pt.isel.ls.executioncommands;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;

import static java.lang.System.getenv;

/**
 * Class used as a test SQL connection factory.
 */
public class TestConnectionFactory {


    private SQLServerDataSource conn = new SQLServerDataSource();
    private final String LS_DB_USER = getenv("dbuser1");
    private final String LS_DB_SERVER = getenv("dbserver1");
    private final String LS_DB_PW = getenv("dbpassword1");

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
