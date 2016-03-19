package pt.isel.ls.command.mapping;

import pt.isel.ls.database.access.*;

/**
 * Class used to return the application method to
 * execute, given a Command index.
 */
public class ExecuteSelection {


    private Commands dataBaseExecutions[] = {
            new PostMovie(),
            new GetAllMovies(),
            new GetMovie(),
            new PostMovieRating(),
            new GetMovieRating(),
            new PostMovieReview(),
            new GetAllReviews(),
            new GetReviewById(),
            new GetTopRatingsHigherAverage(),
            new GetTopsNRatingsHigherAverage(),
            new GetTopRatingsLowerAverage(),
            new GetTopNRatingsLowerAverage(),
            new GetTopReviewHigherCount(),
            new GetTopNMoviesWithHigherReview()
    };


    public Commands getCommandExecutor(int commandIndex){

        return dataBaseExecutions[commandIndex];
    }
}
