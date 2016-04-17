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
 * linecommand nº12
 *
 * GET /tops/{n}/ratings/lower/average
 * returns a list with the n movies with the lower average ratings, sorted decreasingly
 */
public class GetTopNRatingsLowerAverage implements CommandExecution {
    @Override
    public Printable execute(Connection connection, Command command) throws SQLException {
        int n = command.getPath().getPathInt("n");

        String query = "select top (?) * from(\n" +
                "select *, CONVERT(DECIMAL(4,3), ((M1.OneStar + M1.TwoStar*2 + M1.TreeStar * 3 + M1.FourStar * 4 + M1.FiveStar * 5)\n" +
                "/ cast(((M1.OneStar + M1.TwoStar + M1.TreeStar + M1.FourStar + M1.FiveStar)) AS DECIMAL (4,0)))) as Average from  dbo.Movie as M1)as R\n" +
                "order by R.Average";
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
