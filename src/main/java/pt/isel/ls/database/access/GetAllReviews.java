package pt.isel.ls.database.access;

import pt.isel.ls.dbconnection.ConnectionFactory;
import pt.isel.ls.model.Movie;
import pt.isel.ls.model.Review;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

/**
 * command nº7
 * GET /movies/{mid}/reviews
 * returns all the reviews for the movie identified by mid.
 * The information for each review must not include the full review text.
 */
public class GetAllReviews implements Commands {


    private Connection connection = null;
    private Review review = null;

    @Override
    public Object execute(Object... obj) throws SQLException {
        String statementQuery = "select * from Review order by ReviewID";
        connection = new ConnectionFactory().connectionFactory();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(statementQuery);
        Collection<Review> container = new ArrayList<Review>();
        while (rs.next()) {
            review = new Review(
                    rs.getInt(1),
                    rs.getInt(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getInt(6)
            );
            container.add(review);
        }
        connection.close();
        return container;
    }
}
