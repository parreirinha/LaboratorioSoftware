package pt.isel.ls.executioncommands;

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
 * linecommand nº10
 * GET /tops/{n}/ratings/higher/average
 * returns a list with the n movies with higher average ratings, sorted decreasingly
 */
public class GetTopsNRatingsHigherAverage implements CommandExecution {
    @Override
    public Printable execute(Connection connection, Command command) throws SQLException {
        int n = command.getPath().getPathInt("n");

        String query = "select top (?) * from\n" +
                "dbo.Movie as M order by M.Average desc";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, n);
        ResultSet rs = ps.executeQuery();
        Collection<Movie> res = getCollection(rs, n);
        return new PrintDetailedMovie(res);
    }

    private Collection<Movie> getCollection(ResultSet rs, int aux) throws SQLException {
        Collection<Movie> col = new ArrayList<Movie>();
        while (rs.next() && col.size() <= aux - 1) {
            col.add(new Movie(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getInt(3),
                    AccessUtils.returnArrayStarsGivenAResultSet(rs),
                    rs.getFloat(9)
            ));
        }
        return col;
    }
}
