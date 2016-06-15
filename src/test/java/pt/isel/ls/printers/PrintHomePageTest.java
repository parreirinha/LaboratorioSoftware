package pt.isel.ls.printers;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PrintHomePageTest {

    PrintHomePage printHomePage = new PrintHomePage();


    @Test
    public void printHomePageTest(){

        String res = printHomePage.toStringHtml();
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
                        "\t\t\t<a href=/collections?skip=0&top=5>Collections</a>\n" +
                        "\t\t\t<br>\n" +
                        "\t\t\t<a href=/movies?skip=0&top=5>Movies</a>\n" +
                        "\t\t\t<br>\n" +
                        "\t\t\t<a href=/tops/ratings?skip=0&top=5>Top Ratings</a>\n" +
                        "\t\t\t<br>\n" +
                        "\n" +
                        "\t\t</body>\n" +
                        "\t</html>";
        assertEquals(expected, res);
    }
}
