package controller;

import dto.Event;
import dto.EventType;
import infrastructure.event.EventDao;
import infrastructure.subject.SubjectDao;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Model
public class EventController {
    @Inject
    private EventDao dao;
    private int eventId;
    private Event event;

    @Inject
    private SubjectDao subjectDao;
    private int subjectId;

    private Date start;
    private Date end;

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

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
        event.setStartTime(LocalDateTime.ofInstant(start.toInstant(), ZoneId.systemDefault()));
        event.setEndTime(LocalDateTime.ofInstant(end.toInstant(), ZoneId.systemDefault()));
        event.setSubject(subjectDao.findById(subjectId));
        dao.persist(event);
    }

    public void delete(int id) {
        dao.delete(id);
    }

    public void initEvent() {
        event = dao.findById(eventId);
    }

    public List<Event> getAll() {
        return dao.getAll();
    }

    public List<SelectItem> getSubjects() {
        return subjectDao.getAll().stream().map(subject -> new SelectItem(subject.getId(), subject.getName())).collect(Collectors.toList());
    }

    public List<SelectItem> getEventTypes() {
        return Arrays.asList(EventType.values()).stream().map(t -> new SelectItem(t, t.name())).collect(Collectors.toList());
    }
}
