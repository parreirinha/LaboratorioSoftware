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
        connection = new ConnectionFactory().getNewConnection();
        dataTests.createTables();
        dataTests.insertMoviesToTest();
        dataTests.insertReviewsInMovies();
    }

    @Test
    public void checkResultsetGetTopNMoviesWithHigherReviewCountQuery() throws SQLException {
        connection = new ConnectionFactory().getNewConnection();
        input = new String[]{"GET","/tops/2/reviews/higher/count"};
        command = new CommandGetter().getCommand(input);
        result = getTopNMoviesWithHigherReviewCount.execute(connection, command.getPath(), command.getParams()).toStringResult();
        expected = getTop2MoviesWithHihgerReviewCountString();
        assertEquals(expected, result);
    }

    private String getTop2MoviesWithHihgerReviewCountString() {
        return "Name: Fight Club\tYear: 1999\n" + "Name: Seven\tYear: 1995\n";
    }
}
