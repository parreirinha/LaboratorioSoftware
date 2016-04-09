package pt.isel.ls.database.printers;

import pt.isel.ls.model.Movie;

/**
 * Created by fabio on 05-Apr-16.
 */
public class PrintGetMovieRating implements Printable {

    private Movie movie = null;

    public PrintGetMovieRating(Movie m){
        movie = m;
    }


    /**
     * for tests template of string. #->replace by values
     * "the average rating for the movie with the id ## is ##.\n
     * *##;**##;***##;****##;*****##\n"
     * @return
     */
    @Override
    public String toStringResult() {
        return (movie == null) ? "something went wrong!!\n" :
                "the average rating for the movie with the id " + movie.getMovieID() +
                " is " +movie.getAverage()+ ".\n" +
                "*" + movie.getOneStar() +";**" + movie.getTwoStar() + ";***" + movie.getTreeStar() +
                ";****" + movie.getFourStar() + ";*****" + movie.getFiveStar() + "\n";
    }
}
