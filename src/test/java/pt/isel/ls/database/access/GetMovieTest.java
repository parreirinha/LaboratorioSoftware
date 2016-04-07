package pt.isel.ls.database.access;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pt.isel.ls.command.model.Command;
import pt.isel.ls.command.model.Path;
import pt.isel.ls.command.process.CommandGetter;
import pt.isel.ls.database.connection.ConnectionFactory;
import pt.isel.ls.database.printers.Printable;
import pt.isel.ls.model.Movie;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by fabio on 21-Mar-16.
 */
public class GetMovieTest {

    private Connection connection;
    private GetMovie getMovie = new GetMovie();
    private Command command = null;
    private String[] input;
    private String expected;
    private String result;
    private DataCreationTests dataTests = new DataCreationTests();


    @After
    private void undoChangesAndCloseConnection() throws SQLException {
        dataTests.deleteAllMovies();
        dataTests.dropTables();
        connection.close();
    }
    @Before
    private void initConnectionandDataBase() throws SQLException {
        connection = new ConnectionFactory().getNewConnection();
        dataTests.createTables();
        dataTests.insertMoviesToTest();
    }


    @Test
    public void  existingMoviesTest() throws SQLException {

        input = new String[]{"GET", "/movies/{1}"};
        command = new CommandGetter().getCommand(input);
        result = getMovie.execute(connection, command.getPath(),command.getParams()).toStringResult();
        expected = "movieid = 1; movie name = Fight Club; releaseyear = 199;\\n*20;**10;***15;****50;*****32.\\n";
        assertEquals(expected, result);
    }


    @Test
    public void nonExistingID() throws SQLException {

        input = new String[]{"GET", "/movies/{100}"};
        command = new CommandGetter().getCommand(input);
        result = getMovie.execute(connection, command.getPath(),command.getParams()).toStringResult();
        expected = "something went wrong!!\n";
        assertEquals(expected, result);


    }
}
