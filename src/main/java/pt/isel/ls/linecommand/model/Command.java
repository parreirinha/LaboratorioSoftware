package pt.isel.ls.linecommand.model;

/**
 * Class whose instances are used to represent a generic linecommand.
 */
public class Command {

    private Method method;
    private Path path;
    private Headers headers;
    private Parameters params;
    private String location;

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


}
