import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@NamedQueries(
        @NamedQuery(name = "getAllUsers", query = "SELECT u FROM User u")
)

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @NotNull
    @Pattern(regexp = ".*@.*\\..*")
    private String email;

    @NotNull
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$")
    private String password;

    private UserType type;

    public User(int id, String email, String password, UserType type) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.type = type;
    }

    public User() {
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;
        return getId() == user.getId() && getType().equals(user.getType()) &&
                getEmail().equals(user.getEmail()) && getPassword().equals(user.getPassword());
    }

    @Override
    public String toString() {
        return String.format("User{id=%d, email='%s', password='%s', type=%s}", id, email, password, type);
    }

    @Override
    public int hashCode() {
        return getId();
    }
}
