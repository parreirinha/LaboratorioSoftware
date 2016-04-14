package pt.isel.ls.linecommand.process;

import pt.isel.ls.linecommand.model.Parameters;

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

        String[] params = paramString.split("&");

        if(paramString!="")
        for(int i = 0; i < params.length; ++i){
            String[] pair = params[i].split("=");
            String key = pair[0];
            String value = pair[1].replace('+', ' ');

            paramStrings.put(key,value);
        }

        return new Parameters(paramStrings);
    }

}
