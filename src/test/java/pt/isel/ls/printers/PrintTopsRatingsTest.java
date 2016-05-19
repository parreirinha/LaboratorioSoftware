package pt.isel.ls.printers;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pt.isel.ls.printers.PrintTopsRatings;

import static org.junit.Assert.assertEquals;

public class PrintTopsRatingsTest {

    PrintTopsRatings printTopsRatings = new PrintTopsRatings(8080);

    @Test
    public void testPrintTopsRatingsTest(){

        String expected =
                "<a href=http://localhost:8080/top/n/ratings/higher/average/?skip=0&top=5>Top higher average</a>\n" +
                "<a href=http://localhost:8080/top/n/ratings/lower/average/?skip=0&top=5>Top lower average</a>\n" +
                "<a href=http://localhost:8080/top/n/reviews/higher/count/?skip=0&top=5>Top reviews higher count</a>\n" +
                "<a href=http://localhost:8080/top/n/reviews/lower/count/?skip=0&top=5>Top reviews lower count</a>\n" +
                "<a href=http://localhost:8080/movies/?skip=0&top=5>Movies</a>\n" +
                "<a href=http://localhost:8080>HOME</a>";
        String res = printTopsRatings.toStringHtml();

        assertEquals(expected, res);

    }
}
