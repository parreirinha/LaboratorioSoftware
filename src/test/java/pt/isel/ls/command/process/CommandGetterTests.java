package pt.isel.ls.command.process;

import org.junit.Assert;
import org.junit.Test;
import pt.isel.ls.command.model.Command;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

/**
 * Class used to test CommandGetter getCommand method.
 */
public class CommandGetterTests {

    private final String[] getWithMethod = {"GET"};
    private final String[] getWithMethodAndPath = {"GET", "/movies/123"};
    private final String[] getWithMethodAndPathAndParams = {"GET", "/movies", "title=pulp+fiction&releaseYear=1994"};
    private final String[] postWithMethod = {"POST"};
    private final String[] postWithMethodAndPath = {"POST", "/movies"};
    private final String[] posttWithMethodAndPathAndParams = {"POST", "/movies", "title=pulp+fiction&releaseYear=1994"};
    private final String[] nonExistingMethod = {"CLEAN"};

    @Test
    public void unknownMethodTest() {
        Assert.assertNull(new CommandGetter().getCommand(nonExistingMethod));
    }

    @Test
    public void tooMuchArgsGetTest() {
        Assert.assertNull(new CommandGetter().getCommand(getWithMethodAndPathAndParams));
    }

    @Test
    public void notEnoughArgsGetTest() {
        Assert.assertNull(new CommandGetter().getCommand(getWithMethod));
    }

    @Test
    public void returnGetCommandMethodTest() {
        Command c = new CommandGetter().getCommand(getWithMethodAndPath);
        assertEquals("GET", c.getMethod().getMethod());
    }

    @Test
    public void returnGetCommandPathStringTest() {
        Command c = new CommandGetter().getCommand(getWithMethodAndPath);
        assertEquals("moviesmid", c.getPath().getPathString());
    }

    @Test
    public void returnGetCommandPathMidTest() {
        Command c = new CommandGetter().getCommand(getWithMethodAndPath);
        assertEquals(123, (int)c.getPath().getPathInt("mid"));
    }

    @Test
    public void returnGetCommandParamsTest() {
        Command c = new CommandGetter().getCommand(getWithMethodAndPathAndParams);
        assertNull(c);
    }

    @Test
    public void notEnoughArgsPostTest1() {
        Assert.assertNull(new CommandGetter().getCommand(postWithMethod));
    }

    @Test
    public void notEnoughArgsPostTest2() {
        Assert.assertNull(new CommandGetter().getCommand(postWithMethodAndPath));
    }

    @Test
    public void returnPostCommandMethodTest() {
        Command c = new CommandGetter().getCommand(posttWithMethodAndPathAndParams);
        assertEquals("POST" ,c.getMethod().getMethod());
    }

    @Test
    public void returnPostCommandPathStringTest() {
        Command c = new CommandGetter().getCommand(posttWithMethodAndPathAndParams);
        assertEquals("movies" ,c.getPath().getPathString());
    }

    @Test
    public void returnPostCommandParamsStringTest() {
        Command c = new CommandGetter().getCommand(posttWithMethodAndPathAndParams);

        assertEquals("pulp fiction" ,c.getParams().getParamString("title"));
    }

    @Test
    public void returnPostCommandParamsIntTest() {
        Command c = new CommandGetter().getCommand(posttWithMethodAndPathAndParams);

        assertEquals(1994 ,(int)c.getParams().getParamInt("releaseYear"));
    }
}