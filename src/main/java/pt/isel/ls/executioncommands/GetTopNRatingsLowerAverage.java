package pt.isel.ls.executioncommands;

import pt.isel.ls.exceptions.ApplicationException;
import pt.isel.ls.linecommand.model.Command;
import pt.isel.ls.printers.PrintDetailedMovie;
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
 * linecommand nº12
 *
 * GET /tops/{n}/ratings/lower/average
 * returns a list with the n movies with the lower average ratings, sorted decreasingly
 */
public class GetTopNRatingsLowerAverage implements CommandExecution {
    @Override
    public Printable execute(Connection connection, Command command) throws SQLException, ApplicationException {
        int n = command.getPath().getPathInt("n");

        String query =
                "select top (?) *," + setClumnRowCountString(command, "Average") +" from\n" +
                "dbo.Movie as M order by M.Average";
        PreparedStatement ps;

        if (pagingVerification(command)){
            int[]val = getSkipAndTopValuesToUseInPaging(command);
            query = concatenateQuearyIfExistsPaging(query, command, "Average");
            ps = connection.prepareStatement(query);
            setValuesOnPreparedStatement(ps, n, val[0], val[1]);
        }else {
            ps = connection.prepareStatement(query);
            setValuesOnPreparedStatement(ps, n);
        }

        ResultSet rs = ps.executeQuery();
        Collection<Movie> res = getCollection(rs, n);
        return new PrintDetailedMovie(res);
    }

    private Collection<Movie> getCollection(ResultSet rs, int aux) throws SQLException {
        Collection<Movie> col = new ArrayList<Movie>();
        while (rs.next() && col.size() <= aux - 1) {
            col.add(new Movie(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getInt(3),
                    AccessUtils.returnArrayStarsGivenAResultSet(rs),
                    rs.getFloat(9)
            ));
        }
        return col;
    }

}
