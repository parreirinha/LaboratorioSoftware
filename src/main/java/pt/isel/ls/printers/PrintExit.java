package pt.isel.ls.printers;

/**
 * Created by Dani on 13-04-2016.
 */
public class PrintExit implements Printable {
    @Override
    public String toStringText() {
        return "Exiting application. Thank you and good bye.";
    }

    @Override
    public String toStringHtml() {
        return String.format(HtmlGenerator.template, "Exiting application. Thank you and good bye.");
    }
}
