package pt.isel.ls.executioncommands;

import pt.isel.ls.exceptions.ApplicationException;
import pt.isel.ls.linecommand.model.Command;
import pt.isel.ls.linecommand.process.CommandGetter;
import pt.isel.ls.printers.PrintError;
import pt.isel.ls.printers.PrintPostMovieAndReview;
import pt.isel.ls.printers.Printable;

import java.sql.*;

/**
 * linecommand nº6
 * POST /movies/{mid}/reviews
 * creates a new review for the movie identified by mid, given the following parameters
 * reviewerName - the reviewer name
 * reviewSummary - the review summary
 * review - the complete review
 * rating - the review rating
 * This linecommand returns the review unique identifier.
 */
public class PostMovieReview implements CommandExecution {


    @Override
    public Printable execute(Connection connection, Command command) throws SQLException, ApplicationException {

        Integer movieId = command.getPath().getPathInt("mid");
        String reviwerName = command.getParams().getParamString("reviewerName");
        String reviewSummary = command.getParams().getParamString("reviewSummary");
        String review = command.getParams().getParamString("review");
        Integer rating = command.getParams().getParamInt("rating");

        if (movieId > 1 && reviwerName != null && reviewSummary != null &&
                review != null && rating >=1 && rating <=5) {

            int verification = existsMovie(connection, movieId);
            if (verification > 0){
                String[] cmd = {""};
                return new HttpHomePage().execute(connection, new CommandGetter().getCommand(cmd));
            }

            String query = "insert into Review " +
                    "(MovieID, ReviewerName, ReviewSummary, ReviewComplete, ReviewRating)" +
                    "values(?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            AccessUtils.setValuesOnPreparedStatement(ps, movieId, reviwerName, reviewSummary, review, rating);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int rid = rs.getInt(1);
            connection.commit();

            //increment of the star given in the review
            String star = AccessUtils.getColumnName(rating);
            String query2 = "update Movie set "+ star +" = "+ star + " + CAST(1 AS NVARCHAR(10)) where MovieID = ?";
            ps = connection.prepareStatement(query2);
            AccessUtils.setValuesOnPreparedStatement(ps, movieId);
            ps.executeUpdate();
            connection.commit();
            command.setLocation("/movies/"+movieId+"/reviews/"+rid+"/");
            return new PrintPostMovieAndReview(rid, "Review ID is");

        }
        String errorString="";
        if(movieId == -1)
            errorString += "Error: Invalid movie id.\n";
        if(reviwerName == null)
            errorString += "Error: Invalid reviewer name.\n";
        if(reviewSummary == null)
            errorString += "Error: Invalid review summary.\n";
        if(review == null)
            errorString += "Error: Invalid review.\n";
        if(rating <1 || rating >5)
            errorString += "Error: Invalid rating. Rating must be between 1 and 5.\n";

        return new PrintError(errorString);
    }

    private int existsMovie(Connection conn, int movieId) throws SQLException {
        String q = "select * from Movie where MovieID = ? ";
        PreparedStatement ps = conn.prepareStatement(q);
        AccessUtils.setValuesOnPreparedStatement(ps, movieId);
        ResultSet rs = ps.executeQuery();
        if(rs.next())
            return rs.getInt(1);
        return 0;
    }
}
