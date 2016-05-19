package pt.isel.ls.printers.URIGenerator;

import pt.isel.ls.linecommand.model.Command;
import pt.isel.ls.linecommand.model.Parameters;
import pt.isel.ls.linecommand.model.Path;

/**
 * Created by fabio on 18-May-16.
 */
public class URICreator {

    private static String URIInicialString = "<a href=http://localhost:";
    private static String URIFinalString = "</a>";



    public static String getURI(String path, String params, int port, String name) {

        String uri = URIInicialString + port + path;
        if (params != "" && params != null){
            uri += "?" + params;
        }
        uri += "/>" + name + URIFinalString;

        return uri;
    }
}
