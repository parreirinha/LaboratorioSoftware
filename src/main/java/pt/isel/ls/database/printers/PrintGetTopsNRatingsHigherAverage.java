package pt.isel.ls.database.printers;

import pt.isel.ls.model.Movie;

import java.util.Collection;

/**
 * @author Tede Morgado
 *         Created at 06/04/2016
 */
public class PrintGetTopsNRatingsHigherAverage implements Printable
{
    private Collection<Movie> movieCollection;

    public PrintGetTopsNRatingsHigherAverage(Collection<Movie> movies){
        movieCollection = movies;
    }

    @Override
    public String toStringResult()
    {
        String str = "";
        for (Movie m : movieCollection)
        {
            str+="\nID: " + m.getMovieName() + "\n" +
                    "\tName:" + m.getMovieName() + "\n" +
                    "\tYear: " + m.getMovieRelease() + "\n" +
                    "\tAverage: "+m.average()+"\n";
        }
        return str;
    }
}
