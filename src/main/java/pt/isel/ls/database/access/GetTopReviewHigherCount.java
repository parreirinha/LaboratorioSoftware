package pt.isel.ls.database.access;

import java.sql.Connection;

/**
 * command nº 13
 *
 * GET /tops/reviews/higher/count
 * returns the detail for the movie with most reviews.
 */
public class GetTopReviewHigherCount implements Commands {
    @Override
    public Object execute(Connection connection, Object... obj) {
        return null;
    }
}
