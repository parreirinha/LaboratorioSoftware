package pt.isel.ls.executioncommands;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pt.isel.ls.exceptions.ApplicationException;
import pt.isel.ls.linecommand.model.Command;
import pt.isel.ls.linecommand.process.CommandGetter;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

/**
 * @author Tede Morgado
 *         Created at 04/04/2016
 */
public class GetTopNMoviesWithHigherReviewCountTest {
    private Connection connection;
    private GetTopNMoviesWithHigherReviewCount getTopNMoviesWithHigherReviewCount = new GetTopNMoviesWithHigherReviewCount();
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
        input = new String[]{"GET","/tops/2/reviews/higher/count"};
        command = new CommandGetter().getCommand(input);
        try {
            result = getTopNMoviesWithHigherReviewCount.execute(connection, command).toStringText();
        } catch (pt.isel.ls.exceptions.ApplicationException e) {
            e.printStackTrace();
        }
        expected = getTop2MoviesWithHihgerReviewCountString();
        assertEquals(expected, result);
    }

    private String getTop2MoviesWithHihgerReviewCountString() {
        return "Movie ID = 1\n\tName = Fight Club\tRelease = 1999\n" +
                "Movie ID = 2\n\tName = Seven\tRelease = 1995\n";
    }



    @Test
    public void pagingTest() throws SQLException, ApplicationException {
        connection = new TestConnectionFactory().getNewConnection();
        input = new String[]{"GET","/tops/5/reviews/higher/count", "skip=2&top=2"};
        command = new CommandGetter().getCommand(input);
        result = getTopNMoviesWithHigherReviewCount.execute(connection, command).toStringText();
        expected = "Movie ID = 3\n" +
                "\tName = The Matrix\tRelease = 1999\n" +
                "Movie ID = 4\n" +
                "\tName = Inception\tRelease = 2010\n";
        assertEquals(expected, result);
    }
}
