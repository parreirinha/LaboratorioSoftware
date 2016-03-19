package pt.isel.ls.command.model;

import java.util.Collection;

/**
 * Class whose instances are used to represent the command parameters,
 * i.e. an object with 2 Collection fields: one of String type
 * and other of Integer type.
 */
public class Parameters {
    private Collection<String> paramParts;
    private Collection<Integer> integers;

    public Collection<String> getParamParts() {
        return paramParts;
    }

    public Collection<Integer> getIntegers() {
        return integers;
    }

    public Parameters(Collection<String> pathParts, Collection<Integer> ids) {

        this.paramParts = pathParts;
        this.integers = ids;
    }
}
