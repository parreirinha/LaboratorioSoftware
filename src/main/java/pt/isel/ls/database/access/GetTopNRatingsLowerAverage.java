package pt.isel.ls.database.access;

import pt.isel.ls.command.model.Parameters;
import pt.isel.ls.command.model.Path;
import pt.isel.ls.database.printers.PrintGetTopNRatingsLowerAverage;
import pt.isel.ls.database.printers.Printable;
import pt.isel.ls.model.Movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * command nº12
 *
 * GET /tops/{n}/ratings/lower/average
 * returns a list with the n movies with the lower average ratings, sorted decreasingly
 */
public class GetTopNRatingsLowerAverage implements Commands {
    @Override
    public Printable execute(Connection connection, Path path, Parameters parameters) throws SQLException {
        int aux = path.getPathInt("n");


        String querry = "select * from Movie " +
                            "inner join " +
                                "( select top(?) Movie.MovieID from Movie " +
                                    "where OneStar = 0 and TwoStar = 0 and FourStar = 0 and TreeStar = 0 and FiveStar = 0" +
                                ") a " +
                                "on a.MovieID = Movie.MovieID";
        PreparedStatement ps = connection.prepareStatement(querry);
        ps.setInt(1, aux);
        ResultSet rs = ps.executeQuery();
        Collection<Movie> col = new ArrayList<Movie>();
        getCollection(rs, col, aux);
        if(col.size() == aux-1)
        {
            rs.close();
            ps.close();
            return new PrintGetTopNRatingsLowerAverage(col);
        }

        querry = "select * from Movie " +
                    "inner join " +
                        "(select top(?) y.MovieID, y.c from " +
                            "(select MovieID, ((OneStar + (TwoStar*2) + (TreeStar*3) + (FourStar*4) + (FiveStar*5))/(OneStar + TwoStar + TreeStar + FourStar + FiveStar)) as c from Movie " +
                                "where OneStar > 0 or TwoStar > 0 or FourStar > 0 or TreeStar > 0 or FiveStar > 0" +
                            ") y " +
                        ") x " +
                        "ON Movie.MovieID = x.MovieID " +
                    "order by x.c desc";
        ps = connection.prepareStatement(querry);
        ps.setInt(1, aux);
        rs = ps.executeQuery();
        getCollection(rs, col, aux);
        rs.close();
        ps.close();
        return new PrintGetTopNRatingsLowerAverage(col);
    }

    private void getCollection(ResultSet rs, Collection<Movie> col, int aux) throws SQLException
    {
        while(rs.next() && col.size() < aux)
        {
            Movie movie = new Movie(rs.getString(2), rs.getInt(3));
            col.add(movie);
        }
    }

}
