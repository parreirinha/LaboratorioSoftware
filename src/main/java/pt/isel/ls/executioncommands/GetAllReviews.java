package pt.isel.ls.executioncommands;


import pt.isel.ls.linecommand.model.Command;
import pt.isel.ls.printers.PrintReview;
import pt.isel.ls.printers.Printable;
import pt.isel.ls.model.Review;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

import static pt.isel.ls.executioncommands.AccessUtils.*;

/**
 * linecommand nº7
 * GET /movies/{mid}/reviews
 * returns all the reviews for the movie identified by mid.
 * The information for each review must not include the full review text.
 */
public class GetAllReviews implements CommandExecution {


    @Override
    public Printable execute(Connection connection, Command command) throws SQLException {

        int movieId = command.getPath().getPathInt("mid");
        String query = "select *, " + setClumnRowCountString(command, null) + " from Review where MovieID = ?";
        //PreparedStatement ps = connection.prepareStatement(query);
        PreparedStatement ps = preparedStatementWithPaging(connection, query, command, null);
        AccessUtils.setValuesOnPreparedStatement(ps, movieId);
        ResultSet rs = ps.executeQuery();
        Collection<Review> res = getCollection(rs);
        return new PrintReview(res);
    }

    private Collection<Review> getCollection(ResultSet rs) throws SQLException {
        Collection<Review> res = new ArrayList<Review>();
        while (rs.next())
        {
            res.add(new Review(
                    rs.getInt(1),
                    rs.getInt(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getInt(6)
            ));
        }
        return res;
    }

}
