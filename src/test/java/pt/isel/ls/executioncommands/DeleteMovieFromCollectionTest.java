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
 * DELETE /collections/{cid}/movies/{mid}
 * removes the movie mid from the collections cid.
 */
public class DeleteMovieFromCollectionTest {

    private Connection connection;
    private Command command;
    private String[] input;
    private String expected;
    private String result;
    private DataCreationTests dataTests = new DataCreationTests();
    private DeleteMovieFromCollection exe = new DeleteMovieFromCollection();


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
    public void deleteTest() throws SQLException {
        input = new String[] {"DELETE", "/collections/1/movies/4"};
        command = new CommandGetter().getCommand(input);
        result = exe.execute(connection, command).toStringText();
        expected = "Movie with id = " + 4 + " was deleted with sucess from collection";
        assertEquals(expected, result);
    }
}
