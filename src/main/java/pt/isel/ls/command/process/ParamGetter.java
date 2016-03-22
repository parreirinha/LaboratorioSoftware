package pt.isel.ls.command.process;

import pt.isel.ls.command.model.Parameters;

import java.util.ArrayList;

/**
 * Class used to process a parameters string and generate the
 * correspondent Parameters instance.
 */
public class ParamGetter {

    public Parameters getParams(String paramString) {
        ArrayList<String> stringParams = new ArrayList<String>();
        ArrayList<Integer> intParams = new ArrayList<Integer>();
        String[] params = paramString.split("&");

        for(int i = 0; i < params.length; ++i){
            params[i] = params[i].split("=")[1].replace('+', ' ');
        }

        for(int i = 0; i < params.length; ++i){
            if (!PathGetter.isAllDigits(params[i])) {
                stringParams.add(params[i]);
            }
            else
                intParams.add(Integer.parseInt(params[i]));
        }

        return new Parameters(stringParams,intParams);
    }

}
