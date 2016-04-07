package pt.isel.ls.database.access;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pt.isel.ls.command.model.Command;
import pt.isel.ls.command.process.CommandGetter;
import pt.isel.ls.database.connection.ConnectionFactory;
import pt.isel.ls.database.printers.Printable;
import pt.isel.ls.model.Review;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * GET /movies/{mid}/reviews
 * "reviewid: #;\nreviewer name: #;\nreview sumary: #####;\nreview rating: ##;\n"
 */
public class GetAllReviewsTest {

    private Connection connection;
    private GetAllReviews getAllReviews = new GetAllReviews();
    private Command command;
    private String[] input;
    private String expected;
    private String result;
    private DataCreationTests dataTests = new DataCreationTests();


    @After
    private void undoChangesAndCloseConnection() throws SQLException {
        dataTests.deleteAllReviews();
        dataTests.deleteAllMovies();
        dataTests.dropTables();
        connection.close();
    }
    @Before
    private void initConnectionandDataBase() throws SQLException {
        connection = new ConnectionFactory().connectionFactory();
        dataTests.createTables();
        dataTests.insertMoviesToTest();
        dataTests.insertReviewsInMovies();
    }

    /**
     *  new Review(1,1,"Manel","Magnificent","An insomniac office worker, looking for a way to change his life, crosses paths with a devil-may-care soap maker, forming an underground fight club that evolves into something much, much more...",5),
     new Review(2,1,"Bad taste Reviwer","Horrible","This is the worst movie i have seen in my life",1),
     * @throws SQLException
     */
    @Test
    public void movieWithTwoReviews() throws SQLException {


        input = new String[]{"GET", "/movies/{1}/reviews"};
        command = new CommandGetter().getCommand(input);
        result = getAllReviews.execute(connection, command.getPath(),command.getParams()).toStringResult();
        expected =
                "reviewid: 1;\nreviewer name: Manel;\nreview sumary: Magnificent;\nreview rating: 5;\n" +
                "reviewid: 2;\nreviewer name: Bad taste Reviwer;\nreview sumary: Horrible;\nreview rating: 1;\n";
        assertEquals(expected, result);
    }

    @Test
    private void movieWithOneReview() throws SQLException{
        input = new String[]{"GET", "/movies/{2}/reviews"};
        command = new CommandGetter().getCommand(input);
        result = getAllReviews.execute(connection, command.getPath(),command.getParams()).toStringResult();
        expected = "reviewid: 4;\nreviewer name: Jack;\nreview sumary: Morgan Freeman is the best;\nreview rating: 2;\n";
        assertEquals(expected, result);
    }

    @Test
    private void movieWithoutReviews() throws SQLException{
        input = new String[]{"GET", "/movies/{666}/reviews"};
        command = new CommandGetter().getCommand(input);
        result = getAllReviews.execute(connection, command.getPath(),command.getParams()).toStringResult();
        expected = "something went wrong!!\n";
        assertEquals(expected, result);
    }

}
