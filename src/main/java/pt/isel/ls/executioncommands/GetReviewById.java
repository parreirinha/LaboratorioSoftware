package pt.isel.ls.executioncommands;


import pt.isel.ls.exceptions.ApplicationException;
import pt.isel.ls.linecommand.model.Command;
import pt.isel.ls.printers.PrintDetailedReview;
import pt.isel.ls.printers.Printable;
import pt.isel.ls.model.Review;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;


/**
 * linecommand nº8
 * GET /movies/{mid}/reviews/{rid}
 * returns the full information for the review rid of the movie identified by mid.
 */
public class GetReviewById implements CommandExecution {


    @Override
    public Printable execute(Connection connection, Command command) throws SQLException, ApplicationException {

        int movieId = command.getPath().getPathInt("mid");
        int reviewId = command.getPath().getPathInt("rid");
        String query = "select * from Review where MovieID = ? and ReviewID = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        AccessUtils.setValuesOnPreparedStatement(preparedStatement, movieId, reviewId);
        ResultSet rs = preparedStatement.executeQuery();
        Collection<Review> res = getCollection(rs);
        return new PrintDetailedReview(res);
    }

    private Collection<Review> getCollection(ResultSet rs) throws SQLException {
        Collection<Review> res = new ArrayList<Review>();
        while (rs.next()) {
            res.add(new Review(
                    rs.getInt(1),
                    rs.getInt(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getInt(6)
            ));
        }
        return res;
    }

}
