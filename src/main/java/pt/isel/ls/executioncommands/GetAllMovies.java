package pt.isel.ls.executioncommands;

import pt.isel.ls.linecommand.model.Parameters;
import pt.isel.ls.linecommand.model.Path;
import pt.isel.ls.printers.PrintMovie;
import pt.isel.ls.printers.Printable;
import pt.isel.ls.model.Movie;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * linecommand nº2
 * GET /movies
 * returns a collection with all movies
 */
public class GetAllMovies implements CommandExecution {

    @Override
    public Printable execute(Connection connection, Path path, Parameters parameters) throws SQLException
    {
        String query = "select * from Movie order by MovieID";
        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        Collection<Movie> res = getCollection(rs);
        return new PrintMovie(res);
    }

    private Collection<Movie> getCollection(ResultSet rs) throws SQLException {
        Collection<Movie> res = new ArrayList<Movie>();
        while (rs.next())
        {
            res.add(new Movie(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getInt(3)
             ));
        }
        return res;
    }

}