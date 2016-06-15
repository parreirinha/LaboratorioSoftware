package pt.isel.ls.printers;

import pt.isel.ls.printers.html.HtmlGenerator;

import static pt.isel.ls.printers.URIGenerator.URIUtils.getURI;

/**
 * Class used to print the get tops http page.
 */
public class PrintTopsRatings implements Printable {

    public PrintTopsRatings() {
    }

    @Override
    public String toStringText() {
        return null;
    }

    @Override
    public String toStringHtml() {
        HtmlGenerator htmlString = new HtmlGenerator()
                .addLink(getURI("/", "", "HOME"))
                .addLink(getURI("/movies/", "skip=0&top=5", "Movies"))
                .addLink(getURI("/tops/5/ratings/lower/average/", "skip=0&top=5", "Top ratings lower average"))
                .addLink(getURI("/tops/5/ratings/higher/average/", "skip=0&top=5", "Top ratings higher average"))
                .addLink(getURI("/tops/5/reviews/lower/count/", "skip=0&top=5", "Top reviews lower count"))
                .addLink(getURI("/tops/5/reviews/higher/count/", "skip=0&top=5", "Top reviews higher count"));
        return String.format(htmlString.getTemplate(), htmlString.toString());
    }
}
