package subject;

import location.Location;
import user.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.util.List;

public class SubjectDao implements AutoCloseable {
    @PersistenceContext(unitName = "PG5100")
    private EntityManager entityManager;

    public SubjectDao() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PG5100");
        entityManager = factory.createEntityManager();
    }

    public Subject create(String name, Location location) {
        Subject subject = new Subject();
        subject.setName(name);
        subject.setLocation(location);
        entityManager.getTransaction().begin();
        entityManager.persist(subject);
        entityManager.getTransaction().commit();
        return entityManager.find(Subject.class, subject.getId()).equals(subject) ? subject : null;
    }

    public Subject find(int id) {
        return entityManager.find(Subject.class, id);
    }

    public List<User> getAssociatedUsers(Subject subject) {
        return subject.getUsers();
    }

    @Override
    public void close() throws Exception {
        entityManager.close();
    }
}
