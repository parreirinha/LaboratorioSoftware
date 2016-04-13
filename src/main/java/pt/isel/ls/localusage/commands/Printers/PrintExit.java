package pt.isel.ls.localusage.commands.Printers;

import pt.isel.ls.database.printers.Printable;

/**
 * Created by Dani on 13-04-2016.
 */
public class PrintExit implements Printable {
    @Override
    public String toStringResult() {
        return "Exiting application. Thank you and good bye.";
    }
}
