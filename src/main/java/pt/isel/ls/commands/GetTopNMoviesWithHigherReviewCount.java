package pt.isel.ls.commands;

import pt.isel.ls.commandline.model.Parameters;
import pt.isel.ls.commandline.model.Path;
import pt.isel.ls.database.printers.PrintGetTopNMoviesWithHigherReview;
import pt.isel.ls.database.printers.Printable;
import pt.isel.ls.model.Movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * commandline nº14
 * <p>
 * GET /tops/{n}/reviews/higher/count
 * returns a list with the n movies with higher review count.
 */
public class GetTopNMoviesWithHigherReviewCount implements CommandExecution {
    /**
     * @param connection
     * @param path
     * @param parameters @return a collection
     */
    @Override
    public Printable execute(Connection connection, Path path, Parameters parameters) throws SQLException {
        String query = "select top (?) * from Movie as M\n" +
                "inner join(\n" +
                "select R.MovieID, count(R.MovieID)as c from dbo.Review as R\n" +
                "group by R.MovieID)as T\n" +
                "on M.MovieID = T.MovieID\n" +
                "order by M.MovieID";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, path.getPathInt("n"));
        ResultSet rs = ps.executeQuery();
        PrintGetTopNMoviesWithHigherReview printer = new PrintGetTopNMoviesWithHigherReview(getCollection(rs));
        rs.close();
        ps.close();
        return printer;
    }

    private Collection<Movie> getCollection(ResultSet rs) throws SQLException {
        Collection<Movie> col = new ArrayList<Movie>();
        while (rs.next())
            col.add(new Movie(rs.getString(2), rs.getInt(3)));
        return col;
    }

}
