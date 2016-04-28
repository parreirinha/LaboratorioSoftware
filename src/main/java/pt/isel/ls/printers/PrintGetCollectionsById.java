package pt.isel.ls.printers;

import pt.isel.ls.model.Movie;
import pt.isel.ls.model.MovieCollection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * "\nCollection id: #
 * \nCollection name: #
 * \nCollection description: #
 * \nCollection creation date: #
 * \n\nMovies in the collection:
 * \nMovie id: #\nMovie name: #
 *
 */
public class PrintGetCollectionsById implements Printable {

    private MovieCollection movieCollection;
    String[] head = {"Collection id", "Collection name", "Collection description", "Movie id", "Movie name"};

    public  PrintGetCollectionsById(MovieCollection movieCollection){
        this.movieCollection = movieCollection;
    }


    @Override
    public String toStringText() {
        String s =
                head[0] + ": " + movieCollection.getCollections().getCollectionID() +
                "\n"+ head[1] + ": " + movieCollection.getCollections().getName() +
                "\n"+head[2]+": " + movieCollection.getCollections().getDescription() +
                "\n\nMovies in the collection:";
        for (Movie movie : movieCollection.getMovies()) {
            s += "\n"+head[3]+": " + movie.getMovieID() + "\t"+head[4]+": " + movie.getMovieName();
        }
        return s+"\n";
    }

    @Override
    public String toStringHtml()
    {
        if(movieCollection.getMovies().size() == 1)
            return String.format(HtmlGenerator.template, getText());
        return String.format(HtmlGenerator.template, getTable());
    }

    private String getTable()
    {
        String str = "<table border=\"1\" style=\"width:100%\">\n" +
                "\t"+getFullHtmlTitle()+"\n";
        str += "\t"+getFullHtmlDescription()+"\n";
        str += "</table>";
        return str;
    }

    private String getText()
    {
        String str = "<ul><li>"+head[0]+": "+movieCollection.getCollections().getCollectionID()+"</li>\n" +
                "<ul>\n";
        str += "<li>"+ head[1] + ": " + movieCollection.getCollections().getName()+"</li>\n";
        str += "<li>"+head[2]+": " + movieCollection.getCollections().getDescription() +"</li>\n"+
                "<li>Movies in the collection:</li>" +
                "<ul>\n";
        for (Movie movie : movieCollection.getMovies()) {
            str += "<li>"+head[3]+": " + movie.getMovieID() +
                    "\t"+head[4]+": " + movie.getMovieName()+"</li>\n";
        }
        str += "</ul>";
        str += "</ul>\n" +
                "</ul>\n";
        return str;
    }

    private String getFullHtmlDescription()
    {
        String str = "<tr>\n" +
                "<td>"+movieCollection.getCollections().getCollectionID()+"</td>\n"+
                "<td>"+movieCollection.getCollections().getName()+"</td>\n"+
                "<td>"+movieCollection.getCollections().getDescription()+"</td>\n";
        int aux = 0;
        for(Movie m : movieCollection.getMovies())
        {
            if(aux > 0)
            {
                str += "<td></td>\n<td></td>\n<td></td>\n";
            }
            str += "<td>"+movieCollection.getCollections().getCollectionID()+"</td>\n"+
                    "<td>"+movieCollection.getCollections().getCollectionID()+"</td>\n";
            ++aux;
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


}
