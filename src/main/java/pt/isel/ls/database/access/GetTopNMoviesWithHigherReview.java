package pt.isel.ls.database.access;

import pt.isel.ls.command.model.Parameters;
import pt.isel.ls.command.model.Path;

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
     * @param path
     *@param parameters @return a collection
     */
    @Override
    public Object execute(Connection connection, Path path, Parameters parameters) {
        return null;
    }
}
