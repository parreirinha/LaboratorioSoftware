package pt.isel.ls.printers;

import pt.isel.ls.printers.html.HtmlPrinters;

/**
 * Class used to print the return of POST Movie and POST Review commands.
 */
public class PrintPostMovieAndReview implements Printable {

    private final String string;
    int id = 0;
    private final String PostError = "Couldn't do post.\n";


    public PrintPostMovieAndReview(int id, String str) {
        this.id = id;
        this.string = str;
    }


    @Override
    public String toStringText() {
        return (id == 0) ? new PrintError(PostError).toStringText() : string + ": " + id;
        // return (id == 0) ? new PrintError("something went wrong!!\n").toStringText() : "The ID of the new movie is: " +
        //      "" + id;
    }

    @Override
    public String toStringHtml() {
        if (id == 0)
            return String.format(HtmlPrinters.template, PostError);

        return String.format(HtmlPrinters.template, string + ": " + id);
    }
}
