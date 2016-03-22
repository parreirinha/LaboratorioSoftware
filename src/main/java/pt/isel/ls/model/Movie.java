package pt.isel.ls.model;

/**
 * Class whose instances are used to represent a model of a movie.
 */
public class Movie {

    private int movieID, movieRelease;
    private String movieName;
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

    public Movie(int oneStar, int twoStar, int treeStar, int fourStar, int fiveStar) {
        this.oneStar = oneStar;
        this.twoStar = twoStar;
        this.treeStar = treeStar;
        this.fourStar = fourStar;
        this.fiveStar = fiveStar;
        movieName = null;
    }

    public float average() {
        return (oneStar * 1 + twoStar * 2 + treeStar * 3 + fourStar * 4 + fiveStar * 5) / (oneStar + twoStar + treeStar + fourStar + fiveStar);
    }

    public void incrementStar(int star) {
        if (star < 1 || star > 5)
            return;
        switch (star) {
            case 1:
                ++oneStar;
                break;

            case 2:
                ++twoStar;
                break;

            case 3:
                ++treeStar;
                break;

            case 4:
                ++fourStar;
                break;

            case 5:
                ++fiveStar;
                break;
        }
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
