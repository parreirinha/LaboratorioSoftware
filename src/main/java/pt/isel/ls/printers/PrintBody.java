package pt.isel.ls.printers;

import pt.isel.ls.model.Collections;
import pt.isel.ls.model.Review;
import pt.isel.ls.printers.html.HtmlPrinters;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Function;

/**
 * @author Tede Morgado
 *         Created at 25/05/2016
 */
public class PrintBody implements Printable
{
    private final String body;
    public <T> PrintBody(Collection<T> res, String[] head, ArrayList<Function<T, String>> function, ArrayList<String> uri)
    {
        this.body = HtmlPrinters.htmlGenerate(res, head, function, uri);
    }
    public PrintBody()
    {
        this.body = "";
    }

    @Override
    public String toStringText() {
        return null;
    }

    @Override
    public String toStringHtml() {
        return body;
    }
}
