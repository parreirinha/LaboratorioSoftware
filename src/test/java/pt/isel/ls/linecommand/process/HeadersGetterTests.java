package pt.isel.ls.linecommand.process;

import org.junit.Test;

import static junit.framework.Assert.assertNull;
import static org.junit.Assert.assertEquals;

/**
 * Created by Dani on 11-04-2016.
 */
public class HeadersGetterTests {
    private final String OneArgHeaders = "accept:text/html";
    private final String TwoArgsHeaders = "accept:text/plain|accept-language:en-gb";

    @Test
    public  void noHeadersArgumentTest(){
        assertNull(new HeadersGetter().getHeaders(OneArgHeaders).getHeadersString(""));
    }

    @Test
    public  void onlyOneHeadersArgumentTest(){
        assertEquals("text/html", new HeadersGetter().getHeaders(OneArgHeaders).getHeadersString("accept"));
    }

    @Test
    public  void twoHeadersArgumentsTest(){
        assertEquals("text/plain", new HeadersGetter().getHeaders(TwoArgsHeaders).getHeadersString("accept"));
        assertEquals("en-gb", new HeadersGetter().getHeaders(TwoArgsHeaders).getHeadersString("accept-language"));
    }
}
