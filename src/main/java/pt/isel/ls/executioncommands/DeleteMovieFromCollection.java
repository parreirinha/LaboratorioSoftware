package pt.isel.ls.executioncommands;

import pt.isel.ls.linecommand.model.Parameters;
import pt.isel.ls.linecommand.model.Path;
import pt.isel.ls.printers.PrintMensage;
import pt.isel.ls.printers.Printable;
import java.sql.*;


/**
 * phase 2 - Command 5
 *
 * DELETE /collections/{cid}/movies/{mid}
 * removes the movie mid from the collections cid.
 */
public class DeleteMovieFromCollection implements CommandExecution {
    @Override
    public Printable execute(Connection connection, Path path, Parameters parameters) throws SQLException {

        int cid = path.getPathInt("cid");
        int mid = path.getPathInt("mid");
        String query = "delete from MovieCollection where CID = ? and MovieID = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        AccessUtils.setValuesOnPreparedStatement(ps, cid, mid);
        int res = ps.executeUpdate();
        if(res > 0)
            return new PrintMensage("Movie with id = " + mid + " was deleted with sucess from collection");
        return new PrintMensage("Movie has not deleted from collection");
    }
}
