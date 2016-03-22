package pt.isel.ls.commands;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import pt.isel.ls.dbconnection.ConnectionFactory;
import pt.isel.ls.model.Movie;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * command nº1
 * POST /movies
 * creates a new movie, given the following parameters:
 *      title - movie name.
 *      releaseYear - movie's release year.
 *      This command returns the movie unique identifier.
 */
public class PostMovie implements Commands {

    private Connection connection = null;
    private Movie movie = null;
    private PreparedStatement preparedStatement = null;


    @Override
    public Object execute(Object... obj) throws SQLException {

        Movie movie = (Movie) obj[0];
        String query = "insert into Movie (movieName, movieRelease) " + "values(?,?)  " +
                "select @@IDENTITY;";
        connection = new ConnectionFactory().connectionFactory();
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, movie.movieName);
        preparedStatement.setInt(2, movie.getMovieRelease());

        //TODO
        // não consigo retornar o ID correcto
        int id = preparedStatement.executeUpdate();
        connection.commit();
        connection.close();
        return id;
    }
}
