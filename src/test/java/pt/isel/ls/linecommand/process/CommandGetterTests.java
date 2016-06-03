package pt.isel.ls.linecommand.process;

import org.junit.Test;
import pt.isel.ls.linecommand.model.Command;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

/**
 * Class used to test CommandGetter getCommand method.
 */
public class CommandGetterTests {

    private final String[] GetWithMethodAndPath = {"GET", "/movies/123"};
    private final String[] GetWithMethodAndPathAndParams = {"GET", "/movies", "sortBy=year"};
    private final String[] GetWithPathAndHeadersAndParamaters = {"GET", "/movies", "accept:text/html", "skip=6&top=3"};
    private final String[] GetWithPathAndHeaders = {"GET", "/movies", "accept:text/html"};
    private final String[] PostWithMethodAndPathAndParams = {"POST", "/movies", "title=pulp+fiction&releaseYear=1994"};
    private final String[] GetCollectionWithPath = {"GET", "/collections/50"};
    private final String[] PostCollectionWithPathAndParams = {"GET", "/collections/50/movies", "mid=123"};
    private final String[] DeleteCollectionsWithPath = {"DELETE", "/collections/50/movies/123"};
    private final String[] Exit = {"EXIT"};
    private final String[] Option = {"OPTION"};
    private final String[] InteractiveMode = {""};


    @Test
    public void IntModeTest() {
        Command c = new CommandGetter().getCommand(InteractiveMode);
        assertEquals("", c.getMethod().getMethod());
    }

    @Test
    public void optionTest() {
        Command c = new CommandGetter().getCommand(Exit);
        assertEquals("EXIT", c.getMethod().getMethod());
    }

    @Test
    public void exitTest() {
        Command c = new CommandGetter().getCommand(Option);
        assertEquals("OPTION", c.getMethod().getMethod());
    }

    @Test
    public void getWithMethodAndPathTest() {
        Command c = new CommandGetter().getCommand(GetWithMethodAndPath);
        assertEquals("GET", c.getMethod().getMethod());
        assertEquals("/movies/{mid}", c.getPath().getPathString());
        assertEquals(123, (int) c.getPath().getPathInt("mid"));
    }

    @Test
    public void postWithMethodAndPathAndParamsTest() {
        Command c = new CommandGetter().getCommand(PostWithMethodAndPathAndParams);
        assertEquals("POST", c.getMethod().getMethod());
        assertEquals("/movies", c.getPath().getPathString());
        assertEquals("pulp fiction", c.getParams().getParamString("title"));
        assertEquals(1994, (int) c.getParams().getParamInt("releaseYear"));
    }

    @Test
    public void getWithPathAndHeadersAndParamatersTest() {
        Command c = new CommandGetter().getCommand(GetWithPathAndHeadersAndParamaters);
        assertEquals("GET", c.getMethod().getMethod());
        assertEquals("/movies", c.getPath().getPathString());
        assertEquals("text/html", c.getHeaders().getHeadersString("accept"));
        assertEquals(6, c.getParams().getParamInt("skip"));
        assertEquals(3, c.getParams().getParamInt("top"));
    }

    @Test
    public void getWithPathAndHeadersAndNoParamatersTest() {
        Command c = new CommandGetter().getCommand(GetWithPathAndHeaders);
        assertEquals("GET", c.getMethod().getMethod());
        assertEquals("/movies", c.getPath().getPathString());
        assertEquals("text/html", c.getHeaders().getHeadersString("accept"));
    }

    @Test
    public void getWithMethodAndPathAndParamsTest() {
        Command c = new CommandGetter().getCommand(GetWithMethodAndPathAndParams);
        assertEquals("GET", c.getMethod().getMethod());
        assertEquals("/movies", c.getPath().getPathString());
        assertEquals("year", c.getParams().getParamString("sortBy"));
    }

    @Test
    public void getCollectionWithPathTest() {
        Command c = new CommandGetter().getCommand(GetCollectionWithPath);
        assertEquals("GET", c.getMethod().getMethod());
        assertEquals("/collections/{cid}", c.getPath().getPathString());
        assertEquals(50, c.getPath().getPathInt("cid"));
    }

    @Test
    public void postCollectionWithPathAndParamsTest() {
        Command c = new CommandGetter().getCommand(PostCollectionWithPathAndParams);
        assertEquals("GET", c.getMethod().getMethod());
        assertEquals("/collections/{cid}/movies", c.getPath().getPathString());
        assertEquals(50, c.getPath().getPathInt("cid"));
        assertEquals(123, c.getParams().getParamInt("mid"));
    }

    @Test
    public void deleteCollectionsWithPathTest() {
        Command c = new CommandGetter().getCommand(DeleteCollectionsWithPath);
        assertEquals("DELETE", c.getMethod().getMethod());
        assertEquals("/collections/{cid}/movies/{mid}", c.getPath().getPathString());
        assertEquals(50, c.getPath().getPathInt("cid"));
        assertEquals(123, c.getPath().getPathInt("mid"));
        assertEquals(50, c.getPath().getPathInt("cid"));

    }
}

