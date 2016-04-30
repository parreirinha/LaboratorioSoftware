package pt.isel.ls.model;

import java.util.ArrayList;

/**
 * Created by fabio on 17-Apr-16.
 */
public class MovieCollection {

    private ArrayList<Movie> movies;
    private Collections collections;


    public MovieCollection(ArrayList<Movie> movies, Collections collections) {

        this.movies = movies;
        this.collections = collections;
    }


    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public Collections getCollections() {
        return collections;
    }
}
