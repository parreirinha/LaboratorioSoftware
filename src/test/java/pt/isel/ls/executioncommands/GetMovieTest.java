package pt.isel.ls.executioncommands;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pt.isel.ls.linecommand.model.Command;
import pt.isel.ls.linecommand.process.CommandGetter;
import pt.isel.ls.database.connection.ConnectionFactory;
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
        connection = new ConnectionFactory().getNewConnection();
        dataTests.createTables();
        dataTests.insertMoviesToTest();
    }


    @Test
    public void  existingMoviesTest() throws SQLException {

        input = new String[]{"GET", "/movies/1"};
        command = new CommandGetter().getCommand(input);
        result = getMovie.execute(connection, command.getPath(),command.getParams()).toStringResult();
        expected = "movieid = 1; movie name = Fight Club; releaseyear = 1999;\n*20;**10;***15;****50;*****32.\n";
        assertEquals(expected, result);
    }


    @Test
    public void nonExistingID() throws SQLException {

        input = new String[]{"GET", "/movies/100"};
        command = new CommandGetter().getCommand(input);
        result = getMovie.execute(connection, command.getPath(),command.getParams()).toStringResult();
        expected = "something went wrong!!\n";
        assertEquals(expected, result);


    }
}
