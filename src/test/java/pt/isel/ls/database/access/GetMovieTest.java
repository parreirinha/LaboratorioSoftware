package pt.isel.ls.database.access;
import org.junit.After;
import org.junit.Test;
import pt.isel.ls.database.connection.ConnectionFactory;
import pt.isel.ls.model.Movie;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by fabio on 21-Mar-16.
 */
public class GetMovieTest {

    private Connection connection;
    private DataCreationTests dataCreation = new DataCreationTests();
    private GetMovie getMovie = new GetMovie();
    private Movie movie = null;

    @After
    private void closeConnection() throws SQLException {
        connection.close();
    }


    @Test
    public void  existingMoviesTest() throws SQLException {

        connection = new ConnectionFactory().connectionFactory();
        movie = (Movie) getMovie.execute(connection, , 1, );
        assertEquals(movie.getMovieID(), dataCreation.movies[0].getMovieID());
        assertEquals(movie.getMovieName(), dataCreation.movies[0].getMovieName());

        movie = (Movie) getMovie.execute(connection, , 4, );
        assertEquals(movie.getMovieID(), dataCreation.movies[3].getMovieID());
        assertEquals(movie.getMovieName(), dataCreation.movies[3].getMovieName());

        movie = (Movie) getMovie.execute(connection, , 6, );
        assertEquals(movie.getMovieID(), dataCreation.movies[5].getMovieID());
        assertEquals(movie.getMovieName(), dataCreation.movies[5].getMovieName());
    }

    @Test
    public void nonExistingID() throws SQLException {

        connection = new ConnectionFactory().connectionFactory();
        movie = (Movie) getMovie.execute(connection, , 10, );
        assertNull(movie);
    }
}
