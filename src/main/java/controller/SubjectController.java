package controller;

import dto.Location;
import dto.Subject;
import dto.User;
import infrastructure.location.LocationDao;
import infrastructure.subject.SubjectDao;
import infrastructure.user.JpaUser;
import infrastructure.user.UserDao;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Model
public class SubjectController {
    @Inject
    private SubjectDao subjectDao;

    @Inject
    private LocationDao locationDao;

    @Inject
    @JpaUser
    private UserDao userDao;

    private int subjectId;
    private Subject subject;

    private int locationId;
    private List<String> userIds;

    @PostConstruct
    public void init() {
        subject = new Subject();
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public List<String> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<String> userIds) {
        this.userIds = userIds;
    }

    public void initSubject() {
        subject = subjectDao.findById(subjectId);
    }

    public String getSelectedLocation() {
        Location location = subject.getLocation();
        return location.getBuilding() + " - " + location.getRoom();
    }

    public List<SelectItem> getNames() {
        return getAll().stream().map(subject -> new SelectItem(subject.getId(), subject.getName())).collect(Collectors.toList());
    }

    public List<Subject> getAll() {
        return subjectDao.getAll();
    }


    public List<String> getSelectedUsers() {
        return subject.getUsers().stream().map(User::getEmail).collect(Collectors.toList());
    }

    public List<SelectItem> getLocations() {
        return locationDao.getAll().stream().map(l -> new SelectItem(l.getId(), l.getBuilding() + " - " + l.getRoom())).collect(Collectors.toList());
    }

    public List<SelectItem> getUsers() {
        return userDao.getAll().stream().map(u -> new SelectItem(u.getId(), u.getEmail())).collect(Collectors.toList());
    }

    public void delete(int id) {
        subjectDao.removeById(id);
    }

    public void persist() {
        Location location = locationDao.findById(locationId);
        subject.setLocation(location);

        List<User> users = userIds.stream().map(id -> userDao.findById(Integer.parseInt(id))).collect(Collectors.toList());
        subject.setUsers(users);

        subjectDao.persist(subject);
    }
}
