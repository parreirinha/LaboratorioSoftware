package pt.isel.ls.database.access;

import pt.isel.ls.database.connection.ConnectionFactory;

import java.sql.*;

/**
 * command nº4
 * POST /movies/{mid}/ratings
 * submits a new rating for the movie identified by mid, given the following parameters:
 *  rating - integer between 1 and 5.
 */
public class PostMovieRating implements Commands {

    private Connection connection = null;
    private PreparedStatement preparedStatement = null;

    @Override
    public Object execute(Connection connection, Object... obj) throws SQLException {


        int reviewID = (Integer) obj[0];
        int rating = (Integer) obj[1];
        String ratingColumnName = getColumnName(rating);

        String query = "update Review set ? = ? + 1 where MovieID = ?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, ratingColumnName);
        preparedStatement.setString(2, ratingColumnName);
        preparedStatement.setInt(3,reviewID);
        preparedStatement.executeUpdate();
        connection.commit();
        return null;
    }

    public String getColumnName(int rating) {

        switch (rating) {
            case 1:
                return "OneStar";
            case 2:
                return "TwoStar";
            case 3:
                return "TreeStar";
            case 4:
                return "FourStar";
            case 5:
                return "FiveStar";
        }
        return null;
    }
}
