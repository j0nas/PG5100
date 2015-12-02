package controller;

import ejb.LocationRegistryBean;
import entity.Location;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.List;

@Model
public class LocationController {
    private LocationRegistryBean locationRegistry;
    private String building;
    private String room;

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    @Inject
    public void setLocationRegistry(LocationRegistryBean locationRegistry) {
        this.locationRegistry = locationRegistry;
    }

    public Location save(String building, String room) {
        return locationRegistry.save(building, room);
    }

    public List<Location> get() {
        return locationRegistry.getAll();
    }
}
