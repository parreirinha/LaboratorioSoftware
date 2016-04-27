package pt.isel.ls.printers;

import pt.isel.ls.model.Review;

import java.util.Collection;
import java.util.function.Function;

/**
 * Ler Comentario PrintDetailedMovie
 */
public class PrintDetailedReview implements  Printable{

    Collection<Review> review;
    private final String[] head =
            {"Review ID", "Movie ID", "Reviewer Name", "Review Rating", "Summary Review", "Complete Review"};
    private final Function<Review, String >[] function = new Function[6];

    public PrintDetailedReview(Collection<Review> r){
        review = r;
        function[0] = review -> "" + review.getReviewID();
        function[1] = review -> "" + review.getMovieID();
        function[2] = review -> review.getReviewName();
        function[3] = review -> "" + review.getReviewRating();
        function[4] = review -> review.getReviewSummary();
        function[5] = review -> review.getCompleteReview();
    }

    /**
     * String template to be returned:
     * "Review ID = ##\n\tMovie ID = ##\n\tReviewer Name = ##\tReview Rating = ##\n\tSummary Review = ##\n\tComplete Review = ##\n"
     * @return
     */
    @Override
    public String toStringText() {
        String str = "";
        for(Review r : review)
            str += head[0] + " = " + function[0].apply(r) +
                    "\n\t"+ head[1] +" = " + function[1].apply(r) +
                    "\n\t"+ head[2] +" = " + function[2].apply(r) +
                    "\t"+ head[3] +" = " + function[3].apply(r) +
                    "\n\t"+ head[4] +" = " + function[4].apply(r) +
                    "\n\t"+ head[5] +" = " + function[5].apply(r) +
                    "\n";
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
        return HtmlGenerator.htmlGenerate(review, head, function);
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
        return String.format(Printable.super.getTemplate(), str);
    }

    private String getText()
    {
        Review r = review.iterator().next();
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


