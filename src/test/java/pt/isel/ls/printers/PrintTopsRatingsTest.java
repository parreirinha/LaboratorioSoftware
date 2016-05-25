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
                "<p><a href=http://localhost:8080>HOME</a></p><p><a href=http://localhost:8080/movies/?skip=0&top=5>Movies</a>\n" +
                "</p><p><a href=http://localhost:8080/tops/5/ratings/higher/average/?skip=0&top=5>Top ratings higher average</a>\n" +
                "</p><p><a href=http://localhost:8080/tops/5/ratings/lower/average/?skip=0&top=5>Top ratings lower average</a>\n" +
                "</p><p><a href=http://localhost:8080/tops/5/reviews/higher/count/?skip=0&top=5>Top reviews higher count</a>\n" +
                "</p><p><a href=http://localhost:8080/tops/5/reviews/lower/count/?skip=0&top=5>Top reviews lower count</a></p>";
        String res = printTopsRatings.toStringHtml();

        assertEquals(expected, res);

    }
}
