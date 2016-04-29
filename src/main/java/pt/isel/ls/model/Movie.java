package pt.isel.ls.model;

import java.sql.Date;

/**
 * Class whose instances are used to represent a model of a movie.
 */
public class Movie {

    private int movieID, movieRelease;
    public String movieName;
    private int oneStar, twoStar, treeStar, fourStar, fiveStar;
    private float average;

    public Movie(int id, String name) {
        movieID = id;
        movieName = name;
    }

    public Movie(int id, String movieName, int release, int[] stars) {
        this.movieID = id;
        this.movieName = movieName;
        this.movieRelease = release;
        this.oneStar = stars[0];
        this.twoStar = stars[1];
        this.treeStar = stars[2];
        this.fourStar = stars[3];
        this.fiveStar = stars[4];
    }

    public Movie(int id, String movieName, int release, int[] stars, float avge) {
        this.movieID = id;
        this.movieName = movieName;
        this.oneStar = stars[0];
        this.twoStar = stars[1];
        this.treeStar = stars[2];
        this.fourStar = stars[3];
        this.fiveStar = stars[4];
        this.average = avge;
        movieRelease = release;
    }

    public Movie(String name) {
        movieName = name;
    }

    public Movie(String name, int date) {
        movieName = name;
        movieRelease = date;
    }

    public Movie(int id, int[] stars, float avge) {
        movieID = id;
        this.oneStar = stars[0];
        this.twoStar = stars[1];
        this.treeStar = stars[2];
        this.fourStar = stars[3];
        this.fiveStar = stars[4];
        this.average = avge;
        movieName = null;
    }

    public Movie(int mid, String moviename, int movieyear) {
        movieID = mid;
        movieName = moviename;
        movieRelease = movieyear;
    }

    public int getOneStar() {
        return oneStar;
    }

    public int getTwoStar() {
        return twoStar;
    }

    public int getThreeStar() {
        return treeStar;
    }

    public int getFourStar() {
        return fourStar;
    }

    public String getMovieName() {
        return movieName;
    }

    public int getFiveStar() {
        return fiveStar;
    }

    public int getMovieRelease() {
        return movieRelease;
    }

    public int getMovieID() {
        return movieID;
    }

    public float getAverage() {
        return average;
    }


}
