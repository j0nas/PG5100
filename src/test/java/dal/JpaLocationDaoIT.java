package dal;

import dto.Location;
import infrastructure.location.JpaLocationDao;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.Assert.*;

public class JpaLocationDaoIT {
    private EntityManagerFactory factory;
    private EntityManager entityManager;
    private JpaLocationDao dao;
    private Location location;

    @Before
    public void setUp() throws Exception {
        factory = Persistence.createEntityManagerFactory("PG5100");
        entityManager = factory.createEntityManager();
        dao = new JpaLocationDao(entityManager);

        entityManager.getTransaction().begin();
        location = new Location();
        location.setBuilding("BUILDING_TEST");
        location.setRoom("ROOM_TEST");
        location = dao.persist(location);
        entityManager.getTransaction().commit();
    }

    @After
    public void tearDown() throws Exception {
        entityManager.close();
        factory.close();
    }

    @Test
    public void testPersist() throws Exception {
        assertEquals(entityManager.find(Location.class, location.getId()), location);
    }

    @Test
    public void testFindById() throws Exception {
        assertNotNull(location);
        assertEquals(location, dao.findById(location.getId()));
    }

    @Test
    public void testGetAll() throws Exception {
        Assert.assertEquals(1, dao.getAll().size());
    }

    @Test
    public void testRemoveById() throws Exception {
        final int id = location.getId();
        dao.removeById(id);
        assertNull(dao.findById(id));
    }
}