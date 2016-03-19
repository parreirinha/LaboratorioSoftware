package pt.isel.ls.database.access;

/**
 * command nº7
 * GET /movies/{mid}/reviews
 * returns all the reviews for the movie identified by mid.
 * The information for each review must not include the full review text.
 */
public class GetAllReviews implements Commands {
    @Override
    public Object execute(Object obj) {
        return null;
    }
}
