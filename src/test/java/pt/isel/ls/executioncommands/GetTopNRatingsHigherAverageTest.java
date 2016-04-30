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
 * Class used to test GetTopNRatingsHigherAverage.
 */
public class GetTopNRatingsHigherAverageTest {
    private Connection connection;
    private GetTopsNRatingsHigherAverage getTopsNRatingsHigherAverage = new GetTopsNRatingsHigherAverage();
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
        input = new String[]{"GET", "/tops/2/ratings/higher/average"};
        command = new CommandGetter().getCommand(input);
        try {
            result = getTopsNRatingsHigherAverage.execute(connection, command).toStringText();
        } catch (pt.isel.ls.exceptions.ApplicationException e) {
            e.printStackTrace();
        }
        expected = getTopsNRatingsHigherAverageString();
        assertEquals(expected, result);
    }

    private String getTopsNRatingsHigherAverageString() {
        return "Movie ID = 4\n" +
                "\tName = Inception" +
                "\tRelease = 2010\n" +
                "\t* = 0   ** = 5   *** = 30   **** = 44   ***** = 60\n" +
                "\tAverage = 4.144\n" +
                "Movie ID = 6\n" +
                "\tName = American History X" +
                "\tRelease = 1998\n" +
                "\t* = 1   ** = 5   *** = 20   **** = 100   ***** = 50\n" +
                "\tAverage = 4.097\n";

    }


    @Test
    public void pagingTest() throws SQLException, ApplicationException {
        connection = new TestConnectionFactory().getNewConnection();
        input = new String[]{"GET", "/tops/5/ratings/higher/average", "skip=2&top=3"};
        command = new CommandGetter().getCommand(input);
        result = getTopsNRatingsHigherAverage.execute(connection, command).toStringText();
        expected = setExpectedValueToSimplePagingTest();
        assertEquals(expected, result);
    }

    private String setExpectedValueToSimplePagingTest() {
        return "Movie ID = 1\n" +
                "\tName = Fight Club\tRelease = 1999\n" +
                "\t* = 20   ** = 10   *** = 15   **** = 50   ***** = 32\n" +
                "\tAverage = 3.504\n" +
                "Movie ID = 7\n" +
                "\tName = The Silence of the Lambs\tRelease = 1991\n" +
                "\t* = 2   ** = 30   *** = 11   **** = 40   ***** = 22\n" +
                "\tAverage = 3.476\n" +
                "Movie ID = 2\n" +
                "\tName = Seven\tRelease = 1995\n" +
                "\t* = 5   ** = 20   *** = 40   **** = 35   ***** = 22\n" +
                "\tAverage = 3.402\n";
    }
}
