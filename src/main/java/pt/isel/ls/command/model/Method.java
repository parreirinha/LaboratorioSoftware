package pt.isel.ls.command.model;

/**
 * Created by Dani on 19-03-2016.
 */
public class Method {
    public String getMethod() {
        if(this.method == "" || this.method == null){
            return null;
        }

        return method;
    }

    private String method;

    public Method(String method) {
        this.method = method;
    }
}
