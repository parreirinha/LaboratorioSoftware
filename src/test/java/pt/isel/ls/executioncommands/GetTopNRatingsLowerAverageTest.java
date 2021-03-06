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
 * Class used to test GetTopNRatingsLowerAverage.
 */
public class GetTopNRatingsLowerAverageTest {
    private Connection connection;
    private GetTopNRatingsLowerAverage getTopsNRatingsLowerAverage = new GetTopNRatingsLowerAverage();
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
    public void checkResultsetGetTopNMoviesWithLowerReviewCountQuery() throws SQLException {
        connection = new TestConnectionFactory().getNewConnection();
        input = new String[]{"GET", "/tops/3/ratings/lower/average"};
        command = new CommandGetter().getCommand(input);
        try {
            result = getTopsNRatingsLowerAverage.execute(connection, command).toStringText();
        } catch (pt.isel.ls.exceptions.ApplicationException e) {
            e.printStackTrace();
        }
        expected = getTopsNRatingsLowerAverageString();
        assertEquals(expected, result);
    }

    private String getTopsNRatingsLowerAverageString() {
        return "Movie ID = 3\n" +
                "\tName = The Matrix" +
                "\n\tRelease = 1999\n" +
                "\t* = 33   ** = 14   *** = 70   **** = 15   ***** = 1\n" +
                "\tAverage = 2.526\n" +
                "Movie ID = 5\n" +
                "\tName = Pulp Fiction" +
                "\n\tRelease = 1994\n" +
                "\t* = 30   ** = 8   *** = 34   **** = 13   ***** = 20\n" +
                "\tAverage = 2.857\n" +
                "Movie ID = 2\n" +
                "\tName = Seven" +
                "\n\tRelease = 1995\n" +
                "\t* = 5   ** = 20   *** = 40   **** = 35   ***** = 22\n" +
                "\tAverage = 3.402\n";

    }


    @Test
    public void pagingTest() throws SQLException, ApplicationException {
        connection = new TestConnectionFactory().getNewConnection();
        input = new String[]{"GET", "/tops/3/ratings/lower/average", "skip=1&top=1"};
        command = new CommandGetter().getCommand(input);
        result = getTopsNRatingsLowerAverage.execute(connection, command).toStringText();
        expected = "Movie ID = 5\n" +
                "\tName = Pulp Fiction" +
                "\n\tRelease = 1994\n" +
                "\t* = 30   ** = 8   *** = 34   **** = 13   ***** = 20\n" +
                "	Average = 2.857\n";
        ;
        assertEquals(expected, result);
    }
}
