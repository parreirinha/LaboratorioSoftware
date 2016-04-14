package pt.isel.ls.linecommand.mapping;


import org.junit.Test;
import pt.isel.ls.executioncommands.*;
import pt.isel.ls.linecommand.process.CommandGetter;


import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Class used to test CommandMapper class methods.
 */
public class CommandMapperTests {

    private final String[] postMovie = {"POST", "/movies", "name=pulp+fiction&year=1994"};
    private final String[] getAllMovies = {"GET", "/movies"};
    private final String[] getMovie = {"GET", "/movies/123"};
    private final String[] postMovieRating = {"POST", "/movies/123/ratings", "rating=5"};
    private final String[] getMovieRating = {"GET", "/movies/123/ratings"};
    private final String reviewFullParams = " reviewerName=Michael+Java&reviewSummary=This is a software test!&" +
            "review=Really, I mean it, it was a good movie but this is only a software test dude!&rating=5";
    private final String[] postMovieReview = {"POST",  "/movies/123/reviews", reviewFullParams};
    private final String[] getMovieReview = {"GET", "/movies/123/reviews"};
    private final String[] getMovieReviewById = {"GET", "/movies/123/reviews/100"};
    private final String[] getTopAverageHigherRating = {"GET", "/tops/ratings/higher/average"};
    private final String[] getTopNAverageHigherRating = {"GET", "/tops/10/ratings/higher/average"};
    private final String[] getTopAverageLowerRating = {"GET", "/tops/ratings/lower/average"};
    private final String[] getTopNAverageLowerRating = {"GET", "/tops/10/ratings/lower/average"};
    private final String[] getTopReviewHigherCount = {"GET", "/tops/reviews/higher/count"};
    private final String[] getTopNReviewHigherCount = {"GET","/tops/10/reviews/higher/count"};

    CommandMapper map = new CommandMapper();

    @Test
    public void PostMovieTest() {
        assertTrue(map.getExecutionCommandInstance(new CommandGetter().getCommand(postMovie)) instanceof PostMovie);
    }

    @Test
    public void GetAllMoviesTest() {
        assertTrue(map.getExecutionCommandInstance(new CommandGetter().getCommand(getAllMovies)) instanceof GetAllMovies);
    }

    @Test
    public void GetMovieTest() {
        assertTrue(map.getExecutionCommandInstance(new CommandGetter().getCommand(getMovie)) instanceof GetMovie);
    }

    @Test
    public void PostRatingTest() {
        assertTrue(map.getExecutionCommandInstance(new CommandGetter().getCommand(postMovieRating)) instanceof PostMovieRating);
    }

    @Test
    public void GetRatingTest() {
        assertTrue(map.getExecutionCommandInstance(new CommandGetter().getCommand(getMovieRating)) instanceof GetMovieRating);
    }

    @Test
    public void PostReviewTest() {
        assertTrue(map.getExecutionCommandInstance(new CommandGetter().getCommand(postMovieReview)) instanceof PostMovieReview);
    }

    @Test
    public void GetReviewsTest() {
        assertTrue(map.getExecutionCommandInstance(new CommandGetter().getCommand(getMovieReview)) instanceof GetAllReviews);
    }

    @Test
    public void GetReviewByIdTest() {
        assertTrue(map.getExecutionCommandInstance(new CommandGetter().getCommand(getMovieReviewById)) instanceof GetReviewById);
    }

    @Test
    public void GetTopHigherAverageRatingTest() {
        assertTrue(map.getExecutionCommandInstance(new CommandGetter().getCommand(getTopAverageHigherRating)) instanceof GetTopRatingsHigherAverage);
    }

    @Test
    public void GetTopNHigherAverageRatingTest() {
        assertTrue(map.getExecutionCommandInstance(new CommandGetter().getCommand(getTopNAverageHigherRating)) instanceof GetTopsNRatingsHigherAverage);
    }

    @Test
    public void GetTopLowerAverageRatingTest() {
        assertTrue(map.getExecutionCommandInstance(new CommandGetter().getCommand(getTopAverageLowerRating)) instanceof GetTopRatingsLowerAverage);
    }

    @Test
    public void GetTopNLowerAverageRatingTest() {
        assertTrue(map.getExecutionCommandInstance(new CommandGetter().getCommand(getTopNAverageLowerRating)) instanceof GetTopNRatingsLowerAverage);

    }

    @Test
    public void GetTopCountReviewTest() {
        assertTrue(map.getExecutionCommandInstance(new CommandGetter().getCommand(getTopReviewHigherCount)) instanceof GetTopMovieWithHigherReviewCount);
    }

    @Test
    public void GetTopNCounteReviewsTest() {
        assertTrue(map.getExecutionCommandInstance(new CommandGetter().getCommand(getTopNReviewHigherCount)) instanceof GetTopNMoviesWithHigherReviewCount);

    }

}
