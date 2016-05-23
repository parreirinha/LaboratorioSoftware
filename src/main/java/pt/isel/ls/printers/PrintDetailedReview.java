package pt.isel.ls.printers;

import pt.isel.ls.http.ExecutionServlet;
import pt.isel.ls.linecommand.model.Command;
import pt.isel.ls.model.Review;
import pt.isel.ls.printers.URIGenerator.URIUtils;
import pt.isel.ls.printers.html.HtmlPrinters;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Function;

/**
 * Ler Comentario PrintDetailedMovie
 */
public class PrintDetailedReview implements Printable {

    private final Command command;
    Collection<Review> review;
    private final String[] head =
            {"Review ID", "Movie ID", "Reviewer Name", "Review Rating", "Summary Review", "Complete Review"};
    private final ArrayList<Function<Review, String>> function = new ArrayList<>();
    private final String NoReview = "There is no such review.\n";

    public PrintDetailedReview(Collection<Review> r, Command command) {
        this.command = command;
        review = r;
        function.add(review -> "" + review.getReviewID());
        function.add(review -> "" + review.getMovieID());
        function.add(review -> review.getReviewName());
        function.add(review -> "" + review.getReviewRating());
        function.add(review -> review.getReviewSummary());
        function.add(review -> review.getCompleteReview());
    }

    /**
     * String template to be returned:
     * "Review ID = ##\n\tMovie ID = ##\n\tReviewer Name = ##\tReview Rating = ##\n\tSummary Review = ##\n\tComplete Review = ##\n"
     *
     * @return
     */
    @Override
    public String toStringText() {
        String str = "";
        for (Review r : review)
            str += head[0] + " = " + function.get(0).apply(r) +
                    "\n\t" + head[1] + " = " + function.get(1).apply(r) +
                    "\n\t" + head[2] + " = " + function.get(2).apply(r) +
                    "\t" + head[3] + " = " + function.get(3).apply(r) +
                    "\n\t" + head[4] + " = " + function.get(4).apply(r) +
                    "\n\t" + head[5] + " = " + function.get(5).apply(r) +
                    "\n";
        return (str == "") ? new PrintError(NoReview).toStringText() : str;
    }

    @Override
    public String toStringHtml() {
        /*
        if(movieCollection.size() == 1)
            return String.format(Printable.super.getTemplate(), getText());
        return String.format(Printable.super.getTemplate(), getTable());
        */
        String path = "", parameters = "", returnToReviews = "";
        int port =  ExecutionServlet.getPort();
        if (review.isEmpty())
            return String.format(HtmlPrinters.template, NoReview);
        ArrayList<String> uri = new ArrayList<>();
        review.forEach(x -> uri.add("http://localhost:"+port+"/movies/"+x.getMovieID()));

        path = "/movies/"+review.iterator().next().getMovieID()+"/reviews";
        parameters = "top="+command.getParams().getParamInt("top")+"&skip=0";
        returnToReviews = URIUtils.getURI(path, parameters, port, "All Reviews");
        String html = HtmlPrinters.htmlGenerate(review, head, function, uri);
        html += "\n\n"+
                returnToReviews + "\n";
        return String.format(HtmlPrinters.template, html);
    }
/*
    private String getTable()
    {
        String str = "<table border=\"1\" style=\"width:100%\">\n" +
                "\t"+getFullHtmlTitle()+"\n";
        for(Review r : review)
        {
            str += "\t"+getFullHtmlDescription(r)+"\n";
        }
        str += "</table>";
        return str;
    }

    private String getText()
    {
        Review r = review.iterator().next();
        String str = "<ul><li>"+head[0]+": "+function.get(0).apply(r)+"</li>\n" +
                "<ul>\n";
        for(int i = 1; i < head.length; ++i)
        {
            str += "<li>"+head[i]+": "+function.get(i).apply(r)+"</li>\n";
        }
        str += "</ul>\n" +
                "</ul>\n";
        return str;
    }

    private String getFullHtmlDescription(Review r)
    {
        String str = "<tr>\n";
        for(int i = 0; i < function.size(); ++i)
            str += "\t\t<td>"+function.get(i).apply(r)+"</td>\n";
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


