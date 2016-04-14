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
     * "movie id: ##\nmovie name: ##\nrelease year: ##\n"
     * @throws SQLException
     */
    @Test
    public void checkResultsetFromMoviesQueary() throws SQLException {

        connection = new ConnectionFactory().getNewConnection();
        input = new String[]{"GET", "/movies"};
        command = new CommandGetter().getCommand(input);
        result = getAllMovies.execute(connection, command.getPath(), command.getParams()).toStringResult();
        expected = getAllMoviesString();
        assertEquals(expected, result);
    }

    private String getAllMoviesString(){
        return
            "movie id:1\nmovie name: Fight Club\nrelease year: 1999\n" +
            "movie id:2\nmovie name: Seven\nrelease year: 1995\n" +
            "movie id:3\nmovie name: The Matrix\nrelease year: 1999\n" +
            "movie id:4\nmovie name: Inception\nrelease year: 2010\n" +
            "movie id:5\nmovie name: Pulp Fiction\nrelease year: 1994\n" +
            "movie id:6\nmovie name: American History X\nrelease year: 1998\n" +
            "movie id:7\nmovie name: The Silence of the Lambs\nrelease year: 1991\n";            //"movie id: 8\nmovie name: PostMovieTest\nrelease year: 2016\n" ;
    }
}
