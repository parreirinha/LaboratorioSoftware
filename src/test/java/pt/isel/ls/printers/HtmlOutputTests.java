package pt.isel.ls.printers;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import pt.isel.ls.exceptions.ApplicationException;
import pt.isel.ls.executioncommands.DataCreationTests;
import pt.isel.ls.executioncommands.Listen;
import pt.isel.ls.executioncommands.TestConnectionFactory;
import pt.isel.ls.linecommand.mapping.CommandMapper;
import pt.isel.ls.linecommand.model.Command;
import pt.isel.ls.linecommand.process.CommandGetter;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

/**
 * Class used to test Printer implementing classes method toStringHtml.
 */
public class HtmlOutputTests {
    private Connection connection;
    private DataCreationTests dataTests = new DataCreationTests();



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
    public void printDetailedMovieTest() throws SQLException, ApplicationException {
        String[] lineCommand = {"GET", "/movies/1"};
        Command c = new CommandGetter().getCommand(lineCommand);
        String Result = new CommandMapper().getExecutionCommandInstance(c).execute(connection, c).toStringHtml();
        final String Expected = "<!DOCTYPE>\n" +
                "\t<html>\n" +
                "\t\t<head>\n" +
                "\t\t\t<meta\n" +
                "\t\t\t\thttp-equiv=\"Content-Type\"\n" +
                "\t\t\t\tcontent=\"text/html; charset=utf-8\"\n" +
                "\t\t\t/>\n" +
                "\t\t</head>\n" +
                "\n" +
                "\t\t<body>\n" +
                "\t\t\t<ul>\n" +
                "\t\t\t\t<li>Movie ID: 1</li>\n" +
                "\t\t\t\t<ul>\n" +
                "\t\t\t\t\t<li>Name: Fight Club</li>\n" +
                "\t\t\t\t\t<li>Release: 1999</li>\n" +
                "\t\t\t\t\t<li>*: 20</li>\n" +
                "\t\t\t\t\t<li>**: 10</li>\n" +
                "\t\t\t\t\t<li>***: 15</li>\n" +
                "\t\t\t\t\t<li>****: 50</li>\n" +
                "\t\t\t\t\t<li>*****: 32</li>\n" +
                "\t\t\t\t\t<li>Average: 3.504</li>\n" +
                "\t\t\t\t</ul>\n" +
                "\t\t\t</ul>\n" +
                "\t\t\t<br>\n" +
                "\t\t\t<br>\n" +
                "\t\t\t<a href=/movies/?top=-1&skip=0>All Movies</a>\n" +
                "\t\t\t<br>\n" +
                "\t\t\t<a href=/movies/1/ratings>Rating</a>\n" +
                "\t\t\t<br>\n" +
                "\t\t\t<a href=/movies/1/reviews/?top=-1&skip=0>All Reviews</a>\n" +
                "\t\t\t<br>\n" +
                "\t\t\t<a href=/>Home Page</a>\n" +
                "\t\t\t<br>\n" +
                "\t\t\t<br>\n" +
                "\t\t\t<br>\n" +
                "\t\t\t\t\t\t<table border=\"1\" style=\"width:100%\">\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td>Review ID</td>\n" +
                "\t\t\t\t\t<td>Reviewer Name</td>\n" +
                "\t\t\t\t\t<td>Review Rating</td>\n" +
                "\t\t\t\t\t<td>Summary Review</td>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td>1</td>\n" +
                "\t\t\t\t\t<td><a href=\"http://localhost:8000/movies/1/reviews/1\">Manel</a></td>\n" +
                "\t\t\t\t\t<td>5</td>\n" +
                "\t\t\t\t\t<td>Manel</td>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td>2</td>\n" +
                "\t\t\t\t\t<td><a href=\"http://localhost:8000/movies/1/reviews/2\">Bad taste Reviwer</a></td>\n" +
                "\t\t\t\t\t<td>1</td>\n" +
                "\t\t\t\t\t<td>Bad taste Reviwer</td>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t</table>\n" +
                "\t\t\t<br>\n" +
                "\t\t\t<br>\n" +
                "\t\t\t\t\t\t<ul>\n" +
                "\t\t\t\t<li>Collection id: 2</li>\n" +
                "\t\t\t\t<ul>\n" +
                "\t\t\t\t\t<li>Name: <a href=\"http://localhost:8000/collections/2/\">Before 2000</a></li>\n" +
                "\t\t\t\t\t<li>Description: movies before 2000</li>\n" +
                "\t\t\t\t</ul>\n" +
                "\t\t\t</ul>\n" +
                "\n" +
                "\t\t\t<br>\n" +
                "\n" +
                "\t\t</body>\n" +
                "\t</html>";
        assertEquals(Expected, Result);
    }

