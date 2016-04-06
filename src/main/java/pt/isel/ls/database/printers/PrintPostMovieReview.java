package pt.isel.ls.database.printers;

/**
 * Created by fabio on 05-Apr-16.
 */
public class PrintPostMovieReview implements Printable {

    private int id = 0;

    public PrintPostMovieReview(int id){
        this.id = id;
    }

    @Override
    public String toStringResult() {
        return (id == 0) ? "something went wrong!!\n"
                            : "review id: " + id;
    }
}
