package pt.isel.ls.database.access;

import org.junit.After;
import org.junit.Test;
import pt.isel.ls.database.connection.ConnectionFactory;
import pt.isel.ls.model.Review;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by fabio on 21-Mar-16.
 */
public class GetAllReviewsTest {

    private Connection connection;
    private DataCreationTests dataCreation = new DataCreationTests();
    private GetAllReviews test = new GetAllReviews();

    @After
    private void closeConnection() throws SQLException {
        connection.close();
    }

    @Test
    public void testReviews() throws SQLException {
        connection = new ConnectionFactory().connectionFactory();
        ArrayList<Review> container = (ArrayList<Review>) test.execute(connection, null);
        int i = 0;

        for (Review review : container) {
            assertEquals(dataCreation.reviews[i].getReviewID(), review.getReviewID());
            assertEquals(dataCreation.reviews[i].getMovieID(), review.getMovieID());
            assertEquals(dataCreation.reviews[i].getReviewName(), review.getReviewName());
            assertEquals(dataCreation.reviews[i].getReviewRating(), review.getReviewRating());
            assertEquals(dataCreation.reviews[i].getReviewSummary(), review.getReviewSummary());
            assertEquals(dataCreation.reviews[i].getCompleteReview(), review.getCompleteReview());
            i++;
        }
    }
}
