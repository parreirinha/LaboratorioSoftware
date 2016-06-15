package pt.isel.ls.printers;

import pt.isel.ls.printers.html.HtmlGenerator;

/**
 * @author Tede Morgado
 *         Created at 14/04/2016
 */
public class PrintError implements Printable {

    private final String str;

    public PrintError(String str) {
        this.str = str;
    }

    @Override
    public String toStringText() {
        return str;
    }

    @Override
    public String toStringHtml() {
        return String.format(new HtmlGenerator().getTemplate(), str);
    }
}
