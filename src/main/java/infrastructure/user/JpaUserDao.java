package infrastructure.user;

import dto.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@JpaUser
@Stateless
public class JpaUserDao implements UserDao {
    @PersistenceContext(unitName = "PG5100")
    private EntityManager entityManager;

    public JpaUserDao() {
    }

    public JpaUserDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public User persist(User user) {
        entityManager.persist(user);
        return user;
    }

    @Override
    public boolean update(User user) {
        if (!entityManager.contains(user)) {
            entityManager.merge(user);
        }

        return true;
    }

    @Override
    public User findById(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public List<User> getAll() {
        return entityManager.createNamedQuery("User.getAll", User.class).getResultList();
    }

    @Override
    public boolean remove(User user) {
        entityManager.remove(user);
        return true;
    }
}
