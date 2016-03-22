package pt.isel.ls.command.mapping;

import org.junit.Test;
import pt.isel.ls.command.process.CommandGetter;
import pt.isel.ls.model.Movie;
import pt.isel.ls.model.Rating;
import pt.isel.ls.model.Review;

import java.util.ArrayList;

import static junit.framework.Assert.*;

/**
 * Created by Dani on 19-03-2016.
 */
public class ModelFactoryTests {
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

    @Test
    public void postMovieTitleTest() {
        Movie m =  (Movie)(new ModelFactory().getModel(
                new CommandGetter().getCommand(postMovie)));
        assertEquals("pulp fiction", m.getMovieName());
    }

    @Test
    public void postMovieYearTest() {
        Movie m =  (Movie)(new ModelFactory().getModel(
                new CommandGetter().getCommand(postMovie)));
        assertEquals(1994, m.getMovieReleaseYear());
    }

    @Test
    public void getAllMoviesNullTest(){
        assertNull((new ModelFactory().getModel(
                new CommandGetter().getCommand(getAllMovies))));
    }

    @Test
    public void getMovieIdTest(){
        assertEquals(123,(new ModelFactory().getModel(
                new CommandGetter().getCommand(getMovie))));
    }

    @Test
    public void postRatingMidTest(){
       Rating r = (Rating) (new ModelFactory().getModel(
                new CommandGetter().getCommand(postMovieRating)));
        assertEquals(123, r.getMovieId());
    }

    @Test
    public void postRatingRateTest(){
        Rating r = (Rating) (new ModelFactory().getModel(
                new CommandGetter().getCommand(postMovieRating)));
        assertEquals(5, r.getRating());
    }

    @Test
    public void getRatingMidTest(){
         ;
        assertEquals(123,(new ModelFactory().getModel(
                new CommandGetter().getCommand(getMovieRating))));
    }

    @Test
    public void postReviewMidTest(){
        Review r = (Review)new ModelFactory().getModel(new CommandGetter().getCommand(postMovieReview));
        assertEquals(123, r.getMovieID());
    }

    @Test
    public void postReviewRNameTest(){
        Review r = (Review)new ModelFactory().getModel(new CommandGetter().getCommand(postMovieReview));
        assertEquals("Michael Java", r.getReviewName());
    }

    @Test
    public void postReviewSummaryTest(){
        Review r = (Review)new ModelFactory().getModel(new CommandGetter().getCommand(postMovieReview));
        assertEquals("This is a software test!", r.getReviewSummary());
    }

    @Test
    public void postReviewFullReviewTest(){
        Review r = (Review)new ModelFactory().getModel(new CommandGetter().getCommand(postMovieReview));
        assertEquals("Really, I mean it, it was a good movie but this is only a software test dude!", r.getCompleteReview());
    }

    @Test
    public void postReviewRatingTest(){
        Review r = (Review)new ModelFactory().getModel(new CommandGetter().getCommand(postMovieReview));
        assertEquals(5, r.getReviewRating());
    }

    @Test
    public void getReviewMidTest(){
        assertEquals(123, new ModelFactory().getModel(
                new CommandGetter().getCommand(getMovieReview)
        ));
    }

    @Test
    public void getReviewByIdMidTest(){
        ArrayList<Integer> array = (ArrayList<Integer>)new ModelFactory().getModel(
                new CommandGetter().getCommand(getMovieReviewById)
        );
        assertSame(123, array.get(0));
    }

    @Test
    public void getReviewByIRidTest(){
        ArrayList<Integer> array = (ArrayList<Integer>)new ModelFactory().getModel(
                new CommandGetter().getCommand(getMovieReviewById)
        );
        assertSame(100, array.get(1));
    }

    @Test
    public void getTopAverageHigherRatingNullTest(){
        assertNull((new ModelFactory().getModel(
                new CommandGetter().getCommand(getTopAverageHigherRating))));
    }

    @Test
    public void getTopNAverageHigherRatingTest(){
        assertEquals(10, new ModelFactory().getModel(
                new CommandGetter().getCommand(getTopNAverageHigherRating)));
    }


    @Test
    public void getTopAverageLowerRatingNullTest(){
        assertNull((new ModelFactory().getModel(
                new CommandGetter().getCommand(getTopAverageLowerRating))));
    }

    @Test
    public void getTopNAverageLowerRatingTest(){
        assertEquals(10, new ModelFactory().getModel(
                new CommandGetter().getCommand(getTopNAverageLowerRating)));
    }

    @Test
    public void getTopReviewHigherCountNullTest(){
        assertNull((new ModelFactory().getModel(
                new CommandGetter().getCommand(getTopReviewHigherCount))));
    }

    @Test
    public void getTopNReviewHigherCountTest(){
        assertEquals(10, new ModelFactory().getModel(
                new CommandGetter().getCommand(getTopNReviewHigherCount)));
    }

}
