package pt.isel.ls.printers;

import pt.isel.ls.printers.html.HtmlGenerator;

/**
 * Created by fabio on 07-Apr-16.
 */
public class PrintPostMovieRating implements Printable {
    private final String Success = "Rating posted with success";

    @Override
    public String toStringText() {
        return Success;
    }

    @Override
    public String toStringHtml() {
        HtmlGenerator htmlGenerator = new HtmlGenerator();
        return String.format(htmlGenerator.getTemplate(), htmlGenerator.addString(Success).toString());
    }
}
