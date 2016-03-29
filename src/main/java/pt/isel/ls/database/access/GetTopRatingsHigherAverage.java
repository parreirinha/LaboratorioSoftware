package pt.isel.ls.database.access;

import java.sql.Connection;

/**
 * command nº9
 * GET /tops/ratings/higher/average
 * returns the detail for the movie with the higher average rating.
 */
public class GetTopRatingsHigherAverage implements Commands {
    @Override
    public Object execute(Connection connection, Object... obj) {
        return null;
    }
}
