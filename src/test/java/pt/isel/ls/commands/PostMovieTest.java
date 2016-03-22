package pt.isel.ls.commands;
import org.junit.Test;
import pt.isel.ls.model.Movie;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class PostMovieTest {

    Movie movie;
    PostMovie postMovie = new PostMovie();
    DataCreationTests dataCreationTests = new DataCreationTests();

    @Test
    public void postOneMovie() throws SQLException {
        //TODO
        movie = new Movie("PostMovieTest", 2016);
        int newMovieId = (Integer) postMovie.execute(movie);
        assertEquals(dataCreationTests.movies[7].getMovieID(), newMovieId);
    }
}
