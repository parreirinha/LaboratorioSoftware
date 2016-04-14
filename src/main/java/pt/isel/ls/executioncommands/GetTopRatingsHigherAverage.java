package pt.isel.ls.executioncommands;

import pt.isel.ls.linecommand.model.Parameters;
import pt.isel.ls.linecommand.model.Path;
import pt.isel.ls.printers.PrintGetTopRatingsHigherAverage;
import pt.isel.ls.printers.Printable;
import pt.isel.ls.model.Movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * linecommand nº9
 * GET /tops/ratings/higher/average
 * returns the detail for the movie with the higher average rating.
 */
public class GetTopRatingsHigherAverage implements CommandExecution {
    @Override
    public Printable execute(Connection connection, Path path, Parameters parameters) throws SQLException {
        String query = "select top 1 * from(\n" +
                "select *, CONVERT(DECIMAL(4,3), ((M1.OneStar + M1.TwoStar*2 + M1.TreeStar * 3 + M1.FourStar * 4 + M1.FiveStar * 5)\n" +
                "/ cast(((M1.OneStar + M1.TwoStar + M1.TreeStar + M1.FourStar + M1.FiveStar)) AS DECIMAL (4,0)))) as Average from  dbo.Movie as M1)as R\n" +
                "order by R.Average desc";
        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        PrintGetTopRatingsHigherAverage printer = new PrintGetTopRatingsHigherAverage(getMovie(rs));

        rs.close();
        ps.close();
        return printer;
    }

    private Movie getMovie(ResultSet rs) throws SQLException {
        if (rs.next()) {
            return new Movie(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getFloat(9));
        }
        return null;
    }
}
