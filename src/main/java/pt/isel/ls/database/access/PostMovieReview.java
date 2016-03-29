package pt.isel.ls.database.access;

import pt.isel.ls.database.connection.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * command nº6
 * POST /movies/{mid}/reviews
 * creates a new review for the movie identified by mid, given the following parameters
 *      reviewerName - the reviewer name
 *      reviewSummary - the review summary
 *      review - the complete review
 *      rating - the review rating
 * This command returns the review unique identifier.
 */
public class PostMovieReview implements Commands{



    private PreparedStatement preparedStatement = null;

    @Override
    public Object execute(Connection connection, Object... obj) throws SQLException {

        //TODO tenho que incrementar o rating global correspondente!?!?

        String reviwerName = (String) obj[0];
        String reviewSummary = (String) obj[1];
        String review = (String) obj[2];
        int rating = (Integer) obj[3];
        String query =
                "insert into Review (MovieID, ReviewrName, ReviewSummary, ReviewComplete, ReviewRating)" +
                "values(?,?,?,?,?)";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, reviwerName);
        preparedStatement.setString(2, reviewSummary);
        preparedStatement.setString(3,review);
        preparedStatement.setInt(4,rating);
        preparedStatement.executeUpdate();
        connection.commit();

        return null;
    }
}
