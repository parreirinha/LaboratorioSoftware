package pt.isel.ls.model;

/**
 * Class whose instances are used to represent a model of a rating,
 * i.e. an object with only one int field - the rating.
 */
public class Rating {

    private final int rating;
    private final int movieId;

    public Rating(int rating, int movieId){
        this.rating = rating;
        this.movieId = movieId;
    }


    public int getMovieId() {
        return movieId;
    }

    public int getRating() {
        return rating;
    }
}
