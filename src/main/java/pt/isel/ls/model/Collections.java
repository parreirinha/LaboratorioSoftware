package pt.isel.ls.model;

import java.sql.Date;

/**
 * Created by fabio on 17-Apr-16.
 */
public class Collections {

    private int collectionID;
    private String name, description;


    public Collections(int id, String name, String desc){
        collectionID = id;
        this.name = name;
        description = desc;

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

}