    @Test
    public void printNonExistingDetailedMovieTest() throws SQLException, ApplicationException {
        String[] lineCommand = {"GET", "/movies/10"};
        Command c = new CommandGetter().getCommand(lineCommand);
        String Result = new CommandMapper().getExecutionCommandInstance(c).execute(connection, c).toStringHtml();
        final String Expected = "<!DOCTYPE>\n" +
                "\t<html>\n" +
                "\t\t<head>\n" +
                "\t\t\t<meta\n" +
                "\t\t\t\thttp-equiv=\"Content-Type\"\n" +
                "\t\t\t\tcontent=\"text/html; charset=utf-8\"\n" +
                "\t\t\t/>\n" +
                "\t\t</head>\n" +
                "\n" +
                "\t\t<body>\n" +
                "There is no movie with id = 10.\n" +
                "\t\t</body>\n" +
                "\t</html>";
        assertEquals(Expected, Result);
    }

    @Test
    public void printDetailedReviewTest() throws SQLException, ApplicationException {
        String[] lineCommand = {"GET", "/movies/1/reviews/1"};
        Command c = new CommandGetter().getCommand(lineCommand);
        String Result = new CommandMapper().getExecutionCommandInstance(c).execute(connection, c).toStringHtml();
        final String Expected = "<!DOCTYPE>\n" +
                "\t<html>\n" +
                "\t\t<head>\n" +
                "\t\t\t<meta\n" +
                "\t\t\t\thttp-equiv=\"Content-Type\"\n" +
                "\t\t\t\tcontent=\"text/html; charset=utf-8\"\n" +
                "\t\t\t/>\n" +
                "\t\t</head>\n" +
                "\n" +
                "\t\t<body>\n" +
                "\t\t\t<ul>\n" +
                "\t\t\t\t<li>Review ID: 1</li>\n" +
                "\t\t\t\t<ul>\n" +
                "\t\t\t\t\t<li>Movie ID: <a href=\"/movies/1\">1</a></li>\n" +
                "\t\t\t\t\t<li>Reviewer Name: Manel</li>\n" +
                "\t\t\t\t\t<li>Review Rating: 5</li>\n" +
                "\t\t\t\t\t<li>Summary Review: Magnificent</li>\n" +
                "\t\t\t\t\t<li>Complete Review: An insomniac office worker, looking for a way to change his life, crosses paths with a devil-may-care soap maker, forming an underground fight club that evolves into something much, much more... </li>\n" +
                "\t\t\t\t</ul>\n" +
                "\t\t\t</ul>\n" +
                "\t\t\t<br>\n" +
                "\t\t\t<br>\n" +
                "\t\t\t<a href=/movies/1/reviews?top=-1&skip=0>All Reviews</a>\n" +
                "\t\t\t<br>\n" +
                "\t\t\t<a href=/>Home Page</a>\n" +
                "\t\t\t<br>\n" +
                "\n" +
                "\t\t</body>\n" +
                "\t</html>";
        assertEquals(Expected, Result);
    }

    @Test
    public void printNonExistingDetailedReviewTest() throws SQLException, ApplicationException {
        String[] lineCommand = {"GET", "/movies/1/reviews/10"};
        Command c = new CommandGetter().getCommand(lineCommand);
        String Result = new CommandMapper().getExecutionCommandInstance(c).execute(connection, c).toStringHtml();
        final String Expected = "<!DOCTYPE>\n" +
                "\t<html>\n" +
                "\t\t<head>\n" +
                "\t\t\t<meta\n" +
                "\t\t\t\thttp-equiv=\"Content-Type\"\n" +
                "\t\t\t\tcontent=\"text/html; charset=utf-8\"\n" +
                "\t\t\t/>\n" +
                "\t\t</head>\n" +
                "\n" +
                "\t\t<body>\n" +
                "There is no review with id = 10 for movie with id = 1.\n" +
                "\t\t</body>\n" +
                "\t</html>";
        assertEquals(Expected, Result);
    }

