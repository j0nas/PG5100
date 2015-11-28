import javax.enterprise.inject.Alternative;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Jonas on 03.11.2015.
 */

@Alternative
public class ArraylistUser implements UserHandler {
    private List<User> users = new ArrayList<>();
    private int userCount = 0;

    @Override
    public User create(String email, String password, UserType type) {
        User user = new User(userCount++, email, password, type);
        users.add(user);
        return user;
    }

    @Override
    public boolean update(User user) {
        int i = users.indexOf(user);
        return (i != -1) && (users.set(i, user) != null);
    }

    @Override
    public User find(int id) {
        Optional<User> any = users.parallelStream().filter(user -> user.getId() == id).findAny();
        return any.isPresent() ? any.get() : null;
    }

    @Override
    public List<User> getAll() {
        return users;
    }

    @Override
    public boolean delete(int id) {
        return users.removeIf(user -> user.getId() == id);
    }

    public void clear() {
        users.clear();
    }
}
