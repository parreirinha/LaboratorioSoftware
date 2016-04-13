package pt.isel.ls.localusage.commands;

import pt.isel.ls.database.printers.Printable;

/**
 * Created by Dani on 12-04-2016.
 */
public class Exit implements LocalCommands {

    @Override
    public Printable execute() {
        System.exit(-1);
        return null;
        /*
        *TODO: WE NEED TO RETURN DIFERENT THINGS: TEXT , HTML, ...
        *NEEDS AN ABSTRACT PRINT CLASS AND STRUCTURAL ADJUSTMENT
        */
    }

}
