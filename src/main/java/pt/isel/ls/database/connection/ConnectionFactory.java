package pt.isel.ls.database.connection;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;

import static java.lang.System.getenv;

/**
 * Class used as a SQL connection factory.
 */
public class ConnectionFactory {


    private SQLServerDataSource conn;
    private final String LS_DB_USER = getenv("dbuser");
    private final String LS_DB_SERVER = getenv("dbserver");
    private final String LS_DB_PW = getenv("dbpassword");
    private static final Logger _logger = LoggerFactory.getLogger(ConnectionFactory.class);

    private void initValues() {
        conn = new SQLServerDataSource();
        conn.setPassword(LS_DB_PW);
        conn.setUser(LS_DB_USER);
        conn.setDatabaseName(LS_DB_SERVER);
        conn.setServerName("DANI-PC");

    }

    public Connection getNewConnection() throws SQLServerException {
        _logger.info("------------------------dbpassword = '{}', " +
                "dbuser = '{}', " +
                "dbserver = '{}' -----------------------", LS_DB_PW, LS_DB_USER, LS_DB_SERVER);

        initValues();
        return conn.getConnection();
    }


}
