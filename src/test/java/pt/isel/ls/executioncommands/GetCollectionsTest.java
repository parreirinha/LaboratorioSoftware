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
        connection = new ConnectionFactory().getNewConnection();
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
        result = exe.execute(connection, command.getPath(),command.getParams()).toStringResult();
        expected =
            "\nCollection id = 1\nName = STARWARS\nDescription = serie de filmes da saga starwars" +
                    "\nDate of creation = 2016-04-18" +
            "\nCollection id = 2\nName = Before 2000\nDescription = movies before 2000" +
                    "\nDate of creation = 2016-04-18" +
            "\nCollection id = 3\nName = movies after 2000\nDescription = movies from this century" +
                    "\nDate of creation = 2016-04-18" +
            "\n";
        assertEquals(expected, result);
    }
}
