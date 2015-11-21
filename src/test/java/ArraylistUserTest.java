import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Jonas on 18.11.2015.
 */
public class ArraylistUserTest {
    private ArraylistUser userHandler = new ArraylistUser();
    private int id = -1;
    private String email = "test@email.com";
    private String password = "password";
    private UserType type = UserType.STUDENT;

    @Before
    public void setUp() throws Exception {
        userHandler.clear();
        User user = userHandler.create(email, password, type);
        assertNotNull(userHandler.find(user.getId()));
        id = user.getId();
    }

    @Test
    public void testCreate() throws Exception {
        User user = userHandler.create("test@test.com", "testPass", UserType.STUDENT);

        assertNotNull(user);
        assertEquals(user.getEmail(), "test@test.com");
        assertEquals(user.getPassword(), "testPass");
        assertEquals(user.getType(), UserType.STUDENT);
    }

    @Test
    public void testUpdate() throws Exception {
        User user = userHandler.find(id);
        String changedMail = "changed@email.com";
        user.setEmail(changedMail);
        String chagedPassword = "chagedPassword";
        user.setPassword(chagedPassword);
        UserType changedType = UserType.TEACHER;
        user.setType(changedType);
        userHandler.update(user);

        User user1 = userHandler.find(id);
        assertNotNull(user);
        assertEquals(user1.getId(), id);
        assertEquals(user1.getEmail(), changedMail);
        assertEquals(user1.getPassword(), chagedPassword);
        assertEquals(user1.getType(), changedType);
    }

    @Test
    public void testFind() throws Exception {
        User user = userHandler.find(id);
        assertNotNull(user);
        assertEquals(user.getId(), id);
        assertEquals(user.getEmail(), email);
        assertEquals(user.getPassword(), password);
        assertEquals(user.getType(), type);
    }

    @Test
    public void testGetAll() throws Exception {
        List<User> users = userHandler.getAll();
        assertEquals(users.size(), 1);
    }

    @Test
    public void testDelete() throws Exception {
        assertTrue(userHandler.delete(id));
    }
}