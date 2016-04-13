package pt.isel.ls.localusage.commands;

import pt.isel.ls.command.model.Command;
import pt.isel.ls.command.model.Parameters;
import pt.isel.ls.command.model.Path;
import pt.isel.ls.database.access.Commands;
import pt.isel.ls.database.printers.Printable;
import pt.isel.ls.localusage.commands.Printers.PrintExit;

import java.sql.Connection;
import java.sql.SQLException;

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
