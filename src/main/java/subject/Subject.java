package subject;

import location.Location;
import user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @NotNull
    private String name;
    @Size(max = 100)
    /*
    @ManyToMany
    @JoinTable(name = "USR_SUB")
    */
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "USR_SUB", joinColumns = @JoinColumn(name = "fk_course"),
            inverseJoinColumns = @JoinColumn(name = "fk_user"))
    private List<User> users = new ArrayList<>();
    @ManyToOne
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

    public void addUser(User user) {
        getUsers().add(user);
    }
}
