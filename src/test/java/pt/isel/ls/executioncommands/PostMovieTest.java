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
 * POST /movies
 * "the id of the new movie is: ##"
 */
public class PostMovieTest {

    private Connection connection;
    private PostMovie postMovie = new PostMovie();
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
        connection = new TestConnectionFactory().getNewConnection();
        dataTests.createTables();
        dataTests.insertMoviesToTest();
    }

    @Test
    public void postNewMovie() throws SQLException {

        input = new String[]{"POST", "/movies", "title=Big Fish&releaseYear=2003"};
        command = new CommandGetter().getCommand(input);
        try {
            result = postMovie.execute(connection, command).toStringText();
        } catch (pt.isel.ls.exceptions.ApplicationException e) {
            e.printStackTrace();
        }
        expected = "The ID of the new movie is: 8";
        assertEquals(expected, result);
    }

/*
    @Test
    public void  postInvalidMovie() throws SQLException {
        input = new String[]{"POST", "/movies", "title=Fight Club&releaseYear=1999"};
        linecommand = new CommandGetter().getCommand(input);
        result = postMovie.execute(connection, linecommand.getPath(),linecommand.getHeaders()).toStringText();
        expected =  "something went wrong!!\n";
        assertEquals(expected, result);

    }
*/

}
