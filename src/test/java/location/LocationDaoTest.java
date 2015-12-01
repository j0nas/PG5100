package location;

import org.junit.Assert;
import org.junit.Test;

public class LocationDaoTest {
    final LocationDao dao = new LocationDao();

    @Test
    public void testCreate() throws Exception {
        final Location location = new Location();
        location.setBuilding("BUILDING_TEST");
        location.setRoom("ROOM_TEST");
        Assert.assertTrue(dao.create(location));
    }

    @Test
    public void testFind() throws Exception {
        final Location location = new Location();
        location.setBuilding("BUILDING_TEST");
        location.setRoom("ROOM_TEST");
        Assert.assertTrue(dao.create(location));
        Assert.assertNotNull(dao.find(location.getId()));
    }
}