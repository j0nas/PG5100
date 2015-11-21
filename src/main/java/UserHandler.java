import java.util.List;

/**
 * Created by Jonas on 03.11.2015.
 */
public interface UserHandler {
    public User create(String email, String password, UserType type);

    public boolean update(User user);

    public User find(int id);

    public List<User> getAll();

    public boolean delete(int id);
}
