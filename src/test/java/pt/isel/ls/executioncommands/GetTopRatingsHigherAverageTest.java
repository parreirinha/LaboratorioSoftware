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
public class GetTopRatingsHigherAverageTest {
    private Connection connection;
    private GetTopRatingsHigherAverage getTopRatingsHigherAverage = new GetTopRatingsHigherAverage();
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
    public void checkResultsetGetTopRatingsHigherAverageTestQuery() throws SQLException {
        connection = new ConnectionFactory().getNewConnection();
        input = new String[]{"GET", "/tops/ratings/higher/average"};
        command = new CommandGetter().getCommand(input);
        result = getTopRatingsHigherAverage.execute(connection, command).toStringResult();
        expected = getTopRatingsHigherAverageString();
        assertEquals(expected, result);
    }

    private String getTopRatingsHigherAverageString() {
        return "Movie ID = 4\n" +
                "\tName = Inception" +
                "\tRelease = 2010\n" +
                "\t* = 0   ** = 5   *** = 30   **** = 44   ***** = 60\n" +
                "\tAverage = 4.144\n";

    }
}
