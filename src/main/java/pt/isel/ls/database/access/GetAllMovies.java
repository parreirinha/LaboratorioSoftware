package pt.isel.ls.database.access;

import pt.isel.ls.database.connection.ConnectionFactory;
import pt.isel.ls.model.Movie;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

/**
 * command nº2
 * GET /movies
 * returns a collection with all movies
 */
public class GetAllMovies implements Commands {

    private Connection connection = null;
    private Movie movie = null;

    @Override
    public Object execute(Connection connection, Object... obj) throws SQLException {

        String statementQuery = "select * from Movie order by MovieID";
        Statement stmt = this.connection.createStatement();
        ResultSet rs = stmt.executeQuery(statementQuery);
        Collection<Movie> container = new ArrayList<Movie>();
        while (rs.next()) {
            movie = new Movie(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getInt(3),
                    rs.getInt(4),
                    rs.getInt(5),
                    rs.getInt(6),
                    rs.getInt(7),
                    rs.getInt(8)
            );
            container.add(movie);
        }
        return container;
    }
}