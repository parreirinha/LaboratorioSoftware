package pt.isel.ls.database.access;

import pt.isel.ls.command.model.Parameters;
import pt.isel.ls.command.model.Path;
import pt.isel.ls.database.printers.PrintPostMovieRating;
import pt.isel.ls.database.printers.Printable;

import java.sql.*;

/**
 * command nº4
 * POST /movies/{mid}/ratings
 * submits a new rating for the movie identified by mid, given the following parameters:
 *  rating - integer between 1 and 5.
 */
public class PostMovieRating implements Commands {



    @Override
    public Printable execute(Connection connection, Path path, Parameters parameters) throws SQLException {
//TODO erro ao realizar ps.executeUpdate(); provavelmente erro na query
        int movieID = path.getPathInt("mid");
        int rating = parameters.getParamInt("rating");
        String ratingColumnName = AccessUtils.getColumnName(rating);
        String query = "update Movie set ? = (? + 1) where MovieID = ?;";
        PreparedStatement ps = connection.prepareStatement(query);
        AccessUtils.setValuesOnPreparedStatement(ps, ratingColumnName, ratingColumnName, movieID);
        ps.executeUpdate();
        connection.commit();
        ps.close();
        return new PrintPostMovieRating();
    }

}

