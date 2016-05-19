package pt.isel.ls.printers;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PrintHomePageTest {

    PrintHomePage printHomePage = new PrintHomePage(8000);


    @Test
    public void printHomePageTest(){

        String res = printHomePage.toStringHtml();
        String expected =
                "<a href=http://localhost:8000/collections/?skip=0&top=5>Collections</a>\n" +
                "<a href=http://localhost:8000/movies/?skip=0&top=5>Movies</a>\n" +
                "<a href=http://localhost:8000/tops/ratings/?skip=0&top=5>Top Ratings</a>";
        assertEquals(expected, res);
    }
}
