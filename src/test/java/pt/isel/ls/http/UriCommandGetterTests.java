package pt.isel.ls.http;

import org.junit.Test;
import pt.isel.ls.executioncommands.*;
import pt.isel.ls.linecommand.mapping.CommandMapper;
import pt.isel.ls.linecommand.model.Command;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Class used to test UriCommandGetter method getCommandFromUri.
 */
public class UriCommandGetterTests {

    private final String methodName = "GET";

    private final String getMoviesPath = "/movies/";
    private final String getMoviePath = "/movies/1/";
    private final String getMovieRatingPath = "/movies/1/ratings/";
    private final String getMovieReviewsPath = "/movies/1/reviews/";
    private final String getMovieReviewPath = "/movies/1/reviews/1/";
    private final String getCollectionsPath = "/collections/";
    private final String getCollectionPath = "/collections/1";
    private final String getTopRHAPath = "/tops/ratings/higher/average/";
    private final String getTopNRHAPath = "/tops/2/ratings/higher/average/";
    private final String getTopRLAPath = "/tops/ratings/lower/average/";
    private final String getTopNRLAPath = "/tops/2/ratings/lower/average/";
    private final String getTopRHCPath = "/tops/reviews/higher/count/";
    private final String getTopNRHCPath = "/tops/2/reviews/higher/count/";

    private final String acceptTextPlainHeader = "accept:text/plain";

    private final String skipAndTopParams = "skip=2&top=4";

    @Test
    public void shouldReturnGetMovie() {
        Command c = new UriCommandGetter().getCommandFromUri(methodName,
                getMoviePath, null);
        CommandExecution executor = new CommandMapper().getExecutionCommandInstance(c);
        assertTrue(executor instanceof GetMovie);
    }

    @Test
    public void shouldReturnGetMovies() {
        Command c = new UriCommandGetter().getCommandFromUri(methodName,
                getMoviesPath, null);
        CommandExecution executor = new CommandMapper().getExecutionCommandInstance(c);
        assertTrue(executor instanceof GetAllMovies);
    }

    @Test
    public void shouldReturnGetMovieRating() {
        Command c = new UriCommandGetter().getCommandFromUri(methodName,
                getMovieRatingPath, null);
        CommandExecution executor = new CommandMapper().getExecutionCommandInstance(c);
        assertTrue(executor instanceof GetMovieRating);
    }

    @Test
    public void shouldReturnGetMovieReview() {
        Command c = new UriCommandGetter().getCommandFromUri(methodName,
                getMovieReviewPath, null);
        CommandExecution executor = new CommandMapper().getExecutionCommandInstance(c);
        assertTrue(executor instanceof GetReviewById);
    }

    @Test
    public void shouldReturnGetMovieReviews() {
        Command c = new UriCommandGetter().getCommandFromUri(methodName,
                getMovieReviewsPath, null);
        CommandExecution executor = new CommandMapper().getExecutionCommandInstance(c);
        assertTrue(executor instanceof GetAllReviews);
    }

    @Test
    public void shouldReturnGetCollections() {
        Command c = new UriCommandGetter().getCommandFromUri(methodName,
                getCollectionsPath, null);
        CommandExecution executor = new CommandMapper().getExecutionCommandInstance(c);
        assertTrue(executor instanceof GetCollections);
    }

    @Test
    public void shouldReturnGetCollection() {
        Command c = new UriCommandGetter().getCommandFromUri(methodName,
                getCollectionPath, null);
        CommandExecution executor = new CommandMapper().getExecutionCommandInstance(c);
        assertTrue(executor instanceof GetCollectionById);
    }

    @Test
    public void shouldReturnGetTopRatingHA() {
        Command c = new UriCommandGetter().getCommandFromUri(methodName,
                getTopRHAPath, null);
        CommandExecution executor = new CommandMapper().getExecutionCommandInstance(c);
        assertTrue(executor instanceof GetTopRatingsHigherAverage);
    }

    @Test
    public void shouldReturnGetTopRatingNHA() {
        Command c = new UriCommandGetter().getCommandFromUri(methodName,
                getTopNRHAPath, null);
        CommandExecution executor = new CommandMapper().getExecutionCommandInstance(c);
        assertTrue(executor instanceof GetTopNRatingsHigherAverage);
    }

    @Test
    public void shouldReturnGetTopRatingLA() {
        Command c = new UriCommandGetter().getCommandFromUri(methodName,
                getTopRLAPath, null);
        CommandExecution executor = new CommandMapper().getExecutionCommandInstance(c);
        assertTrue(executor instanceof GetTopRatingsLowerAverage);
    }

    @Test
    public void shouldReturnGetTopRatingNLA() {
        Command c = new UriCommandGetter().getCommandFromUri(methodName,
                getTopNRLAPath, null);
        CommandExecution executor = new CommandMapper().getExecutionCommandInstance(c);
        assertTrue(executor instanceof GetTopNRatingsLowerAverage);
    }

    @Test
    public void shouldReturnGetTopReviewHC() {
        Command c = new UriCommandGetter().getCommandFromUri(methodName,
                getTopRHCPath, null);
        CommandExecution executor = new CommandMapper().getExecutionCommandInstance(c);
        assertTrue(executor instanceof GetTopMovieWithHigherReviewCount);
    }

    @Test
    public void shouldReturnGetTopNReviewHC() {
        Command c = new UriCommandGetter().getCommandFromUri(methodName,
                getTopNRHCPath, null);
        CommandExecution executor = new CommandMapper().getExecutionCommandInstance(c);
        assertTrue(executor instanceof GetTopNMoviesWithHigherReviewCount);
    }

    @Test
    public void shouldReturnValidHeadersAndParams() {
        Command c = new UriCommandGetter().getCommandFromUri(methodName,
                getMoviePath, acceptTextPlainHeader + "&" + skipAndTopParams);

        assertEquals("text/plain", c.getHeaders().getHeadersString("accept"));
        assertEquals(2, c.getParams().getParamInt("skip").intValue());
        assertEquals(4, c.getParams().getParamInt("top").intValue());
    }

}
