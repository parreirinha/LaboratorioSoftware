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
        if (params != "" && params != null){
            uri += "?" + params;
        }
        uri += ">" + name + URIFinalString;

        return uri;
    }

    public static String getNextSkipAndTopValuesFromCommand(Command command){
        String res = "";
        Integer top = command.getParams().getParamInt("top");
        Integer skip = command.getParams().getParamInt("skip");
        if (skip == null || top == null){
            return null;
        }

        skip += top;

        if(command.getParams().getParamString("orderby") != null){
            res+="&orderby=" + command.getParams().getParamString("orderby");
        }
        return res;
    }

    public static String getPreviusSkipAndTopValuesFromCommand(Command command){
        String res = "";
        Integer top = command.getParams().getParamInt("top");
        Integer skip = command.getParams().getParamInt("skip");
        if (skip == null || top == null){
            return null;
        }

        skip -= top;

        res += "skip=" + skip + "&top=" + top;
        if(command.getParams().getParamString("orderby") != null){
            res+="&orderby=" + command.getParams().getParamString("orderby");
        }
        return res;
    }
}
