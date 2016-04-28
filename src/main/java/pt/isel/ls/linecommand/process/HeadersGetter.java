package pt.isel.ls.linecommand.process;

import pt.isel.ls.linecommand.model.Headers;


import java.util.HashMap;
import java.util.regex.Pattern;

/**
 * Created by Dani on 11-04-2016.
 */
public class HeadersGetter {
    private HashMap<String, String> headersStrings = new HashMap<String, String >();

    public Headers getHeaders(String headersString) {

        String[] headers = headersString.split(Pattern.quote("|"));

        if(headersString!="")
        for (int i = 0; i < headers.length; ++i) {
            String[] pair = headers[i].split(":");
            if(pair.length==2) {
                String key = pair[0];
                String value = pair[1];
                headersStrings.put(key, value);
            }
        }

        return new Headers(headersStrings);
    }

}
