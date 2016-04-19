package pt.isel.ls.printers;

import pt.isel.ls.model.Movie;

import java.util.Collection;

/**
 * Created by fabio on 05-Apr-16.
 */
public class PrintMovie implements Printable {

    private Collection<Movie> movieCollection;

    public PrintMovie(Collection<Movie> movies){
        movieCollection = movies;
    }

    /**
     * String template to be returned:
     * "Movie ID = ##\n\tName = ##\tRelease = ##\n"
     *
     * @return
     */
    @Override
    public String toStringText() {
        String s = "";
        for (Movie m:movieCollection) {
            s +=    "Movie ID = " + m.getMovieID()+
                    "\n\tName = " + m.getMovieName()+
                    "\tRelease = " + m.getMovieRelease()+
                    "\n";
        }
        return (s == "") ? new PrintError("something went wrong!!\n").toStringText() : s;
    }

    @Override
    public String toStringHtml() {
        return null;
    }
}
