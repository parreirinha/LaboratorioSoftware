package pt.isel.ls.commands;

import pt.isel.ls.model.Movie;

import java.util.Collection;

/**
 * command nº4
 * POST /movies/{mid}/ratings
 * submits a new rating for the movie identified by mid, given the following parameters:
 *  rating - integer between 1 and 5.
 */
public class PostMovieRating implements Commands {
    @Override
    public Object execute(Object obj) {
        //TODO
        return null;
    }
}