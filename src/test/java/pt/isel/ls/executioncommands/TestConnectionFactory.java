package pt.isel.ls.executioncommands;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;

import static java.lang.System.getenv;

/**
 * Class used as a test SQL connection factory.
 */
public class TestConnectionFactory {


    private SQLServerDataSource conn;
    private final String LS_DB_USER = getenv("dbuser1");
    private final String LS_DB_NAME = getenv("dbname1");
    private final String LS_DB_PW = getenv("dbpassword1");
    private final String LS_DB_SERVER = getenv("dbserver1");

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
