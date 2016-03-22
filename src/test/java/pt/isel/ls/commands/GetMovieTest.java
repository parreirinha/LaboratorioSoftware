package pt.isel.ls.commands;
import org.junit.Test;
import pt.isel.ls.model.Movie;
import pt.isel.ls.model.Review;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by fabio on 21-Mar-16.
 */
public class GetMovieTest {

    private DataCreationTests dataCreation = new DataCreationTests();
    private GetMovie getMovie = new GetMovie();
    private Movie movie = null;

    @Test
    public void  existingMoviesTest() throws SQLException {

        movie = (Movie) getMovie.execute(1);
        assertEquals(movie.getMovieID(), dataCreation.movies[0].getMovieID());
        assertEquals(movie.movieName, dataCreation.movies[0].movieName);

        movie = (Movie) getMovie.execute(4);
        assertEquals(movie.getMovieID(), dataCreation.movies[3].getMovieID());
        assertEquals(movie.movieName, dataCreation.movies[3].movieName);

        movie = (Movie) getMovie.execute(6);
        assertEquals(movie.getMovieID(), dataCreation.movies[5].getMovieID());
        assertEquals(movie.movieName, dataCreation.movies[5].movieName);
    }

    @Test
    public void nonExistingID() throws SQLException {

        movie = (Movie) getMovie.execute(10);
        assertNull(movie);
    }
}
