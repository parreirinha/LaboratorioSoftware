package pt.isel.ls.printers;

/**
 * Created by Dani on 18-05-2016.
 */
public class PrintHomePage implements Printable {
    @Override
    public String toStringText() {
        return "HAVE TO DO THIS!";//TODO
    }

    @Override
    public String toStringHtml() {
        return String.format(HtmlGenerator.template, toStringText());//TODO
    }
}
