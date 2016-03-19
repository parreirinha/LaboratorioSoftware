package pt.isel.ls.model;

/**
 * Class whose instances are used to represent a model of a movie review.
 */
public class Review
{
    private int movieID, reviewRating;
    private String reviewName, reviewSummary, completeReview;


    public Review(String reviewerName, String summary, String review, int reviewRating, int movieId) {
        this.reviewName = reviewerName;
        this.reviewSummary = summary;
        this.completeReview = review;
        this.reviewRating = reviewRating;
        this.movieID = movieId;
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


}
