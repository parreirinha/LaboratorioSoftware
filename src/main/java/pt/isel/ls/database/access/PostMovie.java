package pt.isel.ls.database.access;

import pt.isel.ls.database.connection.ConnectionFactory;

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


    private PreparedStatement preparedStatement = null;


    @Override
    public Object execute(Connection connection, Object... obj) throws SQLException {


        String query = "insert into Movie (movieName, movieRelease) " + "values(?,?)  " +
                "select @@IDENTITY;";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, (String)obj[0]);
        preparedStatement.setInt(2, (Integer)obj[1]);

        //TODO
        // não consigo retornar o ID correcto
        int id = preparedStatement.executeUpdate();
        connection.commit();

        return id;
    }
}
