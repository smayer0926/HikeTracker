import com.sun.tools.internal.xjc.model.Model;
import dao.HikesDao;
import dao.LocationsDao;
import dao.Sql2oHikesDao;
import dao.Sql2oLocationsDao;
import org.sql2o.Sql2o;

import models.Hikes;
import models.Locations;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;


public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");
        String connectionString = "jdbc:h2:~/todolist.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        Sql2oLocationsDao locationsDao = new Sql2oLocationsDao(sql2o);
        Sql2oHikesDao hikesDao = new Sql2oHikesDao(sql2o);
        // get: delete all tasks
        get("/hikes/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            hikesDao.clearAllHikes();
            res.redirect("/");
            return null;
        });
        get("/locations/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            locationsDao.clearAllLocations();
            res.redirect("/");
            return null;
        });
        //get: Get ALL instances of objects
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Locations> locations = locationsDao.getAllLocations();
            List<Hikes> hikes = hikesDao.getAll();
            model.put("locations", locations);
            model.put("hikes", hikes);
            return new ModelAndView(model, "index.hbs");
        }, new  HandlebarsTemplateEngine());

        get ("/hikes/new", (request, response) -> {
            Map<String,Object> model = new HashMap<>();
            List<Locations> locations = locationsDao.getAllLocations();
            List<Hikes> hikes = hikesDao.getAll();
            model.put("locations", locations);
            model.put("hikes", hikes);
            return new ModelAndView(model, "hike-input-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/hikes/new",(request, response) -> {
                    Map<String, Object> model = new HashMap<>();
                    String name = request.queryParams("hikeName");
                    String location = request.queryParams("hikeLocation");
                    String notes = request.queryParams("hikeNotes");
                    int rating = Integer.parseInt(request.queryParams("hikeRating"));
                    Hikes newHike = new Hikes(name, location, notes, rating, 1);
                    hikesDao.add(newHike);
                    model.put("newHike", newHike);
                    response.redirect("/");
                    return null;
                });

        get ("/locations/new", (request, response) -> {
            Map<String,Object> model = new HashMap<>();
            return new ModelAndView(model, "locations-input-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/locations/new",(request, response) -> {
            Map<String, Object> model = new HashMap<>();
//            String city = request.queryParams("locationCity");
            String state = request.queryParams("locationState");
//            String country = request.queryParams("locationCountry");
//            int distance = Integer.parseInt(request.queryParams("locationDistance"));
//            int difficulty = Integer.parseInt(request.queryParams("locationDifficulty"));
//            Locations newLocation = new Locations(distance, difficulty, city, state, country);
            Locations newLocation = new Locations(state);
            locationsDao.add(newLocation);
            model.put("newLocation", newLocation);
            return new ModelAndView(model, "locations-detail.hbs");
        }, new HandlebarsTemplateEngine());

        get("/hikes/:hike_id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfHikeToFind = Integer.parseInt(req.params("hike_id"));
            Hikes newHikes = hikesDao.findById(idOfHikeToFind);
            model.put("hikes" , newHikes);
            return new ModelAndView(model, "hike-detail.hbs");
        }, new HandlebarsTemplateEngine());


    }


}
