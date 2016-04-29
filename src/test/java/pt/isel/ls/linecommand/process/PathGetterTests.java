package pt.isel.ls.linecommand.process;

import org.junit.Test;
import pt.isel.ls.linecommand.model.Path;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Class used to test PathGetter class methods.
 */
public class PathGetterTests {

    private final String onlyMoviesPath = "/movies";
    private final String moviesAndMidPath = "/movies/123";
    private final String moviesAndMidAndRatingsPath = "/movies/123/ratings";
    private final String moviesAndMidAndReviewsPath = "/movies/123/reviews";
    private final String moviesAndMidAndReviewsAndRidPath = "/movies/123/reviews/100";
    private final String topsRatingsHigherAveragePath = "/tops/ratings/higher/average";
    private final String topsNRatingsHigherAveragePath = "/tops/10/ratings/higher/average";
    private final String topsRatingsLowerAveragePath = "/tops/ratings/lower/average";
    private final String topsNRatingsLowerAveragePath = "/tops/10/ratings/lower/average";
    private final String topsReviewsHigherCountPath = "/tops/reviews/higher/count";
    private final String topsNReviewsHigherCountPath = "/tops/10/reviews/higher/count";
    private final String OnlyCollectionsPath = "/collections/";
    private final String CollectionsAndCidPath = "/collections/10";
    private final String CollectionsAndCidPathAndMoviesPath = "/collections/10/movies";
    private final String CollectionsAndCidPathAndMoviesAndMidPath = "/collections/10/movies/123";
    private final String EmptyPath = "";

    @Test
    public void emptyPathTest() {
        Path p = new PathGetter().getPath(EmptyPath);
        assertEquals("", p.getPathString());
    }

    @Test
    public void collectionsAndCidAndMoviesAndMidPathTest() {
        Path p = new PathGetter().getPath(CollectionsAndCidPathAndMoviesAndMidPath);
        assertEquals("collectionscidmoviesmid", p.getPathString());
        assertEquals(10, p.getPathInt("cid"));
        assertEquals(123, p.getPathInt("mid"));
    }

    @Test
    public void collectionsAndCidAndMoviesPathTest() {
        Path p = new PathGetter().getPath(CollectionsAndCidPathAndMoviesPath);
        assertEquals("collectionscidmovies", p.getPathString());
        assertEquals(10, p.getPathInt("cid"));
    }

    @Test
    public void collectionsAndCidPathTest() {
        Path p = new PathGetter().getPath(CollectionsAndCidPath);
        assertEquals("collectionscid", p.getPathString());
        assertEquals(10, p.getPathInt("cid"));
    }

    @Test
    public void onlyCollectionsStringPathTest() {
        assertEquals("collections", new PathGetter().getPath(OnlyCollectionsPath).getPathString());
    }

    @Test
    public void onlyMoviesStringPathTest() {
        assertEquals("movies", new PathGetter().getPath(onlyMoviesPath).getPathString());
    }


    @Test
    public void moviesAndMidStringPathTest() {
        assertEquals("moviesmid", new PathGetter().getPath(moviesAndMidPath).getPathString());
    }

    @Test
    public void moviesAndMidMidPathTest() {
        assertEquals(123, (int) new PathGetter().getPath(moviesAndMidPath).getPathInt("mid"));
    }

    @Test
    public void moviesAndMidAndRatingsStringPathTest() {
        assertEquals("moviesmidratings", new PathGetter().getPath(moviesAndMidAndRatingsPath).getPathString());
    }

    @Test
    public void moviesAndMidAndRatingsMidPathTest() {
        assertEquals(123, (int) new PathGetter().getPath(moviesAndMidAndRatingsPath).getPathInt("mid"));
    }

    @Test
    public void moviesAndMidAndReviewsStringPathTest() {
        assertEquals("moviesmidreviews", new PathGetter().getPath(moviesAndMidAndReviewsPath).getPathString());
    }

    @Test
    public void moviesAndMidAndReviewsMidPathTest() {
        assertEquals(123, (int) new PathGetter().getPath(moviesAndMidAndReviewsPath).getPathInt("mid"));
    }

    @Test
    public void moviesAndMidAndReviewsAndRidStringPathTest() {
        assertEquals("moviesmidreviewsrid", new PathGetter().getPath(moviesAndMidAndReviewsAndRidPath).getPathString());
    }

    @Test
    public void moviesAndMidAndReviewsAndRidMidsPathTest() {
        assertEquals(123, new PathGetter().getPath(moviesAndMidAndReviewsAndRidPath).getPathInt("mid"));
    }

    @Test
    public void moviesAndMidAndReviewsAndRidRidsPathTest() {
        assertEquals(100, new PathGetter().getPath(moviesAndMidAndReviewsAndRidPath).getPathInt("rid"));
    }

    @Test
    public void topsRatingsHigherAverageStringPathTest() {
        assertEquals("topsratingshigheraverage", new PathGetter().getPath(topsRatingsHigherAveragePath).getPathString());

    }

    @Test
    public void topsNRatingsHigherAverageStringPathTest() {
        assertEquals("topsnratingshigheraverage", new PathGetter().getPath(topsNRatingsHigherAveragePath).getPathString());

    }

    @Test
    public void topsNRatingsHigherAveragePathNTest() {
        assertEquals(10, (int) new PathGetter().getPath(topsNRatingsHigherAveragePath).getPathInt("n"));

    }

    @Test
    public void topsRatingsLowerAverageStringPathTest() {
        assertEquals("topsratingsloweraverage", new PathGetter().getPath(topsRatingsLowerAveragePath).getPathString());
    }

    @Test
    public void topsNRatingsLowerAverageStringPathTest() {
        assertEquals("topsnratingsloweraverage", new PathGetter().getPath(topsNRatingsLowerAveragePath).getPathString());
    }

    @Test
    public void topsNRatingsLowerAveragePathNTest() {
        assertEquals(10, (int) new PathGetter().getPath(topsNRatingsLowerAveragePath).getPathInt("n"));
    }

    @Test
    public void topsReviewsHigherCountStringPathTest() {
        assertEquals("topsreviewshighercount", new PathGetter().getPath(topsReviewsHigherCountPath).getPathString());
    }

    @Test
    public void topsNReviewsHigherCountStringPathTest() {
        assertEquals("topsnreviewshighercount", new PathGetter().getPath(topsNReviewsHigherCountPath).getPathString());
    }

    @Test
    public void topsNReviewsHigherCountPathNTest() {
        assertEquals(10, (int) new PathGetter().getPath(topsNReviewsHigherCountPath).getPathInt("n"));
    }

}
