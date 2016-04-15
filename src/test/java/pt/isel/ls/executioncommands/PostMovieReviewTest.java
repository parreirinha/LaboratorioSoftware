package pt.isel.ls.executioncommands;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pt.isel.ls.linecommand.model.Command;
import pt.isel.ls.linecommand.process.CommandGetter;
import pt.isel.ls.database.connection.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

/**
 * POST /movies/{mid}/reviews
 * creates a new review for the movie identified by mid, given the following parameters
 *      reviewerName - the reviewer name
 *      reviewSummary - the review summary
 *      review - the complete review
 *      rating - the review rating
 *
 *      expected results:
 *      "something went wrong!!\n"
 *      "review id: " + id;
 */
public class PostMovieReviewTest {

    private Connection connection;
    private PostMovieReview postMovieReview = new PostMovieReview();
    private Command command;
    private String[] input;
    private String expected;
    private String result;
    private DataCreationTests dataTests = new DataCreationTests();


    @After
    public void undoChangesAndCloseConnection() throws SQLException {
        //dataTests.deleteAllMovies();
        dataTests.dropTables();
        connection.close();
    }
    @Before
    public void initConnectionandDataBase() throws SQLException {
        connection = new ConnectionFactory().getNewConnection();
        dataTests.createTables();
        dataTests.insertMoviesToTest();
        dataTests.insertReviewsInMovies();
    }

    @Test
    public void insertReviewInAValidMovieWithOneReview() throws SQLException {

        connection = new ConnectionFactory().getNewConnection();
        input = new String[]{"POST", "/movies/1/reviews", "reviewerName=Chico&reviewSummary=espectaculo&review=eish filme do catano&rating=5"};
        command = new CommandGetter().getCommand(input);
        result = postMovieReview.execute(connection, command.getPath(), command.getParams()).toStringResult();
        expected = "Review ID is: 10";
        assertEquals(expected, result);


    }




}
