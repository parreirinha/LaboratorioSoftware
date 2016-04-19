package pt.isel.ls.user.io;

import pt.isel.ls.printers.Printable;

/**
 * Class used to visualize the obtained result on console.
 */
public class Printer {

    public void printResult(String out ){

        if(out != null)
        System.out.println(out);
        else
            System.out.println("Error: Couldn't print result.");
    }
}
