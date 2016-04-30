package pt.isel.ls.printers;

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
        return String.format(HtmlGenerator.template, Success);
    }
}
