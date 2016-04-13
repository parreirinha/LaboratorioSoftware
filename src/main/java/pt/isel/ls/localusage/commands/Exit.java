package pt.isel.ls.localusage.commands;

import pt.isel.ls.database.printers.Printable;
import pt.isel.ls.localusage.commands.Printers.PrintExit;

/**
 * Created by Dani on 12-04-2016.
 */
public class Exit implements LocalCommands {

    @Override
    public Printable execute() {
        System.exit(-1);
        return new PrintExit();

    }

}
