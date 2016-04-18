package pt.isel.ls.executioncommands;

import pt.isel.ls.linecommand.model.Command;
import pt.isel.ls.printers.PrintPostMovie;
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
    public Printable execute(Connection connection, Command command) throws SQLException {

        String movieName = command.getParams().getParamString("title");
        int movieRelease = command.getParams().getParamInt("releaseYear");
        String query = "insert into Movie (movieName, movieRelease) " + "values(?,?)";
        PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        AccessUtils.setValuesOnPreparedStatement(ps, movieName, movieRelease);
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        rs.next();
        int id = rs.getInt(1);
        return new PrintPostMovie(id);
    }
}
