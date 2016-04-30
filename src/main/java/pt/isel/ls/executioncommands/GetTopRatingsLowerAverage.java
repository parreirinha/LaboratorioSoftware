package pt.isel.ls.executioncommands;

import pt.isel.ls.exceptions.ApplicationException;
import pt.isel.ls.linecommand.model.Command;
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
 * commmand nº11
 * <p>
 * GET /tops/ratings/lower/average
 * returns the detail for the movie with the lower average rating.
 */
public class GetTopRatingsLowerAverage implements CommandExecution {

    @Override
    public Printable execute(Connection connection, Command command) throws SQLException, ApplicationException {
        String query = "select top 1 * from\n dbo.Movie as M order by M.Average";
        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        Collection<Movie> res = getCollection(rs);
        return new PrintDetailedMovie(res);
    }

    private Collection<Movie> getCollection(ResultSet rs) throws SQLException {
        Collection<Movie> res = new ArrayList<Movie>();
        while (rs.next()) {
            res.add(new Movie(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getInt(3),
                    AccessUtils.returnArrayStarsGivenAResultSet(rs),
                    rs.getFloat(9)
            ));
        }
        return res;
    }

}
