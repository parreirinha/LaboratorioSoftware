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
 *  * POST /movies/{mid}/ratings
 * submits a new rating for the movie identified by mid, given the following parameters:
 *  rating - integer between 1 and 5.
 */
public class PostMovieRatingTest {

    private Connection connection;
    private PostMovieRating postMovieRating = new PostMovieRating();
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
    public void postRateMovieSevenWithFiveStars() throws SQLException {

        input = new String[]{"POST", "/movies/2/ratings","rating=5"};
        command = new CommandGetter().getCommand(input);
        result = postMovieRating.execute(connection, command.getPath(),command.getParams()).toStringResult();
        expected = "Rating posted with sucess";
        assertEquals(expected, result);

    }
}
