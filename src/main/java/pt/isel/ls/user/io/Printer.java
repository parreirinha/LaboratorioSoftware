package pt.isel.ls.user.io;

import pt.isel.ls.database.printers.Printable;

import java.sql.ResultSet;

/**
 * Class used to visualize the obtained result on console.
 */
public class Printer {

    public void printResult(Printable printable ){

        if(printable != null)
        System.out.println(printable.toStringResult());

        else
            System.out.println("Error: Couldn't print result.");
    }
}
