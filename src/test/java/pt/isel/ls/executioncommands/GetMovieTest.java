package pt.isel.ls.executioncommands;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pt.isel.ls.linecommand.model.Command;
import pt.isel.ls.linecommand.process.CommandGetter;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class GetMovieTest {

    private Connection connection;
    private GetMovie getMovie = new GetMovie();
    private Command command = null;
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
        connection = new TestConnectionFactory().getNewConnection();
        dataTests.createTables();
        dataTests.insertMoviesToTest();
    }


    @Test
    public void existingMoviesTest() throws SQLException {

        input = new String[]{"GET", "/movies/1"};
        command = new CommandGetter().getCommand(input);
        try {
            result = getMovie.execute(connection, command).toStringText();
        } catch (pt.isel.ls.exceptions.ApplicationException e) {
            e.printStackTrace();
        }
        expected = "Movie ID = 1\n\tName = Fight Club\tRelease = 1999\n\t* = 20   ** = 10   *** = 15" +
                "   **** = 50   ***** = 32\n\tAverage = 3.504\n";
        assertEquals(expected, result);
    }


    @Test
    public void nonExistingID() throws SQLException {

        input = new String[]{"GET", "/movies/100"};
        command = new CommandGetter().getCommand(input);
        try {
            result = getMovie.execute(connection, command).toStringText();
        } catch (pt.isel.ls.exceptions.ApplicationException e) {
            e.printStackTrace();
        }
        expected = "There is no movie with id = 100.";
        assertEquals(expected, result);


    }
}
