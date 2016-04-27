package pt.isel.ls.printers;

import pt.isel.ls.model.Movie;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Function;

/**
 * Ler Comentario PrintDetailedMovie
 */

public class PrintMovie implements Printable {

    private Collection<Movie> movieCollection;
    private final String[] head =
            {"Movie ID", "Name", "Release"};
    private ArrayList<Function<Movie, String >> function = new ArrayList<>();

    public PrintMovie(Collection<Movie> movies){
        movieCollection = movies;
        function.add(movie -> "" + movie.getMovieID());
        function.add(movie -> movie.getMovieName());
        function.add(movie -> ""+movie.getMovieRelease());
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
        for (Movie m : movieCollection) {
            s +=    head[0] + " = " + function.get(0).apply(m)+
                    "\n\t"+ head[1] +" = " + function.get(1).apply(m)+
                    "\t"+ head[2] +" = " + function.get(2).apply(m)+
                    "\n";
        }
        return (s == "") ? new PrintError("something went wrong!!\n").toStringText() : s;
    }

    @Override
    public String toStringHtml() {
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
        return str;
    }

    private String getText()
    {
        Movie m = movieCollection.iterator().next();
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
        {
            str += "\t\t<td>"+function.get(i).apply(m)+"</td>\n";
        }
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
