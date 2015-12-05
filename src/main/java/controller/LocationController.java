package controller;

import dto.Location;
import infrastructure.location.LocationDao;
import infrastructure.subject.SubjectDao;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.List;

@Model
public class LocationController {
    @Inject
    private LocationDao dao;

    @Inject
    private SubjectDao subjectDao;

    private int locationId;
    private Location location;

    @PostConstruct
    private void init() {
        location = new Location();
    }

    public void initLocation() {
        location = dao.findById(locationId);
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void persist() {
        dao.persist(location);
    }

    public List<Location> getAll() {
        return dao.getAll();
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public void delete(int id) {
        Location location = dao.findById(id);
        subjectDao.getAll().parallelStream().filter(subject ->
                subject.getLocation().equals(location)).forEach(subject -> subjectDao.removeById(subject.getId()));
        dao.removeById(id);
    }
}
