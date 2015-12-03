package controller;

import dto.Location;
import infrastructure.location.LocationDao;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.List;

@Model
public class LocationController {
    @Inject
    private LocationDao dao;

    private Location location;

    @PostConstruct
    private void init() {
        location = new Location();
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
}
