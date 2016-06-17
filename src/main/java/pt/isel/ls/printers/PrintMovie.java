package pt.isel.ls.printers;

import pt.isel.ls.linecommand.model.Command;
import pt.isel.ls.model.Movie;
import pt.isel.ls.printers.URIGenerator.URIUtils;
import pt.isel.ls.printers.html.HtmlGenerator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Function;

/**
* class used to print a Movie.
* */
public class PrintMovie implements Printable {

    private final Command command;
    private Collection<Movie> movieCollection;
    private final String[] head =
            {"Movie ID", "Name", "Release"};
    private ArrayList<Function<Movie, String>> function = new ArrayList<>();
    private final String NoMovie = "There are no movies.\n";


    public PrintMovie(Collection<Movie> movies, Command command) {
        this.command = command;
        movieCollection = movies;
        function.add(movie -> "" + movie.getMovieID());
        function.add(movie -> movie.getMovieName());
        function.add(movie -> "" + movie.getMovieRelease());
    }

    /**
     * String template to be returned:
     * "Movie ID = ##\n\tName = ##\tRelease = ##\n"
     *
     * @return
     */
    @Override
    public String toStringText() {
        String s = "";
        for (Movie m : movieCollection) {
            s += head[0] + " = " + function.get(0).apply(m) +
                    "\n\t" + head[1] + " = " + function.get(1).apply(m) +
                    "\t" + head[2] + " = " + function.get(2).apply(m) +
                    "\n";
        }
        return (s == "") ? new PrintError(NoMovie).toStringText() : s;
    }

    @Override
    public String toStringHtml() {
        HtmlGenerator htmlString = new HtmlGenerator();
        ArrayList<String> menu = new ArrayList<>();
        menu.add(URIUtils.getURI("/", null, "Home Page"));
        menu.add(URIUtils.getURI("/tops/ratings", null, "Tops Ratings"));
        if (movieCollection.isEmpty())
            return String.format(htmlString.getTemplate(), htmlString.addString(NoMovie)
                    .postNewMovie()
                    .toString());
        ArrayList<String> uri = new ArrayList<>();
        movieCollection.forEach(x -> uri.add("/movies/" + x.getMovieID()));
        htmlString
                .createMenu(menu)
                .createOrdering(command, "/movies")
                .addBrTag()
                .htmlGenerate(movieCollection, head, function, uri)
                .createPagging(command, "/movies")
                .addBrTag()
                .postNewMovie();

        return String.format(htmlString.getTemplate(), htmlString.toString());
    }

}
