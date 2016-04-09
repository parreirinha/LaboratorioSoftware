package pt.isel.ls.model;

/**
 * Class whose instances are used to represent a model of a movie.
 */
public class Movie {

    private int movieID, movieRelease;
    public String movieName;
    private int oneStar, twoStar, treeStar, fourStar, fiveStar;
    private float average;
    
    public Movie(int id, String movieName, int release, int oneStar, int twoStar, int treeStar, int fourStar, int fiveStar) {
        this.movieID = id;
        this.movieName = movieName;
        this.movieRelease = release;
        this.oneStar = oneStar;
        this.twoStar = twoStar;
        this.treeStar = treeStar;
        this.fourStar = fourStar;
        this.fiveStar = fiveStar;
    }

    public Movie(int id, String movieName, int release, int oneStar, int twoStar, int treeStar, int fourStar, int fiveStar, float avge) {
        this.movieID = id;
        this.movieName = movieName;
        this.movieRelease = release;
        this.oneStar = oneStar;
        this.twoStar = twoStar;
        this.treeStar = treeStar;
        this.fourStar = fourStar;
        this.fiveStar = fiveStar;
        this.average = avge;
    }

    public Movie(String name) {
        movieName = name;
    }

    public Movie(String name, int date) {
        movieName = name;
        movieRelease = date;
    }

    public Movie(int id,int oneStar, int twoStar, int treeStar, int fourStar, int fiveStar) {
        movieID = id;
        this.oneStar = oneStar;
        this.twoStar = twoStar;
        this.treeStar = treeStar;
        this.fourStar = fourStar;
        this.fiveStar = fiveStar;
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

    public int getTreeStar() {
        return treeStar;
    }

    public int getFourStar()
    {
        return fourStar;
    }

    public String getMovieName() {
        return movieName;
    }

    public int getFiveStar()
    {
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
