package pt.isel.ls.printers;

import static pt.isel.ls.printers.URIGenerator.URICreator.getURI;

/**
 * Created by Dani on 18-05-2016.
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

        return "<p>" + colUri + "\n" + moviesUri + "\n" + topsUri + "</p>";
    }
}
