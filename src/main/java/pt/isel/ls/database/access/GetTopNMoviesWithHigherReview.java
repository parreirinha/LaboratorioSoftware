package pt.isel.ls.database.access;

/**
 * command nº14
 *
 * GET /tops/{n}/reviews/higher/count
 * returns a list with the n movies with higher review count.
 */
public class GetTopNMoviesWithHigherReview implements Commands{


    /**
     * @param obj
     * @return a collection
     */
    @Override
    public Object execute(Object... obj) {
        return null;
    }
}
