package pt.isel.ls.commands;

import org.junit.Test;
import pt.isel.ls.model.Movie;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class GetMovieRatingTest {


    private DataCreationTests dataCreation = new DataCreationTests();
    private GetMovieRating movieRating = new GetMovieRating();
    private Movie movie = null;

    @Test
    public void movieSevenTest() throws SQLException {

        movie = (Movie) movieRating.execute(2);
        assertEquals(movie.getOneStar(), dataCreation.movies[1].getOneStar());
        assertEquals(movie.getTwoStar(), dataCreation.movies[1].getTwoStar());
        assertEquals(movie.getTreeStar(), dataCreation.movies[1].getTreeStar());
        assertEquals(movie.getFourStar(), dataCreation.movies[1].getFourStar());
        assertEquals(movie.getFiveStar(), dataCreation.movies[1].getFiveStar());
        //assertEquals(movie.average(), dataCreation.movies[1].average());


    }
}
