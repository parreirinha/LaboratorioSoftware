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
 * POST /collections
 * creates a new collection and returns its identifier, given the following parameters:
 * name - the tag unique name;
 * description - the tag description
 */
public class PostCollectionTest {

    private Connection connection;
    private Command command;
    private String[] input;
    private String expected;
    private String result;
    private DataCreationTests dataTests = new DataCreationTests();
    private PostCollection exe = new PostCollection();


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
    public void insertAValidCollection() throws SQLException {
        input = new String[]{"POST", "/collections", "name=Rocky&description=movies about boxe"};
        command = new CommandGetter().getCommand(input);
        try {
            result = exe.execute(connection, command).toStringText();
        } catch (pt.isel.ls.exceptions.ApplicationException e) {
            e.printStackTrace();
        }
        expected = "Collection posted with success, the id of the new collection is 4";
        assertEquals(expected, result);
    }

}
