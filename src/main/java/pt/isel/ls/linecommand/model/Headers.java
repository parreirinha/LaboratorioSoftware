package pt.isel.ls.linecommand.model;

import java.util.HashMap;

/**
 * Class used to represent the headers component.
 */
public class Headers {
    private HashMap<String, String> headersStrings;

    public Headers(HashMap<String, String> strings) {
        this.headersStrings = strings;
    }

    public String getHeadersString(String key) {
        return headersStrings.get(key);
    }

    public int getHeadersInt(String key) {

        if (headersStrings.containsKey(key)) {
            return Integer.parseInt(headersStrings.get(key));
        }
        return -1;
    }
}
