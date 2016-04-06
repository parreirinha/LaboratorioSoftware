package pt.isel.ls.database.access;

import pt.isel.ls.command.model.Parameters;
import pt.isel.ls.command.model.Path;
import pt.isel.ls.database.printers.PrintGetTopRatingsHigherAverage;
import pt.isel.ls.database.printers.Printable;
import pt.isel.ls.model.Movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * command nº9
 * GET /tops/ratings/higher/average
 * returns the detail for the movie with the higher average rating.
 */
public class GetTopRatingsHigherAverage implements Commands {
    @Override
    public Printable execute(Connection connection, Path path, Parameters parameters) throws SQLException {
        String querry = "select * from Movie " +
                            "inner join " +
                                "(select max(b.average) as maximum from " +
                                    "(select MovieID, ((OneStar + (TwoStar*2) + (TreeStar*3) + (FourStar*4) + (FiveStar*5))/(OneStar + TwoStar + TreeStar + FourStar + FiveStar)) as average from " +
                                        "(select * from Movie " +
                                            "where OneStar > 0 or TwoStar>0 or FourStar > 0 or TreeStar>0 or FiveStar>0 " +
                                        ") a " +
                                    ") b " +
                                ") c " +
                            "on c.maximum = ((OneStar + (TwoStar*2) + (TreeStar*3) + (FourStar*4) + (FiveStar*5))/(OneStar + TwoStar + TreeStar + FourStar + FiveStar))";
        PreparedStatement ps = connection.prepareStatement(querry);
        ResultSet rs = ps.executeQuery();
        Collection<Movie> col = new ArrayList<Movie>();
        getCollection(rs, col);
        if(col.size() != 0)
        {
            rs.close();
            ps.close();
            return new PrintGetTopRatingsHigherAverage(col);
        }

        querry = "select * from Movie " +
                    "where OneStar = 0 and TwoStar = 0 and FourStar = 0 or TreeStar = 0 or FiveStar = 0";
        ps = connection.prepareStatement(querry);
        rs = ps.executeQuery();
        getCollection(rs, col);

        rs.close();
        ps.close();
        return new PrintGetTopRatingsHigherAverage(col);
    }

    private void getCollection(ResultSet rs, Collection<Movie> col) throws SQLException
    {
        while(rs.next())
        {
            Movie movie = new Movie(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8));
            col.add(movie);
        }
    }
}
