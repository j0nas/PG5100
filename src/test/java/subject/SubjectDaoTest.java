package subject;

import location.Location;
import location.LocationDao;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import user.User;
import user.UserDao;
import user.UserType;

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
        final UserDao dao = new UserDao();
        final User student = dao.create("1@test.com", "TESTxPASSSW0RD", UserType.STUDENT);
        final User teacher = dao.create("2@test.com", "TESTxPASSSW0RD", UserType.TEACHER);

        final Subject subject = this.dao.create("PG5100 Enterprise Programming", null);
        subject.addUser(student);
        subject.addUser(teacher);

        Assert.assertEquals(2, this.dao.getAssociatedUsers(subject).size());

        Assert.assertEquals(1, dao.find(student.getId()).getSubjects().size());
        Assert.assertEquals(subject, student.getSubjects().get(0));

        Assert.assertEquals(1, teacher.getSubjects().size());
        Assert.assertEquals(subject, teacher.getSubjects().get(0));
    }
}