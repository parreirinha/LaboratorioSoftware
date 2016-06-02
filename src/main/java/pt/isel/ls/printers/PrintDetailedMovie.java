package pt.isel.ls.printers;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.function.Function;
import pt.isel.ls.database.connection.ConnectionFactory;
import pt.isel.ls.exceptions.ApplicationException;
import pt.isel.ls.executioncommands.GetAllCollectionsWithIDAux;
import pt.isel.ls.executioncommands.GetAllReviewsAux;
import pt.isel.ls.linecommand.model.Command;
import pt.isel.ls.model.Movie;
import pt.isel.ls.printers.URIGenerator.URIUtils;
import pt.isel.ls.printers.html.HtmlGenerator;
import java.util.Collection;

public class PrintDetailedMovie implements Printable
{

    private Collection<Movie> movieCollection;
    private Command command;
    private final String[] head =
            {"Movie ID", "Name", "Release", "*", "**", "***", "****", "*****", "Average"};
    private ArrayList<Function<Movie, String>> function = new ArrayList<>();
    private final String NoMovie = "There is no such movie.\n";
    private Connection con;

    public PrintDetailedMovie(Collection<Movie> movieCollection, Command command, Connection connection) {
        this.command = command;
        this.movieCollection = movieCollection;
        this.con = connection;
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
    public String toStringHtml()
    {
        HtmlGenerator htmlString = new HtmlGenerator();
        if (movieCollection.isEmpty())
            return String.format(htmlString.getTemplate(),
                                htmlString
                                        .addString(NoMovie)
                                        .toString());

        htmlString
                .htmlGenerate(movieCollection, head, function, new ArrayList<>())
                .addBrTag()
                .addBrTag()
                .addLink(
                        URIUtils.getURI("/movies/", "top="+command.getParams().getParamInt("top")+"&skip=0", "All Movies"))
                .addLink(
                        URIUtils.getURI("/movies/"+movieCollection.iterator().next().getMovieID()+"/ratings", null, "Rating"))
                .addLink(
                        URIUtils.getURI("/movies/"+movieCollection.iterator().next().getMovieID()+"/reviews/",
                            "top="+command.getParams().getParamInt("top")+"&skip=0", "All Reviews"))
                .addLink(URIUtils.getURI("/", null, "Home Page"))
                .addBrTag()
                .addBrTag()
                .addString(
                        getAllReviews(
                                URIUtils.getURI("/movies/"+movieCollection.iterator().next().getMovieID()+"/reviews/",
                                    "top="+command.getParams().getParamInt("top")+"&skip=0", "All Reviews")))
                .addBrTag()
                .addString(
                        getCollectionsWithMovie());

        return String.format(htmlString.getTemplate(), htmlString.toString());
    }


    private String getCollectionsWithMovie()
    {
        try {
            return new GetAllCollectionsWithIDAux().execute(con, command).toStringHtml();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
        return "";
    }

    private String getAllReviews(String allReviews) {
        try {
            return new GetAllReviewsAux().execute(con, command).toStringHtml();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
        return "";
    }

}
