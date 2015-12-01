package user;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;

import static junit.framework.Assert.*;

public class UserDaoTest {
    UserDao dao = new UserDao();
    EntityManager entityManager;

    @Before
    public void setUp() throws Exception {
        entityManager = dao.getEntityManager();
    }

    @After
    public void tearDown() throws Exception {
        entityManager.close();
    }

    @Test
    public void testGetEntityManager() throws Exception {
        assertNotNull(dao.getEntityManager());
    }

    @Test
    public void testCreate() throws Exception {
        User user = dao.create("jonas.jensen@msn.com", "paSsssSsw0r1", UserType.STUDENT);
        assertNotNull(dao.find(user.getId()));
    }

    @Test
    public void testUpdate() throws Exception {
        User user = dao.create("jonas.jensen@msn.com", "paSsssSsw0r1", UserType.STUDENT);
        assertNotNull(dao.find(user.getId()));

        user.setPassword("changedP4ssw0rd");
        dao.update(user);
        assertEquals(user, dao.find(user.getId()));
    }

    @Test
    public void testFind() throws Exception {
        User user = dao.create("jonas.jensen@msn.com", "paSsssSsw0r1", UserType.STUDENT);
        assertNotNull(dao.find(user.getId()));
    }

    @Test
    public void testGetAll() throws Exception {
        User user = dao.create("jonas.jensen@msn.com", "Password1", UserType.STUDENT);
        System.out.println("Created: " + (dao.find(user.getId()) != null));
        System.out.println("\n");
        System.out.println(dao.getAll().size());
    }

    @Test
    public void testDelete() throws Exception {
        User user = dao.create("jonas.jensen@msn.com", "paSsssSsw0r1", UserType.STUDENT);
        assertNotNull(dao.find(user.getId()));
        assertTrue(dao.delete(user.getId()));
    }
}