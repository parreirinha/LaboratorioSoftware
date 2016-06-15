package pt.isel.ls.executioncommands;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pt.isel.ls.exceptions.ApplicationException;
import pt.isel.ls.linecommand.model.Command;
import pt.isel.ls.linecommand.process.CommandGetter;

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

        connection = new TestConnectionFactory().getNewConnection();
        input = new String[]{"GET", "/movies"};
        command = new CommandGetter().getCommand(input);
        try {
            result = getAllMovies.execute(connection, command).toStringText();
        } catch (pt.isel.ls.exceptions.ApplicationException e) {
            e.printStackTrace();
        }
        expected = getAllMoviesString();
        assertEquals(expected, result);
    }

    @Test
    public void checkResultsetFromMoviesHtml() throws SQLException, ApplicationException {
        input = new String[]{"GET", "/movies"};
        command = new CommandGetter().getCommand(input);
        result = getAllMovies.execute(connection, command).toStringHtml();
        expected = getAllMoviesHtml();
        assertEquals(expected, result);
    }

    private String getAllMoviesHtml() {
        return "<!DOCTYPE>\n" +
                "\t<html>\n" +
                "\t\t<head>\n" +
                "\t\t\t<meta\n" +
                "\t\t\t\thttp-equiv=\"Content-Type\"\n" +
                "\t\t\t\tcontent=\"text/html; charset=utf-8\"\n" +
                "\t\t\t/>\n" +
                "\t\t</head>\n" +
                "\n" +
                "\t\t<body>\n" +
                "\t\t\t<table border=\"0\"  style=\"width:100%\">\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td align=\"center\"><h3><a href=/>Home Page</a></h3></td>\n" +
                "\t\t\t\t\t<td align=\"center\"><h3><a href=/tops/ratings>Tops Ratings</a></h3></td>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t</table>\n" +
                "\t\t\t<table border=\"1\" style=\"width:100%\">\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td>Movie ID</td>\n" +
                "\t\t\t\t\t<td>Name</td>\n" +
                "\t\t\t\t\t<td>Release</td>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td>1</td>\n" +
                "\t\t\t\t\t<td><a href=\"/movies/1\">Fight Club</a></td>\n" +
                "\t\t\t\t\t<td>1999</td>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td>2</td>\n" +
                "\t\t\t\t\t<td><a href=\"/movies/2\">Seven</a></td>\n" +
                "\t\t\t\t\t<td>1995</td>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td>3</td>\n" +
                "\t\t\t\t\t<td><a href=\"/movies/3\">The Matrix</a></td>\n" +
                "\t\t\t\t\t<td>1999</td>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td>4</td>\n" +
                "\t\t\t\t\t<td><a href=\"/movies/4\">Inception</a></td>\n" +
                "\t\t\t\t\t<td>2010</td>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td>5</td>\n" +
                "\t\t\t\t\t<td><a href=\"/movies/5\">Pulp Fiction</a></td>\n" +
                "\t\t\t\t\t<td>1994</td>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t</table>\n" +
                "\t\t\t<br>\n" +
                "\t\t\t<table border=\"0\" style=\"width:100%\">\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td align=\"left\">\n" +
                "\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t<td align=\"right\">\n" +
                "\t\t\t\t\t\t<a href=/movies/?skip=5&top=5>Next</a>\n" +
                "\t\t\t\t\t</td>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t</table>\n" +
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
    }

    private String getAllMoviesString(){
        return "Movie ID = 1\n\tName = Fight Club\tRelease = 1999\n"+
            "Movie ID = 2\n\tName = Seven\tRelease = 1995\n" +
            "Movie ID = 3\n\tName = The Matrix\tRelease = 1999\n" +
            "Movie ID = 4\n\tName = Inception\tRelease = 2010\n" +
            "Movie ID = 5\n\tName = Pulp Fiction\tRelease = 1994\n";
    }

    @Test
    public void sortByYearDesc() throws SQLException {

        connection = new TestConnectionFactory().getNewConnection();
        input = new String[]{"GET", "/movies", "sortBy=yearDesc"};
        command = new CommandGetter().getCommand(input);
        try {
            result = getAllMovies.execute(connection, command).toStringText();
        } catch (pt.isel.ls.exceptions.ApplicationException e) {
            e.printStackTrace();
        }
        expected = getStringToSortByYearDesc();
        assertEquals(expected, result);
    }
    private String getStringToSortByYearDesc(){
        return  "Movie ID = 4\n" + "\tName = Inception\tRelease = 2010\n" +
                "Movie ID = 1\n" + "\tName = Fight Club\tRelease = 1999\n" +
                "Movie ID = 3\n" + "\tName = The Matrix\tRelease = 1999\n" +
                "Movie ID = 6\n" + "\tName = American History X\tRelease = 1998\n" +
                "Movie ID = 2\n" + "\tName = Seven\tRelease = 1995\n";
    }

    @Test
    public void sortByYear() throws SQLException {

        connection = new TestConnectionFactory().getNewConnection();
        input = new String[]{"GET", "/movies", "sortBy=year"};
        command = new CommandGetter().getCommand(input);
        try {
            result = getAllMovies.execute(connection, command).toStringText();
        } catch (pt.isel.ls.exceptions.ApplicationException e) {
            e.printStackTrace();
        }
        expected = getStringToSortByYear();
        assertEquals(expected, result);
    }
    private String getStringToSortByYear(){
        return "Movie ID = 7\n" +
                "\tName = The Silence of the Lambs\tRelease = 1991\n" +
                "Movie ID = 5\n" +
                "\tName = Pulp Fiction\tRelease = 1994\n" +
                "Movie ID = 2\n" +
                "\tName = Seven\tRelease = 1995\n" +
                "Movie ID = 6\n" +
                "\tName = American History X\tRelease = 1998\n" +
                "Movie ID = 3\n" +
                "\tName = The Matrix\tRelease = 1999\n";
    }


    @Test
    public void sortByAddedDataDesc() throws SQLException {

        connection = new TestConnectionFactory().getNewConnection();
        input = new String[]{"GET", "/movies", "sortBy=addedDataDesc"};
        command = new CommandGetter().getCommand(input);
        try {
            result = getAllMovies.execute(connection, command).toStringText();
        } catch (pt.isel.ls.exceptions.ApplicationException e) {
            e.printStackTrace();
        }
        expected = getStringToSortByAddedDataDescDesc();
        assertEquals(expected, result);
    }
    private String getStringToSortByAddedDataDescDesc(){
        return "Movie ID = 7\n" +
                "\tName = The Silence of the Lambs\tRelease = 1991\n" +
                "Movie ID = 6\n" +
                "\tName = American History X\tRelease = 1998\n" +
                "Movie ID = 5\n" +
                "\tName = Pulp Fiction\tRelease = 1994\n" +
                "Movie ID = 4\n" +
                "\tName = Inception\tRelease = 2010\n" +
                "Movie ID = 3\n" +
                "\tName = The Matrix\tRelease = 1999\n";
    }


    @Test
    public void sortByTitle() throws SQLException {

        connection = new TestConnectionFactory().getNewConnection();
        input = new String[]{"GET", "/movies", "sortBy=title"};
        command = new CommandGetter().getCommand(input);
        try {
            result = getAllMovies.execute(connection, command).toStringText();
        } catch (pt.isel.ls.exceptions.ApplicationException e) {
            e.printStackTrace();
        }
        expected = getStringToSortByTitle();
        assertEquals(expected, result);
    }
    private String getStringToSortByTitle(){
        return "Movie ID = 6\n" +
            "\tName = American History X\tRelease = 1998\n" +
            "Movie ID = 1\n" +
            "\tName = Fight Club\tRelease = 1999\n" +
            "Movie ID = 4\n" +
            "\tName = Inception\tRelease = 2010\n" +
            "Movie ID = 5\n" +
            "\tName = Pulp Fiction\tRelease = 1994\n" +
            "Movie ID = 2\n" +
            "\tName = Seven\tRelease = 1995\n";
    }

    @Test
    public void sortByTitleDesc() throws SQLException {

        connection = new TestConnectionFactory().getNewConnection();
        input = new String[]{"GET", "/movies", "sortBy=titleDesc"};
        command = new CommandGetter().getCommand(input);
        try {
            result = getAllMovies.execute(connection, command).toStringText();
        } catch (pt.isel.ls.exceptions.ApplicationException e) {
            e.printStackTrace();
        }
        expected = getStringToSortByTitleDesc();
        assertEquals(expected, result);
    }

    private String getStringToSortByTitleDesc(){
        return "Movie ID = 7\n" +
                "\tName = The Silence of the Lambs\tRelease = 1991\n" +
                "Movie ID = 3\n" +
                "\tName = The Matrix\tRelease = 1999\n" +
                "Movie ID = 2\n" +
                "\tName = Seven\tRelease = 1995\n" +
                "Movie ID = 5\n" +
                "\tName = Pulp Fiction\tRelease = 1994\n" +
                "Movie ID = 4\n" +
                "\tName = Inception\tRelease = 2010\n";
    }

    @Test
    public void sortByRating() throws SQLException, ApplicationException {

        connection = new TestConnectionFactory().getNewConnection();
        input = new String[]{"GET", "/movies", "sortBy=rating"};
        command = new CommandGetter().getCommand(input);
        result = getAllMovies.execute(connection, command).toStringText();
        expected = getStringToSortByRating();
        assertEquals(expected, result);
    }

    private String getStringToSortByRating(){
        return "Movie ID = 3\n" +
                "\tName = The Matrix\tRelease = 1999\n" +
                "Movie ID = 5\n" +
                "\tName = Pulp Fiction\tRelease = 1994\n" +
                "Movie ID = 2\n" +
                "\tName = Seven\tRelease = 1995\n" +
                "Movie ID = 7\n" +
                "\tName = The Silence of the Lambs\tRelease = 1991\n" +
                "Movie ID = 1\n" +
                "\tName = Fight Club\tRelease = 1999\n";
    }

    @Test
    public void sortByRatingDesc() throws SQLException, ApplicationException {

        connection = new TestConnectionFactory().getNewConnection();
        input = new String[]{"GET", "/movies", "sortBy=ratingDesc"};
        command = new CommandGetter().getCommand(input);
        result = getAllMovies.execute(connection, command).toStringText();
        expected = getStringToSortByRatingDesc();
        assertEquals(expected, result);
    }

    private String getStringToSortByRatingDesc(){
        return "Movie ID = 4\n" +
                "\tName = Inception\tRelease = 2010\n" +
                "Movie ID = 6\n" +
                "\tName = American History X\tRelease = 1998\n" +
                "Movie ID = 1\n" +
                "\tName = Fight Club\tRelease = 1999\n" +
                "Movie ID = 7\n" +
                "\tName = The Silence of the Lambs\tRelease = 1991\n" +
                "Movie ID = 2\n" +
                "\tName = Seven\tRelease = 1995\n";
    }

    @Test
    public void simplePagingTest() throws SQLException, ApplicationException {

        connection = new TestConnectionFactory().getNewConnection();
        input = new String[]{"GET", "/movies", "skip=2&top=3"};
        command = new CommandGetter().getCommand(input);
        result = getAllMovies.execute(connection, command).toStringText();
        expected = getStringToSimplePagingTest();
        assertEquals(expected, result);
    }

    private String getStringToSimplePagingTest(){
        return  "Movie ID = 3\n" +
                "\tName = The Matrix\tRelease = 1999\n" +
                "Movie ID = 4\n" +
                "\tName = Inception\tRelease = 2010\n" +
                "Movie ID = 5\n" +
                "\tName = Pulp Fiction\tRelease = 1994\n";
    }

    @Test
    public void sortByAddedDataData() throws SQLException {

        connection = new TestConnectionFactory().getNewConnection();
        input = new String[]{"GET", "/movies", "sortBy=addedData"};
        command = new CommandGetter().getCommand(input);
        try {
            result = getAllMovies.execute(connection, command).toStringText();
        } catch (pt.isel.ls.exceptions.ApplicationException e) {
            e.printStackTrace();
        }
        expected = getStringToSortByAddedDataDesc();
        assertEquals(expected, result);
    }
    private String getStringToSortByAddedDataDesc(){
        return "Movie ID = 1\n" +
                "\tName = Fight Club\tRelease = 1999\n" +
                "Movie ID = 2\n" +
                "\tName = Seven\tRelease = 1995\n" +
                "Movie ID = 3\n" +
                "\tName = The Matrix\tRelease = 1999\n" +
                "Movie ID = 4\n" +
                "\tName = Inception\tRelease = 2010\n" +
                "Movie ID = 5\n" +
                "\tName = Pulp Fiction\tRelease = 1994\n";
    }

    @Test
    public void sortByTitleAndPaging() throws SQLException, ApplicationException {

        connection = new TestConnectionFactory().getNewConnection();
        input = new String[]{"GET", "/movies", "skip=2&top=3&sortBy=title"};
        command = new CommandGetter().getCommand(input);
        result = getAllMovies.execute(connection, command).toStringText();
        expected = getStringToSortByTitleWithPaging();
        assertEquals(expected, result);
    }

    private String getStringToSortByTitleWithPaging(){
        return "Movie ID = 4\n" +
                "\tName = Inception\tRelease = 2010\n" +
                "Movie ID = 5\n" +
                "\tName = Pulp Fiction\tRelease = 1994\n" +
                "Movie ID = 2\n" +
                "\tName = Seven\tRelease = 1995\n";
    }

}
