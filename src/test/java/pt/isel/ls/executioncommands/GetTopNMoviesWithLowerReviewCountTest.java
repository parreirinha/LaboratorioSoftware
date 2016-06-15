package pt.isel.ls.executioncommands;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pt.isel.ls.exceptions.ApplicationException;
import pt.isel.ls.executioncommands.DataCreationTests;
import pt.isel.ls.executioncommands.GetTopNMoviesWithLowerReviewCount;
import pt.isel.ls.executioncommands.TestConnectionFactory;
import pt.isel.ls.linecommand.model.Command;
import pt.isel.ls.linecommand.process.CommandGetter;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class GetTopNMoviesWithLowerReviewCountTest {


    private Connection connection;
    private GetTopNMoviesWithLowerReviewCount getTopNMoviesWithLowerReviewCount = new GetTopNMoviesWithLowerReviewCount();
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
    public void top5ReviewsLowerCountTest() throws SQLException {
        connection = new TestConnectionFactory().getNewConnection();
        input = new String[]{"GET", "/tops/5/reviews/lower/count", "skip=0&top=5"};
        command = new CommandGetter().getCommand(input);
        try {
            result = getTopNMoviesWithLowerReviewCount.execute(connection, command).toStringText();
        } catch (pt.isel.ls.exceptions.ApplicationException e) {
            e.printStackTrace();
        }
        expected = getTop5MoviesWithlowerReviewCountString();
        assertEquals(expected, result);
    }

    private String getTop5MoviesWithlowerReviewCountString() {
        return "Movie ID = 3\n" +
                "\tName = The Matrix\tRelease = 1999\n" +
                "Movie ID = 4\n" +
                "\tName = Inception\tRelease = 2010\n" +
                "Movie ID = 5\n" +
                "\tName = Pulp Fiction\tRelease = 1994\n" +
                "Movie ID = 2\n" +
                "\tName = Seven\tRelease = 1995\n" +
                "Movie ID = 1\n" +
                "\tName = Fight Club\tRelease = 1999\n";
    }


    @Test
    public void pagingTest() throws SQLException, ApplicationException {
        connection = new TestConnectionFactory().getNewConnection();
        input = new String[]{"GET", "/tops/5/reviews/lower/count", "skip=2&top=2"};
        command = new CommandGetter().getCommand(input);
        result = getTopNMoviesWithLowerReviewCount.execute(connection, command).toStringText();
        expected = "Movie ID = 5\n" +
                "\tName = Pulp Fiction\tRelease = 1994\n" +
                "Movie ID = 2\n" +
                "\tName = Seven\tRelease = 1995\n";
        assertEquals(expected, result);
    }
}
