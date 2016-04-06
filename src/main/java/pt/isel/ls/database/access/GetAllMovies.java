package pt.isel.ls.database.access;

import pt.isel.ls.command.model.Parameters;
import pt.isel.ls.command.model.Path;
import pt.isel.ls.database.printers.PrintGetAllMovies;
import pt.isel.ls.model.Movie;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * command nº2
 * GET /movies
 * returns a collection with all movies
 */
public class GetAllMovies implements Commands {



    @Override
    public Object execute(Connection connection, Path path, Parameters parameters) throws SQLException {

        String query = "select * from Movie order by MovieID";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet rs = preparedStatement.executeQuery();
        Collection<Movie> container = new ArrayList<Movie>();
        Movie movie;
        while (rs.next()) {
            movie = new Movie(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getInt(3)
            );
            container.add(movie);
        }
        rs.close();
        preparedStatement.close();
        return new PrintGetAllMovies(container);
    }
}