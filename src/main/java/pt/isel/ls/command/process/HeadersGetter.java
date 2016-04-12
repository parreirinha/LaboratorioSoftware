package pt.isel.ls.command.process;

import pt.isel.ls.command.model.Headers;


import java.util.HashMap;
import java.util.regex.Pattern;

/**
 * Created by Dani on 11-04-2016.
 */
public class HeadersGetter {
    private HashMap<String, String> headersStrings = new HashMap<>();

    public Headers getHeaders(String headersString) {

        if(headersString == null || headersString == ""){
            return null;
        }

        String[] headers = headersString.split(Pattern.quote("|"));

        for (int i = 0; i < headers.length; ++i) {
            String[] pair = headers[i].split(":");
            String key = pair[0];
            String value = pair[1];

            headersStrings.put(key, value);
        }

        return new Headers(headersStrings);
    }

}
