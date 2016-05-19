package pt.isel.ls.printers;

import pt.isel.ls.printers.html.HtmlPrinters;

/**
 * Created by fabio on 17-Apr-16.
 */
public class PrintMessage implements Printable {

    String str;

    public PrintMessage(String msg) {
        str = msg;
    }

    @Override
    public String toStringText() {
        return str;
    }

    @Override
    public String toStringHtml() {
        return String.format(HtmlPrinters.template, str);
    }
}
