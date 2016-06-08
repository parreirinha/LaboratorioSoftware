package pt.isel.ls.executioncommands;


import pt.isel.ls.exceptions.ApplicationException;
import pt.isel.ls.linecommand.model.Command;
import pt.isel.ls.printers.PrintError;
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
    public Printable execute(Connection connection, Command command) throws SQLException, ApplicationException {

        int movieId = command.getPath().getPathInt("mid");
        String query = "select *, " + setClumnRowCountString(command, null) + " from Review where MovieID = ?";
        PreparedStatement ps;
        //if (pagingVerification(command)) {
            int[] val = getSkipAndTopValuesToUseInPaging(command);
            query = concatenateQuearyIfExistsPaging(query, command, "MovieID");
            ps = connection.prepareStatement(query);
            setValuesOnPreparedStatement(ps, movieId, val[0], val[1]);
        /*} else {
            ps = connection.prepareStatement(query);
            setValuesOnPreparedStatement(ps, movieId);
        }*/
        ResultSet rs = ps.executeQuery();
        Collection<Review> res = getCollection(rs);
        if (res.isEmpty())
            return new PrintError("There are no reviews for movie with id = "+movieId+".");

        return new PrintReview(res, command);
    }

    private Collection<Review> getCollection(ResultSet rs) throws SQLException {
        Collection<Review> res = new ArrayList<Review>();
        while (rs.next()) {
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