    @Test
    public void printErrorTest() {
        String Result = new PrintError("Error!").toStringHtml();
        final String Expected = "<!DOCTYPE>\n" +
                "\t<html>\n" +
                "\t\t<head>\n" +
                "\t\t\t<meta\n" +
                "\t\t\t\thttp-equiv=\"Content-Type\"\n" +
                "\t\t\t\tcontent=\"text/html; charset=utf-8\"\n" +
                "\t\t\t/>\n" +
                "\t\t</head>\n" +
                "\n" +
                "\t\t<body>\n" +
                "Error!\n" +
                "\t\t</body>\n" +
                "\t</html>";
        assertEquals(Expected, Result);
    }

    @Test
    public void printExitTest() {
        String Result = new PrintExit().toStringHtml();
        final String Expected = "<!DOCTYPE>\n" +
                "\t<html>\n" +
                "\t\t<head>\n" +
                "\t\t\t<meta\n" +
                "\t\t\t\thttp-equiv=\"Content-Type\"\n" +
                "\t\t\t\tcontent=\"text/html; charset=utf-8\"\n" +
                "\t\t\t/>\n" +
                "\t\t</head>\n" +
                "\n" +
                "\t\t<body>\n" +
                "Exiting application. Thank you and good bye.\n" +
                "\t\t</body>\n" +
                "\t</html>";
        assertEquals(Expected, Result);
    }


    @Test
    public void printCollectionsTest() throws SQLException, ApplicationException {
        String[] lineCommand = {"GET", "/collections"};
        Command c = new CommandGetter().getCommand(lineCommand);
        String Result = new CommandMapper().getExecutionCommandInstance(c).execute(connection, c).toStringHtml();
        final String Expected = "<!DOCTYPE>\n" +
                "\t<html>\n" +
                "\t\t<head>\n" +
                "\t\t\t<meta\n" +
                "\t\t\t\thttp-equiv=\"Content-Type\"\n" +
                "\t\t\t\tcontent=\"text/html; charset=utf-8\"\n" +
                "\t\t\t/>\n" +
                "\t\t</head>\n" +
                "\n" +
                "\t\t<body>\n" +
                "\t\t\t<table border=\"1\" style=\"width:100%\">\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td>Collection id</td>\n" +
                "\t\t\t\t\t<td>Name</td>\n" +
                "\t\t\t\t\t<td>Description</td>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td>1</td>\n" +
                "\t\t\t\t\t<td><a href=\"/collections/1\">STARWARS</a></td>\n" +
                "\t\t\t\t\t<td>serie de filmes da saga starwars</td>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td>2</td>\n" +
                "\t\t\t\t\t<td><a href=\"/collections/2\">Before 2000</a></td>\n" +
                "\t\t\t\t\t<td>movies before 2000</td>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td>3</td>\n" +
                "\t\t\t\t\t<td><a href=\"/collections/3\">movies after 2000</a></td>\n" +
                "\t\t\t\t\t<td>movies from this century</td>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t</table>\t\t\t<br>\n" +
                "\t\t\t<br>\n" +
                "\t\t\t<table border=\"0\" style=\"width:100%\">\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td align=\"left\">\n" +
                "\t\t\t\t\t\t<a href=/collections/>Previous</a>\n" +
                "\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t<td align=\"right\">\n" +
                "\t\t\t\t\t\t<a href=/collections/>Next</a>\n" +
                "\t\t\t\t\t</td>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t</table>\n" +
                "\t\t\t<br>\n" +
                "\t\t\t<br>\n" +
                "\t\t\t<a href=/>Home Page</a>\n" +
                "\t\t\t<br>\n" +
                "\t\t\t<br>\n" +
                "\t\t\t<br>\n" +
                "\t\t\tPost Collection<br>\t\t\t<form method=\"POST\" action=\"/collections?\">\n" +
                "\t\t\t\tName:<input name=\"name\" type=\"text\">\n" +
                "\t\t\t\t<br>\n" +
                "\t\t\t\tDescription:<input name=\"description\" type=\"text\">\n" +
                "\t\t\t\t<br>\n" +
                "\t\t\t\t<input type=\"submit\" value=\"Submit\">\n" +
                "\t\t\t\t<br>\t\t\t</form>\n" +
                "\t\t\t<br>\t\t\t<br>\n" +
                "\t\t</body>\n" +
                "\t</html>";
        assertEquals(Expected, Result);
    }

