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
 * Created by Dani on 08-04-2016.
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
        connection = new ConnectionFactory().getNewConnection();
        dataTests.createTables();
        dataTests.insertMoviesToTest();
        dataTests.insertReviewsInMovies();
    }

    @Test
    public void checkResultsetGetTopRatingsLowerAverageTestQuery() throws SQLException {
        connection = new ConnectionFactory().getNewConnection();
        input = new String[]{"GET", "/tops/ratings/lower/average"};
        command = new CommandGetter().getCommand(input);
        result = getTopRatingsLowerAverage.execute(connection, command.getPath(), command.getParams()).toStringResult();
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
