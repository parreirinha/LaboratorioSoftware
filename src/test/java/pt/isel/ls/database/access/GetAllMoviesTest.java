package pt.isel.ls.database.access;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pt.isel.ls.database.connection.ConnectionFactory;
import pt.isel.ls.model.Movie;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;


/**
 * à segunda vez que se corre o teste dá erro porque o MovieID está definido como identity(1, 1), logo quando
 * corresmos a segunda vez os testes os valores nao batem certo para o conjunto da FK com PK
 */
public class GetAllMoviesTest {

    private Connection connection;
    private DataCreationTests dataCreation = new DataCreationTests();
    private GetAllMovies test = new GetAllMovies();


    @After
    public void deleteCreatedData() throws SQLException {
        //dataCreation.deleteAllReviews();
        //dataCreation.deleteAllMovies();
        connection.close();
    }

    @Before
    public void insertMoviesInDB() throws SQLException {
        //dataCreation.insertMoviesToTest();
        //dataCreation.insertReviewsInMovies();
    }

    @Test
    public void checkResultsetFromMoviesQueary() throws SQLException {

        connection = new ConnectionFactory().connectionFactory();


    }
}
