package dto;

import javax.annotation.PreDestroy;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@NamedQuery(name = "Subject.getAll", query = "select s from Subject s")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String name;

    @Size(max = 100)
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "USR_SUB")
    private List<User> users;

    @ManyToOne
    @JoinColumn(name = "FK_LOCATION")
    @Valid
    private Location location;

    @PreDestroy
    private void removeLocationsAndUsersFromSubject() {
        for (User user : users) {
            user.getSubjects().remove(this);
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
