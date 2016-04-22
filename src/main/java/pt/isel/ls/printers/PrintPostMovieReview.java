package pt.isel.ls.printers;

/**
 *
 */
public class PrintPostMovieReview implements Printable {

    private int id = 0;

    public PrintPostMovieReview(int id){
        this.id = id;
    }

    @Override
    public String toStringText() {
        return (id == 0) ? new PrintError("something went wrong!!\n").toStringText()
                            : "Review ID is: " + id;
    }

    @Override
    public String toStringHtml() {
        return String.format(HtmlGenerator.template, "Review ID is: " + id);
    }
}
