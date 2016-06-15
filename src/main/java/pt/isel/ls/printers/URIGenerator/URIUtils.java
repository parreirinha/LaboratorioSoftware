package pt.isel.ls.printers.URIGenerator;

import pt.isel.ls.linecommand.model.Command;
import pt.isel.ls.linecommand.model.Parameters;
import pt.isel.ls.linecommand.model.Path;

/**
 * Created by fabio on 18-May-16.
 */
public class URIUtils {

    private static String URIInicialString = "<a href=";
    private static String URIFinalString = "</a>";


    public static String getURI(String path, String params, String name) {

        String uri = URIInicialString + path;
        if (params != "" && params != null) {
            uri += "?" + params;
        }
        uri += ">" + name + URIFinalString;

        return uri;
    }

    public static String getNextSkipAndTopValuesFromCommand(Command command) {

        Integer top = command.getParams().getParamInt("top");
        Integer skip = command.getParams().getParamInt("skip");
        if (skip == null || top == null || skip < 0 || top <= 0) {
            return "skip=0&top=5" + getOrderBy(command);
        }
        skip += top;
        return "skip=" + skip + "&top=" + top + getOrderBy(command);
    }

    private static String getOrderBy(Command command) {
        if (command.getParams().getParamString("orderby") != null)
            return "&orderby=" + command.getParams().getParamString("orderby");
        return "";
    }

    public static String getPreviusSkipAndTopValuesFromCommand(Command command) {
        String res = "";
        Integer top = command.getParams().getParamInt("top");
        Integer skip = command.getParams().getParamInt("skip");
        if (skip == null || top == null) {
            return null;
        }
        skip -= top;
        if (skip < 0 || top <= 0)
            return null;

        res += "skip=" + skip + "&top=" + top + getOrderBy(command);

        return res;
    }
}
