package infrastructure.subject;

import dto.Subject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class JpaSubjectDaoIT {
    private EntityManagerFactory factory;
    private EntityManager entityManager;
    private JpaSubjectDao dao;
    private Subject subject;

    @Before
    public void setUp() throws Exception {
        factory = Persistence.createEntityManagerFactory("PG5100");
        entityManager = factory.createEntityManager();
        dao = new JpaSubjectDao(entityManager);

        entityManager.getTransaction().begin();
        subject = new Subject();
        subject.setName("NAME_TEST");
        subject.setUsers(new ArrayList<>());
        subject = dao.persist(subject);
        entityManager.getTransaction().commit();
    }

    @After
    public void tearDown() throws Exception {
        entityManager.close();
        factory.close();
    }

    @Test
    public void testPersist() throws Exception {
        assertNotNull(subject);
    }

    @Test
    public void testFindById() throws Exception {
        assertNotNull(subject);
        assertEquals(subject, dao.findById(subject.getId()));
    }

    @Test
    public void testGetAll() throws Exception {
        final List<Subject> allSubjects = dao.getAll();

        assertNotNull(allSubjects);
        assertEquals(1, allSubjects.size());
        assertEquals(subject, allSubjects.get(0));
    }
}
