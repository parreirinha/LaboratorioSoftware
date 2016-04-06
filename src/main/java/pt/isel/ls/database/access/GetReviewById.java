package pt.isel.ls.database.access;


import pt.isel.ls.command.model.Parameters;
import pt.isel.ls.command.model.Path;
import pt.isel.ls.database.printers.PrintGetReviewById;
import pt.isel.ls.database.printers.Printable;
import pt.isel.ls.model.Review;

import java.sql.*;


/**
 * command nº8
 * GET /movies/{mid}/reviews/{rid}
 * returns the full information for the review rid of the movie identified by mid.
 *
 * returns a object Review
 */
public class GetReviewById implements Commands {


    @Override
    public Printable execute(Connection connection, Path path, Parameters parameters) throws SQLException {

        int movieId = path.getPathInt("mid");
        int reviewId = parameters.getParamInt("rid");
        String query = "select * from Review where MovieId = ? and ReviewID = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        AccessUtils.setValuesOnPreparedStatement(preparedStatement,movieId,reviewId);
        ResultSet rs = preparedStatement.executeQuery();
        Review review;
        if(!rs.next())      // query nao encontrou resultados   //TODO verificar
            return null;
        review = new Review(
                rs.getInt(1),
                rs.getInt(2),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5),
                rs.getInt(6)
        );
        rs.close();
        preparedStatement.close();
        return new PrintGetReviewById(review);
    }
}
