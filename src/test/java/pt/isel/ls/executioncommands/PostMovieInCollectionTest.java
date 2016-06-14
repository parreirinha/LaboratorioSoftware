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
 * POST /collections/{cid}/movies/
 * adds a movie to the cid collection, given
 * mid - the movie unique identifier.
 */
public class PostMovieInCollectionTest {

/*
    private Connection connection;
    private Command command;
    private String[] input;
    private String expected;
    private String result;
    private DataCreationTests dataTests = new DataCreationTests();
    private PostMovieInCollection exe = new PostMovieInCollection();

    @Before
    public void beforeTest() throws SQLException {
        connection = new TestConnectionFactory().getNewConnection();
        dataTests.initValuesInDBToTests();
    }

    @After
    public void afterTes() throws SQLException {
        dataTests.dropTables();
        connection.close();
    }

    @Test
    public void insertMovieInCollection() throws SQLException {
        input = new String[]{"POST", "/collections/1/movies/", "mid=1"};
        command = new CommandGetter().getCommand(input);
        try {
            result = exe.execute(connection, command).toStringText();
        } catch (pt.isel.ls.exceptions.ApplicationException e) {
            e.printStackTrace();
        }
        expected = "The movie with id = 1 was added with success to the collection";
        assertEquals(expected, result);
    }*/
}
