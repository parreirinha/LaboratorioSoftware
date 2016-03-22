package pt.isel.ls.commands;

/**
 * command nº12
 *
 * GET /tops/{n}/ratings/lower/average
 * returns a list with the n movies with the lower average ratings, sorted decreasingly
 */
public class GetTopNRatingsLowerAverage implements Commands {
    @Override
    public Object execute(Object... obj) {
        return null;
    }
}
