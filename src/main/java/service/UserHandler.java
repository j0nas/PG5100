package service;

import java.util.List;

/**
 * Created by Jonas on 03.11.2015.
 */
public interface UserHandler {
    User create(String email, String password, UserType type);

    boolean update(User user);

    User find(int id);

    List<User> getAll();

    boolean delete(int id);
}
