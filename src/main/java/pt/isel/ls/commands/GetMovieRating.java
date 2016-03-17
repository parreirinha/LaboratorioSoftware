package pt.isel.ls.commands;

import java.util.Collection;

/**
 * command nº5
 * GET /movies/{mid}/ratings
 * returns the rating information for the movie identified by mid. This rating information include:
 * The rating average
 * The number of votes for each rating value
 */
public class GetMovieRating implements Commands {


    @Override
    public Object execute(Object obj) {
        return null;
    }
}
