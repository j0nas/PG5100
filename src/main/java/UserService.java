import javax.inject.Inject;

public class UserService {
    @Inject
    private H2UserDao dao;

    public void printUsers() {
        System.out.println("Creating user: ");
        User user = dao.create("jonas.jensen@msn.com", "paSsssSsw0r1", UserType.STUDENT);
        System.out.println(user);

        System.out.println("\nFinding user:");
        System.out.println(dao.find(user.getId()));

        System.out.println("\nListing all users:");
        dao.getAll().forEach(System.out::println);

        System.out.println("\nDeleting user and re-listing all users:");
        System.out.println("Deletion: " + dao.delete(user.getId()));
        dao.getAll().forEach(System.out::println);
    }
}
