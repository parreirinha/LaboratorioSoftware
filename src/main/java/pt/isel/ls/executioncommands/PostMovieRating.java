package pt.isel.ls.executioncommands;

import pt.isel.ls.exceptions.ApplicationException;
import pt.isel.ls.linecommand.model.Command;
import pt.isel.ls.printers.PrintError;
import pt.isel.ls.printers.PrintPostMovieRating;
import pt.isel.ls.printers.Printable;

import java.sql.*;

/**
 * linecommand nº4
 * POST /movies/{mid}/ratings
 * submits a new rating for the movie identified by mid, given the following parameters:
 * rating - integer between 1 and 5.
 */
public class PostMovieRating implements CommandExecution {


    @Override
    public Printable execute(Connection connection, Command command) throws SQLException, ApplicationException {
        int movieID = command.getPath().getPathInt("mid");
        int rating = command.getParams().getParamInt("rating");

        if (movieID != -1 && rating != -1) {
            String ratingColumnName = AccessUtils.getColumnName(rating);
            String query = "update Movie set ? = ? + CAST(1 AS NVARCHAR(10)) where MovieID = ?;";
            PreparedStatement ps = connection.prepareStatement(query);
            AccessUtils.setValuesOnPreparedStatement(ps, ratingColumnName, ratingColumnName, movieID);
            ps.executeUpdate();
            connection.commit();
            return new PrintPostMovieRating();
        }
        return new PrintError("Error: Invalid parameter(s).");
    }

}

