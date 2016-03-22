package pt.isel.ls.commands;

import org.junit.Test;
import pt.isel.ls.model.Movie;
import pt.isel.ls.model.Review;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by fabio on 21-Mar-16.
 */
public class GetAllReviewsTest {


    private DataCreationTests dataCreation = new DataCreationTests();
    private GetAllReviews test = new GetAllReviews();

    @Test
    public void testReviews() throws SQLException {
        ArrayList<Review> container = (ArrayList<Review>) test.execute(null);
        int i = 0;
        for (Review review : container) {
            assertEquals(review.reviewID, dataCreation.reviews[i].reviewID);
            assertEquals(review.movieID, dataCreation.reviews[i].movieID);
            assertEquals(review.reviewName, dataCreation.reviews[i].reviewName);
            assertEquals(review.reviewRating, dataCreation.reviews[i].reviewRating);
            assertEquals(review.reviewSummary, dataCreation.reviews[i].reviewSummary);
            assertEquals(review.completeReview, dataCreation.reviews[i].completeReview);
            i++;
        }


    }
}
