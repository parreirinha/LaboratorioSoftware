package pt.isel.ls.command.mapping;


import org.junit.Test;
import pt.isel.ls.command.mapping.CommandMapper;
import pt.isel.ls.command.process.CommandGetter;

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
    private final String[] getTopAverageLoweerRating = {"GET", "/tops/ratings/lower/average"};
    private final String[] getTopNAverageLoweerRating = {"GET", "/tops/10/ratings/lower/average"};
    private final String[] getTopReviewHigherCount = {"GET", "/tops/reviews/higher/count"};
    private final String[] getTopNReviewHigherCount = {"GET","/tops/10/reviews/higher/count"};

    @Test
    public void PostMovieIndexTest() {
        assertEquals(0, new CommandMapper().getCommandIndex(new CommandGetter().getCommand(postMovie)));
    }

    @Test
    public void GetAllMovieIndexTest() {
        assertEquals(1, new CommandMapper().getCommandIndex(new CommandGetter().getCommand(getAllMovies)));
    }

    @Test
    public void GetMovieIndexTest() {
        assertEquals(2, new CommandMapper().getCommandIndex(new CommandGetter().getCommand(getMovie)));
    }

    @Test
    public void PostRatingIndexTest() {
        assertEquals(3, new CommandMapper().getCommandIndex(new CommandGetter().getCommand(postMovieRating)));
    }

    @Test
    public void GetRatingIndexTest() {
        assertEquals(4, new CommandMapper().getCommandIndex(new CommandGetter().getCommand(getMovieRating)));
    }

    @Test
    public void PostReviewIndexTest() {
        assertEquals(5, new CommandMapper().getCommandIndex(new CommandGetter().getCommand(postMovieReview)));
    }

    @Test
    public void GetReviewIndexTest() {
        assertEquals(6, new CommandMapper().getCommandIndex(new CommandGetter().getCommand(getMovieReview)));
    }

    @Test
    public void GetReviewByIdIndexTest() {
        assertEquals(7, new CommandMapper().getCommandIndex(new CommandGetter().getCommand(getMovieReviewById)));
    }

    @Test
    public void GetTopHigherAverageRatingIndexTest() {
        assertEquals(8, new CommandMapper().getCommandIndex(new CommandGetter().getCommand(getTopAverageHigherRating)));
    }

    @Test
    public void GetTopNHigherAverageRatingIndexTest() {
        assertEquals(9, new CommandMapper().getCommandIndex(new CommandGetter().getCommand(getTopNAverageHigherRating)));
    }

    @Test
    public void GetTopLowerAverageRatingIndexTest() {
        assertEquals(10, new CommandMapper().getCommandIndex(new CommandGetter().getCommand(getTopAverageLoweerRating)));
    }

    @Test
    public void GetTopNLowerAverageRatingIndexTest() {
        assertEquals(11, new CommandMapper().getCommandIndex(new CommandGetter().getCommand(getTopNAverageLoweerRating)));
    }

    @Test
    public void GetTopCountReviewIndexTest() {
        assertEquals(12, new CommandMapper().getCommandIndex(new CommandGetter().getCommand(getTopReviewHigherCount)));
    }

    @Test
    public void GetTopNCounteReviewsIndexTest() {
        assertEquals(13, new CommandMapper().getCommandIndex(new CommandGetter().getCommand(getTopNReviewHigherCount)));
    }

}
