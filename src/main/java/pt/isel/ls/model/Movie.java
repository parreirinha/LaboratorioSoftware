package pt.isel.ls.model;

/**
 * Class whose instances are used to represent a model of a movie.
 */
public class Movie
{
    private final int movieReleaseYear;
    private final String movieTitle;


    public Movie(String title, int releaseYear){
        this.movieTitle = title;
        this.movieReleaseYear = releaseYear;

    }

    public int getMovieReleaseYear() {
        return movieReleaseYear;
    }

    public String getMovieTitle() {
        return movieTitle;
    }
}
