package pt.isel.ls.database.access;

import pt.isel.ls.command.model.Parameters;
import pt.isel.ls.command.model.Path;
import pt.isel.ls.database.printers.Printable;
import pt.isel.ls.model.Movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * command nº14
 *
 * GET /tops/{n}/reviews/higher/count
 * returns a list with the n movies with higher review count.
 */
public class GetTopNMoviesWithHigherReview implements Commands{
    /**
     *
     * @param connection
     * @param path
     *@param parameters @return a collection
     */
    @Override
    public Printable execute(Connection connection, Path path, Parameters parameters) throws SQLException {
        String querry = "select * from Movie " +
                            "inner join " +
                                "(select top(?) * from " +
                                    "(select Review.MovieID, count(Review.MovieID) as number from Review " +
                                        "group by MovieID " +
                                    ") x " +
                                    "order by MovieID desc" +
                                ") y " +
                                "on Movie.MovieID = y.MovieID " +
                            "order by Movie.MovieID asc";
        PreparedStatement ps = connection.prepareStatement(querry);
        ps.setInt(1, path.getPathInt("n"));
        ResultSet rs = ps.executeQuery();
        Collection<Movie> col = new ArrayList<Movie>();
        while(rs.next())
        {
            Movie movie = new Movie(rs.getString(2), rs.getInt(3));
            col.add(movie);
        }
        rs.close();
        ps.close();
        return col;
    }

}
