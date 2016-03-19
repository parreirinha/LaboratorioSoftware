package pt.isel.ls.database.access;

/**
 * command nº6
 * POST /movies/{mid}/reviews
 * creates a new review for the movie identified by mid, given the following parameters
 *      reviewerName - the reviewer name
 *      reviewSummary - the review summary
 *      review - the complete review
 *      rating - the review rating
 * This command returns the review unique identifier.
 */
public class PostMovieReview implements Commands{

    @Override
    public Object execute(Object obj) {
        return null;
    }
}
