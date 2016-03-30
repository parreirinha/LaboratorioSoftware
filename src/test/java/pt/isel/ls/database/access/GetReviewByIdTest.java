package pt.isel.ls.database.access;

import org.junit.After;
import org.junit.Test;
import pt.isel.ls.database.connection.ConnectionFactory;
import pt.isel.ls.model.Review;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 *
 */
public class GetReviewByIdTest {



    Review review = null;
    Integer[] values = new Integer[2]; // 0-movieId 1-reviewId
    GetReviewById getReviewById = new GetReviewById();
    DataCreationTests dataCreationTests = new DataCreationTests();
    private Connection connection;


    @After
    public void closeConnection() throws SQLException {
        connection.close();
    }
    @Test
    public void reviewIdNotExists() throws SQLException {
        values[0] = 1;
        values[1] = 5;  //review with id = 5 not exists
        connection = new ConnectionFactory().connectionFactory();
        review = (Review) getReviewById.execute(connection, , values, );
        assertNull(review);
    }


    @Test
    public void validReviewId() throws SQLException {
        values[0] = 3;  //movie 3 -matrix
        values[1] = 5;  //review with id = 5 not exists
        connection = new ConnectionFactory().connectionFactory();
        review = (Review) getReviewById.execute(connection, , values, );

        assertEquals((int)values[0],review.getMovieID());
        assertEquals(dataCreationTests.reviews[4].getReviewID(), review.getReviewID());
        assertEquals(dataCreationTests.reviews[4].getReviewName(),review.getReviewName());
        assertEquals(dataCreationTests.reviews[4].getCompleteReview(), review.getCompleteReview());
        assertEquals(dataCreationTests.reviews[4].getReviewRating(), review.getReviewRating());
        assertEquals(dataCreationTests.reviews[4].getReviewSummary(), review.getReviewSummary());
    }

    @Test
    public void movieWithTwoReviews() throws SQLException {
        values[0] = 1;  //movie 3 -matrix
        values[1] = 2;  //review with id = 5 not exists
        connection = new ConnectionFactory().connectionFactory();
        review = (Review) getReviewById.execute(connection, , values, );

        assertEquals((int)values[0], review.getMovieID());
        assertEquals(dataCreationTests.reviews[1].getReviewID(), review.getReviewID());
        assertEquals(dataCreationTests.reviews[1].getReviewName(),review.getReviewName());
        assertEquals(dataCreationTests.reviews[1].getCompleteReview(), review.getCompleteReview());
        assertEquals(dataCreationTests.reviews[1].getReviewRating(), review.getReviewRating());
        assertEquals(dataCreationTests.reviews[1].getReviewSummary(), review.getReviewSummary());
    }

}
