package pt.isel.ls.database.access;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pt.isel.ls.command.model.Command;
import pt.isel.ls.command.process.CommandGetter;
import pt.isel.ls.database.connection.ConnectionFactory;
import pt.isel.ls.model.Review;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * * GET /movies/{mid}/reviews/{rid}
 * returns the full information for the review rid of the movie identified by mid.
 *
 * "reviewid: ##\nmovie id: ##\nreviewer name: ##\nreview summary: ##\ncomplete review: ##\n"
 *
 */
public class GetReviewByIdTest {



    private Connection connection;
    private GetReviewById getReviewById = new GetReviewById();
    private Command command;
    private String[] input;
    private String expected;
    private String result;
    private DataCreationTests dataTests = new DataCreationTests();


    @After
    public void undoChangesAndCloseConnection() throws SQLException {
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
    public void getReviewFromValidMovieWithTwoReviews() throws SQLException {

        input = new String[]{"GET", "/movies/1/reviews/2"};
        command = new CommandGetter().getCommand(input);
        result = getReviewById.execute(connection, command.getPath(),command.getParams()).toStringResult();
        expected = "reviewid: 2\nmovie id: 1\nreviewer name: Bad taste Reviwer\nreview rating: 1\nreview summary: Horrible\ncomplete review: This is the worst movie i have seen in my life\n";
        assertEquals(expected, result);
    }


    @Test
    public void getReviewFromValidMovie() throws SQLException {

        input = new String[]{"GET", "/movies/2/reviews/4"};
        command = new CommandGetter().getCommand(input);
        result = getReviewById.execute(connection, command.getPath(),command.getParams()).toStringResult();
        expected = "reviewid: 4\nmovie id: 2\nreviewer name: Jack\nreview rating: 2\nreview summary: Morgan Freeman is the best\ncomplete review: Two detectives, a rookie and a veteran, hunt a serial killer who uses the seven deadly sins as his modus operandi\n";
        assertEquals(expected, result);
    }
}
