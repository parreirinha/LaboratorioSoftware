package pt.isel.ls.printers;

import pt.isel.ls.model.Movie;
import pt.isel.ls.model.MovieCollection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * "\nCollection id: #
 * \nCollection name: #
 * \nCollection description: #
 * \nCollection creation date: #
 * \n\nMovies in the collection:
 * \nMovie id: #\nMovie name: #
 */
public class PrintGetCollectionsById implements Printable {

    private MovieCollection movieCollection;
    String[] head = {"Collection id", "Collection name", "Collection description", "Movie id", "Movie name"};
    ArrayList<Function<MovieCollection, String>> func = new ArrayList<>();
    ArrayList<Function<Movie, String>> func1 = new ArrayList<>();
    public PrintGetCollectionsById(MovieCollection movieCollection) {
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
        return HtmlGenerator.htmlGenerate(mc, movieCollection.getMovies(),head, func, func1);

        /*
        if (movieCollection.getMovies().size() == 1)
            return String.format(HtmlGenerator.template, getText());
        return String.format(HtmlGenerator.template, getTable());
        */
    }
/*
    private String getTable() {
        String str = "<table border=\"1\" style=\"width:100%\">\n" +
                "\t" + getFullHtmlTitle() + "\n";
        str += "\t" + getFullHtmlDescription() + "\n";
        str += "</table>";
        return str;
    }

    private String getText() {
        String str = "<ul><li>" + head[0] + ": " + movieCollection.getCollections().getCollectionID() + "</li>\n" +
                "<ul>\n";
        str += "<li>" + head[1] + ": " + movieCollection.getCollections().getName() + "</li>\n";
        str += "<li>" + head[2] + ": " + movieCollection.getCollections().getDescription() + "</li>\n" +
                "<li>Movies in the collection:</li>" +
                "<ul>\n";
        for (Movie movie : movieCollection.getMovies()) {
            str += "<li>" + head[3] + ": " + movie.getMovieID() +
                    "\t" + head[4] + ": " + movie.getMovieName() + "</li>\n";
        }
        str += "</ul>";
        str += "</ul>\n" +
                "</ul>\n";
        return str;
    }

    private String getFullHtmlDescription() {
        String str = "<tr>\n" +
                "<td>" + movieCollection.getCollections().getCollectionID() + "</td>\n" +
                "<td>" + movieCollection.getCollections().getName() + "</td>\n" +
                "<td>" + movieCollection.getCollections().getDescription() + "</td>\n";
        int aux = 0;
        for (Movie m : movieCollection.getMovies()) {
            if (aux > 0) {
                str += "<tr>\n<td></td>\n<td></td>\n<td></td>\n";
        }
            str += "<td>" + m.getMovieID() + "</td>\n" +
                    "<td>" + m.getMovieName() + "</td>\n";
            ++aux;
            str += "</tr>\n";
        }

        return str + "</tr>\n";
    }

    private String getFullHtmlTitle() {
        String str = "<tr>\n";
        for (int i = 0; i < head.length; ++i)
            str += "\t\t<td>" + head[i] + "</td>\n";
        return str + "</tr>\n";
    }
*/

}
