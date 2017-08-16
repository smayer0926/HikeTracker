package dao;

import models.Locations;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oLocationsDao implements LocationsDao {
    private final Sql2o sql2o;
    public Sql2oLocationsDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    public void add(Locations locations) {
        String sql= ("INSERT INTO locations (locationDistance, locationDifficulty, locationCity, locationState, locationCountry ) VALUES (:locationDistance, :locationDifficulty, :locationCity, :locationState, :locationCountry) ");
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql)
                    .bind(locations)
                    .executeUpdate()
                    .getKey();
            locations.setId(id);
        } catch (Sql2oException e) {
            e.printStackTrace();
        }
    }

    public Locations findById(int id) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM locations WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Locations.class);
        }
    }

    public List<Locations> getAllLocations() {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM locations")
                    .executeAndFetch(Locations.class);
        }
    }

    public void update(String newLocationCity, String newLocationState, String newLocationCountry, int newLocationDistance, int newLocationDifficulty, int id) {
        String sql = "UPDATE locations SET (locationCity, locationState, locationCountry, locationDistance, locationDifficulty) = (:locationCity, :locationState, :locationCountry, :locationDistance, :locationDifficulty) WHERE id = :id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("locationCity", newLocationCity)
                    .addParameter("locationState", newLocationState)
                    .addParameter("locationCountry", newLocationCountry)
                    .addParameter("locationDistance", newLocationDistance)
                    .addParameter("locationDifficulty", newLocationDifficulty)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteById(int id) {
        String sql = "DELETE from locations WHERE id=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    public void clearAllLocations() {
        String sql = "DELETE from locations";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }





}
