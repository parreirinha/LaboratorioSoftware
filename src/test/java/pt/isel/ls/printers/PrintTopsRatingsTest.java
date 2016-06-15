package pt.isel.ls.printers;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pt.isel.ls.printers.PrintTopsRatings;

import static org.junit.Assert.assertEquals;

public class PrintTopsRatingsTest {

    PrintTopsRatings printTopsRatings = new PrintTopsRatings();

    @Test
    public void testPrintTopsRatingsTest() {

        String expected =
                "<!DOCTYPE>\n" +
                        "\t<html>\n" +
                        "\t\t<head>\n" +
                        "\t\t\t<meta\n" +
                        "\t\t\t\thttp-equiv=\"Content-Type\"\n" +
                        "\t\t\t\tcontent=\"text/html; charset=utf-8\"\n" +
                        "\t\t\t/>\n" +
                        "\t\t</head>\n" +
                        "\n" +
                        "\t\t<body>\n" +
                        "\t\t\t<a href=/>HOME</a>\n" +
                        "\t\t\t<br>\n" +
                        "\t\t\t<a href=/movies/?skip=0&top=5>Movies</a>\n" +
                        "\t\t\t<br>\n" +
                        "\t\t\t<a href=/tops/5/ratings/lower/average/?skip=0&top=5>Top ratings lower average</a>\n" +
                        "\t\t\t<br>\n" +
                        "\t\t\t<a href=/tops/5/ratings/higher/average/?skip=0&top=5>Top ratings higher average</a>\n" +
                        "\t\t\t<br>\n" +
                        "\t\t\t<a href=/tops/5/reviews/lower/count/?skip=0&top=5>Top reviews lower count</a>\n" +
                        "\t\t\t<br>\n" +
                        "\t\t\t<a href=/tops/5/reviews/higher/count/?skip=0&top=5>Top reviews higher count</a>\n" +
                        "\t\t\t<br>\n" +
                        "\n" +
                        "\t\t</body>\n" +
                        "\t</html>";
        String res = printTopsRatings.toStringHtml();

        assertEquals(expected, res);

    }
}
