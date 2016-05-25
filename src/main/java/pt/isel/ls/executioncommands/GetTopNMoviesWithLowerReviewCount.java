package pt.isel.ls.executioncommands;

import pt.isel.ls.exceptions.ApplicationException;
import pt.isel.ls.linecommand.model.Command;
import pt.isel.ls.model.Movie;
import pt.isel.ls.printers.PrintError;
import pt.isel.ls.printers.PrintMovie;
import pt.isel.ls.printers.PrintReviewsCount;
import pt.isel.ls.printers.Printable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import static pt.isel.ls.executioncommands.AccessUtils.*;
import static pt.isel.ls.executioncommands.AccessUtils.setValuesOnPreparedStatement;

/**
 * Created by fabio on 20-May-16.
 */
public class GetTopNMoviesWithLowerReviewCount implements CommandExecution {

    //todo
    @Override
    public Printable execute(Connection connection, Command command) throws SQLException, ApplicationException {
        String query = "select top (?) m.*, t.C, " + setClumnRowCountString(command, "C") +
                " from Movie as M\nleft join(\n" +
                "select R.MovieID, count(R.MovieID)as c from dbo.Review as R\n" +
                "group by R.MovieID)as T\n on M.MovieID = T.MovieID\n" +
                "order by M.MovieID";
        PreparedStatement ps;
        if (pagingVerification(command)) {
            int[] val = getSkipAndTopValuesToUseInPaging(command);
            query = concatenateQuearyIfExistsPaging(query, command, "C");
            ps = connection.prepareStatement(query);
            setValuesOnPreparedStatement(ps, command.getPath().getPathInt("n"), val[0], val[1]);
        } else {
            ps = connection.prepareStatement(query);
            setValuesOnPreparedStatement(ps, command.getPath().getPathInt("n"));
        }
        ResultSet rs = ps.executeQuery();
        Collection<Movie> res = getCollection(rs);
        if (res.isEmpty())
            return new PrintError("There are no movies to show.");

        return new PrintReviewsCount(command, res, "/tops/5/reviews/lower/count/");
    }

    private Collection<Movie> getCollection(ResultSet rs) throws SQLException {
        Collection<Movie> res = new ArrayList<Movie>();
        while (rs.next())
            res.add(new Movie(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getInt(3)
            ));
        return res;
    }

}

