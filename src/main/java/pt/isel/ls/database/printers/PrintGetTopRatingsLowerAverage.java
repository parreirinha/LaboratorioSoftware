package pt.isel.ls.database.printers;

import pt.isel.ls.model.Movie;

import java.util.Collection;

/**
 * @author Tede Morgado
 *         Created at 06/04/2016
 */
public class PrintGetTopRatingsLowerAverage implements Printable
{
    private Movie movie;

    public PrintGetTopRatingsLowerAverage(Movie movie){
        movie = movie;
    }

    @Override
    public String toStringResult()
    {
        return "\tID: " + movie.getMovieID() + "\n" +
                "\tName:" + movie.getMovieName() + "\n" +
                "\tYear: " + movie.getMovieRelease() + "\n" +
                "\t*" + movie.getOneStar() +"" +
                "  **" + movie.getTwoStar()+"" +
                "  ***" + movie.getTreeStar()+"" +
                "  ****" + movie.getFourStar() +"" +
                "  *****" + movie.getFiveStar() +"\n" +
                "\tAverage: "+movie.getAverage()+"\n";
    }
}
