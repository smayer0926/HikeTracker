package dao;

import models.Hikes;
import models.Locations;

import java.util.List;


public interface LocationsDao {


    //create
    void add(Locations locations);

    //read
    List<Locations> getAllLocations();
   List<Hikes> getAllHikesByLocations (int locationId);

    Locations findById(int id);

    //update
    void update (String newLocationCity, String newLocationState, String newLocationCountry, int newLocationDistance, int newLocationDifficulty, int id);

    //delete
    void deleteById(int id);
//    void clearAllCategories();
//    void clearAllTasksByLocations(int id);
}
