package infrastructure.location;

import dto.Location;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class JpaLocationDao implements LocationDao {
    @PersistenceContext(unitName = "PG5100")
    private EntityManager entityManager;

    public JpaLocationDao() {
    }

    public JpaLocationDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Location persist(Location location) {
        entityManager.persist(location);
        return location;
    }

    @Override
    public Location findById(int id) {
        return entityManager.find(Location.class, id);
    }

    @Override
    public List<Location> getAll() {
        return entityManager.createNamedQuery("Location.getAll", Location.class).getResultList();
    }
}
