package ejb;

import common.UserType;
import entity.Subject;
import entity.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class UserRegistryBean {
    private EntityManager entityManager;

    @PersistenceContext(unitName = "PG5100")
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(String email, String password, UserType type, List<Subject> subjects) {
        final User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setType(type);
        user.setSubjects(subjects);
        entityManager.persist(user);
    }

    public List<User> getAll() {
        return entityManager.createNamedQuery("User.getAll", User.class).getResultList();
    }
}
