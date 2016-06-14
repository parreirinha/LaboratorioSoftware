package pt.isel.ls.executioncommands;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pt.isel.ls.linecommand.model.Command;
import pt.isel.ls.linecommand.process.CommandGetter;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * * GET /movies/{mid}/reviews/{rid}
 * returns the full information for the review rid of the movie identified by mid.
 * <p>
 * "reviewid: ##\nmovie id: ##\nreviewer name: ##\nreview summary: ##\ncomplete review: ##\n"
 */
public class GetReviewByIdTest {
/*

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
        connection = new TestConnectionFactory().getNewConnection();
        dataTests.createTables();
        dataTests.insertMoviesToTest();
        dataTests.insertReviewsInMovies();
    }


    @Test
    public void getReviewFromValidMovieWithTwoReviews() throws SQLException {
        //"Review ID = ##\n\tMovie ID = ##\n\tReviewer Name = ##\tReview Rating = ##\n\tSummary Review = ##\n\tComplete Review = ##\n"
        input = new String[]{"GET", "/movies/1/reviews/2"};
        command = new CommandGetter().getCommand(input);
        try {
            result = getReviewById.execute(connection, command).toStringText();
        } catch (pt.isel.ls.exceptions.ApplicationException e) {
            e.printStackTrace();
        }
        expected = "Review ID = 2\n\tMovie ID = 1\n\tReviewer Name = Bad taste Reviwer\tReview Rating = 1" +
                "\n\tSummary Review = Horrible\n\tComplete Review = This is the worst movie i have seen in my life\n";
        assertEquals(expected, result);
    }


    @Test
    public void getReviewFromValidMovie() throws SQLException {

        input = new String[]{"GET", "/movies/2/reviews/4"};
        command = new CommandGetter().getCommand(input);
        try {
            result = getReviewById.execute(connection, command).toStringText();
        } catch (pt.isel.ls.exceptions.ApplicationException e) {
            e.printStackTrace();
        }
        expected = "Review ID = 4\n\tMovie ID = 2\n\tReviewer Name = Jack\tReview Rating = 2" +
                "\n\tSummary Review = Morgan Freeman is the best\n" +
                "\tComplete Review = Two detectives, a rookie and a veteran, hunt a serial killer who uses the seven deadly sins as his modus operandi\n";
        assertEquals(expected, result);
    }*/
}