    @Test
    public void printCollectionTest() throws SQLException, ApplicationException {
        String[] lineCommand = {"GET", "/collections/2"};
        Command c = new CommandGetter().getCommand(lineCommand);
        String Result = new CommandMapper().getExecutionCommandInstance(c).execute(connection, c).toStringHtml();
        final String Expected = "<!DOCTYPE>\n" +
                "\t<html>\n" +
                "\t\t<head>\n" +
                "\t\t\t<meta\n" +
                "\t\t\t\thttp-equiv=\"Content-Type\"\n" +
                "\t\t\t\tcontent=\"text/html; charset=utf-8\"\n" +
                "\t\t\t/>\n" +
                "\t\t</head>\n" +
                "\n" +
                "\t\t<body>\n" +
                "\t\t\t<table border=\"1\" style=\"width:100%\">\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td>Collection id</td>\n" +
                "\t\t\t\t\t<td>Collection name</td>\n" +
                "\t\t\t\t\t<td>Collection description</td>\n" +
                "\t\t\t\t\t<td>Movie id</td>\n" +
                "\t\t\t\t\t<td>Movie name</td>\n" +
                "\t\t\t\t</tr>\n" +
                "\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td>2</td>\n" +
                "\t\t\t\t\t<td>Before 2000</td>\n" +
                "\t\t\t\t\t<td>movies before 2000</td>\n" +
                "\t\t\t\t\t<td>1</td>\n" +
                "\t\t\t\t\t<td><a href=\"/movies/1\">Fight Club</a></td>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td></td>\n" +
                "\t\t\t\t\t<td></td>\n" +
                "\t\t\t\t\t<td></td>\n" +
                "\t\t\t\t\t<td>2</td>\n" +
                "\t\t\t\t\t<td><a href=\"/movies/2\">Seven</a></td>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td></td>\n" +
                "\t\t\t\t\t<td></td>\n" +
                "\t\t\t\t\t<td></td>\n" +
                "\t\t\t\t\t<td>3</td>\n" +
                "\t\t\t\t\t<td><a href=\"/movies/3\">The Matrix</a></td>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td></td>\n" +
                "\t\t\t\t\t<td></td>\n" +
                "\t\t\t\t\t<td></td>\n" +
                "\t\t\t\t\t<td>5</td>\n" +
                "\t\t\t\t\t<td><a href=\"/movies/5\">Pulp Fiction</a></td>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td></td>\n" +
                "\t\t\t\t\t<td></td>\n" +
                "\t\t\t\t\t<td></td>\n" +
                "\t\t\t\t\t<td>6</td>\n" +
                "\t\t\t\t\t<td><a href=\"/movies/6\">American History X</a></td>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td></td>\n" +
                "\t\t\t\t\t<td></td>\n" +
                "\t\t\t\t\t<td></td>\n" +
                "\t\t\t\t\t<td>7</td>\n" +
                "\t\t\t\t\t<td><a href=\"/movies/7\">The Silence of the Lambs</a></td>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t</table>\t\t\t<br>\n" +
                "\t\t\t<br>\n" +
                "\t\t\t<a href=/collections?top=-1&skip=0>All Collections</a>\n" +
                "\t\t\t<br>\n" +
                "\t\t\t<a href=/>Home Page</a>\n" +
                "\t\t\t<br>\n" +
                "\t\t\t<br>\n" +
                "\t\t\t<br>\n" +
                "\t\t\tPost Movie into Collection\n" +
                "\t\t\t<br>\n" +
                "\t\t\t<form method=\"POST\" action=\"/collections/2/?\">\n" +
                "\t\t\t\tID:<input name=\"mid\" type=\"number\">\n" +
                "\t\t\t</form>\n" +
                "\t\t\t<br>\n" +
                "\t\t\t<br>\n" +
                "\n" +
                "\t\t</body>\n" +
                "\t</html>";
        assertEquals(Expected, Result);
    }


