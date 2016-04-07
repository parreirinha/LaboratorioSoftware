package pt.isel.ls.database.printers;

import pt.isel.ls.model.Movie;

import java.util.Collection;

/**
 * @author Tede Morgado
 *         Created at 06/04/2016
 */
public class PrintGetTopReviewHigherCount implements Printable
{
    private Collection<Movie> movieCollection;

    public PrintGetTopReviewHigherCount(Collection<Movie> movies){
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
                    "\t*" + m.getOneStar() +"" +
                    "  **" + m.getTwoStar()+"" +
                    "  ***" + m.getTreeStar()+"" +
                    "  ****" + m.getFourStar() +"" +
                    "  *****" + m.getFiveStar() +"\n" +
                    "\tAverage: "+m.average()+"\n";
        }
        return str;
    }
}
