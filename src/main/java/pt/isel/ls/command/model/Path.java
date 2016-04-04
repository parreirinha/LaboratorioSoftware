package pt.isel.ls.command.model;

import java.util.HashMap;

/**
 * Class whose instances are used to represent a command path,
 * i.e. an object with 2 Collection fields: one of String type
 * and other of Integer type.
 */
public class Path {
    private String pathString;
    private HashMap<String, Integer> pathIntegers;

    public Path(String pathString, HashMap<String, Integer> ints) {
        this.pathString = pathString;
        this.pathIntegers = ints;
    }

    public String getPathString() {
        return pathString;
    }

    public int getPathInt(String key) {
        //TODO: fix verifications... they screw some tests...
       /* if(key != "mid" || key != "rid" || key != "n"){
            System.out.println("Error: the key doesn't match a number.");
            return 0;
        }*/
        return pathIntegers.get(key);
    }

}
