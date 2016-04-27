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

import static pt.isel.ls.executioncommands.AccessUtils.*;
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
        String query = "select top (?) *, " + setClumnRowCountString(null, " c desc") +  " from (\n" +
                "select R.MovieID, count(R.MovieID)as c from dbo.Review as R\n" +
                "group by R.MovieID) as ct\n" +
                "inner join Movie as M\n" +
                "on\n" +
                "M.MovieId = ct.MovieID\n" +
                "order by c desc";
        PreparedStatement ps = connection.prepareStatement(query);
        if (pagingVerification(command)){
            int[]val = getSkipAndTopValuesToUseInPaging(command);
            query = concatenateQuearyIfExistsPaging(query, command, " c desc");
            ps = connection.prepareStatement(query);
            setValuesOnPreparedStatement(ps, command.getPath().getPathInt("n"), val[0], val[1]);
        }else {
            ps = connection.prepareStatement(query);
            setValuesOnPreparedStatement(ps, command.getPath().getPathInt("n"));
        }


        //ps.setInt(1, command.getPath().getPathInt("n"));
        ResultSet rs = ps.executeQuery();
        Collection<Movie> res = getCollection(rs);
        return new PrintMovie(res);
    }

    private Collection<Movie> getCollection(ResultSet rs) throws SQLException {
        Collection<Movie> res = new ArrayList<Movie>();
        while (rs.next())
            res.add(new Movie(
                    rs.getInt(3),
                    rs.getString(4),
                    rs.getInt(5)
            ));
        return res;
    }

}
