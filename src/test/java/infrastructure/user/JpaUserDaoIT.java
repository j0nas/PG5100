package infrastructure.user;

import dto.User;
import dto.UserType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

import static org.junit.Assert.*;

public class JpaUserDaoIT {
    private EntityManagerFactory factory;
    private EntityManager entityManager;
    private JpaUserDao dao;
    private User user;

    @Before
    public void setUp() throws Exception {
        factory = Persistence.createEntityManagerFactory("PG5100");
        entityManager = factory.createEntityManager();
        dao = new JpaUserDao(entityManager);

        entityManager.getTransaction().begin();
        user = new User();
        user.setEmail("EMAIL@TEST.COM");
        user.setPassword("testPassw0rd");
        user.setType(UserType.STUDENT);
        user = dao.persist(user);
        entityManager.getTransaction().commit();
    }

    @After
    public void tearDown() throws Exception {
        entityManager.close();
        factory.close();
    }

    @Test
    public void testPersist() throws Exception {
        assertNotNull(user);
    }

    @Test
    public void testUpdate() throws Exception {
        final String email = "altered_mail@test.com";
        user.setEmail(email);

        entityManager.getTransaction().begin();
        dao.update(user);
        entityManager.getTransaction().commit();

        assertEquals(email, dao.findById(user.getId()).getEmail());
    }

    @Test
    public void testFindById() throws Exception {
        assertEquals(this.user, dao.findById(this.user.getId()));
    }

    @Test
    public void testGetAll() throws Exception {
        final List<User> users = dao.getAll();
        assertEquals(1, users.size());
        assertEquals(user, users.get(0));
    }

    @Test
    public void testRemove() throws Exception {
        final User user = this.user;
        dao.remove(user);
        assertNull(dao.findById(user.getId()));
    }
}
