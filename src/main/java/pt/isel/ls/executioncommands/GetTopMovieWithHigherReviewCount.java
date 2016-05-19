package pt.isel.ls.executioncommands;

import pt.isel.ls.exceptions.ApplicationException;
import pt.isel.ls.linecommand.model.Command;
import pt.isel.ls.printers.PrintError;
import pt.isel.ls.printers.PrintMovie;
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

    private int[] stars = new int[5];

    @Override
    public Printable execute(Connection connection, Command command) throws SQLException, ApplicationException {
        String query = "select top 1 * from (\n" +
                "select R.MovieID, count(R.MovieID)as c from dbo.Review as R\n" +
                "group by R.MovieID) as ct\n" +
                "left join Movie as M\n" +
                "on\n" +
                "M.MovieId = ct.MovieID\n" +
                "order by c desc ";
        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        Collection<Movie> res = getCollection(rs);
        if (res.isEmpty())
            return new PrintError("There are no movies.");

        return new PrintMovie(res, command);
    }

    private Collection<Movie> getCollection(ResultSet rs) throws SQLException {
        Collection<Movie> res = new ArrayList<Movie>();
        while (rs.next()) {
            res.add(new Movie(
                    rs.getInt(3),
                    rs.getString(4),
                    rs.getInt(5)
            ));
        }
        return res;
    }

}
