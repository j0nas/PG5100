import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Jonas on 21.11.2015.
 */
public class H2UserTest {

    @Test
    public void testCreate() throws Exception {
        H2User h2User = new H2User();
        String email = "jonas.jensen@msn.com";
        String password = "abcdef";
        UserType type = UserType.TEACHER;
        User user = h2User.create(email, password, type);

        assertEquals(email, user.getEmail());
        assertEquals(password, user.getPassword());
        assertEquals(type, user.getType());
    }

    @Test
    public void testUpdate() throws Exception {
        H2User h2User = new H2User();
        String email = "jonas.jensen@msn.com";
        String password = "abcdef";
        UserType type = UserType.TEACHER;
        User user = h2User.create(email, password, type);

        assertEquals(email, user.getEmail());
        assertEquals(password, user.getPassword());
        assertEquals(type, user.getType());

        user.setEmail("changed@email.com");
        user.setPassword("changedPassword");
        user.setType(UserType.STUDENT);
        assertTrue(h2User.update(user));
    }

    @Test
    public void testFind() throws Exception {
        H2User h2User = new H2User();

        String email = "jonas.jensen@msn.com";
        String password = "abcdef";
        UserType type = UserType.TEACHER;
        User user = h2User.create(email, password, type);

        User foundUser = h2User.find(user.getId());
        assertEquals(email, foundUser.getEmail());
        assertEquals(password, foundUser.getPassword());
        assertEquals(type, foundUser.getType());
    }

    @Test
    public void testGetAll() throws Exception {
    }

    @Test
    public void testDelete() throws Exception {
    }
}