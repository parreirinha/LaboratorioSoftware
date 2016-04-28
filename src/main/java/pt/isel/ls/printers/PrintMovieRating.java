package pt.isel.ls.printers;

import pt.isel.ls.model.Movie;

import java.util.ArrayList;
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
    private ArrayList<Function<Movie, String >> function = new ArrayList<>();

    public PrintMovieRating(Collection<Movie> m){

        movie = m;
        function.add(movie -> "" + movie.getMovieID());
        function.add(movie -> "" + movie.getAverage());
        function.add(movie -> "" + movie.getOneStar());
        function.add(movie -> "" + movie.getTwoStar());
        function.add(movie -> "" + movie.getThreeStar());
        function.add(movie -> "" + movie.getFourStar());
        function.add(movie -> "" + movie.getFiveStar());
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
            str += head[0] + " " + function.get(0).apply(m) +
                    " "+head[1]+" "+ function.get(1).apply(m) + ".\n" +
                    "\t" +head[2]+" = " + function.get(2).apply(m) +
                    "   "+head[3]+" = " + function.get(3).apply(m) +
                    "   "+head[4]+" = " + function.get(4).apply(m) +
                    "   "+head[5]+" = " + function.get(5).apply(m) +
                    "   "+head[6]+" = " + function.get(6).apply(m) + "\n";
        return (str == "") ? new PrintError("There is no such movie.\n").toStringText() : str;
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
        return str;
    }

    private String getText()
    {
        Movie m = movie.iterator().next();
        String str = "<ul><li>"+head[0]+": "+function.get(0).apply(m)+"</li>\n" +
                "<ul>\n";
        for(int i = 1; i < head.length; ++i)
        {
            str += "<li>"+head[i]+": "+function.get(i).apply(m)+"</li>\n";
        }
        str += "</ul>\n" +
                "</ul>\n";
        return str;
    }

    private String getFullHtmlDescription(Movie m)
    {
        String str = "<tr>\n";
        for(int i = 0; i < function.size(); ++i)
            str += "\t\t<td>"+function.get(i).apply(m)+"</td>\n";
        return str + "</tr>\n";
    }

    private String getFullHtmlTitle()
    {
        String str = "<tr>\n";
        for(int i = 0; i < head.length; ++i)
            str += "\t\t<td>"+head[i]+"</td>\n";
        return str + "</tr>\n";
    }
*/
}
