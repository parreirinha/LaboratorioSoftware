package pt.isel.ls.executioncommands;

import pt.isel.ls.linecommand.model.Parameters;
import pt.isel.ls.linecommand.model.Path;
import pt.isel.ls.printers.PrintPostMovieRating;
import pt.isel.ls.printers.Printable;

import java.sql.*;

/**
 * linecommand nº4
 * POST /movies/{mid}/ratings
 * submits a new rating for the movie identified by mid, given the following parameters:
 *  rating - integer between 1 and 5.
 */
public class PostMovieRating implements CommandExecution {



    @Override
    public Printable execute(Connection connection, Path path, Parameters parameters) throws SQLException {
        int movieID = path.getPathInt("mid");
        int rating = parameters.getParamInt("rating");
        String ratingColumnName = AccessUtils.getColumnName(rating);
        String query = "update Movie set ? = ? + CAST(1 AS NVARCHAR(10)) where MovieID = ?;";
        PreparedStatement ps = connection.prepareStatement(query);
        AccessUtils.setValuesOnPreparedStatement(ps, ratingColumnName, ratingColumnName, movieID);
        ps.executeUpdate();
        connection.commit();
        ps.close();
        return new PrintPostMovieRating();
    }

}

