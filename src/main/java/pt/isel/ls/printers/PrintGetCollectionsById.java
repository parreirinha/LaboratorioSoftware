package pt.isel.ls.printers;

import pt.isel.ls.linecommand.model.Command;
import pt.isel.ls.model.Movie;
import pt.isel.ls.model.MovieCollection;
import pt.isel.ls.printers.URIGenerator.URIUtils;
import pt.isel.ls.printers.html.HtmlGenerator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Function;

/**
 * "\nCollection id: #
 * \nCollection name: #
 * \nCollection description: #
 * \nCollection creation date: #
 * \n\nMovies in the collection:
 * \nMovie id: #\nMovie name: #
 */
public class PrintGetCollectionsById implements Printable {

    private final Command command;
    private MovieCollection movieCollection;
    String[] head = {"Collection id", "Collection name", "Collection description", "Movie id", "Movie name"};
    ArrayList<Function<MovieCollection, String>> func = new ArrayList<>();
    ArrayList<Function<Movie, String>> func1 = new ArrayList<>();

    public PrintGetCollectionsById(MovieCollection movieCollection, Command command) {
        this.command = command;
        this.movieCollection = movieCollection;
        Function<MovieCollection, String> f = mc -> ""+mc.getCollections().getCollectionID();
        func.add(f);
        f = mc -> mc.getCollections().getName();
        func.add(f);
        f = mc -> mc.getCollections().getDescription();
        func.add(f);
        Function<Movie, String> f1 = m -> "" + m.getMovieID();
        func1.add(f1);
        f1 = m -> m.getMovieName();
        func1.add(f1);
    }


    @Override
    public String toStringText() {
        String s =
                head[0] + ": " + func.get(0).apply(movieCollection) +
                        "\n" + head[1] + ": " + func.get(1).apply(movieCollection) +
                        "\n" + head[2] + ": " + func.get(2).apply(movieCollection) +
                        "\n\nMovies in the collection:";
        for (Movie movie : movieCollection.getMovies()) {
            s += "\n" + head[3] + ": " + func1.get(0).apply(movie) + "\t" + head[4] + ": " + func1.get(1).apply(movie);
        }
        return s + "\n";
    }

    @Override
    public String toStringHtml()
    {
        Collection<MovieCollection> mc = new ArrayList<>();
        mc.add(movieCollection);
        ArrayList<String> uri = new ArrayList<>();
        movieCollection.getMovies().forEach(x -> uri.add("/movies/"+x.getMovieID()));
        ArrayList<String> menu = new ArrayList<>();
        menu.add(URIUtils.getURI("/", null, "Home Page"));
        menu.add(URIUtils.getURI("/collections",
                "top="+command.getParams().getParamInt("top")+"&skip=0",
                "All Collections"));
        HtmlGenerator htmlString = new HtmlGenerator();
        htmlString
                .createMenu(menu)
                .htmlGenerate(mc, movieCollection.getMovies(),head, func, func1, uri)
                .addBrTag()
                .postMovieIntoCollection(movieCollection.getCollections().getCollectionID());
        return String.format(htmlString.getTemplate(), htmlString.toString());
    }

}
