package pt.isel.ls.executioncommands;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pt.isel.ls.linecommand.model.Command;
import pt.isel.ls.linecommand.process.CommandGetter;
import pt.isel.ls.database.connection.ConnectionFactory;
import pt.isel.ls.printers.HtmlGenerator;

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
        connection = new TestConnectionFactory().getNewConnection();
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
        input = new String[]{"GET", "/movies"};
        command = new CommandGetter().getCommand(input);
        result = getAllMovies.execute(connection, command).toStringText();
        expected = getAllMoviesString();
        assertEquals(expected, result);
    }
    @Test
    public void checkResultsetFromMoviesHtml() throws SQLException {
        input = new String[]{"GET", "/movies"};
        command = new CommandGetter().getCommand(input);
        result = getAllMovies.execute(connection, command).toStringHtml();
        expected = getAllMoviesHtml();
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
    private String getAllMoviesHtml(){
        String str = "\t\t\t<table border=\"1\" style=\"width:100%\">\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td>Movie ID</td>\n\t\t\t\t\t<td>Name</td>\n\t\t\t\t\t<td>Release</td>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td>1</td>\n\t\t\t\t\t<td>Fight Club</td>\n\t\t\t\t\t<td>1999</td>\n" +
                "\t\t\t\t</tr>\n"+
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td>2</td>\n\t\t\t\t\t<td>Seven</td>\n\t\t\t\t\t<td>1995</td>\n" +
                "\t\t\t\t</tr>\n"+
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td>3</td>\n\t\t\t\t\t<td>The Matrix</td>\n\t\t\t\t\t<td>1999</td>\n" +
                "\t\t\t\t</tr>\n"+
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td>4</td>\n\t\t\t\t\t<td>Inception</td>\n\t\t\t\t\t<td>2010</td>\n" +
                "\t\t\t\t</tr>\n"+
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td>5</td>\n\t\t\t\t\t<td>Pulp Fiction</td>\n\t\t\t\t\t<td>1994</td>\n" +
                "\t\t\t\t</tr>\n"+
                "\t\t\t\t<tr>\n" +
                 "\t\t\t\t\t<td>6</td>\n\t\t\t\t\t<td>American History X</td>\n\t\t\t\t\t<td>1998</td>\n" +
                "\t\t\t\t</tr>\n"+
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td>7</td>\n\t\t\t\t\t<td>The Silence of the Lambs</td>\n\t\t\t\t\t<td>1991</td>\n" +
                "\t\t\t\t</tr>\n"+
                "\t\t\t</table>";
        return String.format(HtmlGenerator.template, str);
    }
}
