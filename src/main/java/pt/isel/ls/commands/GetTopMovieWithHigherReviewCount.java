package pt.isel.ls.commands;

import pt.isel.ls.commandline.model.Parameters;
import pt.isel.ls.commandline.model.Path;
import pt.isel.ls.database.printers.PrintGetTopReviewHigherCount;
import pt.isel.ls.database.printers.Printable;
import pt.isel.ls.model.Movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * commandline nº 13
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
        PrintGetTopReviewHigherCount printer = new PrintGetTopReviewHigherCount(getMovie(rs));
        rs.close();
        ps.close();
        return printer;
    }

    private Movie getMovie(ResultSet rs) throws SQLException {
        if (rs.next())
            return new Movie(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8));
        return null;
    }
}
