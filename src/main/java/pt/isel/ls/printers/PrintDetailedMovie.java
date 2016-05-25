package pt.isel.ls.printers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.function.Function;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import pt.isel.ls.database.connection.ConnectionFactory;
import pt.isel.ls.exceptions.ApplicationException;
import pt.isel.ls.executioncommands.GetAllCollectionsWithIDAux;
import pt.isel.ls.executioncommands.GetAllReviewsAux;
import pt.isel.ls.http.ExecutionServlet;
import pt.isel.ls.linecommand.mapping.CommandMapper;
import pt.isel.ls.linecommand.model.Command;
import pt.isel.ls.model.Movie;
import pt.isel.ls.printers.URIGenerator.URIUtils;
import pt.isel.ls.printers.html.HtmlPrinters;

import java.util.Collection;


/**
 * .
 * / \
 * /   \
 * /  |  \
 * /   |   \
 * /    |    \
 * /     |     \
 * /      |      \
 * /       |       \
 * /        .        \
 * /                   \
 * ---------------------
 * <p>
 * <p>
 * <p>
 * <p>
 * Para o metodo toStringHtml() deixar de ser generico, basta trocar a ordem dos comentarios no metodo e,
 * Descomentar os metodos:
 * - getTable()
 * - getText()
 * - getFullHtmlDescription(Movie m)
 * -getFullHtmlTitle()
 * E também é necessario descomentar o metodo default getTemplate() da interface Printable
 */

public class PrintDetailedMovie implements Printable {


    private Collection<Movie> movieCollection;
    private Command command;
    private final String[] head =
            {"Movie ID", "Name", "Release", "*", "**", "***", "****", "*****", "Average"};
    private ArrayList<Function<Movie, String>> function = new ArrayList<>();
    private final String NoMovie = "There is no such movie.\n";

    public PrintDetailedMovie(Collection<Movie> movieCollection, Command command) {
        this.command = command;
        this.movieCollection = movieCollection;
        function.add(movie -> "" + movie.getMovieID());
        function.add(movie -> movie.getMovieName());
        function.add(movie -> "" + movie.getMovieRelease());
        function.add(movie -> "" + movie.getOneStar());
        function.add(movie -> "" + movie.getTwoStar());
        function.add(movie -> "" + movie.getThreeStar());
        function.add(movie -> "" + movie.getFourStar());
        function.add(movie -> "" + movie.getFiveStar());
        function.add(movie -> "" + movie.getAverage());
    }

    /**
     * String template to be returned:
     * "Movie ID = ##\n\tName = ##\tRelease = ##\n\t* = ##   ** = ##   *** = ##   **** = ##   ***** = ##\n\tAverage = ##\n"
     *
     * @return
     */
    @Override
    public String toStringText() {
        String str = "";
        for (Movie m : movieCollection)
            str += head[0] + " = " + function.get(0).apply(m) + "\n" +
                    "\t" + head[1] + " = " + function.get(1).apply(m) +
                    "\t" + head[2] + " = " + function.get(2).apply(m) + "\n" +
                    "\t" + head[3] + " = " + function.get(3).apply(m) +
                    "   " + head[4] + " = " + function.get(4).apply(m) +
                    "   " + head[5] + " = " + function.get(5).apply(m) +
                    "   " + head[6] + " = " + function.get(6).apply(m) +
                    "   " + head[7] + " = " + function.get(7).apply(m) +
                    "\n\t" + head[8] + " = " + function.get(8).apply(m) + "\n";
        return (str == "") ? new PrintError(NoMovie).toStringText() : str;
    }

    @Override
    public String toStringHtml() {
        /*
        if(movieCollection.size() == 1)
            return String.format(Printable.super.getTemplate(), getText());
        return String.format(Printable.super.getTemplate(), getTable());
        */

        if (movieCollection.isEmpty())
            return String.format(HtmlPrinters.template, NoMovie);

        String html = null;
        try {
            html = HtmlPrinters.htmlGenerate(movieCollection, head, function, new ArrayList<>())+
                    "<br>\n<br>\n"+
                    getConnections()+
                    "<br>\n";
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ApplicationException e) {
            e.printStackTrace();
        }

        return String.format(HtmlPrinters.template, html);
    }

    private String getConnections() throws SQLException, ApplicationException {
        int port = ExecutionServlet.getPort();
        String allMovies, rating, allReviews;
        allMovies = URIUtils.getURI("/movies/", "top="+command.getParams().getParamInt("top")+"&skip=0", port, "All Movies");
        rating = URIUtils.getURI("/movies/"+movieCollection.iterator().next().getMovieID()+"/ratings", null, port, "Rating");
        allReviews = URIUtils.getURI("/movies/"+movieCollection.iterator().next().getMovieID()+"/reviews/",
                "top="+command.getParams().getParamInt("top")+"&skip=0", port, "All Reviews");
        String html = allMovies+"\n<br>\n<br>\n"+
                rating+"\n<br>\n<br>\n";
        String aux = getAllReviews(allReviews);
        if(!aux.equals(""))
            html += aux +"\n<br>\n<br>\n";
        aux = getCollectionsWithMovie();
        if(!aux.equals(""))
            html += aux +"\n<br>\n<br>\n";
        return html;
    }

    private String getCollectionsWithMovie()
    {
        try {
            return new GetAllCollectionsWithIDAux().execute(new ConnectionFactory().getNewConnection(), command).toStringHtml();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
        return "";
    }

    private String getAllReviews(String allReviews) {
        try {
            String res = new GetAllReviewsAux().execute(new ConnectionFactory().getNewConnection(), command).toStringHtml();
            return (res == "") ? res : allReviews+"\n<br>\n<br>\n"+res;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
        return "";
    }
/*
    private String getTable()
    {
        String str = "\t\t\t<table border=\"1\" style=\"width:100%\">\n" +
                getFullHtmlTitle()+"\n";
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
        return str;
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
