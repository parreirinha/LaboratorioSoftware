package pt.isel.ls.executioncommands;

import pt.isel.ls.linecommand.model.Command;
import pt.isel.ls.printers.PrintMovie;
import pt.isel.ls.printers.Printable;
import pt.isel.ls.model.Movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * linecommand nº14
 * <p>
 * GET /tops/{n}/reviews/higher/count
 * returns a list with the n movies with higher review count.
 */
public class GetTopNMoviesWithHigherReviewCount implements CommandExecution {
    /**
     * @param connection
     * @param command
     */
    @Override
    public Printable execute(Connection connection, Command command) throws SQLException {
        String query = "select top (?) * from Movie as M\n" +
                "inner join(\n" +
                "select R.MovieID, count(R.MovieID)as c from dbo.Review as R\n" +
                "group by R.MovieID)as T\n" +
                "on M.MovieID = T.MovieID\n" +
                "order by M.MovieID";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, command.getPath().getPathInt("n"));
        ResultSet rs = ps.executeQuery();
        Collection<Movie> res = getCollection(rs);
        return new PrintMovie(res);
    }

    private Collection<Movie> getCollection(ResultSet rs) throws SQLException {
        Collection<Movie> res = new ArrayList<Movie>();
        while (rs.next())
            res.add(new Movie(
                    //TODO falta completar o id correcto. está a preencher com default = 0
                    rs.getString(2),
                    rs.getInt(3)
            ));
        return res;
    }

}
