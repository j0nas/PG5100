package subject;

import location.Location;
import user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @NotNull
    private String name;
    @Size(max = 100)
    @OneToMany
    @CollectionTable(name = "SUBJECT_USERS", joinColumns = @JoinColumn(name = "ID"))
    private List<User> users;
    @OneToOne
    @JoinColumn(name = "FK_LOCATION")
    private Location location;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }
}
