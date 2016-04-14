package pt.isel.ls.printers;

import pt.isel.ls.model.Movie;

/**
 * @author Tede Morgado
 *         Created at 06/04/2016
 */
public class PrintGetTopRatingsHigherAverage implements Printable
{
    private Movie movie;

    public PrintGetTopRatingsHigherAverage(Movie m){
        movie = m;
    }

    @Override
    public String toStringResult()
    {
        return "\tID: " + movie.getMovieID() + "\n" +
                "\tName:" + movie.getMovieName() + "\n" +
                "\tYear: " + movie.getMovieRelease() + "\n" +
                "\t*" + movie.getOneStar() +"" +
                "  **" + movie.getTwoStar()+"" +
                "  ***" + movie.getThreeStar()+"" +
                "  ****" + movie.getFourStar() +"" +
                "  *****" + movie.getFiveStar() +"\n" +
                "\tAverage: "+movie.getAverage()+"\n";
    }
}
