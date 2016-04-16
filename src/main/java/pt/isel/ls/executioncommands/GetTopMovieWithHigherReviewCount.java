package pt.isel.ls.executioncommands;

import pt.isel.ls.linecommand.model.Parameters;
import pt.isel.ls.linecommand.model.Path;
import pt.isel.ls.printers.PrintDetailedMovie;
import pt.isel.ls.printers.Printable;
import pt.isel.ls.model.Movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * linecommand nº 13
 * <p>
 * GET /tops/reviews/higher/count
 * returns the detail for the movie with most reviews.
 */
public class GetTopMovieWithHigherReviewCount implements CommandExecution {
    @Override
    public Printable execute(Connection connection, Path path, Parameters parameters) throws SQLException {
        String query = "select top 1 * from Movie as M\n" +
                "inner join(\n" +
                "select R.MovieID, count(R.MovieID)as c from dbo.Review as R\n" +
                "group by R.MovieID)as T\n" +
                "on M.MovieID = T.MovieID\n" +
                "order by M.MovieID";
        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        Collection<Movie> res = getCollection(rs);
        return new PrintDetailedMovie(res);
    }

    private Collection<Movie> getCollection(ResultSet rs) throws SQLException {
        Collection<Movie> res = new ArrayList<Movie>();
        while (rs.next())
        {
            res.add(new Movie(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getInt(3),
                    rs.getInt(4),
                    rs.getInt(5),
                    rs.getInt(6),
                    rs.getInt(7),
                    rs.getInt(8)
            ));
        }
        return res;
    }

}
