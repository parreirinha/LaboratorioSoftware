package pt.isel.ls.printers;

import pt.isel.ls.printers.html.HtmlGenerator;
import static pt.isel.ls.printers.URIGenerator.URIUtils.getURI;

/**
 * Class used to visualize the application home page.
 */
public class PrintHomePage implements Printable {

    public PrintHomePage(){}

    @Override
    public String toStringText() {
        return null;  //não é chamado
    }

    @Override
    public String toStringHtml()
    {
        HtmlGenerator htmlString = new HtmlGenerator();
        htmlString
                .addLink(getURI("/collections", "skip=0&top=5", "Collections"))
                .addLink(getURI("/movies", "skip=0&top=5", "Movies"))
                .addLink(getURI("/tops/ratings","skip=0&top=5", "Top Ratings"));
        return String.format(htmlString.getTemplate(), htmlString.toString());

    }
}
