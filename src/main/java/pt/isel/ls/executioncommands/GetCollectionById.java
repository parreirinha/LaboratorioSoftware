package pt.isel.ls.executioncommands;

import pt.isel.ls.linecommand.model.Command;
import pt.isel.ls.printers.Printable;
import java.sql.Connection;
import java.sql.SQLException;


/**
 * phase 2 - Command 3
 *
 * GET /collections/{cid}
 * returns the details for the cid collection, namely all the movies in that collection.
 */
public class GetCollectionById implements CommandExecution {

    @Override
    public Printable execute(Connection connection, Command command) throws SQLException {


        //TODO - duvidas no inner join e na estrutura a retornar
        int collectionId = command.getPath().getPathInt("cid");

        String query = "select * from Collection where CollectionID = ?" +
                "inner join select MovieName from Movie";


        return null;
    }
}
