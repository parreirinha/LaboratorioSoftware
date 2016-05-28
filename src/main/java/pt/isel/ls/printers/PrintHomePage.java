package pt.isel.ls.printers;

import static pt.isel.ls.printers.URIGenerator.URIUtils.getURI;

/**
 * Class used to visualize the application home page.
 */
public class PrintHomePage implements Printable {

    private int port;
    private String colUri;
    private String moviesUri;
    private String topsUri;

    public PrintHomePage(int port){this.port = port;}

    @Override
    public String toStringText() {
        return null;  //não é chamado
    }

    @Override
    public String toStringHtml() {

        colUri =    getURI("/collections/", "skip=0&top=5", port, "Collections");
        moviesUri = getURI("/movies/", "skip=0&top=5", port, "Movies");
        topsUri =   getURI("/tops/ratings/","skip=0&top=5",port, "Top Ratings");

        return "<p>" + colUri + "\n"+ "</p>" + moviesUri + "\n" + "<p>" + topsUri + "</p>";
    }
}
