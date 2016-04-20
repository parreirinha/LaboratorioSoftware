package pt.isel.ls.printers;

import java.util.function.Function;
import pt.isel.ls.model.Movie;

import java.util.Collection;

/**
 * Created by fabio on 05-Apr-16.
 */
public class PrintDetailedMovie implements Printable {


    private Collection<Movie> movieCollection;
    private final String[] head =
        {"Movie ID", "Name", "Release", "*", "**", "***", "****", "*****", "Average"};
    private Function<Movie, String >[] function = new Function[9];

    public PrintDetailedMovie(Collection<Movie> movieCollection){
        this.movieCollection = movieCollection;
        function[0] = movie -> "" + movie.getMovieID();
        function[1] = movie -> movie.getMovieName();
        function[2] = movie -> ""+movie.getMovieRelease();
        function[3] = movie -> "" + movie.getOneStar();
        function[4] = movie -> "" + movie.getTwoStar();
        function[5] = movie -> "" + movie.getThreeStar();
        function[6] = movie -> "" + movie.getFourStar();
        function[7] = movie -> "" + movie.getFiveStar();
        function[8] = movie -> "" + movie.getAverage();

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
             str += "Movie ID = "+function[0].apply(m) +"\n"+
                    "\tName = "+ function[1].apply(m) +
                    "\tRelease = "+ function[2].apply(m) +"\n"+
                    "\t* = " + function[3].apply(m) +
                    "   ** = " + function[4].apply(m) +
                    "   *** = " + function[5].apply(m) +
                    "   **** = " + function[6].apply(m) +
                    "   ***** = " + function[7].apply(m) +
                    "\n\tAverage = "+ function[8].apply(m) +"\n";
            /*str += "Movie ID = "+m.getMovieID() +"\n"+
                    "\tName = "+m.getMovieName() +
                    "\tRelease = "+m.getMovieRelease() +"\n"+
                    "\t* = " + m.getOneStar() +
                    "   ** = " + m.getTwoStar()+
                    "   *** = " + m.getThreeStar()+
                    "   **** = " + m.getFourStar() +
                    "   ***** = " + m.getFiveStar() +
                    "\n\tAverage = "+m.getAverage()+"\n";*/
        return (str == "") ? new PrintError("something went wrong!!\n").toStringText() : str;
    }

    @Override
    public String toStringHtml()
    {/*
        String str = "<table border=\"1\" style=\"width:100%\">\n" +
                "\t"+getFullHtmlTitle()+"\n";
        for(Movie m : movieCollection)
        {
            str += "\t"+getFullHtmlDescription(m)+"\n";
        }
        str += "</table>";
        return String.format(Printable.super.getHead(), str);*/

        return HtmlGenerator.htmlGenerate(movieCollection, head, function);
    }
/*
    private String getFullHtmlDescription(Movie m)
    {
        return "<tr>\n" +
                "\t\t<td>"+m.getMovieID()+"</td>\n" +
                "\t\t<td>"+m.getMovieName()+"</td> \n" +
                "\t\t<td>"+m.getMovieRelease()+"</td>\n" +
                "\t\t<td>"+m.getOneStar()+"</td>\n" +
                "\t\t<td>"+m.getTwoStar()+"</td>\n" +
                "\t\t<td>"+m.getThreeStar()+"</td>\n" +
                "\t\t<td>"+m.getFourStar()+"</td>\n" +
                "\t\t<td>"+m.getFiveStar()+"</td>\n" +
                "\t\t<td>"+m.getAverage()+"</td>\n" +
                "</tr>";
    }

    private String getFullHtmlTitle()
    {
    HtmlGenerator(movieCollection, new String[]{"Movie ID", "Name", "Release", "*", "**", "***", "****", "*****", "Average"});
        return "<tr>\n" +
                "\t\t<td>Movie ID</td>\n" +
                "\t\t<td>Name</td> \n" +
                "\t\t<td>Release</td>\n" +
                "\t\t<td>*</td>\n" +
                "\t\t<td>**</td>\n" +
                "\t\t<td>***</td>\n" +
                "\t\t<td>****</td>\n" +
                "\t\t<td>*****</td>\n" +
                "\t\t<td>Average</td>\n" +
                "</tr>";
    }*/

}
