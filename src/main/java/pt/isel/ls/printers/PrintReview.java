package pt.isel.ls.printers;

import pt.isel.ls.linecommand.model.Command;
import pt.isel.ls.model.Review;
import pt.isel.ls.printers.URIGenerator.URIUtils;
import pt.isel.ls.printers.html.HtmlGenerator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Function;

/**
 * Class used to print the brief Review.
 */
public class PrintReview implements Printable {

    private final Command command;
    private Collection<Review> reviews;
    private final String[] head =
            {"Review ID", "Reviewer Name", "Review Rating", "Summary Review"};
    private final ArrayList<Function<Review, String>> function = new ArrayList<>();
    private final String NoReview = "There is no such review.\n";

    public PrintReview(Collection<Review> reviews, Command command) {
        this.command = command;
        this.reviews = reviews;
        function.add(review -> "" + review.getReviewID());
        function.add(review -> review.getReviewName());
        function.add(review -> "" + review.getReviewRating());
        function.add(review -> review.getReviewSummary());
    }


    /**
     * String template to be returned:
     * "Review ID = #\n\tReviewer Name = ##\tReview Rating = ##\n\tSummary Review = ##\n"
     *
     * @return
     */
    @Override
    public String toStringText() {
        String s = "";
        for (Review r : reviews) {
            s += head[0] + " = " + r.getReviewID() +
                    "\n\t" + head[1] + " = " + r.getReviewName() +
                    "\t" + head[2] + " = " + r.getReviewRating() +
                    "\n\t" + head[3] + " = " + r.getReviewSummary() +
                    "\n";
        }
        return (s == "") ? new PrintError(NoReview).toStringText() : s;
    }

    @Override
    public String toStringHtml()
    {
        HtmlGenerator htmlString = new HtmlGenerator();
        if (reviews.isEmpty())
            return String.format(htmlString.getTemplate(), htmlString.addString(NoReview));

        ArrayList<String> uri = new ArrayList<>();
        reviews.forEach(x -> uri.add("/movies/"+x.getMovieID()+"/reviews/"+x.getReviewID()));
        ArrayList<String> menu = new ArrayList<>();
        menu.add(URIUtils.getURI("/", null, "Home"));
        menu.add(URIUtils.getURI("/movies/"+reviews.iterator().next().getMovieID(), null, "Movie"));
        htmlString
                .createMenu(menu)
                .htmlGenerate(reviews, head, function, uri)
                .addBrTag();

        String prev = URIUtils.getPreviusSkipAndTopValuesFromCommand(command);
        if(prev != null)
                htmlString
                        .createPagging(
                            URIUtils.getURI("/movies/"+reviews.iterator().next().getMovieID()+"/reviews/",
                                    prev,
                                    "Previous"),
                            URIUtils.getURI("/movies/"+reviews.iterator().next().getMovieID()+"/reviews/",
                                    URIUtils.getNextSkipAndTopValuesFromCommand(command),
                                    "Next"))
                        .addBrTag();
        else
            htmlString
                    .createPagging(null,
                            URIUtils.getURI("/movies/"+reviews.iterator().next().getMovieID()+"/reviews/",
                                    URIUtils.getNextSkipAndTopValuesFromCommand(command),
                                    "Next"))
                    .addBrTag();
        htmlString
                .addBrTag()
                .postNewReview(reviews.iterator().next().getMovieID());
        return String.format(htmlString.getTemplate(), htmlString.toString());
    }

}
