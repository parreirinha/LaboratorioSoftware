package pt.isel.ls.printers;

import static pt.isel.ls.printers.URIGenerator.URIUtils.getURI;

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

        topRatHigAvg = getURI("/tops/5/ratings/higher/average/", "skip=0&top=5", port, "Top ratings higher average");  ;
        topRatLowAvg = getURI("/tops/5/ratings/lower/average/", "skip=0&top=5", port, "Top ratings lower average")  ;
        topRevHigCount = getURI("/tops/5/reviews/higher/count/", "skip=0&top=5", port, "Top reviews higher count");
        topRevLowCount = getURI("/tops/5/reviews/lower/count/", "skip=0&top=5", port, "Top reviews lower count");//todo comando nao existe!! nao posso fazer sorting em tops PS: está repetido o de cima
        movies = getURI("/movies/", "skip=0&top=5", port, "Movies");
        home = getURI("", "", port, "HOME");

        return  "<p>"+home+"</p>" +
                "<p>"+movies+"\n"+"</p>" +
                "<p>"+topRatHigAvg+"\n"+ "</p>" +
                "<p>"+topRatLowAvg+"\n" + "</p>" +
                "<p>"+topRevHigCount+"\n"+"</p>" +
                "<p>"+topRevLowCount+"</p>";
    }
}
