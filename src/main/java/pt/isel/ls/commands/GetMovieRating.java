package pt.isel.ls.commands;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import pt.isel.ls.dbconnection.ConnectionFactory;
import pt.isel.ls.model.Movie;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;

/**
 * command nº5
 * GET /movies/{mid}/ratings
 * returns the rating information for the movie identified by mid. This rating information include:
 * The rating average
 * The number of votes for each rating value
 *
 * returns a movie object only with the ratings
 */
public class GetMovieRating implements Commands {

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
