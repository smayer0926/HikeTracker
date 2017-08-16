package dao;

import models.Hikes;
import models.Locations;

import java.util.List;

/**
 * Created by Guest on 8/16/17.
 */
public interface LocationsDao {


    //create
    void add (Locations category);

    //read
    List<Locations> getAllLocations();
    List<Hikes> getAllHikesByLocations (int categoryId);

    Locations findById(int id);

    //update
    void update (int id, String name);

    //delete
    void deleteById(int id);
    void clearAllCategories();
    void clearAllTasksByLocations(int categoryId);
}
