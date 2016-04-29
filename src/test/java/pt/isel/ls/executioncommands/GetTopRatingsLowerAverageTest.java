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
 * Class used to test GetTopRatingsLowerAverage.
 */
public class GetTopRatingsLowerAverageTest {
    private Connection connection;
    private GetTopRatingsLowerAverage getTopRatingsLowerAverage = new GetTopRatingsLowerAverage();
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
    public void checkResultsetGetTopRatingsLowerAverageTestQuery() throws SQLException {
        connection = new TestConnectionFactory().getNewConnection();
        input = new String[]{"GET", "/tops/ratings/lower/average"};
        command = new CommandGetter().getCommand(input);
        try {
            result = getTopRatingsLowerAverage.execute(connection, command).toStringText();
        } catch (pt.isel.ls.exceptions.ApplicationException e) {
            e.printStackTrace();
        }
        expected = getTopRatingsLowerAverageString();
        assertEquals(expected, result);
    }

    private String getTopRatingsLowerAverageString() {
        return "Movie ID = 3\n" +
                "\tName = The Matrix" +
                "\tRelease = 1999\n" +
                "\t* = 33   ** = 14   *** = 70   **** = 15   ***** = 1\n" +
                "\tAverage = 2.526\n";
    }
}
