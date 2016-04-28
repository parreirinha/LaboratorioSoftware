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
    public void collectionNumberTwoTest() throws SQLException {
        input = new String[] {"GET", "/collections/2"};
        command = new CommandGetter().getCommand(input);
        try {
            result = exe.execute(connection, command).toStringText();
        } catch (pt.isel.ls.exceptions.ApplicationException e) {
            e.printStackTrace();
        }
        expected =
                "Collection id: 2\n" +
                "Collection name: Before 2000\n" +
                "Collection description: movies before 2000\n" +
                "\n" +
                "Movies in the collection:\n" +
                "Movie id: 1\tMovie name: Fight Club\n" +
                "Movie id: 2\tMovie name: Seven\n" +
                "Movie id: 3\tMovie name: The Matrix\n" +
                "Movie id: 5\tMovie name: Pulp Fiction\n" +
                "Movie id: 6\tMovie name: American History X\n" +
                "Movie id: 7\tMovie name: The Silence of the Lambs" +
                "\n";
        assertEquals(expected, result);
    }

    @Test
    public void collectionOneTwoTest() throws SQLException {
        input = new String[] {"GET", "/collections/1"};
        command = new CommandGetter().getCommand(input);
        try {
            result = exe.execute(connection, command).toStringText();
        } catch (pt.isel.ls.exceptions.ApplicationException e) {
            e.printStackTrace();
        }
        expected =
                "Collection id: 1\n" +
                "Collection name: STARWARS\n" +
                "Collection description: serie de filmes da saga starwars\n" +
                "\n" +
                "Movies in the collection:\n" +
                "Movie id: 4\tMovie name: Inception" +
                "\n";

        assertEquals(expected, result);
    }

}
