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
        //conn.setDatabaseName(LS_DB_SERVER);
        conn.setServerName(LS_DB_SERVER);
    }

    public Connection getNewConnection() throws SQLServerException {
        _logger.info("------------------------dbpassword = '{}'-----------------------", LS_DB_PW);
        _logger.info("------------------------dbuser = '{}'-----------------------", LS_DB_USER);
        _logger.info("------------------------dbserver = '{}'-----------------------", LS_DB_SERVER);

        initValues();
        _logger.info("------------------------'{}'-----------------------", conn.toString());
        return conn.getConnection();
    }


}
