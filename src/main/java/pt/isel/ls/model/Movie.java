package pt.isel.ls.model;

/**
 * Class whose instances are used to represent a model of a movie.
 */
public class Movie {

    private int movieID, movieRelease;
    public String movieName;
    private int oneStar, twoStar, treeStar, fourStar, fiveStar;

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

    public float average() {
        return (oneStar * 1 + twoStar * 2 + treeStar * 3 + fourStar * 4 + fiveStar * 5) / (oneStar + twoStar + treeStar + fourStar + fiveStar);
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

    public int getMovieReleaseYear() {
        return movieRelease;
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
}
