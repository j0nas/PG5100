package ejb;

import entity.Location;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class LocationRegistryBean {
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    @PersistenceContext(unitName = "PG5100")
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Location save(String building, String room) {
        Location location = new Location();
        location.setBuilding(building);
        location.setRoom(room);
        entityManager.persist(location);
        return location;
    }

    public List<Location> getAll() {
        return entityManager.createNamedQuery("Location.getAll", Location.class).getResultList();
    }
}
