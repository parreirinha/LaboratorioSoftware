package pt.isel.ls.model;

/**
 * Created by Utilizador on 16/03/2016.
 */
public class Movie
{
    public final int movieID, movieRelease;
    private int oneStar, twoStar, treeStar, fourStar, fiveStar;
    public final String movieName;

    public Movie(int id, int release, int oneStar, int twoStar, int treeStar, int fourStar, int fiveStar, String movieName)
    {
        this.movieID = id;
        this.movieName = movieName;
        this.movieRelease = release;
        this.oneStar = oneStar;
        this.twoStar = twoStar;
        this.treeStar = treeStar;
        this.fourStar = fourStar;
        this.fiveStar = fiveStar;
    }

    public double average()
    {
        return (oneStar*1 + twoStar*2 + treeStar*3 + fourStar*4 + fiveStar*5)/(oneStar+twoStar+treeStar+fourStar+fiveStar);
    }

    public void incrementStar(int star)
    {
        if(star < 1 || star > 5)
            return;
        switch (star)
        {
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

    public int getOneStar()
    {
        return oneStar;
    }

    public int getTwoStar()
    {
        return twoStar;
    }

    public int getTreeStar()
    {
        return treeStar;
    }

    public int getFourStar()
    {
        return fourStar;
    }

    public int getFiveStar()
    {
        return fiveStar;
    }

}
