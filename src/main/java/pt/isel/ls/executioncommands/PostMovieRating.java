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
        Integer movieID = command.getPath().getPathInt("mid");
        Integer rating = command.getParams().getParamInt("rating");

        if (movieID > 1 && rating >= 1 && rating <= 5) {
            String ratingColumnName = AccessUtils.getColumnName(rating);
            String query = "update Movie set "+ ratingColumnName +" = "+ ratingColumnName + " + CAST(1 AS NVARCHAR(10)) where MovieID = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            AccessUtils.setValuesOnPreparedStatement(ps, movieID);
            ps.executeUpdate();
            connection.commit();
            command.setLocation("/movies/"+movieID+"/ratings/");
            return new PrintPostMovieRating();
        }
        String errorString="";
        if(movieID < 1)
            errorString += "Error: Invalid movie id.\n";
        if(rating < 1 || rating > 5)
            errorString += "Error: Invalid rating. Rating must be between 1 and 5.\n";

        return new PrintError(errorString);
    }

}

