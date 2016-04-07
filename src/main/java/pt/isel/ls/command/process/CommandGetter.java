package pt.isel.ls.command.process;

import pt.isel.ls.command.model.Command;
import pt.isel.ls.command.model.Method;

/**
 * Class used to generate a Command instance from the String array
 * command passed as argument.
 */
public class CommandGetter {

    /*
    * Class used to returns a new Command instance from the String array
    * command passed as argument, or null if the command is
    * invalid.
    * */
    public Command getCommand(String[] args) {
        String method, path, params;

        if (!validate(args)) return null;

        method = args[0];

        path = args[1];

        boolean hasParams = method.equals("POST") && args.length == 3;

        if (hasParams) {
            params = args[2];
            return new Command(new Method(method), new PathGetter().getPath(path),
                    new ParamGetter().getParams(params));
        }

        return new Command (new Method(method), new PathGetter().getPath(path));
    }

    private boolean validate(String[] args) {
        if (args.length < 2 || args[0].equals("POST") && args.length < 3) {
            System.out.println("Error: not enough arguments.");
            return false;
        }

        if (args.length > 3 || args[0].equals("GET") && args.length > 2) {
            System.out.println("Error: too many arguments.");
            return false;
        }


        if (!args[0].equals("GET") && !args[0].equals("POST")) {
            System.out.println("Error: unkown action.");
            return false;
        }
        return true;
    }


}
