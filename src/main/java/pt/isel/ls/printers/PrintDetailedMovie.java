package pt.isel.ls.printers;

import java.util.function.Function;
import pt.isel.ls.model.Movie;

import java.util.Collection;

/**
*
*                                               .
*                                              / \
*                                             /   \
*                                            /  |  \
*                                           /   |   \
*                                          /    |    \
*                                         /     |     \
*                                        /      |      \
*                                       /       |       \
*                                      /        .        \
*                                     /                   \
*                                     ---------------------
*
*
*
*
* Para o metodo toStringHtml() deixar de ser generico, basta trocar a ordem dos comentarios no metodo e,
* Descomentar os metodos:
*                       - getTable()
*                       - getText()
*                       - getFullHtmlDescription(Movie m)
*                       -getFullHtmlTitle()
*E também é necessario descomentar o metodo default getTemplate() da interface Printable
*
 *
*/

public class PrintDetailedMovie implements Printable
{


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
             str += head[0] + " = "+function[0].apply(m) +"\n"+
                     head[1] + " = "+ function[1].apply(m) +
                    "\t" + head[2] +" = "+ function[2].apply(m) +"\n"+
                    "\t"+head[3]+" = " + function[3].apply(m) +
                    "   "+head[4]+" = " + function[4].apply(m) +
                    "   "+head[5]+" = " + function[5].apply(m) +
                    "   "+head[6]+" = " + function[6].apply(m) +
                    "   "+head[7]+" = " + function[7].apply(m) +
                    "\n\t"+head[8]+" = "+ function[8].apply(m) +"\n";
        return (str == "") ? new PrintError("something went wrong!!\n").toStringText() : str;
    }

    @Override
    public String toStringHtml()
    {
        /*
        if(movieCollection.size() == 1)
            return String.format(Printable.super.getTemplate(), getText());
        return String.format(Printable.super.getTemplate(), getTable());
        */
        return HtmlGenerator.htmlGenerate(movieCollection, head, function);
    }

    /*
    private String getTable()
    {
        String str = "<table border=\"1\" style=\"width:100%\">\n" +
                "\t"+getFullHtmlTitle()+"\n";
        for(Movie m : movieCollection)
        {
            str += "\t"+getFullHtmlDescription(m)+"\n";
        }
        str += "</table>";
        return String.format(Printable.super.getTemplate(), str);
    }

    private String getText()
    {
        Movie m = movieCollection.iterator().next();
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
