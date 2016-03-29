package pt.isel.ls.database.access;

import org.junit.After;
import org.junit.Test;
import pt.isel.ls.database.connection.ConnectionFactory;
import pt.isel.ls.model.Movie;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class GetMovieRatingTest {

    private Connection connection;
    private DataCreationTests dataCreation = new DataCreationTests();
    private GetMovieRating movieRating = new GetMovieRating();
    private Movie movie = null;

    @Test
    public void movieSevenTest() throws SQLException {

        connection = new ConnectionFactory().connectionFactory();
        movie = (Movie) movieRating.execute(connection, 2);
        assertEquals(dataCreation.movies[1].getOneStar(), movie.getOneStar());
        assertEquals(dataCreation.movies[1].getTwoStar(), movie.getTwoStar());
        assertEquals(dataCreation.movies[1].getTreeStar(), movie.getTreeStar());
        assertEquals(dataCreation.movies[1].getFourStar(), movie.getFourStar());
        assertEquals(dataCreation.movies[1].getFiveStar(), movie.getFiveStar());
        //assertEquals(movie.average(), dataCreation.movies[1].average());

    }

    @After
    private void closeConnection() throws SQLException {
        connection.close();
    }
}
