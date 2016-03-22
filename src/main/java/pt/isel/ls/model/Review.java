package pt.isel.ls.model;

/**
 * Created by Tede on 16/03/2016.
 */
public class Review
{
    public final int reviewID, movieID, reviewRating;
    public final String reviewName, reviewSummary, completeReview;

    public Review(int reviewID, int movieID, String reviewName, String reviewSummary, String completeReview, int reviewRating)
    {
        this.reviewID = reviewID;
        this.movieID = movieID;
        this.reviewRating = reviewRating;
        this.reviewName = reviewName;
        this.reviewSummary = reviewSummary;
        this.completeReview = completeReview;
    }

    public Review(int reviewID, int movieID, String reviewName, String reviewSummary, int reviewRating) {
        this.reviewID = reviewID;
        this.movieID = movieID;
        this.reviewName = reviewName;
        this.reviewSummary = reviewSummary;
        this.reviewRating = reviewRating;
        completeReview = null;
    }
}
