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
 * GET /movies/{mid}/ratings
 * "The average rating for the movie with the ID ## is ##.\n\t* = ##   ** = ##   *** = ##   **** = ##   ***** = ##\n"
 *
 */
public class GetMovieRatingTest {

    private Connection connection;
    private GetMovieRating getMovieRating = new GetMovieRating();
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
    public void initConnectionandDataBase() throws SQLException {
        connection = new ConnectionFactory().getNewConnection();
        dataTests.createTables();
        dataTests.insertMoviesToTest();
        dataTests.insertReviewsInMovies();
    }




    @Test
    public void verifyRatingForExistingMovie() throws SQLException {

        connection = new ConnectionFactory().getNewConnection();
        input = new String[]{"GET", "/movies/6/ratings"};
        command = new CommandGetter().getCommand(input);
        result = getMovieRating.execute(connection, command).toStringText();
        expected = "The average rating for the movie with the ID 6 is 4.097.\n\t* = 1   ** = 5   *** = 20   **** = 100   ***** = 50\n";
        assertEquals(expected, result);
    }

}
