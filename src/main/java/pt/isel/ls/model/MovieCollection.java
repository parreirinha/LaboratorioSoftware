package pt.isel.ls.model;

/**
 * Created by fabio on 17-Apr-16.
 */
public class MovieCollection {

    private Movie[] movies;
    private Collections collections;


    public MovieCollection(Movie[] movies, Collections collections){

        this.movies = movies;
        this.collections = collections;
    }


    public Movie[] getMovies() {
        return movies;
    }

    public Collections getCollections() {
        return collections;
    }
}
