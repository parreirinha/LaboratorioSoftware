package pt.isel.ls.printers;

import pt.isel.ls.printers.Printable;

/**
 * Created by Dani on 13-04-2016.
 */
public class PrintExit implements Printable {
    @Override
    public String toStringResult() {
        return "Exiting application. Thank you and good bye.";
    }
}
