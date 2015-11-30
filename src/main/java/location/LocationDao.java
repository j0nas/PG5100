package location;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

public class LocationDao {
    @PersistenceContext(unitName = "PG5100")
    private final EntityManager entityManager;

    public LocationDao() {
        final EntityManagerFactory factory = Persistence.createEntityManagerFactory("PG5100");
        entityManager = factory.createEntityManager();
    }

    public boolean create(Location location) {
        entityManager.getTransaction().begin();
        entityManager.persist(location);
        entityManager.getTransaction().commit();
        return entityManager.find(Location.class, location.getId()) != null;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
