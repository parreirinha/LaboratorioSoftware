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
 * GET /collections
 * returns the list of collections, using the insertion order.
 */
public class GetCollectionsTest  {

    private Connection connection;
    private Command command;
    private String[] input;
    private String expected;
    private String result;
    private DataCreationTests dataTests = new DataCreationTests();
    private GetCollections exe = new GetCollections();


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
    public void someTest() throws SQLException {
        input = new String[] {"POST", "/collections", "name=Rocky&description=movies about boxe"};
        command = new CommandGetter().getCommand(input);
        result = exe.execute(connection, command).toStringText();
        expected =
            "\nCollection id = 1\nName = STARWARS\nDescription = serie de filmes da saga starwars" +
            "\nCollection id = 2\nName = Before 2000\nDescription = movies before 2000" +
            "\nCollection id = 3\nName = movies after 2000\nDescription = movies from this century" +
            "\n";
        assertEquals(expected, result);
    }
}
