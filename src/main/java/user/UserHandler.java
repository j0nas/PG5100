package user;

import java.util.List;

public interface UserHandler {
    User create(String email, String password, UserType type);

    boolean update(User user);

    User find(int id);

    List<User> getAll();

    boolean delete(int id);
}
