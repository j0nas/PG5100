package user;

import javax.enterprise.inject.Alternative;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

@H2Transaction
@Alternative
public class UserDao implements UserHandler {
    private EntityManager entityManager;

    public UserDao() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PG5100");
        entityManager = factory.createEntityManager();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public User create(String email, String password, UserType type) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setType(type);
        entityManager.persist(user);
        return user;
    }

    @Override
    public boolean update(User user) {
        entityManager.persist(user);
        return find(user.getId()) != null;
    }

    @Override
    public User find(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public List getAll() {
        return entityManager.createNamedQuery("getAllUsers").getResultList();
    }

    @Override
    public boolean delete(int id) {
        entityManager.remove(find(id));
        return find(id) == null;
    }
}
