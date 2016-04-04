package pt.isel.ls.database.access;

import pt.isel.ls.command.model.Parameters;
import pt.isel.ls.command.model.Path;
import pt.isel.ls.model.Movie;

import java.sql.*;

/**
 * command nº3
 * GET /movies/{mid}
 * returns the detailed information for the movie identified by mid
 * returns a Movie object
 */
public class GetMovie implements Commands {


    @Override
    public Object execute(Connection connection, Path path, Parameters parameters) throws SQLException {

        int id = path.getPathInt("mid");
        String query = "select * from Movie where (MovieID = ?);";
        PreparedStatement ps = connection.prepareStatement(query);
        AccessUtils.setValuesOnPreparedStatement(ps, id);
        ResultSet rs = ps.executeQuery();
        Movie res = null;
        while (rs.next()) {
            res = new Movie(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getInt(3),
                    rs.getInt(4),
                    rs.getInt(5),
                    rs.getInt(6),
                    rs.getInt(7),
                    rs.getInt(8)
            );
        }
        rs.close();
        ps.close();
        return res;
    }
}
