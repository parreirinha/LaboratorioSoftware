package pt.isel.ls.printers;

import pt.isel.ls.linecommand.model.Command;
import pt.isel.ls.model.Movie;
import pt.isel.ls.printers.URIGenerator.URIUtils;
import pt.isel.ls.printers.html.HtmlGenerator;

import java.util.ArrayList;
import java.util.Collection;
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
    public String toStringHtml()
    {
        HtmlGenerator htmlString = new HtmlGenerator();
        ArrayList<String> uri = new ArrayList<>();
        collection.forEach(x -> uri.add("/movies/"+x.getMovieID()));
        htmlString
                .htmlGenerate(collection, head, functions, uri)
                .addBrTag()
                .addBrTag()
                .addLink(URIUtils.getURI("/tops/ratings", null, "Tops Ratings"))
                .addLink(URIUtils.getURI("/", null, "Home"));
        return String.format(htmlString.getTemplate(), htmlString.toString());
    }

}
