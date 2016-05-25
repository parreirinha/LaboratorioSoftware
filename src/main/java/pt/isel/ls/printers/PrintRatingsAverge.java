package pt.isel.ls.printers;

import pt.isel.ls.http.ExecutionServlet;
import pt.isel.ls.linecommand.model.Command;
import pt.isel.ls.model.Movie;
import pt.isel.ls.printers.URIGenerator.URIUtils;
import pt.isel.ls.printers.html.HtmlPrinters;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Function;

/**
 * @author Tede Morgado
 *         Created at 25/05/2016
 */
public class PrintRatingsAverge implements Printable {

    private final Collection<Movie> collection;
    private final Command command;
    private final String[] head = {"Movie ID", "Name", "Release", "*", "**", "***", "****", "*****", "Average"};
    private final String link;
    private ArrayList<Function<Movie, String>> function = new ArrayList<>();

    public PrintRatingsAverge(Command command, Collection<Movie> collection, String link)
    {
        this.command = command;
        this.collection = collection;
        this.link = link;
        Function<Movie, String> f = x -> ""+x.getMovieID();
        function.add(f);
        f = x -> x.getMovieName();
        function.add(f);
        f = x -> ""+x.getMovieRelease();
        function.add(f);
        f = x -> ""+x.getOneStar();
        function.add(f);
        f = x -> ""+x.getTwoStar();
        function.add(f);
        f = x -> ""+x.getThreeStar();
        function.add(f);
        f = x -> ""+x.getFourStar();
        function.add(f);
        f = x -> ""+x.getFiveStar();
        function.add(f);
        f = x -> ""+x.getAverage();
        function.add(f);
    }

    @Override
    public String toStringText() {
        String res = "";
        for (Movie m:collection) {
            res += "Movie ID = " + m.getMovieID() +
                    "\n\tName = " + m.getMovieName() +
                    "\n\tRelease = " + m.getMovieRelease() +
                    "\n\t* = " + m.getOneStar() +
                    "   ** = " + m.getTwoStar() +
                    "   *** = " + m.getThreeStar() +
                    "   **** = " + m.getFourStar() +
                    "   ***** = " + m.getFiveStar() +
                    "\n\tAverage = " + m.getAverage() + "\n";
        }
        return res;
    }

    @Override
    public String toStringHtml()
    {
        ArrayList<String> uri = new ArrayList<>();
        collection.forEach(x -> uri.add("http://localhost:"+ ExecutionServlet.getPort()+"/movies/"+x.getMovieID()));
        String html = HtmlPrinters.htmlGenerate(collection, head, function, uri)+
                "<br>\n<br>\n"+
                getConnections();
        return String.format(HtmlPrinters.template, html);
    }

    private String getConnections()
    {
        String html = URIUtils.getURI("/tops/ratings", null, ExecutionServlet.getPort(), "Tops Ratings")+
                "<br>";
        return html;
    }
}
