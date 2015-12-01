package user;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class H2User implements UserHandler {
    public static final int COL_ID = 1;
    public static final int COL_EMAIL = 2;
    public static final int COL_PASSWORD = 3;
    public static final int COL_USERTYPE = 4;

    public static final int TYPE_TEACHER = 1;
    public static final int TYPE_STUDENT = 0;

    private Connection connectToDb() throws SQLException, ClassNotFoundException {
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:tcp://localhost/~/javaee", "sa", "sa");
    }

    @Override
    public User create(String email, String password, UserType type) {
        try (Connection connection = connectToDb()) {
            try (PreparedStatement statement = connection.prepareStatement("INSERT INTO USERS VALUES (NULL, ?, ?, ?)")) {
                statement.setString(1, email);
                statement.setString(2, password);
                statement.setInt(3, type == UserType.STUDENT ? 0 : 1);
                statement.execute();
            }

            try (ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM USERS ORDER BY ID DESC LIMIT 1")) {
                if (resultSet.next()) {
                    return new User(resultSet.getInt(COL_ID),
                            resultSet.getString(COL_EMAIL),
                            resultSet.getString(COL_PASSWORD),
                            resultSet.getInt(COL_USERTYPE) == TYPE_STUDENT ? UserType.STUDENT : UserType.TEACHER);
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
        try (Connection connection = connectToDb()) {
            String updateQuery = "UPDATE USERS SET EMAIL = ?, PASSWORD = ?, USERTYPE = ? WHERE ID = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                preparedStatement.setString(1, user.getEmail());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.setInt(3, user.getType() == UserType.STUDENT ? 0 : 1);
                preparedStatement.setInt(4, user.getId());
                return preparedStatement.executeUpdate() == 1;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public User find(int id) {
        try (Connection connection = connectToDb();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM USERS WHERE ID = ? LIMIT 1")) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new User(resultSet.getInt(COL_ID),
                        resultSet.getString(COL_EMAIL),
                        resultSet.getString(COL_PASSWORD),
                        resultSet.getInt(COL_USERTYPE) == TYPE_STUDENT ? UserType.STUDENT : UserType.TEACHER);
            }
        } catch (SQLException | ClassNotFoundException e1) {
            e1.printStackTrace();
        }

        return null;
    }

    @Override
    public List<User> getAll() {
        ArrayList<User> users = new ArrayList<>();

        try (Statement statement = connectToDb().createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM USERS");
            while (resultSet.next()) {
                users.add(new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getInt(4) == TYPE_STUDENT ? UserType.STUDENT : UserType.TEACHER));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }

        return users;
    }

    @Override
    public boolean delete(int id) {
        try (Connection db = connectToDb();
             PreparedStatement statement = db.prepareStatement("DELETE FROM USERS WHERE ID = ? LIMIT 1;")) {
            statement.setInt(1, id);
            return statement.executeUpdate() == 1;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return false;
    }
}