    @Test
    public void printNonExistingCollectionTest() throws SQLException, ApplicationException {
        String[] lineCommand = {"GET", "/collections/10"};
        Command c = new CommandGetter().getCommand(lineCommand);
        String Result = new CommandMapper().getExecutionCommandInstance(c).execute(connection, c).toStringHtml();
        final String Expected = "<!DOCTYPE>\n" +
                "\t<html>\n" +
                "\t\t<head>\n" +
                "\t\t\t<meta\n" +
                "\t\t\t\thttp-equiv=\"Content-Type\"\n" +
                "\t\t\t\tcontent=\"text/html; charset=utf-8\"\n" +
                "\t\t\t/>\n" +
                "\t\t</head>\n" +
                "\n" +
                "\t\t<body>\n" +
                "There are no movies in collection.\n" +
                "\n" +
                "\t\t</body>\n" +
                "\t</html>";
        assertEquals(Expected, Result);
    }

    @Test
    public void printMovieTest() throws SQLException, ApplicationException {
        String[] lineCommand = {"GET", "/tops/reviews/higher/count"};
        Command c = new CommandGetter().getCommand(lineCommand);
        String Result = new CommandMapper().getExecutionCommandInstance(c).execute(connection, c).toStringHtml();
        final String Expected = "<!DOCTYPE>\n" +
                "\t<html>\n" +
                "\t\t<head>\n" +
                "\t\t\t<meta\n" +
                "\t\t\t\thttp-equiv=\"Content-Type\"\n" +
                "\t\t\t\tcontent=\"text/html; charset=utf-8\"\n" +
                "\t\t\t/>\n" +
                "\t\t</head>\n" +
                "\n" +
                "\t\t<body>\n" +
                "\t\t\t<ul>\n" +
                "\t\t\t\t<li>Movie ID: 1</li>\n" +
                "\t\t\t\t<ul>\n" +
                "\t\t\t\t\t<li>Name: <a href=\"/movies/1\">Fight Club</a></li>\n" +
                "\t\t\t\t\t<li>Release: 1999</li>\n" +
                "\t\t\t\t</ul>\n" +
                "\t\t\t</ul>\n" +
                "\t\t\t<br>\n" +
                "\t\t\t<table border=\"0\" style=\"width:100%\">\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td align=\"left\">\n" +
                "\t\t\t\t\t\t<a href=/movies/>Previous</a>\n" +
                "\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t<td align=\"right\">\n" +
                "\t\t\t\t\t\t<a href=/movies/>Next</a>\n" +
                "\t\t\t\t\t</td>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t</table>\n" +
                "\t\t\t<br>\n" +
                "\t\t\t<a href=/tops/ratings>Tops Ratings</a>\n" +
                "\t\t\t<br>\n" +
                "\t\t\t<a href=/>Home Page</a>\n" +
                "\t\t\t<br>\n" +
                "\t\t\t<br>\n" +
                "\t\t\t<br>\n" +
                "\t\t\tPost Movie\n" +
                "\t\t\t<br>\n" +
                "\t\t\t<form method=\"POST\" action=\"/movies?\">\n" +
                "\t\t\t\tName:<input name=\"title\" type=\"text\">\n" +
                "\t\t\t\t<br>\n" +
                "\t\t\t\tYear:<input name=\"releaseYear\" type=\"number\">\n" +
                "\t\t\t\t<br>\n" +
                "\t\t\t\t<input type=\"submit\" value=\"Submit\">\n" +
                "\t\t\t</form>\t\t\t<br>\n" +
                "\t\t\t<br>\n" +
                "\n" +
                "\t\t</body>\n" +
                "\t</html>";
        assertEquals(Expected, Result);
    }

