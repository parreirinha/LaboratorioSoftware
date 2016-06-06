package pt.isel.ls.printers;

import pt.isel.ls.linecommand.model.Command;
import pt.isel.ls.model.Movie;
import pt.isel.ls.printers.URIGenerator.URIUtils;
import pt.isel.ls.printers.html.HtmlGenerator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Function;

/**
 * Created by fabio on 05-Apr-16.
 */
public class PrintMovieRating implements Printable {

    private final Command command;
    private Collection<Movie> movie;
    private final String[] head =
            {"The average rating for the movie is ",
                    "Movie ID ", "*", "**", "***", "****", "*****"};
    private ArrayList<Function<Movie, String>> function = new ArrayList<>();
    private final String NoMovie = "There is no such movie.\n";

    public PrintMovieRating(Collection<Movie> m, Command command) {
        this.command = command;
        movie = m;
        function.add(movie -> "" + movie.getAverage());
        function.add(movie -> "" + movie.getMovieID());
        function.add(movie -> "" + movie.getOneStar());
        function.add(movie -> "" + movie.getTwoStar());
        function.add(movie -> "" + movie.getThreeStar());
        function.add(movie -> "" + movie.getFourStar());
        function.add(movie -> "" + movie.getFiveStar());
    }


    /**
     * for tests template of string. #->replace by values
     * "The average rating for the movie with the ID ## is ##.\n\t* = ##   ** = ##   *** = ##   **** = ##   ***** = ##\n"
     *
     * @return
     */
    @Override
    public String toStringText() {
        String str = "";
        for (Movie m : movie)
            str += head[0] + " " + function.get(0).apply(m) +
                    " " + head[1] + " " + function.get(1).apply(m) + ".\n" +
                    "\t" + head[2] + " = " + function.get(2).apply(m) +
                    "   " + head[3] + " = " + function.get(3).apply(m) +
                    "   " + head[4] + " = " + function.get(4).apply(m) +
                    "   " + head[5] + " = " + function.get(5).apply(m) +
                    "   " + head[6] + " = " + function.get(6).apply(m) + "\n";
        return (str == "") ? new PrintError(NoMovie).toStringText() : str;
    }

    @Override
    public String toStringHtml()
    {
        HtmlGenerator htmlString = new HtmlGenerator();
        if (movie.isEmpty())
            return String.format(htmlString.getTemplate(), htmlString.addString(NoMovie).toString());

        ArrayList<String> uri = new ArrayList<>();
        movie.forEach(x -> uri.add("/movies/"+x.getMovieID()));
        ArrayList<String> menu = new ArrayList<>();
        menu.add(URIUtils.getURI("/", null, "Home"));
        htmlString
                .createMenu(menu)
                .htmlGenerate(movie, head, function, uri)
                .addBrTag()
                .postRatingMovie(movie.iterator().next().getMovieID());
        return String.format(htmlString.getTemplate(), htmlString.toString());
    }
}
