package pt.isel.ls.printers;

import pt.isel.ls.model.Movie;
import pt.isel.ls.model.MovieCollection;

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
 *
 */
public class PrintGetCollectionsById implements Printable {

    private MovieCollection movieCollection;
    String[] head = {"Collection id", "Collection name", "Collection description", "Movie id", "Movie name"};

    public  PrintGetCollectionsById(MovieCollection movieCollection){
        this.movieCollection = movieCollection;

    }


    @Override
    public String toStringText() {
        String s =
                head[0] + ": " + movieCollection.getCollections().getCollectionID() +
                "\n"+ head[1] + ": " + movieCollection.getCollections().getName() +
                "\n"+head[2]+": " + movieCollection.getCollections().getDescription() +
                "\n\nMovies in the collection:";
        for (Movie movie : movieCollection.getMovies()) {
            s += "\n"+head[3]+": " + movie.getMovieID() + "\t"+head[4]+" " + movie.getMovieName();
        }
        return s+"\n";
    }

    @Override
    public String toStringHtml() {
        //TODO
        return null;
    }

}
