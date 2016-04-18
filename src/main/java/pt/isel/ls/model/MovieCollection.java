package pt.isel.ls.model;

/**
 * Created by fabio on 17-Apr-16.
 */
public class MovieCollection {

    private int movieID, collectionID;
    private String addedDate;

    public MovieCollection(int mid, int cid, String date){
        movieID = mid;
        collectionID = cid;
        addedDate = date;
    }

    public int getMovieID() {
        return movieID;
    }

    public int getCollectionID() {
        return collectionID;
    }

    public String getAddedDate() {
        return addedDate;
    }
}
