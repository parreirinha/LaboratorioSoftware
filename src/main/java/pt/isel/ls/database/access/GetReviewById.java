package pt.isel.ls.database.access;


import pt.isel.ls.database.connection.ConnectionFactory;
import pt.isel.ls.model.Review;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * command nº8
 * GET /movies/{mid}/reviews/{rid}
 * returns the full information for the review rid of the movie identified by mid.
 *
 * returns a object Review
 */
public class GetReviewById implements Commands {

    private Connection connection = null;
    private Review review = null;

    @Override
    public Object execute(Connection connection, Object... obj) throws SQLException {


        Integer movieId = (Integer) obj[0];
        Integer reviewId = (Integer) obj[1];

        String statementQuery = "select * from Review where MovieId = " +movieId +
                " and ReviewID = " + reviewId +";";
        Statement stmt = this.connection.createStatement();
        ResultSet rs = stmt.executeQuery(statementQuery);
        if(!rs.next())      // query nao encontrou resultados
            return null;
        review = new Review(
                rs.getInt(1),
                rs.getInt(2),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5),
                rs.getInt(6)
        );
        return review;
    }
}
