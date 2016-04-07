package pt.isel.ls.database.access;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pt.isel.ls.command.mapping.CommandMapper;
import pt.isel.ls.command.model.Command;
import pt.isel.ls.command.model.Parameters;
import pt.isel.ls.command.model.Path;
import pt.isel.ls.command.process.CommandGetter;
import pt.isel.ls.database.connection.ConnectionFactory;
import pt.isel.ls.model.Movie;

import java.sql.Connection;
import java.sql.SQLException;


import static org.junit.Assert.assertEquals;

/**
 * POST /movies
 * "the id of the new movie is: ##"
 */
public class PostMovieTest {

    private Connection connection;
    private PostMovie postMovie = new PostMovie();
    private Command command;
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
        connection = new ConnectionFactory().connectionFactory();
        dataTests.createTables();
        dataTests.insertMoviesToTest();
    }

    @Test
    public void  postNewMovie() throws SQLException {

        input = new String[]{"POST", "/movies", "title=Big Fish&releaseYear=2003"};
        command = new CommandGetter().getCommand(input);
        result = postMovie.execute(connection, command.getPath(),command.getParams()).toStringResult();
        expected = "the id of the new movie is: 9";
        assertEquals(expected, result);
    }


}
