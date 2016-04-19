package pt.isel.ls.printers;

import pt.isel.ls.model.Review;

import java.util.Collection;

/**
 * Created by fabio on 05-Apr-16.
 */
public class PrintReview implements Printable {

    private Collection<Review> reviews;

    public PrintReview(Collection<Review> reviews){
        this.reviews = reviews;
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
            s += "Review ID = " + r.getReviewID() +
                    "\n\tReviewer Name = " + r.getReviewName()+
                    "\tReview Rating = " + r.getReviewRating() +
                    "\n\tSummary Review = " + r.getReviewSummary() +
                    "\n";
        }
        return (s == "") ? new PrintError("something went wrong!!\n").toStringText() : s;
    }

    @Override
    public String toStringHtml() {
        return null;
    }


}
