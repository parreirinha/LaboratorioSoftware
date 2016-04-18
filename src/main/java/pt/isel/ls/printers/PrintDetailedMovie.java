package pt.isel.ls.printers;

import pt.isel.ls.model.Movie;

import java.util.Collection;

/**
 * Created by fabio on 05-Apr-16.
 */
public class PrintDetailedMovie implements Printable {


    private Collection<Movie> movieCollection;


    public PrintDetailedMovie(Collection<Movie> movieCollection){
        this.movieCollection = movieCollection;
    }
    /**
     * String template to be returned:
     * "Movie ID = ##\n\tName = ##\tRelease = ##\n\t* = ##   ** = ##   *** = ##   **** = ##   ***** = ##\n\tAverage = ##\n"
     * @return
     */
    @Override
    public String toStringText() {
        String str = "";
        for(Movie m : movieCollection)
            str += "Movie ID = "+m.getMovieID() +"\n"+
                    "\tName = "+m.movieName +
                    "\tRelease = "+m.getMovieRelease() +"\n"+
                    "\t* = " + m.getOneStar() +
                    "   ** = " + m.getTwoStar()+
                    "   *** = " + m.getThreeStar()+
                    "   **** = " + m.getFourStar() +
                    "   ***** = " + m.getFiveStar() +
                    "\n\tAverage = "+m.getAverage()+"\n";
        return (str == "") ? new PrintError("something went wrong!!\n").toStringText() : str;
    }

    @Override
    public String toStringHtml(String[] head)
    {
        return null;
    }
}
