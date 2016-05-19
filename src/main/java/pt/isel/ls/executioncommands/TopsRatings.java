package pt.isel.ls.executioncommands;

import pt.isel.ls.exceptions.ApplicationException;
import pt.isel.ls.linecommand.model.Command;
import pt.isel.ls.printers.PrintTopsRatings;
import pt.isel.ls.printers.Printable;

import java.sql.Connection;
import java.sql.SQLException;

import static pt.isel.ls.http.ExecutionServlet.getPort;

/**
 * Created by fabio on 19-May-16.
 */
public class TopsRatings implements CommandExecution {
    @Override
    public Printable execute(Connection connection, Command command) throws SQLException, ApplicationException {
        return new PrintTopsRatings(getPort());
    }
}
