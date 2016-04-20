package pt.isel.ls.printers;

import pt.isel.ls.model.Movie;
import pt.isel.ls.model.MovieCollection;

/**
 * "\nCollection id: #
 * \nCollection name: #
 * \nCollection description: #
 * \nCollection creation date: #
 * \n\nMovies in the collection:
 * \nMovie id: #\nMovie name: #\nDate of insertion in the collection: #
 *
 */
public class PrintGetCollectionsById implements Printable {

    private MovieCollection movieCollection;

    public  PrintGetCollectionsById(MovieCollection movieCollection){
        this.movieCollection = movieCollection;
    }


    @Override
    public String toStringResult() {

        String s =
                "\nCollection id: " + movieCollection.getCollections().getCollectionID() +
                "\nCollection name: " + movieCollection.getCollections().getName() +
                "\nCollection description: " + movieCollection.getCollections().getDescription() +
                "\nCollection creation date: " + movieCollection.getCollections().getCreationDate() +
                "\n\nMovies in the collection:";
        for (Movie movie : movieCollection.getMovies()) {
            s +=
                "\nMovie id: " + movie.getMovieID() +
                "\nMovie name: " + movie.getMovieName() +
                "\nDate of insertion in the collection: " + movie.getDate();
        }
        return s+"\n";
    }
}
