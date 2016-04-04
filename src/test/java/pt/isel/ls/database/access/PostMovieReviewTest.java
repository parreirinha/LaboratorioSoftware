package pt.isel.ls.database.access;

import org.junit.After;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by fabio on 27-Mar-16.
 */
public class PostMovieReviewTest {

    private Connection connection=null;

    @After
    private void closeConnection() throws SQLException {
        connection.close();
    }
}
