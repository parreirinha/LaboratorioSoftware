package pt.isel.ls.database.access;

import pt.isel.ls.command.model.Parameters;
import pt.isel.ls.command.model.Path;
import pt.isel.ls.database.printers.PrintGetTopsNRatingsHigherAverage;
import pt.isel.ls.database.printers.Printable;
import pt.isel.ls.model.Movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * command nº10
 * GET /tops/{n}/ratings/higher/average
 * returns a list with the n movies with higher average ratings, sorted decreasingly
 */
public class GetTopsNRatingsHigherAverage implements Commands {
    @Override
    public Printable execute(Connection connection, Path path, Parameters parameters) throws SQLException {
        int n = path.getPathInt("n");

        String query = "select top (?) * from(\n" +
                "select *, CONVERT(DECIMAL(4,3), ((M1.OneStar + M1.TwoStar*2 + M1.TreeStar * 3 + M1.FourStar * 4 + M1.FiveStar * 5)\n" +
                "/ cast(((M1.OneStar + M1.TwoStar + M1.TreeStar + M1.FourStar + M1.FiveStar)) AS DECIMAL (4,0)))) as Average from  dbo.Movie as M1)as R\n" +
                "order by R.Average desc";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, n);
        ResultSet rs = ps.executeQuery();
        PrintGetTopsNRatingsHigherAverage printer = new PrintGetTopsNRatingsHigherAverage(getCollection(rs, n));
        rs.close();
        ps.close();
        return printer;
    }

    private Collection<Movie> getCollection(ResultSet rs, int aux) throws SQLException {
        Collection<Movie> col = new ArrayList<>();
        while (rs.next() && col.size() <= aux - 1) {
            col.add(new Movie(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getFloat(9)));
        }
        return col;
    }
}
