package pt.isel.ls.database.access;


import pt.isel.ls.command.model.Parameters;
import pt.isel.ls.command.model.Path;
import pt.isel.ls.model.Review;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * command nº7
 * GET /movies/{mid}/reviews
 * returns all the reviews for the movie identified by mid.
 * The information for each review must not include the full review text.
 */
public class GetAllReviews implements Commands {


    @Override
    public Object execute(Connection connection, Path path, Parameters parameters) throws SQLException {

        String query = "select * from Review order by ReviewID";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet rs = preparedStatement.executeQuery();
        Collection<Review> container = new ArrayList<Review>();
        Review review;
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
        rs.close();
        preparedStatement.close();
        return container;
    }
}
