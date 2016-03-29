package pt.isel.ls.database.access;

import org.junit.After;
import org.junit.Test;
import pt.isel.ls.database.connection.ConnectionFactory;
import pt.isel.ls.model.Movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

/**
 * Created by fabio on 26-Mar-16.
 */
public class PostMovieRatingTest {


    private PreparedStatement preparedStatement = null;
    private Connection connection = null;
    private PostMovieRating postMovieRating = new PostMovieRating();
    private GetMovieRating getMovieRating = new GetMovieRating();
    private Movie movie = null;

    @Test
    public void incrementMatrizMovieStarTree() throws SQLException {
        //int[] values = {3,2};
        connection = new ConnectionFactory().connectionFactory();
        postMovieRating.execute(connection, 3, 2);
        connection = new ConnectionFactory().connectionFactory();
        movie = (Movie) getMovieRating.execute(connection, 3);
        assertEquals(34, movie.getTwoStar());

    }

    @Test
    public void incrementSevenMovieStarFive() throws SQLException {
        //int[] values = {2,5};
        connection = new ConnectionFactory().connectionFactory();
        postMovieRating.execute(connection, 2, 5);
        connection = new ConnectionFactory().connectionFactory();
        movie = (Movie) getMovieRating.execute(connection, 2);
        assertEquals(23, movie.getFiveStar());
    }

    @After
    public void undoChanges() throws SQLException {

        decrementValuesThatHaveBeenIncreasedInTest(2,5);
        decrementValuesThatHaveBeenIncreasedInTest(3,2);
        connection.close();
    }



    public void decrementValuesThatHaveBeenIncreasedInTest(int movieID, int star) throws SQLException {


        String ratingColumnName = postMovieRating.getColumnName(star);
        String query = "update Review set ? = ? - 1 where MovieID = ?";
        connection = new ConnectionFactory().connectionFactory();
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, ratingColumnName);
        preparedStatement.setString(2, ratingColumnName);
        preparedStatement.setInt(3,movieID);
        preparedStatement.executeUpdate();
        connection.commit();
        connection.close();
    }
}
