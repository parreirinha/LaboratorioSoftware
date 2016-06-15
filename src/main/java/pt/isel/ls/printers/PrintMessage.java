package pt.isel.ls.printers;

import pt.isel.ls.printers.html.HtmlGenerator;

/**
 * Class used to print a message.
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
        HtmlGenerator htmlString = new HtmlGenerator()
                .addString(str);
        return String.format(htmlString.getTemplate(), htmlString.toString());
    }
}
