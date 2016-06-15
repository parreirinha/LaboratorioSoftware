package pt.isel.ls.linecommand.model;

import java.util.HashMap;

/**
 * Class whose instances are used to represent the linecommand parameters.
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
        if (s == null)
            return null;

        return Integer.parseInt(s);
    }

    public void setParamInt(String name, int value) {
        paramStrings.put(name, Integer.toString(value));
    }
}
