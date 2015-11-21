import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonas on 18.11.2015.
 */
public class H2User implements UserHandler {
    public static final int COL_ID = 1;
    public static final int COL_EMAIL = 2;
    public static final int COL_PASSWORD = 3;
    public static final int COL_USERTYPE = 4;

    public static final int TYPE_TEACHER = 0;
    public static final int TYPE_STUDENT = 1;

    private Connection connectToDb() throws SQLException, ClassNotFoundException {
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "sa");
    }

    @Override
    public User create(String email, String password, UserType type) {
        try (Connection connection = connectToDb()) {
            try (Statement statement = connection.createStatement()) {
                // TODO: replace with preparedStatement to avoid injections?
                statement.execute(String.format("INSERT INTO USERS VALUES (NULL, '%s', '%s', '%d');",
                        email, password, type == UserType.TEACHER ? 0 : 1));

                try (ResultSet resultSet = statement.executeQuery("SELECT * FROM USERS ORDER BY ID DESC LIMIT 1")) {
                    if (resultSet.next()) {
                        return new User(resultSet.getInt(COL_ID),
                                resultSet.getString(COL_EMAIL),
                                resultSet.getString(COL_PASSWORD),
                                resultSet.getInt(COL_USERTYPE) == TYPE_STUDENT ? UserType.STUDENT : UserType.TEACHER);
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.err.println("Query failed.");
        return null;
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public User find(int id) {
        return null;
    }


    @Override
    public List<User> getAll() {
        ArrayList<User> users = new ArrayList<>();

        try (Statement statement = connectToDb().createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM USERS");
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
