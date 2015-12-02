package location;

import controller.LocationController;
import entity.Location;
import org.junit.Assert;
import org.junit.Test;

public class LocationRegistryTest {
    // TODO: TEST ALL CRUD

    @Test
    public void testCreate() throws Exception {
        LocationController controller = new LocationController();
        Assert.assertNotNull(controller);
        Location location = controller.save("BUILDING_TEST", "ROOM_TEST");
        Assert.assertNotNull(location);
    }

    @Test
    public void testFind() throws Exception {
/*
        Location location = dao.save("BUILDING_TEST", "ROOM_TEST");
        Assert.assertNotNull(location);
        Assert.assertEquals(location, dao.getEntityManager().find(Location.class, location.getId()));
        */
    }
}