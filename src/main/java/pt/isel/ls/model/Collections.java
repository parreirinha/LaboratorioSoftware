package pt.isel.ls.model;

/**
 * Created by fabio on 17-Apr-16.
 */
public class Collections {

    private int collectionID;
    private String name, description, creationDate;

    public Collections(int id, String name, String desc, String date){
        collectionID = id;
        this.name = name;
        description = desc;
        creationDate = date;
    }

    public int getCollectionID() {
        return collectionID;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCreationDate() {
        return creationDate;
    }
}
