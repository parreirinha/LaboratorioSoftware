package pt.isel.ls.executioncommands;

import pt.isel.ls.linecommand.model.Parameters;
import pt.isel.ls.linecommand.model.Path;
import pt.isel.ls.printers.PrintDetailedMovie;
import pt.isel.ls.printers.Printable;
import pt.isel.ls.model.Movie;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * linecommand nº3
 * GET /movies/{mid}
 * returns the detailed information for the movie identified by mid
 * returns a Movie object
 */
public class GetMovie implements CommandExecution {


    @Override
    public Printable execute(Connection connection, Path path, Parameters parameters) throws SQLException {

        int id = path.getPathInt("mid");
        String query = "select * from Movie where (MovieID = ?);";
        PreparedStatement ps = connection.prepareStatement(query);
        AccessUtils.setValuesOnPreparedStatement(ps, id);
        ResultSet rs = ps.executeQuery();
        Collection<Movie> res = getCollection(rs);
        return new PrintDetailedMovie(res);
    }

    private Collection<Movie> getCollection(ResultSet rs) throws SQLException {
        Collection<Movie> res = new ArrayList<Movie>();
        while (rs.next())
        {
            res.add(new Movie(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getInt(3),
                    rs.getInt(4),
                    rs.getInt(5),
                    rs.getInt(6),
                    rs.getInt(7),
                    rs.getInt(8)
            ));
        }
        return res;
    }

}
