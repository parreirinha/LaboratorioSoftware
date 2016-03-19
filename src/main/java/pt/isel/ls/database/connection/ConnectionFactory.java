package pt.isel.ls.database.connection;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;
import java.sql.SQLException;

import static java.lang.System.getenv;

/**
 * Created by fabio on 09-Mar-16.}
 */
public class ConnectionFactory {


    private SQLServerDataSource conn = new SQLServerDataSource();
    private final String LS_DB_USER = getenv("LS_DB_USER");
    private final String LS_DB_SERVER = getenv("LS_DB_SERVER");
    private final String LS_DB_PW = getenv("LS_DB_PW");

    public ConnectionFactory(){}

    private void initValues(){
        conn.setPassword(LS_DB_PW);
        conn.setUser(LS_DB_USER);
        conn.setDatabaseName(LS_DB_SERVER);
    }

    public Connection connectionFactory() throws SQLServerException {
        initValues();
        return conn.getConnection();
    }

    public void connectionClose(Connection conn) throws SQLException {
        if(conn != null)
            conn.close();
    }



}
