package pt.isel.ls.command.model;

import java.util.Collection;
import java.util.HashMap;

/**
 * Class whose instances are used to represent the command parameters,
 * i.e. an object with 2 Collection fields: one of String type
 * and other of Integer type.
 */
public class Parameters {
    private HashMap<String, String> paramStrings;

    public Parameters(HashMap<String, String> strings) {
        this.paramStrings = strings;
    }

    //TODO: fix verifications... they screw some tests...
    public String getParamString(String key){
       /* if(key.equals("releaseYear") || key.equals("rating")){
            System.out.println("Error: the key doesn't match a string.");
            return null;
        }*/

        return paramStrings.get(key);
    }

    public int getParamInt(String key){
      /*  if(!key.equals("releaseYear") || !key.equals("rating")){
            System.out.println("Error: the key doesn't match a number.");
            return 0;
        }*/

        return Integer.parseInt(paramStrings.get(key));
    }
}
