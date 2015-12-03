package dto;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NamedQuery(name = "Location.getAll", query = "SELECT l FROM Location l")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String room;

    @NotNull
    private String building;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;
        return id == location.id && room.equals(location.room) && building.equals(location.building);
    }

    @Override
    public int hashCode() {
        return id;
    }

    public int getId() {
        return id;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }
}