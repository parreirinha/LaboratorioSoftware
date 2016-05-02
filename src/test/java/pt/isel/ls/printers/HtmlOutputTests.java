package pt.isel.ls.printers;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pt.isel.ls.exceptions.ApplicationException;
import pt.isel.ls.executioncommands.DataCreationTests;
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
                "There is no such movie.\n" +
                "\n" +
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
                "\t\t\t\t\t<li>Movie ID: 1</li>\n" +
                "\t\t\t\t\t<li>Reviewer Name: Manel</li>\n" +
                "\t\t\t\t\t<li>Review Rating: 5</li>\n" +
                "\t\t\t\t\t<li>Summary Review: Magnificent</li>\n" +
                "\t\t\t\t\t<li>Complete Review: An insomniac office worker, looking for a way to change his life, crosses paths with a devil-may-care soap maker, forming an underground fight club that evolves into something much, much more... </li>\n" +
                "\t\t\t\t</ul>\n" +
                "\t\t\t</ul>\n" +
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
                "There is no such review.\n" +
                "\n" +
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
                "\t\t\t\t\t<td>STARWARS</td>\n" +
                "\t\t\t\t\t<td>serie de filmes da saga starwars</td>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td>2</td>\n" +
                "\t\t\t\t\t<td>Before 2000</td>\n" +
                "\t\t\t\t\t<td>movies before 2000</td>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td>3</td>\n" +
                "\t\t\t\t\t<td>movies after 2000</td>\n" +
                "\t\t\t\t\t<td>movies from this century</td>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t</table>\n" +
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
                "<table border=\"1\" style=\"width:100%\">\n" +
                "\t<tr>\n" +
                "\t\t<td>Collection id</td>\n" +
                "\t\t<td>Collection name</td>\n" +
                "\t\t<td>Collection description</td>\n" +
                "\t\t<td>Movie id</td>\n" +
                "\t\t<td>Movie name</td>\n" +
                "</tr>\n" +
                "\n" +
                "\t<tr>\n" +
                "<td>2</td>\n" +
                "<td>Before 2000</td>\n" +
                "<td>movies before 2000</td>\n" +
                "<td>1</td>\n" +
                "<td>Fight Club</td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td></td>\n" +
                "<td></td>\n" +
                "<td></td>\n" +
                "<td>2</td>\n" +
                "<td>Seven</td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td></td>\n" +
                "<td></td>\n" +
                "<td></td>\n" +
                "<td>3</td>\n" +
                "<td>The Matrix</td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td></td>\n" +
                "<td></td>\n" +
                "<td></td>\n" +
                "<td>5</td>\n" +
                "<td>Pulp Fiction</td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td></td>\n" +
                "<td></td>\n" +
                "<td></td>\n" +
                "<td>6</td>\n" +
                "<td>American History X</td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td></td>\n" +
                "<td></td>\n" +
                "<td></td>\n" +
                "<td>7</td>\n" +
                "<td>The Silence of the Lambs</td>\n" +
                "</tr>\n" +
                "</tr>\n" +
                "\n" +
                "</table>\n" +
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
                "\t\t\t\t\t<li>Name: Fight Club</li>\n" +
                "\t\t\t\t\t<li>Release: 1999</li>\n" +
                "\t\t\t\t</ul>\n" +
                "\t\t\t</ul>\n" +
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
                "\t\t\t\t<li>The average rating for the movie with the ID: 1</li>\n" +
                "\t\t\t\t<ul>\n" +
                "\t\t\t\t\t<li>is: 3.504</li>\n" +
                "\t\t\t\t\t<li>*: 20</li>\n" +
                "\t\t\t\t\t<li>**: 10</li>\n" +
                "\t\t\t\t\t<li>***: 15</li>\n" +
                "\t\t\t\t\t<li>****: 50</li>\n" +
                "\t\t\t\t\t<li>*****: 32</li>\n" +
                "\t\t\t\t</ul>\n" +
                "\t\t\t</ul>\n" +
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
                "There is no such movie.\n" +
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
                "\t\t\t\t\t<td>Manel</td>\n" +
                "\t\t\t\t\t<td>5</td>\n" +
                "\t\t\t\t\t<td>Magnificent</td>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td>2</td>\n" +
                "\t\t\t\t\t<td>Bad taste Reviwer</td>\n" +
                "\t\t\t\t\t<td>1</td>\n" +
                "\t\t\t\t\t<td>Horrible</td>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td>8</td>\n" +
                "\t\t\t\t\t<td>Ze</td>\n" +
                "\t\t\t\t\t<td>5</td>\n" +
                "\t\t\t\t\t<td>Film of the year</td>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t</table>\n" +
                "\t\t</body>\n" +
                "\t</html>";
        assertEquals(Expected, Result);
    }

}
