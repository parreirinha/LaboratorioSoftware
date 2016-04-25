package pt.isel.ls.printers;

import pt.isel.ls.model.Review;

import java.util.Collection;
import java.util.function.Function;

/**
 * Created by fabio on 05-Apr-16.
 */
public class PrintReview implements Printable {

    private Collection<Review> reviews;
    private final String[] head =
            {"Review ID", "Reviewer Name", "Review Rating", "Summary Review"};
    private final Function<Review, String >[] function = new Function[4];

    public PrintReview(Collection<Review> reviews){
        this.reviews = reviews;
        function[0] = review -> "" + review.getReviewID();
        function[1] = review -> review.getReviewName();
        function[2] = review -> "" +review.getReviewRating();
        function[3] = review -> review.getReviewSummary();
    }


    /**
     * String template to be returned:
     * "Review ID = #\n\tReviewer Name = ##\tReview Rating = ##\n\tSummary Review = ##\n"
     * @return
     */
    @Override
    public String toStringText() {
        String s ="";
        for (Review r: reviews) {
            s += head[0] +" = " + r.getReviewID() +
                    "\n\t"+head[1]+" = " + r.getReviewName()+
                    "\t"+head[2]+" = " + r.getReviewRating() +
                    "\n\t"+head[3]+" = " + r.getReviewSummary() +
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
        return HtmlGenerator.htmlGenerate(reviews, head, function);
    }
    /*
    private String getTable()
    {
        String str = "<table border=\"1\" style=\"width:100%\">\n" +
                "\t"+getFullHtmlTitle()+"\n";
        for(Review r : reviews)
        {
            str += "\t"+getFullHtmlDescription(r)+"\n";
        }
        str += "</table>";
        return String.format(Printable.super.getTemplate(), str);
    }

    private String getText()
    {
        Review r = reviews.iterator().next();
        String str = "<ul><li>"+head[0]+": "+function[0].apply(r)+"</li>\n" +
                "<ul>\n";
        for(int i = 1; i < head.length; ++i)
        {
            str += "<li>"+head[i]+": "+function[i].apply(r)+"</li>\n";
        }
        str += "</ul>\n" +
                "</ul>\n";
        return str;
    }

    private String getFullHtmlDescription(Review r)
    {
        String str = "<tr>\n";
        for(int i = 0; i < function.length(); ++i)
            str += ""\t\t<td>"+function[i].apply(r)+"</td>\n"";
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
