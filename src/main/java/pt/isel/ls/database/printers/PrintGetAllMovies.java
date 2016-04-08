package pt.isel.ls.database.printers;

import pt.isel.ls.model.Movie;

import java.util.Collection;

/**
 * Created by fabio on 05-Apr-16.
 */
public class PrintGetAllMovies implements Printable {

    private Collection<Movie> movieCollection;

    public PrintGetAllMovies(Collection<Movie> movies){
        movieCollection = movies;
    }

    /**
     * String template to be returned:
     * "movie id: ##\nmovie name: ##\nrelease year: ##\n"
     *
     * @return
     */
    @Override
    public String toStringResult() {
        String s = "";
        for (Movie m:movieCollection) {
            s +=    "movie id:" + m.getMovieID()+
                    "\nmovie name: " + m.getMovieName()+
                    "\nrelease year: " + m.getMovieRelease()+
                    "\n";
        }
        return (s == "") ? "something went wrong!!\n" : s;
    }
}
