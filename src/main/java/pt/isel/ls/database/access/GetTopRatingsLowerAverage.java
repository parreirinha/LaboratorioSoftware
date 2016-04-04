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
 * commmand nº11
 *
 * GET /tops/ratings/lower/average
 * returns the detail for the movie with the lower average rating.
 */
public class GetTopRatingsLowerAverage implements Commands {
    @Override
    public Object execute(Connection connection, Path path, Parameters parameters) throws SQLException
    {
        String querry = "select * from Movie " +
                            "where OneStar = 0 and TwoStar = 0 and FourStar = 0 and TreeStar = 0 and FiveStar = 0";
        PreparedStatement ps = connection.prepareStatement(querry);
        ResultSet rs = ps.executeQuery();
        Collection<Movie> col = new ArrayList<Movie>();
        getCollection(rs, col);
        if(col.size() != 0)
        {
            rs.close();
            ps.close();
            return col;
        }

        querry = "select * from Movie " +
                    "inner join " +
                        "(select min(b.average) as minumum from " +
                            "(select MovieID, ((OneStar + (TwoStar*2) + (TreeStar*3) + (FourStar*4) + (FiveStar*5))/(OneStar + TwoStar + TreeStar + FourStar + FiveStar)) as average from " +
                                "(select * from Movie " +
                                    "where OneStar > 0 or TwoStar>0 or FourStar > 0 or TreeStar>0 or FiveStar>0 " +
                                ") a " +
                            ") b " +
                        ") c " +
                        "on c.minumum = ((OneStar + (TwoStar*2) + (TreeStar*3) + (FourStar*4) + (FiveStar*5))/(OneStar + TwoStar + TreeStar + FourStar + FiveStar)) and (OneStar > 0 or TwoStar>0 or FourStar > 0 or TreeStar>0 or FiveStar>0)";
        ps = connection.prepareStatement(querry);
        rs = ps.executeQuery();
        getCollection(rs, col);

        rs.close();
        ps.close();
        return col;
    }

    private void getCollection(ResultSet rs, Collection<Movie> col) throws SQLException
    {
        rs.next();
        //while(rs.next())
        //{
            Movie movie = new Movie(rs.getString(2), rs.getInt(3));
            col.add(movie);
        //}
    }
}
