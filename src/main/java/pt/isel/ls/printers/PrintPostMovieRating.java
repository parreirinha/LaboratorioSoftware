package pt.isel.ls.printers;

/**
 * Created by fabio on 07-Apr-16.
 */
public class PrintPostMovieRating implements Printable {
    @Override
    public String toStringText() {
        return "Rating posted with sucess";
    }

    @Override
    public String toStringHtml() {
        return null;
    }
}
