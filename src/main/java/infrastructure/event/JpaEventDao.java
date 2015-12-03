package infrastructure.event;

import dto.Event;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;

@Stateless
public class JpaEventDao implements EventDao {
    @PersistenceContext(unitName = "PG5100")
    private EntityManager entityManager;

    public JpaEventDao() {
    }

    public JpaEventDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Event persist(Event event) {
        entityManager.persist(event);
        return event;
    }

    @Override
    public Event findById(int id) {
        return entityManager.find(Event.class, id);
    }

    @Override
    public List<Event> getAll() {
        final List<Event> resultList = entityManager.createNamedQuery("Event.getAll", Event.class).getResultList();
        if (resultList != null) {
            Collections.sort(resultList, (o1, o2) -> o1.getStartTime().compareTo(o2.getStartTime()));
        }

        return resultList;
    }
}
