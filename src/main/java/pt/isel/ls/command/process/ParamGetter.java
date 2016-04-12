package pt.isel.ls.command.process;

import pt.isel.ls.command.model.Parameters;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class used to process a parameters string and generate the
 * correspondent Parameters instance.
 */
public class ParamGetter {
    private HashMap<String, String> paramStrings;

    public ParamGetter(){
        paramStrings = new HashMap<String, String>();
    }

    public Parameters getParams(String paramString) {
        if(paramString == null || paramString == ""){
            return null;
        }

        String[] params = paramString.split("&");

        for(int i = 0; i < params.length; ++i){
            String[] pair = params[i].split("=");
            String key = pair[0];
            String value = pair[1].replace('+', ' ');

            paramStrings.put(key,value);
        }

        return new Parameters(paramStrings);
    }

}
