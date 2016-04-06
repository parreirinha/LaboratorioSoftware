package pt.isel.ls.database.printers;

import pt.isel.ls.model.Review;

import java.util.Collection;

/**
 * Created by fabio on 05-Apr-16.
 */
public class PrintGetAllReviews implements Printable {

    private Collection<Review> reviews;

    public PrintGetAllReviews(Collection<Review> reviews){
        this.reviews = reviews;
    }


    /**
     * String template to be returned:
     * "reviewid: #;\nreviewer name: #;\nreview sumary: #####;\nreview rating: ##;\n"
     * @return
     */
    @Override
    public String toStringResult() {
        String s = null;
        for (Review r: reviews) {
            s +=    "reviewid: "+ r.getReviewID() +
                    ";\nreviewer name: " + r.getReviewName() +
                    ";\nreview sumary: " + r.getReviewSummary() +
                    ";\nreview rating: " + r.getReviewRating() +
                    ";\n";
        }
        return (s == null) ? "something went wrong!!\n" : s;
    }


}
