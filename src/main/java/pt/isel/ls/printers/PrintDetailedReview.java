package pt.isel.ls.printers;

import pt.isel.ls.linecommand.model.Command;
import pt.isel.ls.model.Review;
import pt.isel.ls.printers.URIGenerator.URIUtils;
import pt.isel.ls.printers.html.HtmlGenerator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Function;

public class PrintDetailedReview implements Printable
{

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
    public String toStringHtml()
    {
        HtmlGenerator htmlString = new HtmlGenerator();
        if (review.isEmpty())
            return String.format(htmlString.getTemplate(),
                        htmlString
                                .addString(NoReview)
                                .toString());
        ArrayList<String> uri = new ArrayList<>();
        review.forEach(x -> uri.add("/movies/"+x.getMovieID()));
        ArrayList<String> menu = new ArrayList<>();
        menu.add(URIUtils.getURI("/", null, "Home Page"));
        menu.add(URIUtils.getURI("/movies/"+review.iterator().next().getMovieID()+"/reviews",
                "top="+command.getParams().getParamInt("top")+"&skip=0", "All Reviews"));
        htmlString
                .createMenu(menu)
                .htmlGenerate(review, head, function, uri);

        return String.format(htmlString.getTemplate(), htmlString.toString());
    }

}


