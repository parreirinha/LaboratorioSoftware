package pt.isel.ls.printers;

import pt.isel.ls.model.Review;

import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import java.util.Collection;

/**
 * Created by fabio on 05-Apr-16.
 */
public class PrintDetailedReview implements  Printable{

    Collection<Review> review;

    public PrintDetailedReview(Collection<Review> r){
        review = r;
    }

    /**
     * String template to be returned:
     * "Review ID = ##\n\tMovie ID = ##\n\tReviewer Name = ##\tReview Rating = ##\n\tSummary Review = ##\n\tComplete Review = ##\n"
     * @return
     */
    @Override
    public String toStringResult() {
        String str = "";
        for(Review r : review)
            str += "Review ID = " + r.getReviewID() +
                    "\n\tMovie ID = " + r.getMovieID() +
                    "\n\tReviewer Name = " + r.getReviewName()+
                    "\tReview Rating = " + r.getReviewRating() +
                    "\n\tSummary Review = " + r.getReviewSummary() +
                    "\n\tComplete Review = " +r.getCompleteReview() +
                    "\n";
        return (str == "") ? new PrintError("something went wrong!!\n").toStringResult() : str;
    }
}


