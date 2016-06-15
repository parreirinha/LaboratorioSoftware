package pt.isel.ls.linecommand.process;

import org.junit.Test;

import static junit.framework.Assert.assertNull;
import static org.junit.Assert.assertEquals;

/**
 * Class used to test HeadersGetter getHeaders method.
 */
public class HeadersGetterTests {
    private final String OneArgHeaders = "accept:text/html";
    private final String TwoArgsHeaders = "accept:text/plain|accept-language:en-gb";
    private final String FileNameHeader = "file-name:newfilename";

    @Test
    public void filenameHeadersTest() {
        assertEquals("newfilename", new HeadersGetter().getHeaders(FileNameHeader).getHeadersString("file-name"));
    }

    @Test
    public void noHeadersArgumentTest() {
        assertNull(new HeadersGetter().getHeaders(OneArgHeaders).getHeadersString(""));
    }

    @Test
    public void onlyOneHeadersArgumentTest() {
        assertEquals("text/html", new HeadersGetter().getHeaders(OneArgHeaders).getHeadersString("accept"));
    }

    @Test
    public void twoHeadersArgumentsTest() {
        assertEquals("text/plain", new HeadersGetter().getHeaders(TwoArgsHeaders).getHeadersString("accept"));
        assertEquals("en-gb", new HeadersGetter().getHeaders(TwoArgsHeaders).getHeadersString("accept-language"));
    }
}
