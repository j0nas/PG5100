package infrastructure.event;

import dto.Event;
import dto.Subject;
import infrastructure.subject.JpaSubjectDao;
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

public class JpaEventDaoIT {
    private EntityManagerFactory factory;
    private EntityManager entityManager;
    private JpaEventDao dao;
    private Event event;

    @Before
    public void setUp() throws Exception {
        factory = Persistence.createEntityManagerFactory("PG5100");
        entityManager = factory.createEntityManager();

        JpaSubjectDao subjectDao = new JpaSubjectDao(entityManager);
        entityManager.getTransaction().begin();
        Subject subject = new Subject();
        subject.setName("PG5100");
        subject.setUsers(new ArrayList<>());
        subject = subjectDao.persist(subject);
        entityManager.getTransaction().commit();
        entityManager.close();
/*
        entityManager = factory.createEntityManager();
        dao = new JpaEventDao(entityManager);

        event = new Event();
        event.setType(EventType.LECTURE);
        event.setTitle("Lecture 12: Reiku healing");
        event.setSubject(subject);
        event.setStartTime(new Date());
        event.setEndTime(new Date());

        entityManager.getTransaction().begin();
        event = dao.persist(event);
        entityManager.getTransaction().commit();
        */
    }

    @After
    public void tearDown() throws Exception {
        entityManager.close();
        factory.close();
    }

    @Test
    public void testPersist() throws Exception {
        assertNotNull(event);
    }

    @Test
    public void testFindById() throws Exception {
        final Event event = dao.findById(this.event.getId());
        assertNotNull(event);
        assertEquals(this.event, event);
    }

    @Test
    public void testGetAll() throws Exception {
        final List<Event> events = dao.getAll();
        assertEquals(1, events.size());
        assertEquals(event, events.get(0));
    }
}