package pt.isel.ls.printers;

import pt.isel.ls.printers.html.HtmlGenerator;
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
        this.body = new HtmlGenerator()
                .htmlGenerate(res, head, function, uri)
                .toString();
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
