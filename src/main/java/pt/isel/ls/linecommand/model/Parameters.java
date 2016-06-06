package pt.isel.ls.linecommand.model;

import java.util.HashMap;

/**
 * Class whose instances are used to represent the linecommand parameters,
 * i.e. an object with 2 Collection fields: one of String type
 * and other of Integer type.
 */
public class Parameters {
    private HashMap<String, String> paramStrings;

    public Parameters(HashMap<String, String> strings) {
        this.paramStrings = strings;
    }

    public String getParamString(String key) {
        return paramStrings.get(key);
    }

    public Integer getParamInt(String key) {
        String s = paramStrings.get(key);
        if(s==null)
            return null;

            return Integer.parseInt(s);
    }
}
