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
public class PrintRatingsAverage implements Printable {

    private final Collection<Movie> collection;
    private final Command command;
    private final String[] head = {"Movie ID", "Name", "Release", "*", "**", "***", "****", "*****", "Average"};
    private final String link;
    private ArrayList<Function<Movie, String>> function = new ArrayList<>();

    public PrintRatingsAverage(Command command, Collection<Movie> collection, String link)
    {
        this.command = command;
        this.collection = collection;
        this.link = link;
        function.add(x -> ""+x.getMovieID());
        function.add(x -> x.getMovieName());
        function.add(x -> ""+x.getMovieRelease());
        function.add(x -> ""+x.getOneStar());
        function.add(x -> ""+x.getTwoStar());
        function.add(x -> ""+x.getThreeStar());
        function.add(x -> ""+x.getFourStar());
        function.add(x -> ""+x.getFiveStar());
        function.add(x -> ""+x.getAverage());
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
        HtmlGenerator htmlString = new HtmlGenerator();
        ArrayList<String> uri = new ArrayList<>();
        collection.forEach(x -> uri.add("/movies/"+x.getMovieID()));
        ArrayList<String> menu = new ArrayList<>();
        menu.add(URIUtils.getURI("/", null, "Home"));
        menu.add(URIUtils.getURI("/tops/ratings/", null, "Tops Ratings"));

        htmlString
                .createMenu(menu)
                .htmlGenerate(collection, head, function, uri);
        return String.format(htmlString.getTemplate(), htmlString.toString());
    }

}
