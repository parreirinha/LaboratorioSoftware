package pt.isel.ls.printers;

import pt.isel.ls.model.Movie;

import java.util.Collection;

/**
 * Created by fabio on 05-Apr-16.
 */
public class PrintMovieRating implements Printable {

    private Collection<Movie> movie;

    public PrintMovieRating(Collection<Movie> m){
        movie = m;
    }


    /**
     * for tests template of string. #->replace by values
     * "The average rating for the movie with the ID ## is ##.\n\t* = ##   ** = ##   *** = ##   **** = ##   ***** = ##\n"
     * @return
     */
    @Override
    public String toStringText() {
        String str = "";
        for(Movie m : movie)
            str += "The average rating for the movie with the ID " + m.getMovieID() +
                    " is " +m.getAverage()+ ".\n" +
                    "\t* = " + m.getOneStar() +
                    "   ** = " + m.getTwoStar()+
                    "   *** = " + m.getThreeStar()+
                    "   **** = " + m.getFourStar() +
                    "   ***** = " + m.getFiveStar() + "\n";
        return (str == "") ? new PrintError("something went wrong!!\n").toStringText() : str;
    }

    @Override
    public String toStringHtml() {
        return null;
    }
}
