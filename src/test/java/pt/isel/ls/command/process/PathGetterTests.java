package pt.isel.ls.command.process;

import org.junit.Test;

import java.util.ArrayList;

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

    @Test
    public void onlyMoviesStringPathTest() {
         assertEquals("movies", new PathGetter().getPath(onlyMoviesPath).getPathString());
    }

    @Test
    public void onlyMoviesIdsPathTest() {
        assertEquals(0, new PathGetter().getPath(onlyMoviesPath).getIntegers().size());    }

    @Test
    public void moviesAndMidStringPathTest() {
        assertEquals("movies", new PathGetter().getPath(onlyMoviesPath).getPathString());
    }

    @Test
    public void moviesAndMidMidPathTest() {
        assertEquals(123, (int)new PathGetter().getPath(moviesAndMidPath).getIntegers().iterator().next());
    }

    @Test
    public void moviesAndMidAndRatingsStringPathTest(){
        assertEquals("moviesmidratings", new PathGetter().getPath(moviesAndMidAndRatingsPath).getPathString());
    }

    @Test
    public void moviesAndMidAndRatingsMidPathTest(){
        assertEquals(123, (int)new PathGetter().getPath(moviesAndMidAndRatingsPath).getIntegers().iterator().next());
    }

    @Test
    public void moviesAndMidAndReviewsStringPathTest(){
        assertEquals("moviesmidreviews", new PathGetter().getPath(moviesAndMidAndReviewsPath).getPathString());
    }

    @Test
    public void moviesAndMidAndReviewsMidPathTest(){
        assertEquals(123, (int)new PathGetter().getPath(moviesAndMidAndReviewsPath).getIntegers().iterator().next());
    }

    @Test
    public void moviesAndMidAndReviewsAndRidStringPathTest(){
        assertEquals("moviesmidreviewsrid", new PathGetter().getPath(moviesAndMidAndReviewsAndRidPath).getPathString());
    }

    @Test
    public void moviesAndMidAndReviewsAndRidIdsPathTest(){
        ArrayList<Integer> array = new ArrayList<Integer>();
        array.add(123);
        array.add(100);

        assertEquals(array, new PathGetter().getPath(moviesAndMidAndReviewsAndRidPath).getIntegers());
    }

    @Test
    public void topsRatingsHigherAverageStringPathTest(){
        assertEquals("topsratingshigheraverage", new PathGetter().getPath(topsRatingsHigherAveragePath).getPathString());

    }

    @Test
    public void topsNRatingsHigherAverageStringPathTest(){
        assertEquals("topsnratingshigheraverage", new PathGetter().getPath(topsNRatingsHigherAveragePath).getPathString());

    }

    @Test
    public void topsNRatingsHigherAveragePathNTest(){
        assertEquals(10, (int)new PathGetter().getPath(topsNRatingsHigherAveragePath).getIntegers().iterator().next());

    }

    @Test
    public void topsRatingsLowerAverageStringPathTest(){
        assertEquals("topsratingsloweraverage", new PathGetter().getPath(topsRatingsLowerAveragePath).getPathString());
    }

    @Test
    public void topsNRatingsLowerAverageStringPathTest(){
        assertEquals("topsnratingsloweraverage", new PathGetter().getPath(topsNRatingsLowerAveragePath).getPathString());
    }

    @Test
    public void topsNRatingsLowerAveragePathNTest(){
        assertEquals(10, (int)new PathGetter().getPath(topsNRatingsLowerAveragePath).getIntegers().iterator().next());
    }

    @Test
    public void topsReviewsHigherCountStringPathTest(){
        assertEquals("topsreviewshighercount", new PathGetter().getPath(topsReviewsHigherCountPath).getPathString());
    }

    @Test
    public void topsNReviewsHigherCountStringPathTest(){
        assertEquals("topsnreviewshighercount", new PathGetter().getPath(topsNReviewsHigherCountPath).getPathString());
    }

    @Test
    public void topsNReviewsHigherCountPathNTest(){
        assertEquals(10, (int)new PathGetter().getPath(topsNReviewsHigherCountPath).getIntegers().iterator().next());
    }

}
