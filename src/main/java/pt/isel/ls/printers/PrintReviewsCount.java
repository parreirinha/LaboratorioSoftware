package pt.isel.ls.printers;

import pt.isel.ls.http.ExecutionServlet;
import pt.isel.ls.linecommand.model.Command;
import pt.isel.ls.model.Movie;
import pt.isel.ls.printers.URIGenerator.URIUtils;
import pt.isel.ls.printers.html.HtmlPrinters;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.function.Function;

/**
 * @author Tede Morgado
 *         Created at 25/05/2016
 */
public class PrintReviewsCount implements Printable
{

    private final String link;
    private final Collection<Movie> collection;
    private final Command command;
    private final String[] head = {"Movie ID", "Name", "Release"};
    private ArrayList<Function<Movie, String>> functions = new ArrayList<>();

    public PrintReviewsCount(Command command, Collection<Movie> collection, String link)
    {
        this.command = command;
        this.collection = collection;
        this.link = link;
        functions.add(x -> ""+x.getMovieID());
        functions.add(x -> x.getMovieName());
        functions.add(x -> ""+x.getMovieRelease());
    }
    @Override
    public String toStringText() {
        String res = "";

        for (Movie m :collection) {
            res += "Movie ID = "+m.getMovieID()+"\n\tName = "+m.getMovieName()+"\tRelease = "+m.getMovieRelease()+"\n";
        }

        return res;
    }

    @Override
    public String toStringHtml() {
        ArrayList<String> uri = new ArrayList<>();
        collection.forEach(x -> uri.add("http://localhost:"+ ExecutionServlet.getPort()+"/movies/"+x.getMovieID()));
        String html = HtmlPrinters.htmlGenerate(collection, head, functions, uri)+
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
