package pt.isel.ls.linecommand.model;

import java.util.HashMap;

/**
 * Class whose instances are used to represent a linecommand path
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

    public Integer getPathInt(String key) {
        return pathIntegers.get(key);
    }

}
