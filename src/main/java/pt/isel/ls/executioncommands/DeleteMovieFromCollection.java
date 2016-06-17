package pt.isel.ls.executioncommands;

import pt.isel.ls.exceptions.ApplicationException;
import pt.isel.ls.linecommand.model.Command;
import pt.isel.ls.printers.PrintMessage;
import pt.isel.ls.printers.Printable;

import java.sql.*;


/**
 * phase 2 - Command 5
 * <p>
 * DELETE /collections/{cid}/movies/{mid}
 * removes the movie mid from the collections cid.
 */
public class DeleteMovieFromCollection implements CommandExecution {
    @Override
    public Printable execute(Connection connection, Command command) throws SQLException, ApplicationException {

        Integer cid = command.getPath().getPathInt("cid");
        Integer mid = command.getPath().getPathInt("mid");

        int verification = existsMovie(connection, mid);
        if (verification == 0){
            return new PrintMessage("Movie could not be deleted because does't exist in this collection");
        }
        String query = "delete from MovieCollection where CID = ? and MovieID = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        AccessUtils.setValuesOnPreparedStatement(ps, cid, mid);
        int res = ps.executeUpdate();
        connection.commit();
        if (res > 0)
            return new PrintMessage("Movie with id = " + mid + " was deleted with sucess from collection with id = " + cid);
        return new PrintMessage("Movie has not deleted from collection");
    }


    private int existsMovie(Connection conn, int mid) throws SQLException {
        String q = "select * from MovieCollection where MovieID = ?";
        PreparedStatement ps = conn.prepareStatement(q);
        AccessUtils.setValuesOnPreparedStatement(ps, mid);
        ResultSet rs = ps.executeQuery();
        if(rs.next())
            return rs.getInt(2);
        return 0;
    }
}
