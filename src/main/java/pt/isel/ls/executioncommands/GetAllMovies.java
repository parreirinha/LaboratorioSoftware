package pt.isel.ls.executioncommands;

import pt.isel.ls.exceptions.ApplicationException;
import pt.isel.ls.linecommand.model.Command;
import pt.isel.ls.printers.PrintError;
import pt.isel.ls.printers.PrintMovie;
import pt.isel.ls.printers.Printable;
import pt.isel.ls.model.Movie;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import static pt.isel.ls.executioncommands.AccessUtils.*;

/**
 * linecommand nº2
 * GET /movies
 * returns a collection with all movies
 */
public class GetAllMovies implements CommandExecution {

    private HashMap<String, String> sortDecoder;

    @Override
    public Printable execute(Connection connection, Command command) throws SQLException, ApplicationException {
        String query = "select *, " + setClumnRowCountString(command, null) +
                " from Movie\n";
        query = concatenateQuearyIfExistsPaging(query, command, null);
        PreparedStatement ps = connection.prepareStatement(query);
        if (pagingVerification(command)) {
            int[] val = getSkipAndTopValuesToUseInPaging(command);
            setValuesOnPreparedStatement(ps, val[0], val[1]);
        }
        ResultSet rs = ps.executeQuery();
        Collection<Movie> res = getCollection(rs);
        if (res.isEmpty())
            return new PrintError("There are no movies.");

        return new PrintMovie(res, command);
    }

    private Collection<Movie> getCollection(ResultSet rs) throws SQLException {
        Collection<Movie> res = new ArrayList<Movie>();
        while (rs.next()) {
            res.add(new Movie(rs.getInt(1), rs.getString(2), rs.getInt(3)));
        }
        return res;
    }


}