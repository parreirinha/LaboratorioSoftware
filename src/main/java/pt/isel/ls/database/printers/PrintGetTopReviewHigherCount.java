package pt.isel.ls.database.printers;

import pt.isel.ls.model.Movie;

import java.util.Collection;

/**
 * @author Tede Morgado
 *         Created at 06/04/2016
 */
public class PrintGetTopReviewHigherCount implements Printable {
    private Movie m;

    public PrintGetTopReviewHigherCount(Movie movie) {
        m = movie;
    }

    @Override
    public String toStringResult() {

        return "\tID: " + m.getMovieID() + "\n" +
                "\tName:" + m.getMovieName() + "\n" +
                "\tYear: " + m.getMovieRelease() + "\n" +
                "\t*" + m.getOneStar() + "" +
                "  **" + m.getTwoStar() + "" +
                "  ***" + m.getTreeStar() + "" +
                "  ****" + m.getFourStar() + "" +
                "  *****" + m.getFiveStar() + "\n";
    }
}
