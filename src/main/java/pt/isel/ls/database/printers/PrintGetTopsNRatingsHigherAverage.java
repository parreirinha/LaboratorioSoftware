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
            str+="\tID: " + m.getMovieID() + "\n" +
                    "\tName:" + m.getMovieName() + "\n" +
                    "\tYear: " + m.getMovieRelease() + "\n" +
                    "\t*" + m.getOneStar() +"" +
                    "  **" + m.getTwoStar()+"" +
                    "  ***" + m.getThreeStar()+"" +
                    "  ****" + m.getFourStar() +"" +
                    "  *****" + m.getFiveStar() +"\n" +
                    "\tAverage: "+m.getAverage()+"\n\n";
        }
        return str;
    }
}
