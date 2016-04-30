package pt.isel.ls.model;

/**
 * Class whose instances are used to represent a model of a movie review.
 */
public class Review {
    private int movieID;
    private int reviewRating;
    private int reviewID;
    private String reviewName, reviewSummary, completeReview;


    public Review(int reviewID, int movieID, String reviewName, String reviewSummary, String completeReview, int reviewRating) {

        this.reviewID = reviewID;
        this.movieID = movieID;
        this.reviewName = reviewName;
        this.reviewSummary = reviewSummary;
        this.completeReview = completeReview;
        this.reviewRating = reviewRating;
    }


    public int getMovieID() {
        return movieID;
    }

    public int getReviewRating() {
        return reviewRating;
    }

    public String getReviewName() {
        return reviewName;
    }

    public String getReviewSummary() {
        return reviewSummary;
    }

    public String getCompleteReview() {
        return completeReview;
    }


    public Review(int reviewID, int movieID, String reviewName, String reviewSummary, int reviewRating) {
        this.reviewID = reviewID;
        this.movieID = movieID;
        this.reviewName = reviewName;
        this.reviewSummary = reviewSummary;
        this.reviewRating = reviewRating;
        completeReview = null;
    }

    public int getReviewID() {
        return reviewID;
    }
}
