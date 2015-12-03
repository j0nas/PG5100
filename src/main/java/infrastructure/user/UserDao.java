package infrastructure.user;

import dto.User;

import java.util.List;

public interface UserDao {
    User persist(User user);

    boolean update(User user);

    User findById(int id);

    List<User> getAll();

    boolean remove(User user);
}
