package pt.isel.ls.executioncommands;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pt.isel.ls.linecommand.model.Command;
import pt.isel.ls.linecommand.process.CommandGetter;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

/**
 * Class used to test GetTopMovieWithHigherReview.
 */
public class GetTopMovieWithHigherReviewCountTest {
    private Connection connection;
    private GetTopMovieWithHigherReviewCount getTopMovieWithHigherReviewCount = new GetTopMovieWithHigherReviewCount();
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
    public void initConnectionAndDataBase() throws SQLException {
        connection = new TestConnectionFactory().getNewConnection();
        dataTests.createTables();
        dataTests.insertMoviesToTest();
        dataTests.insertReviewsInMovies();
    }

    @Test
    public void checkResultsetGetTopNMoviesWithHigherReviewCountQuery() throws SQLException {
        connection = new TestConnectionFactory().getNewConnection();
        input = new String[]{"GET", "/tops/reviews/higher/count"};
        command = new CommandGetter().getCommand(input);
        try {
            result = getTopMovieWithHigherReviewCount.execute(connection, command).toStringText();
        } catch (pt.isel.ls.exceptions.ApplicationException e) {
            e.printStackTrace();
        }
        expected = getTopMovieWithHihgerReviewCountString();
        assertEquals(expected, result);
    }

    private String getTopMovieWithHihgerReviewCountString() {
        return
                "Movie ID = 1\n\tName = Fight Club\tRelease = 1999\n";

    }
}
