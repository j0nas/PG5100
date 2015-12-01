package subject;

import location.Location;
import location.LocationDao;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import user.User;
import user.UserType;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.ArrayList;

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
    public void testCourseUserAssociation() throws Exception {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PG5100");
        EntityManager entityManager = factory.createEntityManager();

        entityManager.getTransaction().begin();
        Location location = new Location();
        location.setBuilding("Campus Galleriet");
        location.setRoom("Auditorium");
        entityManager.persist(location);
        entityManager.getTransaction().commit();

        entityManager.getTransaction().begin();
        User teacher = new User();
        teacher.setEmail("2@test.com");
        teacher.setPassword("TESTxPASSSW0RD");
        teacher.setType(UserType.TEACHER);
        entityManager.persist(teacher);
        entityManager.getTransaction().commit();

        ArrayList<User> users = new ArrayList<>();
        users.add(teacher);

        entityManager.getTransaction().begin();
        Subject subject = new Subject();
        subject.setName("PG5100 Enterprise Programming");
        subject.setLocation(location);
        subject.setUsers(users);
        entityManager.persist(subject);
        entityManager.getTransaction().commit();

        TypedQuery<User> query = entityManager.createNamedQuery("Subject.getUsersInCourse", User.class).setParameter("subjectId", subject.getId());
        query.getResultList().forEach(System.out::println);

        User persistedUser = entityManager.find(User.class, teacher.getId());
        System.out.println(persistedUser);
        System.out.println(persistedUser.getSubjects().size());
        persistedUser.getSubjects().forEach(System.out::println);

        entityManager.close();
        factory.close();
    }
}