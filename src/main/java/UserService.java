import service.User;
import service.UserHandler;
import service.UserType;

import javax.inject.Inject;

/**
 * Created by Jonas on 26.11.2015.
 */
public class UserService {
    @Inject
    private UserHandler userHandler;

    public void printUsers() {
        User user = userHandler.create("jonas.jensen@msn.com", "12345", UserType.STUDENT);
        User user1 = userHandler.create("contact@martinfolwer.com", "12345", UserType.TEACHER);

        System.out.println("All users:");
        userHandler.getAll().forEach(System.out::println);

        System.out.println("User by ID:");
        System.out.println(userHandler.find(user.getId()));

        user.setPassword("correct horse battery staple");
        userHandler.update(user);
        System.out.println("Updated user:");
        userHandler.getAll().forEach(System.out::println);

        userHandler.delete(user.getId());
        System.out.println("Deleted user:");
        userHandler.getAll().forEach(System.out::println);
    }
}
