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
    public void reviewIdNotExists() throws SQLException {}




}
