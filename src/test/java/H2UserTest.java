import org.junit.Test;

import static org.junit.Assert.assertEquals;

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

        assertEquals(user.getEmail(), email);
        assertEquals(user.getPassword(), password);
        assertEquals(user.getType(), type);
    }

    @Test
    public void testUpdate() throws Exception {

    }

    @Test
    public void testFind() throws Exception {

    }

    @Test
    public void testGetAll() throws Exception {

    }

    @Test
    public void testDelete() throws Exception {

    }
}