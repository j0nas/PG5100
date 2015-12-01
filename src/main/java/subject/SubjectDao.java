package subject;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

public class SubjectDao implements AutoCloseable {
    @PersistenceContext(unitName = "PG5100")
    private EntityManager entityManager;

    public SubjectDao() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PG5100");
        entityManager = factory.createEntityManager();
    }

    public Subject create(String name) {
        Subject subject = new Subject();
        subject.setName(name);
        entityManager.getTransaction().begin();
        entityManager.persist(subject);
        entityManager.getTransaction().commit();
        return entityManager.find(Subject.class, subject.getId()).equals(subject) ? subject : null;
    }

    public Subject find(int id) {
        return entityManager.find(Subject.class, id);
    }

    @Override
    public void close() throws Exception {
        entityManager.close();
    }
}
