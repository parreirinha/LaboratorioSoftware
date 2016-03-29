package pt.isel.ls.database.access;

import java.sql.Connection;

/**
 * command nº14
 *
 * GET /tops/{n}/reviews/higher/count
 * returns a list with the n movies with higher review count.
 */
public class GetTopNMoviesWithHigherReview implements Commands{


    /**
     *
     * @param connection
     * @param obj
     * @return a collection
     */
    @Override
    public Object execute(Connection connection, Object... obj) {
        return null;
    }
}
