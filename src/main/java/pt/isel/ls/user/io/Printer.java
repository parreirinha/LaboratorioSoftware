package pt.isel.ls.user.io;

import pt.isel.ls.database.printers.Printable;

import java.sql.ResultSet;

/**
 * Class used to visualize the obtained ResultSet on console.
 */
public class Printer {


    private Printable printable = null;

    public Printer(Printable p){
        printable = p;
    }

    public void printResult(){
        System.out.println(printable.toStringResult());
    }
}
