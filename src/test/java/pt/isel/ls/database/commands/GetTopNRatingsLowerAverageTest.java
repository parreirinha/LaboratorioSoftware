package pt.isel.ls.database.access;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pt.isel.ls.command.model.Command;
import pt.isel.ls.command.process.CommandGetter;
import pt.isel.ls.database.connection.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

/**
 * Created by Dani on 08-04-2016.
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
        connection = new ConnectionFactory().getNewConnection();
        dataTests.createTables();
        dataTests.insertMoviesToTest();
        dataTests.insertReviewsInMovies();
    }

    @Test
    public void checkResultsetGetTopNMoviesWithLowerReviewCountQuery() throws SQLException {
        connection = new ConnectionFactory().getNewConnection();
        input = new String[]{"GET", "/tops/3/ratings/lower/average"};
        command = new CommandGetter().getCommand(input);
        result = getTopsNRatingsLowerAverage.execute(connection, command.getPath(), command.getParams()).toStringResult();
        expected = getTopsNRatingsLowerAverageString();
        assertEquals(expected, result);
    }

    private String getTopsNRatingsLowerAverageString() {
        return "\tID: 3\n" +
                "\tName:The Matrix\n" +
                "\tYear: 1999\n" +
                "\t*33  **14  ***70  ****15  *****1\n" +
                "\tAverage: 2.526\n" +
                "\n" +
                "\tID: 5\n" +
                "\tName:Pulp Fiction\n" +
                "\tYear: 1994\n" +
                "\t*30  **8  ***34  ****13  *****20\n" +
                "\tAverage: 2.857\n" +
                "\n" +
                "\tID: 2\n" +
                "\tName:Seven\n" +
                "\tYear: 1995\n" +
                "\t*5  **20  ***40  ****35  *****22\n" +
                "\tAverage: 3.402\n\n";

    }
}
