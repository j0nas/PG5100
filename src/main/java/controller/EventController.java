package controller;

import dto.Event;
import dto.EventType;
import infrastructure.event.EventDao;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Model
public class EventController {
    @Inject
    private EventDao eventDao;

    private Event event;

    @PostConstruct
    private void init() {
        event = new Event();
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public void persist() {
        eventDao.persist(event);
    }

    public List<Event> getAll() {
        return eventDao.getAll();
    }

    public List<SelectItem> getEventTypes() {
        return Arrays.asList(EventType.values()).stream().map(t -> new SelectItem(t, t.name())).collect(Collectors.toList());
    }
}
