package pt.isel.ls.printers.URIGenerator;

import org.junit.Test;
import pt.isel.ls.linecommand.model.Command;
import pt.isel.ls.linecommand.process.CommandGetter;

import static org.junit.Assert.assertEquals;
import static pt.isel.ls.printers.URIGenerator.URIUtils.*;


public class URIUtilsTest {

    @Test
    public void getURITest() {

        String uri = getURI("/collections", "skip=0&top=5", "Collections");
        String expected = "<a href=/collections?skip=0&top=5>Collections</a>";
        assertEquals(expected, uri);

        uri = getURI("/movies", "skip=0&top=5", "Movies");
        expected = "<a href=/movies?skip=0&top=5>Movies</a>";
        assertEquals(expected, uri);

        uri = getURI("/tops/ratings", "skip=5&top=5", "Top Ratings");
        expected = "<a href=/tops/ratings?skip=5&top=5>Top Ratings</a>";
        assertEquals(expected, uri);
    }


    @Test
    public void getNextSkipAndTopValuesFromCommandTest() {

        String[] input = new String[]{"GET", "/tops/5/ratings/higher/average", "skip=0&top=5"};
        Command command = new CommandGetter().getCommand(input);
        String curr = getNextSkipAndTopValuesFromCommand(command);
        String expected = "skip=5&top=5";
        assertEquals(expected, curr);
    }

    @Test
    public void getNextSkipAndTopValuesFromCommandWithOrderByClauseTest() {

        String[] input = new String[]{"GET", "/tops/5/ratings/higher/average", "skip=10&top=5&orderby=rating"};
        Command command = new CommandGetter().getCommand(input);
        String curr = getNextSkipAndTopValuesFromCommand(command);
        String expected = "skip=15&top=5";
        assertEquals(expected, curr);
    }


    @Test
    public void getPreviusSkipAndTopValuesFromCommandTest() {
        String[] input = new String[]{"GET", "/tops/5/ratings/higher/average", "skip=15&top=5"};
        Command command = new CommandGetter().getCommand(input);
        String curr = getPreviusSkipAndTopValuesFromCommand(command);
        String expected = "skip=10&top=5";
        assertEquals(expected, curr);
    }

    @Test
    public void getPreviusSkipAndTopValuesFromCommandWithNegativeSkipValueTest() {

        String[] input = new String[]{"GET", "/tops/5/ratings/higher/average", "skip=0&top=5&orderby=releaseYear"};
        Command command = new CommandGetter().getCommand(input);
        String curr = getPreviusSkipAndTopValuesFromCommand(command);
        String expected = null;
        assertEquals(expected, curr);
    }

    @Test
    public void getPreviusSkipAndTopValuesFromCommandWithOrderByTest() {

        String[] input = new String[]{"GET", "/tops/5/ratings/higher/average", "skip=38&top=4&orderby=releaseYear"};
        Command command = new CommandGetter().getCommand(input);
        String curr = getPreviusSkipAndTopValuesFromCommand(command);
        String expected = "skip=34&top=4";
        assertEquals(expected, curr);
    }

}
