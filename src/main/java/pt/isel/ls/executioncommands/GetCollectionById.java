package pt.isel.ls.executioncommands;

import pt.isel.ls.exceptions.ApplicationException;
import pt.isel.ls.linecommand.model.Command;
import pt.isel.ls.model.Collections;
import pt.isel.ls.model.Movie;
import pt.isel.ls.model.MovieCollection;

import pt.isel.ls.printers.PrintGetCollectionsById;
import pt.isel.ls.printers.Printable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 * phase 2 - Command 3
 *
 * GET /collections/{cid}
 * returns the details for the cid collection, namely all the movies in that collection.
 */
public class GetCollectionById implements CommandExecution {

    private ArrayList<Movie> movies = new ArrayList<Movie>();
    private Collections collections;

    @Override
    public Printable execute(Connection connection, Command command) throws SQLException, ApplicationException {


        int collectionId = command.getPath().getPathInt("cid");
        String query =
            "select MC.CID, MC.MovieID, C.Name, C.Description, M.MovieName\n" +
                "from MovieCollection as MC \n" +
                "inner join Collections as C on MC.CID = C.CollectionID\n" +
                "inner join Movie as M on M.MovieID = MC.MovieID\n" +
                "where CID = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        AccessUtils.setValuesOnPreparedStatement(ps, collectionId);
        ResultSet rs = ps.executeQuery();
        setParametersForObjectMovieCollectionFromResultSet(rs);
        MovieCollection movieCollection = new MovieCollection(movies, collections);
        return new PrintGetCollectionsById(movieCollection);
    }

    private void setParametersForObjectMovieCollectionFromResultSet(ResultSet rs) throws SQLException {
        rs.next();
        collections = new Collections(rs.getInt(1), rs.getString(3),rs.getString(4));
        int i = 0;
        do{
            movies.add(i++, new Movie(rs.getInt(2), rs.getString(5)));
        }
        while(rs.next());
    }
}
