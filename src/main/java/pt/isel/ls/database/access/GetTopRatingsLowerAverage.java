package pt.isel.ls.database.access;

import pt.isel.ls.command.model.Parameters;
import pt.isel.ls.command.model.Path;

import java.sql.Connection;

/**
 * commmand nº11
 *
 * GET /tops/ratings/lower/average
 * returns the detail for the movie with the lower average rating.
 */
public class GetTopRatingsLowerAverage implements Commands {
    @Override
    public Object execute(Connection connection, Path path, Parameters parameters) {
        return null;
    }
}
