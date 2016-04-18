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


/**
 * à segunda vez que se corre o teste dá erro porque o MovieID está definido como identity(1, 1), logo quando
 * corresmos a segunda vez os testes os valores nao batem certo para o conjunto da FK com PK
 */
public class GetAllMoviesTest {

    private Connection connection;
    private GetAllMovies getAllMovies = new GetAllMovies();
    private Command command;
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


    /**
     * GET /movies
     * "Movie ID = ##\n\tName = ##\tRelease = ##\n"
     * @throws SQLException
     */
    @Test
    public void checkResultsetFromMoviesQueary() throws SQLException {

        connection = new ConnectionFactory().getNewConnection();
        input = new String[]{"GET", "/movies"};
        command = new CommandGetter().getCommand(input);
        result = getAllMovies.execute(connection, command).toStringResult();
        expected = getAllMoviesString();
        assertEquals(expected, result);
    }

    private String getAllMoviesString(){
        return "Movie ID = 1\n\tName = Fight Club\tRelease = 1999\n"+
            "Movie ID = 2\n\tName = Seven\tRelease = 1995\n" +
            "Movie ID = 3\n\tName = The Matrix\tRelease = 1999\n" +
            "Movie ID = 4\n\tName = Inception\tRelease = 2010\n" +
            "Movie ID = 5\n\tName = Pulp Fiction\tRelease = 1994\n" +
            "Movie ID = 6\n\tName = American History X\tRelease = 1998\n" +
            "Movie ID = 7\n\tName = The Silence of the Lambs\tRelease = 1991\n";           //"movie id: 8\nmovie name: PostMovieTest\nrelease year: 2016\n" ;
    }
}
