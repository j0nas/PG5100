package ejb;

import entity.Location;
import entity.Subject;
import entity.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class SubjectRegistryBean {
    private EntityManager entityManager;

    @PersistenceContext(unitName = "PG5100")
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(String name, Location location, List<User> users) {
        Subject subject = new Subject();
        subject.setName(name);
        subject.setLocation(location);
        subject.setUsers(users);
        entityManager.persist(subject);
    }

    public List<Subject> getAll() {
        return entityManager.createNamedQuery("Subject.getAll", Subject.class).getResultList();
    }
}
