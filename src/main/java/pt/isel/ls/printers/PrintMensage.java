package pt.isel.ls.printers;

/**
 * Created by fabio on 17-Apr-16.
 */
public class PrintMensage implements Printable {

    String str;

    public PrintMensage(String msg){
        str = msg;
    }

    @Override
    public String toStringText() {
        return str;
    }

    @Override
    public String toStringHtml() {
        return null;
    }
}
