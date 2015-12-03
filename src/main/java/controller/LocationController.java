package controller;

import dto.Location;
import infrastructure.location.LocationDao;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Model
public class LocationController {
    @Inject
    private LocationDao locationDao;

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
        locationDao.persist(location);
    }
}
