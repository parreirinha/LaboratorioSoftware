package pt.isel.ls.database.printers;

import pt.isel.ls.model.Movie;

/**
 * Created by fabio on 05-Apr-16.
 */
public class PrintGetMovies implements Printable {


    private Movie movie = null;


    public PrintGetMovies(Movie movie){
        this.movie = movie;
    }


    /**
     * String template to be returned:
     * "movieid = ##; movie name = ##; releaseyear = ##;\n*#;**#;***#;****#;*****#.\n"
     * @return
     */
    @Override
    public String toStringResult() {
        return  (movie == null) ? "something went wrong!!\n" :
                "movieid = "+movie.getMovieID() +
                "; movie name = "+movie.movieName +
                "; releaseyear = "+movie.getMovieRelease()+
                ";\n*" + movie.getOneStar() +
                ";**" + movie.getTwoStar()+
                ";***" + movie.getTreeStar()+
                ";****" + movie.getFourStar() +
                ";*****" + movie.getFiveStar() +
                ".\n";
    }
}
