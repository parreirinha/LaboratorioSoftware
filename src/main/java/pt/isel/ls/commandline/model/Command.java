package pt.isel.ls.commandline.model;

/**
 * Class whose instances are used to represent a generic commandline,
 * i.e. an object with 3 fields of each type Method, Path and Parameters.
 */
public class Command {

    private Method method;
    private Path path;
    private Headers headers;
    private Parameters params;

    public Command(Method method, Path path, Headers headers, Parameters params) {
        this.method = method;
        this.headers = headers;
        this.path = path;
        this.params = params;
    }


    public Method getMethod() {
        return method;
    }

    public Path getPath() {
        return path;
    }

    public Headers getHeaders() {
        return headers;
    }

    public Parameters getParams() {
        return params;
    }



}
