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
    public String toStringResult() {
        return (id == 0) ? new PrintError("something went wrong!!\n").toStringResult()
                            : "Review ID is: " + id;
    }
}
