package pt.isel.ls.database.access;

import pt.isel.ls.database.connection.ConnectionFactory;
import pt.isel.ls.model.Movie;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
/**
 * command nº3
 * GET /movies/{mid}
 * returns the detailed information for the movie identified by mid
 * returns a Movie object
 */
public class GetMovie implements Commands {

    private Connection connection = null;
    private Movie movie = null;

    @Override
    public Object execute(Object... obj) throws SQLException {
        int ID = (Integer) obj[0];
        String statementQuery =
                "select * from Movie where (MovieID = " + ID+");";
        connection = new ConnectionFactory().connectionFactory();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(statementQuery);
        Movie res = null;
        while (rs.next()) {
            res = new Movie(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getInt(3),
                    rs.getInt(4),
                    rs.getInt(5),
                    rs.getInt(6),
                    rs.getInt(7),
                    rs.getInt(8)
            );
        }
        connection.close();
        return res;
    }
}
