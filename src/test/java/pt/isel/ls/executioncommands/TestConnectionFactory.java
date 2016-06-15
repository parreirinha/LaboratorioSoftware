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
    private final String LS_DB_USER = "eoscrdcjsavogkyh";
    private final String LS_DB_SERVER = "2154d7d2-a4e0-4536-9175-a6260133d4e8.sqlserver.sequelizer.com";
    private final String LS_DB_PW = "GF4YDkDwevbQySzBRS2Tp4iTs5WPJ2sGy84FM228dvasgx68JwZEGmcLwiEGvXUk";

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
