package pt.isel.ls.printers;

import pt.isel.ls.model.Movie;
import pt.isel.ls.model.MovieCollection;

/**
 * "\nCollection id: #
 * \nCollection name: #
 * \nCollection description: #
 * \nCollection creation date: #
 * \n\nMovies in the collection:
 * \nMovie id: #\nMovie name: #
 *
 */
public class PrintGetCollectionsById implements Printable {

    private MovieCollection movieCollection;

    public  PrintGetCollectionsById(MovieCollection movieCollection){
        this.movieCollection = movieCollection;
    }


    @Override
    public String toStringText() {
        String s =
                "\nCollection id: " + movieCollection.getCollections().getCollectionID() +
                        "\nCollection name: " + movieCollection.getCollections().getName() +
                        "\nCollection description: " + movieCollection.getCollections().getDescription() +
                        "\n\nMovies in the collection:";
        for (Movie movie : movieCollection.getMovies()) {
            s += "\nMovie id: " + movie.getMovieID() + "\nMovie name: " + movie.getMovieName();
        }
        return s+"\n";
    }

    @Override
    public String toStringHtml() {
        //TODO
        return null;
    }
}
