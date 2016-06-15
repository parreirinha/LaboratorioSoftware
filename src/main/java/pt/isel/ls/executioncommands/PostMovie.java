package pt.isel.ls.executioncommands;

import pt.isel.ls.exceptions.ApplicationException;
import pt.isel.ls.linecommand.model.Command;
import pt.isel.ls.linecommand.process.CommandGetter;
import pt.isel.ls.printers.PrintError;
import pt.isel.ls.printers.PrintPostMovieAndReview;
import pt.isel.ls.printers.Printable;

import java.sql.*;

/**
 * linecommand nº1
 * POST /movies
 * creates a new movie, given the following parameters:
 *      title - movie name.
 *      releaseYear - movie's release year.
 *      This linecommand returns the movie unique identifier.
 */
public class PostMovie implements CommandExecution {


    @Override
    public Printable execute(Connection connection, Command command) throws SQLException, ApplicationException {

        String movieName = command.getParams().getParamString("title");
        Integer movieRelease = command.getParams().getParamInt("releaseYear");

        if(movieName != null && movieRelease != null && movieRelease > 1900) {
            int verification = existsMovie(connection, movieName, movieRelease);
            if (verification > 0){
                String[] cmd = {"GET", "/movies/"+verification, ""};
                return new GetMovie().execute(connection, new CommandGetter().getCommand(cmd));
            }
            String query = "insert into Movie (movieName, movieRelease) " + "values(?,?)";
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            AccessUtils.setValuesOnPreparedStatement(ps, movieName, movieRelease);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            connection.commit();
            command.setLocation("/movies/"+id+"/");
            return new PrintPostMovieAndReview(id, "The ID of the new movie is");
        }
        String errorString="";
        if(movieName == null)
            errorString += "Error: Invalid movie name.\n";
        if(movieRelease == null || movieRelease <= 1900)
            errorString += "Error: Invalid movie release year.\n";

        return new PrintError(errorString);
    }

    private int existsMovie(Connection conn, String name, int release) throws SQLException {
        String q = "select * from Movie where movieName = ? and movieRelease = ?";
        PreparedStatement ps = conn.prepareStatement(q);
        AccessUtils.setValuesOnPreparedStatement(ps, name, release);
        ResultSet rs = ps.executeQuery();
        if(rs.next())
            return rs.getInt(1);
        return 0;
    }


}
