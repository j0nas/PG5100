package location;

import dto.Location;
import infrastructure.location.JpaLocationDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;

@Transactional
public class LocationRegistryTest {
    // TODO: TEST ALL CRUD
    private EntityManagerFactory factory;
    private EntityManager entityManager;
    private JpaLocationDao locationDao;

    @After
    public void tearDown() throws Exception {
        entityManager.close();
        factory.close();
        locationDao = new JpaLocationDao(entityManager);
    }

    @Before
    public void setUp() throws Exception {
        factory = Persistence.createEntityManagerFactory("PG5100");
        entityManager = factory.createEntityManager();
    }

    @Test
    public void testCreate() throws Exception {
        final Location location = new Location();
        location.setBuilding("BUILDING_TEST");
        location.setRoom("ROOM_TEST");
        entityManager.persist(location);
        assertEquals(entityManager.find(Location.class, location.getId()), location);
    }

    @Test
    public void testFind() throws Exception {
/*
        Location location = dao.save("BUILDING_TEST", "ROOM_TEST");
        Assert.assertNotNull(location);
        Assert.assertEquals(location, dao.getEntityManager().find(Location.class, location.getId()));
        */
    }
}