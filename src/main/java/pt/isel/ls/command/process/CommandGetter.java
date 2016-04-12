package pt.isel.ls.command.process;

import pt.isel.ls.command.model.Command;
import pt.isel.ls.command.model.Method;

import java.util.regex.Pattern;

/**
 * Class used to generate a Command instance from the String array
 * command passed as argument.
 */
public class CommandGetter {

    /*
    * Class used to return a new Command instance from the String array
    * command passed as argument.
    * * */
    public Command getCommand(String[] args) {
        String method = "", path = "", headers = "", params = "";

        if (args.length >= 1) {
            method = args[0];
        }

        if (args.length >= 2) {
            path = args[1];
        }

        if (args.length == 3) {
            if (hasHeaders(args[2])) {
                headers = args[2];
            } else {
                params = args[2];
            }
        }

        if (args.length == 4) {
            headers = args[2];
            params = args[3];
        }

        return new Command(
                new Method(method),
                new PathGetter().getPath(path),
                new HeadersGetter().getHeaders(headers),
                new ParamGetter().getParams(params));

    }

    private boolean hasHeaders(String arg) {
        String[]aux = arg.split(Pattern.quote("|"));
        return aux[0].contains(":") && !aux[0].contains("=");
    }

}
