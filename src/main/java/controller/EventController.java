package controller;

import dto.Event;
import infrastructure.event.EventDao;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

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
}
