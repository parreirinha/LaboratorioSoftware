package pt.isel.ls.command.mapping;

import org.junit.Test;
import pt.isel.ls.command.mapping.ExecuteSelection;
import pt.isel.ls.database.access.*;

import static junit.framework.Assert.assertTrue;

/**
 * Created by Dani on 19-03-2016.
 */
public class ExecuteSelectionTests {
    @Test
    public void postMovieInstanceTest() {
        assertTrue(new ExecuteSelection().getCommandExecutor(0) instanceof PostMovie);
    }

    @Test
    public void getAllMoviesInstanceTest() {
        assertTrue(new ExecuteSelection().getCommandExecutor(1) instanceof GetAllMovies);
    }

    @Test
    public void getMovieInstanceTest() {
        assertTrue(new ExecuteSelection().getCommandExecutor(2) instanceof GetMovie);
    }

    @Test
    public void postMovieRatingInstanceTest() {
        assertTrue(new ExecuteSelection().getCommandExecutor(3) instanceof PostMovieRating);
    }

    @Test
    public void getMovieRatingInstanceTest() {
        assertTrue(new ExecuteSelection().getCommandExecutor(4) instanceof GetMovieRating);
    }

    @Test
    public void postMovieReviewInstanceTest() {
        assertTrue(new ExecuteSelection().getCommandExecutor(5) instanceof PostMovieReview);
    }

    @Test
    public void getMoviesReviewsInstanceTest() {
        assertTrue(new ExecuteSelection().getCommandExecutor(6) instanceof GetAllReviews);
    }

    @Test
    public void getMovieReviewByIdInstanceTest() {
        assertTrue(new ExecuteSelection().getCommandExecutor(7) instanceof GetReviewById);
    }

    @Test
    public void getTopsRatingsHigherAverageInstanceTest() {
        assertTrue(new ExecuteSelection().getCommandExecutor(8) instanceof GetTopRatingsHigherAverage);
    }

    @Test
    public void getTopsNRatingsHigherAverageInstanceTest() {
        assertTrue(new ExecuteSelection().getCommandExecutor(9) instanceof GetTopsNRatingsHigherAverage);
    }

    @Test
    public void getTopsRatingsLoweerAverageInstanceTest() {
        assertTrue(new ExecuteSelection().getCommandExecutor(10) instanceof GetTopRatingsLowerAverage);
    }

    @Test
    public void getTopNRatingsLoweerAverageInstanceTest() {
        assertTrue(new ExecuteSelection().getCommandExecutor(11) instanceof GetTopNRatingsLowerAverage);
    }

    @Test
    public void getTopReviwesHigherCountInstanceTest() {
        assertTrue(new ExecuteSelection().getCommandExecutor(12) instanceof GetTopReviewHigherCount);
    }

    @Test
    public void getTopNReviwesHigherCountInstanceTest() {
        assertTrue(new ExecuteSelection().getCommandExecutor(13) instanceof GetTopNMoviesWithHigherReview);
    }

}

