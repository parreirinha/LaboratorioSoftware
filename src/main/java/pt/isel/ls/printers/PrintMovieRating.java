package pt.isel.ls.printers;

import pt.isel.ls.model.Movie;

import java.util.Collection;
import java.util.function.Function;

/**
 * Created by fabio on 05-Apr-16.
 */
public class PrintMovieRating implements Printable {

    private Collection<Movie> movie;
    private final String[] head =
            {"The average rating for the movie with the ID",
                    "is", "*", "**", "***", "****", "*****"};
    private Function<Movie, String >[] function = new Function[7];

    public PrintMovieRating(Collection<Movie> m){

        movie = m;
        function[0] = movie -> "" + movie.getMovieID();
        function[1] = movie -> "" + movie.getAverage();
        function[2] = movie -> "" + movie.getOneStar();
        function[3] = movie -> "" + movie.getTwoStar();
        function[4] = movie -> "" + movie.getThreeStar();
        function[5] = movie -> "" + movie.getFourStar();
        function[6] = movie -> "" + movie.getFiveStar();
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
            str += head[0] +" "+ function[0].apply(m) +
                    " "+head[1]+" "+ function[1].apply(m) + ".\n" +
                    "\t"+head[2]+" = " + function[2].apply(m) +
                    "   "+head[3]+" = " + function[3].apply(m) +
                    "   "+head[4]+" = " + function[4].apply(m) +
                    "   "+head[5]+" = " + function[5].apply(m) +
                    "   "+head[6]+" = " + function[6].apply(m) + "\n";
        return (str == "") ? new PrintError("something went wrong!!\n").toStringText() : str;
    }

    @Override
    public String toStringHtml() {

        /*
        if(movieCollection.size() == 1)
            return String.format(Printable.super.getTemplate(), getText());
        return String.format(Printable.super.getTemplate(), getTable());
        */
        return HtmlGenerator.htmlGenerate(movie, head, function);
    }

    /*
    private String getTable()
    {
        String str = "<table border=\"1\" style=\"width:100%\">\n" +
                "\t"+getFullHtmlTitle()+"\n";
        for(Movie m : movie)
        {
            str += "\t"+getFullHtmlDescription(m)+"\n";
        }
        str += "</table>";
        return String.format(Printable.super.getTemplate(), str);
    }

    private String getText()
    {
        Movie m = movie.iterator().next();
        String str = "<ul><li>"+head[0]+": "+function[0].apply(m)+"</li>\n" +
                "<ul>\n";
        for(int i = 1; i < head.length; ++i)
        {
            str += "<li>"+head[i]+": "+function[i].apply(m)+"</li>\n";
        }
        str += "</ul>\n" +
                "</ul>\n";
        return str;
    }

    private String getFullHtmlDescription(Movie m)
    {
        String str = "<tr>\n";
        for(int i = 0; i < function.length(); ++i)
            str += ""\t\t<td>"+function[i].apply(m)+"</td>\n"";
    }

    private String getFullHtmlTitle()
    {
        String str = "<tr>\n";
        for(int i = 0; i < head.length(); ++i)
            str += "\t\t<td>head[i]</td>\n";
        return str + "</tr>\n";
    }
    */
}
