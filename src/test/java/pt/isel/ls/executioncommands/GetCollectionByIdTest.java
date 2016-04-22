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
 * GET /collections/{cid}
 * returns the details for the cid collection, namely all the movies in that collection.
 */
public class GetCollectionByIdTest {


    private Connection connection;
    private Command command;
    private String[] input;
    private String expected;
    private String result;
    private DataCreationTests dataTests = new DataCreationTests();
    private GetCollectionById exe = new GetCollectionById();


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
        input = new String[] {"GET", "/collections/1"};
        command = new CommandGetter().getCommand(input);
        result = exe.execute(connection, command).toStringText();
        expected = "";
        assertEquals(expected, result);
    }

}
