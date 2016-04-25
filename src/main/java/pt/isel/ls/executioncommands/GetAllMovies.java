package pt.isel.ls.executioncommands;

import pt.isel.ls.linecommand.model.Command;
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
    public Printable execute(Connection connection, Command command) throws SQLException
    {
        String query = "select *, "+ getClumnRowCountString(command) + ", " + getRatingColumnFormula() +
                " from Movie\n";
        //TODO teste deste novo metodo - funciona sem paging excepto no rating

        //PreparedStatement ps = preparedStatementWithPaging(connection, query, command);
        PreparedStatement ps = connection.prepareStatement(query);

        ResultSet rs = ps.executeQuery();
        Collection<Movie> res = getCollection(rs);
        return new PrintMovie(res);
    }

    private Collection<Movie> getCollection(ResultSet rs) throws SQLException {
        Collection<Movie> res = new ArrayList<Movie>();
        while (rs.next()) {
            res.add(new Movie(rs.getInt(1), rs.getString(2), rs.getInt(3)));
        }
        return res;
    }



}