    @Test
    public void printMovieRatingTest() throws SQLException, ApplicationException {
        String[] lineCommand = {"GET", "/movies/1/ratings"};
        Command c = new CommandGetter().getCommand(lineCommand);
        String Result = new CommandMapper().getExecutionCommandInstance(c).execute(connection, c).toStringHtml();
        final String Expected = "<!DOCTYPE>\n" +
                "\t<html>\n" +
                "\t\t<head>\n" +
                "\t\t\t<meta\n" +
                "\t\t\t\thttp-equiv=\"Content-Type\"\n" +
                "\t\t\t\tcontent=\"text/html; charset=utf-8\"\n" +
                "\t\t\t/>\n" +
                "\t\t</head>\n" +
                "\n" +
                "\t\t<body>\n" +
                "\t\t\t<ul>\n" +
                "\t\t\t\t<li>The average rating for the movie is : 3.504</li>\n" +
                "\t\t\t\t<ul>\n" +
                "\t\t\t\t\t<li>Movie ID : <a href=\"/movies/1\">1</a></li>\n" +
                "\t\t\t\t\t<li>*: 20</li>\n" +
                "\t\t\t\t\t<li>**: 10</li>\n" +
                "\t\t\t\t\t<li>***: 15</li>\n" +
                "\t\t\t\t\t<li>****: 50</li>\n" +
                "\t\t\t\t\t<li>*****: 32</li>\n" +
                "\t\t\t\t</ul>\n" +
                "\t\t\t</ul>\n" +
                "\t\t\t<br>\n" +
                "\t\t\t<br>\n" +
                "\t\t\t<a href=/>Home</a>\n" +
                "\t\t\t<br>\n" +
                "\t\t\t<br>\n" +
                "\t\t\t<br>\n" +
                "\t\t\tPost Rating\n" +
                "\t\t\t<br>\n" +
                "\t\t\t<form method=\"POST\" action=\"/movies/1/ratings?\">\n" +
                "\t\t\t\tRating(1-5):\n" +
                "\t\t\t\t<br>\n" +
                "\t\t\t\t<input type=\"radio\" name=\"rating\" value=\"1\"> 1\n" +
                "\t\t\t\t<br>\n" +
                "\t\t\t\t<input type=\"radio\" name=\"rating\" value=\"2\"> 2\n" +
                "\t\t\t\t<br>\n" +
                "\t\t\t\t<input type=\"radio\" name=\"rating\" value=\"3\"> 3\n" +
                "\t\t\t\t<br>\n" +
                "\t\t\t\t<input type=\"radio\" name=\"rating\" value=\"4\"> 4\n" +
                "\t\t\t\t<br>\n" +
                "\t\t\t\t<input type=\"radio\" name=\"rating\" value=\"5\"> 5\n" +
                "\t\t\t\t<br>\n" +
                "\t\t\t</form>\n" +
                "\t\t\t<br>\n" +
                "\t\t\t<br>\n" +
                "\n" +
                "\t\t</body>\n" +
                "\t</html>";
        assertEquals(Expected, Result);
    }

    @Test
    public void printNonExistingMovieRatingTest() throws SQLException, ApplicationException {
        String[] lineCommand = {"GET", "/movies/10/ratings"};
        Command c = new CommandGetter().getCommand(lineCommand);
        String Result = new CommandMapper().getExecutionCommandInstance(c).execute(connection, c).toStringHtml();
        final String Expected = "<!DOCTYPE>\n" +
                "\t<html>\n" +
                "\t\t<head>\n" +
                "\t\t\t<meta\n" +
                "\t\t\t\thttp-equiv=\"Content-Type\"\n" +
                "\t\t\t\tcontent=\"text/html; charset=utf-8\"\n" +
                "\t\t\t/>\n" +
                "\t\t</head>\n" +
                "\n" +
                "\t\t<body>\n" +
                "\t\t\tThere is no such movie.\n" +
                "\n" +
                "\t\t\t<br>\n" +
                "\n" +
                "\t\t</body>\n" +
                "\t</html>";
        assertEquals(Expected, Result);
    }

