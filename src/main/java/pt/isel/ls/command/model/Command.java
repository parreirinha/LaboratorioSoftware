package pt.isel.ls.command.model;

/**
 * Class whose instances are used to represent a generic command,
 * i.e. an object with 3 fields of each type Method, Path and Parameters.
 */
public class Command {

    private Method method;
    private Path path;
    private Parameters params;

    public Command(Method method, Path path) {
        this.method = method;
        this.path = path;
    }

    public Command(Method method, Path path, Parameters params) {
        this.method = method;
        this.path = path;
        this.params = params;
    }


    public Method getMethod() {
        return method;
    }

    public Path getPath() {
        return path;
    }

    public Parameters getParams() {
        return params;
    }



}
