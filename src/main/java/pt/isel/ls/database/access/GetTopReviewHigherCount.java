package pt.isel.ls.database.access;

import pt.isel.ls.command.model.Parameters;
import pt.isel.ls.command.model.Path;

import java.sql.Connection;

/**
 * command nº 13
 *
 * GET /tops/reviews/higher/count
 * returns the detail for the movie with most reviews.
 */
public class GetTopReviewHigherCount implements Commands {
    @Override
    public Object execute(Connection connection, Path path, Parameters parameters) {
        return null;
    }
}