    @Test
    public void printMovieReviewTest() throws SQLException, ApplicationException {
        String[] lineCommand = {"GET", "/movies/1/reviews"};
        Command c = new CommandGetter().getCommand(lineCommand);
        String Result = new CommandMapper().getExecutionCommandInstance(c).execute(connection, c).toStringHtml();
        final String Expected = "<!DOCTYPE>\n" +
                "\t<html>\n" +
                "\t\t<head>\n" +
                "\t\t\t<meta\n" +
                "\t\t\t\thttp-equiv=\"Content-Type\"\n" +
                "\t\t\t\tcontent=\"text/html; charset=utf-8\"\n" +
                "\t\t\t/>\n" +
                "\t\t</head>\n" +
                "\n" +
                "\t\t<body>\n" +
                "\t\t\t<table border=\"1\" style=\"width:100%\">\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td>Review ID</td>\n" +
                "\t\t\t\t\t<td>Reviewer Name</td>\n" +
                "\t\t\t\t\t<td>Review Rating</td>\n" +
                "\t\t\t\t\t<td>Summary Review</td>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td>1</td>\n" +
                "\t\t\t\t\t<td><a href=\"/movies/1/reviews/1\">Manel</a></td>\n" +
                "\t\t\t\t\t<td>5</td>\n" +
                "\t\t\t\t\t<td>Magnificent</td>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td>2</td>\n" +
                "\t\t\t\t\t<td><a href=\"/movies/1/reviews/2\">Bad taste Reviwer</a></td>\n" +
                "\t\t\t\t\t<td>1</td>\n" +
                "\t\t\t\t\t<td>Horrible</td>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td>8</td>\n" +
                "\t\t\t\t\t<td><a href=\"/movies/1/reviews/8\">Ze</a></td>\n" +
                "\t\t\t\t\t<td>5</td>\n" +
                "\t\t\t\t\t<td>Film of the year</td>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t</table>\t\t\t<br>\n" +
                "\t\t\t<br>\n" +
                "\t\t\t<table border=\"0\" style=\"width:100%\">\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td align=\"left\">\n" +
                "\t\t\t\t\t\t<a href=/movies/1/reviews/>Previous</a>\n" +
                "\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t<td align=\"right\">\n" +
                "\t\t\t\t\t\t<a href=/movies/1/reviews/>Next</a>\n" +
                "\t\t\t\t\t</td>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t</table>\n" +
                "\t\t\t<br>\n" +
                "\t\t\t<a href=/movies/1>Movie</a>\n" +
                "\t\t\t<br>\n" +
                "\t\t\t<a href=/>Home</a>\n" +
                "\t\t\t<br>\n" +
                "\t\t\t<br>\n" +
                "\t\t\t<br>\n" +
                "\t\t\tPost Review\n" +
                "\t\t\t<br>\n" +
                "\t\t\t<form method=\"POST\" action=\"/movies/1/reviews?\">\n" +
                "\t\t\t\tName:<input name=\"reviewerName\" type=\"text\">\n" +
                "\t\t\t\t<br>\t\t\t\tSummary:<input name=\"reviewSummary\" type=\"text\">\n" +
                "\t\t\t\t<br>\n" +
                "\t\t\t\tComplete review:<input name=\"review\" type=\"text\">\n" +
                "\t\t\t\t<br>\n" +
                "\t\t\t\tRating(1-5):\n" +
                "\t\t\t\t<br>\t\t\t\t<input type=\"radio\" name=\"rating\" value=\"1\"> 1\n" +
                "\t\t\t\t<br>\n" +
                "\t\t\t\t<input type=\"radio\" name=\"rating\" value=\"2\"> 2\n" +
                "\t\t\t\t<br>\n" +
                "\t\t\t\t<input type=\"radio\" name=\"rating\" value=\"3\"> 3\n" +
                "\t\t\t\t<br>\n" +
                "\t\t\t\t<input type=\"radio\" name=\"rating\" value=\"4\"> 4\n" +
                "\t\t\t\t<br>\n" +
                "\t\t\t\t<input type=\"radio\" name=\"rating\" value=\"5\"> 5\n" +
                "\t\t\t\t<br>\n" +
                "\t\t\t\t<input type=\"submit\" value=\"Submit\">\n" +
                "\t\t\t\t<br>\n" +
                "\t\t\t\t</form>\n" +
                "\t\t\t\t<br>\n" +
                "\t\t\t\t<br>\n" +
                "\n" +
                "\t\t</body>\n" +
                "\t</html>";
        assertEquals(Expected, Result);
    }

}
