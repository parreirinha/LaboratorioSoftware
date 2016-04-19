package pt.isel.ls.executioncommands;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pt.isel.ls.database.connection.ConnectionFactory;
import pt.isel.ls.linecommand.model.Command;
import pt.isel.ls.linecommand.process.CommandGetter;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

/**
 * POST /collections/{cid}/movies/
 * adds a movie to the cid collection, given
 *  mid - the movie unique identifier.
 */
public class PostMovieInCollectionTest {


    private Connection connection;
    private Command command;
    private String[] input;
    private String expected;
    private String result;
    private DataCreationTests dataTests = new DataCreationTests();
    private PostMovieInCollection exe = new PostMovieInCollection();

    @Before
    public void beforeTest() throws SQLException {
        connection = new ConnectionFactory().getNewConnection();
        dataTests.initValuesInDBToTests();
    }

    @After
    public void afterTes() throws SQLException {
        dataTests.dropTables();
        connection.close();
    }

    //TODO erro - falar com o daniel
    @Test
    public void insertMovieInCollection() throws SQLException {
        input = new String[] {"POST", "/collections/1/movies/", "1"};
        command = new CommandGetter().getCommand(input);
        result = exe.execute(connection, command).toStringText();
        expected = "The movie with id = 1 was added with sucess to the collection";
        assertEquals(expected, result);
    }
}
