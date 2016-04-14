package pt.isel.ls.executioncommands;

import pt.isel.ls.linecommand.model.Parameters;
import pt.isel.ls.linecommand.model.Path;
import pt.isel.ls.printers.PrintPostMovieReview;
import pt.isel.ls.printers.Printable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    public Printable execute(Connection connection, Path path, Parameters parameters) throws SQLException {


        int movieId = path.getPathInt("mid");
        String reviwerName = parameters.getParamString("reviewerName");
        String reviewSummary = parameters.getParamString("reviewSummary");
        String review = parameters.getParamString("review");
        int rating = parameters.getParamInt("rating");
        String query =
                "insert into Review (MovieID, ReviewerName, ReviewSummary, ReviewComplete, ReviewRating)" +
                        "values(?,?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(query);
        AccessUtils.setValuesOnPreparedStatement(ps, movieId, reviwerName, reviewSummary, review, rating);
        ps.executeUpdate();
        connection.commit();

        //increment of the star given in the review
        String query2 = "update Movie set ? = ? + CAST(1 AS NVARCHAR(10)) where MovieID = ?";
        String star = AccessUtils.getColumnName(rating);
        ps = connection.prepareStatement(query2);
        AccessUtils.setValuesOnPreparedStatement(ps, star, star, movieId);
        ps.executeUpdate();
        connection.commit();

        //*** get rid
        String queary3 = "select @@identity";
        ps = connection.prepareStatement(queary3);
        ResultSet rs = ps.executeQuery();
        rs.next();
        int rid = rs.getInt(1);
        ps.close();

        return new PrintPostMovieReview(rid);



    }
}
