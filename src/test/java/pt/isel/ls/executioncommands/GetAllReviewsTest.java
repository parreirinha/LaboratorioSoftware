package pt.isel.ls.executioncommands;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pt.isel.ls.exceptions.ApplicationException;
import pt.isel.ls.linecommand.model.Command;
import pt.isel.ls.linecommand.process.CommandGetter;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

/**
 * GET /movies/{mid}/reviews
 * "Review ID = #\n\tReviewer Name = ##\tReview Rating = ##\n\tSummary Review = ##\n"
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
    public void undoChangesAndCloseConnection() throws SQLException {
        dataTests.dropTables();
        connection.close();
    }

    @Before
    public void initConnectionandDataBase() throws SQLException {
        connection = new TestConnectionFactory().getNewConnection();
        dataTests.createTables();
        dataTests.insertMoviesToTest();
        dataTests.insertReviewsInMovies();
    }

    /**
     * new Review(1,1,"Manel","Magnificent","An insomniac office worker, looking for a way to change his life, crosses paths with a devil-may-care soap maker, forming an underground fight club that evolves into something much, much more...",5),
     * new Review(2,1,"Bad taste Reviwer","Horrible","This is the worst movie i have seen in my life",1),
     *
     * @throws SQLException
     */
    @Test
    public void movieWithThreeReviews() throws SQLException {


        input = new String[]{"GET", "/movies/1/reviews"};
        command = new CommandGetter().getCommand(input);
        try {
            result = getAllReviews.execute(connection, command).toStringText();
        } catch (pt.isel.ls.exceptions.ApplicationException e) {
            e.printStackTrace();
        }
        expected = "Review ID = 1" +
                "\n\tReviewer Name = Manel" +
                "\tReview Rating = 5" +
                "\n\tSummary Review = Magnificent\n" +
                "Review ID = 2" +
                "\n\tReviewer Name = Bad taste Reviwer" +
                "\tReview Rating = 1" +
                "\n\tSummary Review = Horrible\n" +
                "Review ID = 8" +
                "\n\tReviewer Name = Ze" +
                "\tReview Rating = 5" +
                "\n\tSummary Review = Film of the year\n";

        assertEquals(expected, result);
    }

    @Test
    public void movieWithTwoReview() throws SQLException {
        input = new String[]{"GET", "/movies/2/reviews"};
        command = new CommandGetter().getCommand(input);
        try {
            result = getAllReviews.execute(connection, command).toStringText();
        } catch (pt.isel.ls.exceptions.ApplicationException e) {
            e.printStackTrace();
        }
        expected = "Review ID = 4" +
                "\n\tReviewer Name = Jack" +
                "\tReview Rating = 2" +
                "\n\tSummary Review = Morgan Freeman is the best\n" +
                "Review ID = 7" +
                "\n\tReviewer Name = Ze" +
                "\tReview Rating = 5" +
                "\n\tSummary Review = Film of the year candidate\n";

        assertEquals(expected, result);
    }

    @Test
    public void movieWithoutReviews() throws SQLException {
        input = new String[]{"GET", "/movies/666/reviews"};
        command = new CommandGetter().getCommand(input);
        try {
            result = getAllReviews.execute(connection, command).toStringText();
        } catch (pt.isel.ls.exceptions.ApplicationException e) {
            e.printStackTrace();
        }
        expected = "There is no such review.\n";
        assertEquals(expected, result);
    }


    @Test
    public void pagingTest() throws SQLException, ApplicationException {


        input = new String[]{"GET", "/movies/1/reviews", "skip=1&top=1"};
        command = new CommandGetter().getCommand(input);
        result = getAllReviews.execute(connection, command).toStringText();
        expected =
                "Review ID = 2" +
                        "\n\tReviewer Name = Bad taste Reviwer" +
                        "\tReview Rating = 1" +
                        "\n\tSummary Review = Horrible\n";

        assertEquals(expected, result);
    }

}
