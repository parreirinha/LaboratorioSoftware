package pt.isel.ls.database.access;

import pt.isel.ls.command.model.Parameters;
import pt.isel.ls.command.model.Path;
import pt.isel.ls.database.printers.Printable;

import java.sql.*;

/**
 * command nº1
 * POST /movies
 * creates a new movie, given the following parameters:
 *      title - movie name.
 *      releaseYear - movie's release year.
 *      This command returns the movie unique identifier.
 */
public class PostMovie implements Commands {


    @Override
    public Printable execute(Connection connection, Path path, Parameters parameters) throws SQLException {

        String movieName = parameters.getParamString("title");
        int movieRelease = parameters.getParamInt("releaseYear");

        String query = "insert into Movie (movieName, movieRelease) " + "values(?,?)";
        PreparedStatement ps = connection.prepareStatement(query);
        AccessUtils.setValuesOnPreparedStatement(ps, movieName, movieRelease);
        ps.executeUpdate();

        String query2 = "select @@IDENTITY;";
        ps = connection.prepareStatement(query2);
        ResultSet rs = ps.executeQuery();
        rs.next();
        int id = rs.getInt(1);
        rs.close();
        ps.close();
        return id;



    }
}
