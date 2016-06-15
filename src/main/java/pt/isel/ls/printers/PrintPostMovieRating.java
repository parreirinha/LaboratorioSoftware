package pt.isel.ls.printers;

import pt.isel.ls.printers.html.HtmlGenerator;

/**
 * Class used to print the return of a post movie rating.
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
