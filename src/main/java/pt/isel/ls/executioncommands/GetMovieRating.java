package pt.isel.ls.executioncommands;


import pt.isel.ls.linecommand.model.Parameters;
import pt.isel.ls.linecommand.model.Path;
import pt.isel.ls.printers.PrintMovieRating;
import pt.isel.ls.printers.Printable;
import pt.isel.ls.model.Movie;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * linecommand nº5
 * GET /movies/{mid}/ratings
 * returns the rating information for the movie identified by mid. This rating information include:
 * The rating average
 * The number of votes for each rating value
 *
 * returns a movie object only with the ratings
 */
public class GetMovieRating implements CommandExecution {

    @Override
    public Printable execute(Connection connection, Path path, Parameters parameters) throws SQLException {

        int id = path.getPathInt("mid");
        String query = "select *, CONVERT(DECIMAL(4,3), ((M1.OneStar + M1.TwoStar*2 + M1.TreeStar * 3 + M1.FourStar * 4 + M1.FiveStar * 5)\n" +
                "/ cast(((M1.OneStar + M1.TwoStar + M1.TreeStar + M1.FourStar + M1.FiveStar)) AS DECIMAL (4,0)))) as Average from Movie as M1\n" +
                "where M1.MovieID = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        AccessUtils.setValuesOnPreparedStatement(ps, id);
        ResultSet rs = ps.executeQuery();
        Collection<Movie> res = getCollection(rs);
        return new PrintMovieRating(res);
    }

    private Collection<Movie> getCollection(ResultSet rs) throws SQLException {
        Collection<Movie> res = new ArrayList<Movie>();
        while (rs.next())
        {
            res.add(new Movie(
                    rs.getInt(1),
                    rs.getInt(4),
                    rs.getInt(5),
                    rs.getInt(6),
                    rs.getInt(7),
                    rs.getInt(8),
                    rs.getFloat(9)
            ));
        }
        return res;
    }

}
