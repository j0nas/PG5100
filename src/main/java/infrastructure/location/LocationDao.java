package infrastructure.location;

import dto.Location;

import java.util.List;

public interface LocationDao {
    Location persist(Location location);

    Location findById(int id);

    List<Location> getAll();
}
