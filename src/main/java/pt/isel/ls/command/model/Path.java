package pt.isel.ls.command.model;

import java.util.Collection;

/**
 * Class whose instances are used to represent a command path,
 * i.e. an object with 2 Collection fields: one of String type
 * and other of Integer type.
 */
public class Path {
    private String pathString;
    private Collection<Integer> integers;

    public Path(String pathString, Collection<Integer> ids) {
        this.pathString = pathString;
        this.integers = ids;
    }

    public String getPathString() {
        return pathString;
    }

    public Collection<Integer> getIntegers() {
        return integers;
    }

}
