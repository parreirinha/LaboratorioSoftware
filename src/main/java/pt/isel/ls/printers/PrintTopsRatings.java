package pt.isel.ls.printers;

import static pt.isel.ls.printers.URIGenerator.URICreator.getURI;

/**
 * Created by fabio on 19-May-16.
 */
public class PrintTopsRatings implements Printable {

    private String topRatHigAvg;
    private String topRatLowAvg;
    private String topRevHigCount;
    private String topRevLowCount;
    private String movies;
    private String home;
    private int port;

    public PrintTopsRatings(int port){this.port=port;}

    @Override
    public String toStringText() {
        return null;
    }

    @Override
    public String toStringHtml() {

        topRatHigAvg = getURI("/top/n/ratings/higher/average", "skip=0&top=5", port, "Top higher average");  ;
        topRatLowAvg = getURI("/top/n/ratings/lower/average", "skip=0&top=5", port, "Top lower average")  ;
        topRevHigCount = getURI("/top/n/reviews/higher/count", "skip=0&top=5", port, "Top reviews higher count");
        topRevLowCount = getURI("/top/n/reviews/lower/count", "skip=0&top=5", port, "Top reviews lower count");
        movies = getURI("/movies", "skip=0&top=5", port, "Movies");
        home = getURI("/", "", port, "HOME");   //todo verificar caminho home
        return topRatHigAvg+"\n"+topRatLowAvg+"\n"+topRevHigCount+"\n"+topRevLowCount+"\n"+movies+"\n"+home;
    }
}
