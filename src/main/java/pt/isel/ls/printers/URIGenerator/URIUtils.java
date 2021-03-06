package pt.isel.ls.printers.URIGenerator;

import pt.isel.ls.linecommand.model.Command;

/**
 * Class with static methods used to help uri manipulation in http navigation.
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
        if (command.getParams().getParamString("sortBy") != null)
            return "&sortBy=" + command.getParams().getParamString("sortBy");
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

    public static String getUriOrder(Command command, String path, String order) {
        Integer top = command.getParams().getParamInt("top");
        Integer skip = command.getParams().getParamInt("skip");
        if (skip == null || top == null || skip < 0 || top <= 0)
        {
            skip = 0;
            top = 5;
        }
        String skipTop = "skip="+skip+"&top="+top;
        String orderBy = "sortBy="+order;
        return "<a href="+path + "?" + skipTop + "&" + orderBy+">"+order+"</a>";
    }
}
