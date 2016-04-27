package pt.isel.ls.printers;

/**
 * Created by fabio on 06-Apr-16.
 */
public class PrintPostMovieAndReview implements Printable {

    private final String string;
    int id = 0;

    public PrintPostMovieAndReview(int id, String str){
        this.id = id;
        this.string = str;
    }


    @Override
    public String toStringText() {
        return (id == 0) ? new PrintError("something went wrong!!\n").toStringText() : string +": "+ id;
       // return (id == 0) ? new PrintError("something went wrong!!\n").toStringText() : "The ID of the new movie is: " +
          //      "" + id;
    }

    @Override
    public String toStringHtml() {
        return String.format(HtmlGenerator.template, string +": "+id);
    }
}
