package pt.isel.ls.printers;

/**
 * Created by fabio on 06-Apr-16.
 */
public class PrintPostMovie implements Printable {

    int id = 0;

    public PrintPostMovie(int id){
        this.id = id;
    }


    @Override
    public String toStringResult() {
        return (id == 0) ? new PrintError("something went wrong!!\n").toStringResult() : "The ID of the new movie is: " +
                "" + id;
    }
}
