package dao;


import models.Hikes;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oHikesDao implements HikesDao {
    private final Sql2o sql2o;
    public Sql2oHikesDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }
    @Override
    public void add(Hikes hikes) {
        String sql = "INSERT INTO hikes (hikeName, hikeLocation, hikeNotes, hikeRating) VALUES (:hikeName, :hikeLocation, :hikeNotes, :hikeRating)";
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql)
                    .bind(hikes)
                    .executeUpdate()
                    .getKey();
            hikes.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
    @Override
    public List<Hikes> getAll() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM hikes")
                    .executeAndFetch(Hikes.class);
        }
    }
    @Override
    public Hikes findById(int id) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM hikes WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Hikes.class);
        }
    }
    @Override
    public void update(String newHikeName, String newHikeLocation, String newHikeNotes, int newHikeRating, int id){
        String sql = "UPDATE hikes SET (hikeName, hikeLocation, hikeNotes, hikeRating) = (:hikeName, :hikeLocation, :hikeNotes, :hikeRating) WHERE id=:id";
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("hikeName", newHikeName)
                    .addParameter("hikeLocation", newHikeLocation)
                    .addParameter("hikeNotes", newHikeNotes)
                    .addParameter("hikeRating", newHikeRating)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
    @Override
    public void deleteById(int id) {
        String sql = "DELETE from hikes WHERE id=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }
    @Override
    public void clearAllHikes() {
        String sql = "DELETE from hikes";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }
}
