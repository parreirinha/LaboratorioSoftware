package pt.isel.ls.http;

import pt.isel.ls.linecommand.model.Command;
import pt.isel.ls.linecommand.process.CommandGetter;

/**
 * Class used to return an instance of {@code} Command from a given uri,
 * i.e. the method name (only GET is supported), the getRequestUri and
 * getQueryString methods from a given instance of
 * {@code} javax.servlet.http.HttpServletRequest.
 */
public class UriCommandGetter {

    private String[] commandParts;

    public Command getCommandFromUri(String methodName, String requestUri, String query) {

        if (query != null) {
            String[] queryParts = query.split("&");

            String headersString = "", paramsString = "";


            for (int i = 0; i < queryParts.length; ++i) {
                if (queryParts[i].contains(":")) {
                    headersString += queryParts[i];
                }

                if (queryParts[i].contains("=")) {
                    paramsString += "&" + queryParts[i];
                }
            }

            if (!headersString.equals("")) {
                commandParts = new String[4];
                commandParts[2] = headersString;
                if (!paramsString.equals("")) {
                    commandParts[3] = paramsString;
                }
            } else {
                commandParts = new String[3];
                commandParts[2] = paramsString;
            }

        } else {
            commandParts = new String[2];
        }

        commandParts[0] = methodName;
        commandParts[1] = requestUri;

        return new CommandGetter().getCommand(commandParts);
    }
}