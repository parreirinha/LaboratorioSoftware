package pt.isel.ls.database.printers;

import pt.isel.ls.model.Review;

/**
 * Created by fabio on 05-Apr-16.
 */
public class PrintGetReviewById implements  Printable{

    Review review;

    public PrintGetReviewById(Review r){
        review = r;
    }

    /**
     * String template to be returned:
     * "reviewid: ##\nmovie id: ##\nreviewer name: ##\nreview summary: ##\ncomplete review: ##\n"
     * @return
     */
    @Override
    public String toStringResult() {
        return (review == null) ? "something went wrong!!\n" :
                "reviewid: " + review.getReviewID() +
                "\nmovie id: " + review.getMovieID() +
                "\nreviewer name: " + review.getReviewName()+
                "\nreview rating: " + review.getReviewRating() +
                "\nreview summary: " + review.getReviewSummary() +
                "\ncomplete review: " +review.getCompleteReview() +
                "\n";
    }
}


