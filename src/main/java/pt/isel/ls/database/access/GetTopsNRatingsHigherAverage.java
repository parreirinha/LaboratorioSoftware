package pt.isel.ls.database.access;

import pt.isel.ls.command.model.Parameters;
import pt.isel.ls.command.model.Path;

import java.sql.Connection;

/**
 * command nº10
 * GET /tops/{n}/ratings/higher/average
 * returns a list with the n movies with higher average ratings, sorted decreasingly
 */
public class GetTopsNRatingsHigherAverage implements Commands {
    @Override
    public Object execute(Connection connection, Path path, Parameters parameters) {
        return null;
    }
}
