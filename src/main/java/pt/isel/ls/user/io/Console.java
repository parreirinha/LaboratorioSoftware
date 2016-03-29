package pt.isel.ls.user.io;

import java.sql.SQLException;

/**
 * Class used as entry point to the application.
 */
public class Console {

    public static void main(String[] args) throws SQLException {
        new Run().RunApp(args);
    }

}
