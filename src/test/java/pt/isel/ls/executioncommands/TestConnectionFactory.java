package pt.isel.ls.executioncommands;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;

import static java.lang.System.getenv;

/**
 * Created by Dani on 21-04-2016.
 */
public class TestConnectionFactory {


    private SQLServerDataSource conn = new SQLServerDataSource();
    private final String LS_DB_USER = getenv("LS_DB_USER");
    private final String LS_DB_SERVER = "lsTests";
    private final String LS_DB_PW = getenv("LS_DB_PW");

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
