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

    public String getParamString(String key){

        return paramStrings.get(key);
    }

    public int getParamInt(String key){
        if(!paramStrings.containsKey(key))
            return -1;

        return Integer.parseInt(paramStrings.get(key));
    }
}
