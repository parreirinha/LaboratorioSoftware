package pt.isel.ls.database.access;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pt.isel.ls.command.model.Command;
import pt.isel.ls.command.process.CommandGetter;
import pt.isel.ls.database.connection.ConnectionFactory;
import pt.isel.ls.model.Movie;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;


/**
 * GET /movies/{mid}/ratings
 * "the average rating for the movie with the id ## is ##.\n*##;**##;***##;****##;*****##\n"
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
    private void undoChangesAndCloseConnection() throws SQLException {
        dataTests.deleteAllMovies();
        dataTests.deleteAllReviews();
        dataTests.dropTables();
        connection.close();
    }
    @Before
    private void initConnectionandDataBase() throws SQLException {
        connection = new ConnectionFactory().connectionFactory();
        dataTests.createTables();
        dataTests.insertMoviesToTest();
        dataTests.insertReviewsInMovies();
    }

    @Test
    public void verifyRatingForExistingMovie() throws SQLException {

        connection = new ConnectionFactory().connectionFactory();
        input = new String[]{"GET", "/movies/{6}/ratings"};
        command = new CommandGetter().getCommand(input);
        result = getMovieRating.execute(connection, command.getPath(), command.getParams()).toStringResult();
        expected = "the average rating for the movie with the id 6 is ##.\\n*1;**5;***20;****100;*****50n";
        assertEquals(expected, result);
        //TODO calcular média para a string expected. até dá false
    }

}
