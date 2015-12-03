package infrastructure.event;

import dto.Event;

import java.util.List;

public interface EventDao {
    Event persist(Event event);

    Event findById(int id);

    List<Event> getAll();
}
