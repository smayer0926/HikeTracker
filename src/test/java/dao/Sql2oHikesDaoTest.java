package dao;

import models.Hikes;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

import static org.junit.Assert.*;


public class Sql2oHikesDaoTest {
    private Sql2oHikesDao hikesDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        hikesDao = new Sql2oHikesDao(sql2o);
        conn = sql2o.open();
    }
    @After
    public void tearDown() throws Exception {
        conn.close();
    }
    @Test
    public void addingHikestoDatabasebyId() throws Exception {
        Hikes hikes  = setupNew();
        int originalHikeId = hikes.getId();
        hikesDao.add(hikes);
        assertNotEquals(originalHikeId, hikes.getId());
    }
    @Test
    public void existingHikeCanBeFoundById() throws Exception {
        Hikes hikes  = setupNew();
        hikesDao.add(hikes); //add to dao (takes care of saving)
        Hikes foundHike = hikesDao.findById(hikes.getId()); //retrieve
        assertEquals(hikes, foundHike);
    }
    @Test
    public void getlistofAllHikes() throws Exception {
        Hikes hikes = setupNew();
        hikesDao.add(hikes); //add to dao (takes care of saving)
        assertEquals(1, hikesDao.getAll().size());
    }
    @Test
    public void noHikesListedIfEmpty() throws Exception {
        assertEquals(0, hikesDao.getAll().size());
    }
    @Test
    public void updateHikeInformation() throws Exception {
        String initialDescription = "";
        Hikes hikes = setupNew();
        hikesDao.add(hikes);
        hikesDao.update("Hike", hikes.getId());
        Hikes updatedHike = hikesDao.findById(hikes.getId());
        assertNotEquals(initialDescription, updatedHike.getHikeName());
    }
    @Test
    public void deleteByIdDeletesCorrectHike() throws Exception {
        Hikes hikes  = setupNew();
        hikesDao.add(hikes);
        hikesDao.deleteById(hikes.getId());
        assertEquals(0, hikesDao.getAll().size());
    }
    @Test
    public void clearAllClearsAll() throws Exception {
        Hikes hikes  = setupNew();
        Hikes otherHike = setupOther();
        hikesDao.add(hikes);
        hikesDao.add(otherHike);
        int daoSize = hikesDao.getAll().size();
        hikesDao.clearAllHikes();
        assertTrue(daoSize > 0 && daoSize > hikesDao.getAll().size());
    }
    public Hikes setupNew(){
        return  new Hikes ("Hikes");
    }
    public Hikes setupOther(){
        return  new Hikes ("Bobette");
    }
}