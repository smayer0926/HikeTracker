package dao;

import models.Locations;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;


public class Sql2oLocationsDaoTest {
    private Sql2oLocationsDao locationsDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        locationsDao = new Sql2oLocationsDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addingLocationstoDatabasebyId() throws Exception {
        Locations locations  = setupNew();
        int originalLocationId = locations.getId();
        locationsDao.add(locations);
        assertNotEquals(originalLocationId, locations.getId());
    }

    @Test
    public void existingLocationsCanBeFoundById() throws Exception {
        Locations locations  = setupNew();
        locationsDao.add(locations);
        Locations foundLocation = locationsDao.findById(locations.getId());
        assertEquals(locations, foundLocation);
    }
    @Test
    public void getlistofAllLocations() throws Exception {
        Locations locations = setupNew();
        locationsDao.add(locations);
        assertEquals(1, locationsDao.getAllLocations().size());
    }
    @Test
    public void noLocationsListedIfEmpty() throws Exception {
        assertEquals(0, locationsDao.getAllLocations().size());
    }
    @Test
    public void updateLocationsInformation() throws Exception {
        String initialDescription = "";
        Locations locations = setupNew();
        locationsDao.add(locations);
        locationsDao.update("Hike", "California", "None", 5, 4,locations.getId());
        Locations updatedLocation = locationsDao.findById(locations.getId());
        assertNotEquals(initialDescription, updatedLocation.getLocationCity());
    }
    @Test
    public void deleteByIdDeletesCorrectLocations() throws Exception {
        Locations locations  = setupNew();
        locationsDao.add(locations);
        locationsDao.deleteById(locations.getId());
        assertEquals(0, locationsDao.getAllLocations().size());
    }
    @Test
    public void clearAllClearsAll() throws Exception {
        Locations locations  = setupNew();
        Locations otherLocation = setupOther();
        locationsDao.add(locations);
        locationsDao.add(otherLocation);
        int daoSize = locationsDao.getAllLocations().size();
        locationsDao.clearAllLocations();
        assertTrue(daoSize > 0 && daoSize > locationsDao.getAllLocations().size());
    }
    public Locations setupNew(){
        return  new Locations (4,5, "Portland", "Oregon", "USA");
    }
    public Locations setupOther(){
        return  new Locations (4,5, "Portland", "Oregon", "USA");
    }


}