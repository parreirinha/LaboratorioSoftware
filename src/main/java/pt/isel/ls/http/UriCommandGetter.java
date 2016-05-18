package pt.isel.ls.http;

import pt.isel.ls.linecommand.model.Command;
import pt.isel.ls.linecommand.process.CommandGetter;

/**
 * Created by Dani on 18-05-2016.
 */
public class UriCommandGetter {

    private String[] commandParts = new String[4];

    public Command getCommandFromUri(String methodName, String requestUri, String query) {
        commandParts[0] = methodName;
        commandParts[1] = requestUri;

        if(query!=null) {
            String[] queryParts = query.split("&");

        String headersString = "", paramsString = "";


        for (int i = 0; i < queryParts.length; ++i) {
            if (queryParts[i].contains(":")) {
                headersString += queryParts[i];
            }

            if (queryParts[i].contains("=")) {
                paramsString += queryParts[i];
            }
        }

        if (!headersString.equals("")) {
            commandParts[2] = headersString;
            if (!paramsString.equals("")) {
                commandParts[3] = paramsString;
            }
        }
        if (!paramsString.equals("")) {
            commandParts[2] = paramsString;

        }
    }
        return new CommandGetter().getCommand(commandParts);
    }
}