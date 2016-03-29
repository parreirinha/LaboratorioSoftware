package pt.isel.ls.database.access;
import org.junit.After;
import org.junit.Test;
import pt.isel.ls.database.connection.ConnectionFactory;
import pt.isel.ls.model.Movie;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class PostMovieTest {

    Movie movie;
    PostMovie postMovie = new PostMovie();
    DataCreationTests dataCreationTests = new DataCreationTests();
    private Connection connection;

    @Test
    public void postOneMovie() throws SQLException {
        //TODO
        connection = new ConnectionFactory().connectionFactory();
        movie = new Movie("PostMovieTest", 2016);
        int newMovieId = (Integer) postMovie.execute(connection, movie);
        assertEquals(dataCreationTests.movies[7].getMovieID(), newMovieId);
    }


    @After
    private void closeConnection() throws SQLException {
        connection.close();
    }
}
