package pt.isel.ls.database.access;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pt.isel.ls.command.model.Command;
import pt.isel.ls.command.model.Path;
import pt.isel.ls.database.connection.ConnectionFactory;
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
    private Movie movie = null;
    private Path path = null;
    private Command command = null;


    @After
    private void closeConnection() throws SQLException {
        connection.close();
    }
    @Before
    private void doBefore() throws SQLServerException {
        connection = new ConnectionFactory().getNewConnection();

    }


    @Test
    public void  existingMoviesTest() {



    }

    @Test
    public void nonExistingID() throws SQLException {



    }
}
