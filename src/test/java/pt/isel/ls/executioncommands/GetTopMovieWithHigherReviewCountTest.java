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
        connection = new ConnectionFactory().getNewConnection();
        dataTests.createTables();
        dataTests.insertMoviesToTest();
        dataTests.insertReviewsInMovies();
    }

    @Test
    public void checkResultsetGetTopNMoviesWithHigherReviewCountQuery() throws SQLException {
        connection = new ConnectionFactory().getNewConnection();
        input = new String[]{"GET","/tops/reviews/higher/count"};
        command = new CommandGetter().getCommand(input);
        result = getTopMovieWithHigherReviewCount.execute(connection, command.getPath(), command.getParams()).toStringResult();
        expected = getTopMovieWithHihgerReviewCountString();
        assertEquals(expected, result);
    }

    private String getTopMovieWithHihgerReviewCountString() {
        return
        "\tID: 1\n"+
        "\tName:Fight Club\n"+
        "\tYear: 1999\n"+
        "\t*20  **10  ***15  ****50  *****32\n";

    }
}
