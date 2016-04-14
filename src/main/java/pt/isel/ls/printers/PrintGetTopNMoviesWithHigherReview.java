package pt.isel.ls.printers;

import pt.isel.ls.model.Movie;

import java.util.Collection;

/**
 * @author Tede Morgado
 *         Created at 06/04/2016
 */
public class PrintGetTopNMoviesWithHigherReview implements Printable
{

    private Collection<Movie> movieCollection;

    public PrintGetTopNMoviesWithHigherReview(Collection<Movie> movies){
        movieCollection = movies;
    }

    @Override
    public String toStringResult()
    {
        String str = "";
        for (Movie m : movieCollection)
        {
            str += "Name: " + m.getMovieName() + "\tYear: " + m.getMovieRelease() + "\n";
        }
        return str;
    }
}
