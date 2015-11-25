import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Jonas on 21.11.2015.
 */
public class H2UserTest {

    private H2User h2User = new H2User();
    private String email = "jonas.jensen@msn.com";
    private String password = "abcdef";
    private UserType type = UserType.TEACHER;
    private User user;

    @After
    public void tearDown() throws Exception {
        h2User.delete(user.getId());

    }

    @Before
    public void setUp() throws Exception {
        user = h2User.create(email, password, type);
    }

    @Test
    public void testCreate() throws Exception {
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

        h2User.delete(user.getId());
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

        h2User.delete(user.getId());
    }

    @Test
    public void testGetAll() throws Exception {
        H2User h2User = new H2User();

        String email = "jonas.jensen@msn.com";
        String password = "abcdef";
        UserType type = UserType.TEACHER;

        int userCount = 10;
        User[] users = new User[userCount];
        for (int i = 0; i < users.length; i++) {
            users[i] = h2User.create(email, password, type);
        }

        List<User> all = h2User.getAll();
        assertNotNull(all);
        assertEquals(userCount, all.size());
        for (int i = 0; i < all.size(); i++) {
            assertEquals(email, users[i].getEmail());
            assertEquals(password, users[i].getPassword());
            assertEquals(type, users[i].getType());
        }

        for (User user : users) {
            h2User.delete(user.getId());
        }
    }

    @Test
    public void testDelete() throws Exception {
    }
}