package subject;

import location.Location;
import location.LocationDao;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SubjectDaoTest {
    private SubjectDao dao;

    @Before
    public void setUp() throws Exception {
        dao = new SubjectDao();
    }

    @After
    public void tearDown() throws Exception {
        dao.close();
    }

    @Test
    public void testCreate() throws Exception {
        Assert.assertNotNull(dao.create("TEST_SUBJECT", null));
    }

    @Test
    public void testFind() throws Exception {
        final Subject subject = dao.create("TEST_SUBJECT", null);
        Assert.assertNotNull(dao.find(subject.getId()));
    }

    private Location createTestLocation() {
        final LocationDao locationDao = new LocationDao();
        final Location location = new Location();
        location.setBuilding("TEST_BUILDING");
        location.setRoom("TEST_ROOM");
        return locationDao.create(location) ? location : null;
    }

    @Test
    public void testLocationPersistence() throws Exception {
        final Location testLocation = createTestLocation();
        Assert.assertNotNull(testLocation);

        final Subject subject = dao.create("TEST_SUBJECT", testLocation);
        Assert.assertNotNull(subject);
        Assert.assertEquals(subject, dao.find(subject.getId()));
    }

    @Test
    public void testGetAssociatedUsers() throws Exception {
        //dao.getAssociatedUsers()
    }
}