package pt.isel.ls.executioncommands;

import pt.isel.ls.exceptions.ApplicationException;
import pt.isel.ls.linecommand.model.Command;
import pt.isel.ls.printers.PrintError;
import pt.isel.ls.printers.PrintPostMovieAndReview;
import pt.isel.ls.printers.Printable;

import java.sql.*;

/**
 * linecommand nº6
 * POST /movies/{mid}/reviews
 * creates a new review for the movie identified by mid, given the following parameters
 *      reviewerName - the reviewer name
 *      reviewSummary - the review summary
 *      review - the complete review
 *      rating - the review rating
 * This linecommand returns the review unique identifier.
 */
public class PostMovieReview implements CommandExecution {


    @Override
    public Printable execute(Connection connection, Command command) throws SQLException, ApplicationException {

        int movieId = command.getPath().getPathInt("mid");
        String reviwerName = command.getParams().getParamString("reviewerName");
        String reviewSummary = command.getParams().getParamString("reviewSummary");
        String review = command.getParams().getParamString("review");
        int rating = command.getParams().getParamInt("rating");

        if(movieId!=-1 && reviwerName != null && reviewSummary != null &&
                review != null && rating !=-1) {

            String query = "insert into Review " +
                    "(MovieID, ReviewerName, ReviewSummary, ReviewComplete, ReviewRating)" +
                    "values(?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            AccessUtils.setValuesOnPreparedStatement(ps, movieId, reviwerName, reviewSummary, review, rating);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int rid = rs.getInt(1);
            connection.commit();

            //increment of the star given in the review
            String query2 = "update Movie set ? = ? + CAST(1 AS NVARCHAR(10)) where MovieID = ?";
            String star = AccessUtils.getColumnName(rating);
            ps = connection.prepareStatement(query2);
            AccessUtils.setValuesOnPreparedStatement(ps, star, star, movieId);
            ps.executeUpdate();
            connection.commit();

            return new PrintPostMovieAndReview(rid, "Review ID is");

        }
        return new PrintError("Error: Invalid parameter(s).");
    }
}
