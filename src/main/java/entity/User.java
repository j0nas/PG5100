package entity;

import common.UserType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQuery(name = "User.getAll", query = "SELECT u FROM User u")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Pattern(regexp = ".*@.*\\..*")
    private String email;

    @NotNull
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$")
    private String password;

    private UserType type;
    @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Subject> subjects = new ArrayList<>();

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
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
        final StringBuilder stringBuilder = new StringBuilder();
        subjects.forEach(subject -> stringBuilder.append(subject).append(" "));
        return String.format("User{id=%d, email='%s', password='%s', type=%s, subjects=[ %s]}",
                id, email, password, type, stringBuilder.toString());
    }

    @Override
    public int hashCode() {
        return getId();
    }
}
