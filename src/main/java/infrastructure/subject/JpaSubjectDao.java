package infrastructure.subject;

import dto.Subject;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class JpaSubjectDao implements SubjectDao {
    @PersistenceContext(unitName = "PG5100")
    private EntityManager entityManager;

    public JpaSubjectDao() {
    }

    public JpaSubjectDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Subject persist(Subject subject) {
        entityManager.persist(subject);
        return subject;
    }

    @Override
    public Subject findById(int id) {
        return entityManager.find(Subject.class, id);
    }

    @Override
    public List<Subject> getAll() {
        return entityManager.createNamedQuery("Subject.getAll", Subject.class).getResultList();
    }

    @Override
    public void removeById(int id) {
        entityManager.remove(findById(id));
    }
}
