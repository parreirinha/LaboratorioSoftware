package pt.isel.ls.database.access;

import pt.isel.ls.command.model.Parameters;
import pt.isel.ls.command.model.Path;
import pt.isel.ls.model.Movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * command nº 13
 *
 * GET /tops/reviews/higher/count
 * returns the detail for the movie with most reviews.
 */
public class GetTopReviewHigherCount implements Commands
{
    @Override
    public Object execute(Connection connection, Path path, Parameters parameters) throws SQLException
    {
        String querry = "select max(a.counterReviews) as maximum from " +
                            "(select Review.MovieID, count(Review.MovieID) as counterReviews from Review " +
                                "group by Review.MovieID" +
                            ") a";
        PreparedStatement ps = connection.prepareStatement(querry);
        ResultSet rs = ps.executeQuery();
        rs.next();
        int max = rs.getInt(1);
        querry = "select * from Movie " +
                    "inner join " +
                        "(select Review.MovieID, count(Review.MovieID) as counterReviews from Review " +
                            "group by Review.MovieID " +
                        ") a " +
                        "on a.MovieID = Movie.MovieID and a.counterReviews = ?";
        ps = connection.prepareStatement(querry);
        ps.setInt(1, max);
        rs = ps.executeQuery();
        Collection<Movie> col = new ArrayList<Movie>();
        getCollection(rs, col);
        rs.close();
        ps.close();
        return col;
    }

    private void getCollection(ResultSet rs, Collection<Movie> col) throws SQLException {
        while (rs.next()) {
            Movie movie = new Movie(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8));
            col.add(movie);
        }
    }
}
