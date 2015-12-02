package entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Subject.getAll",
                query = "select s from Subject s"),
        @NamedQuery(name = "Subject.getUsersInCourse",
                query = "select distinct (u) from User u " +
                        "where exists (select s from Subject s where s.id = :subjectId)"),
        @NamedQuery(name = "Subject.getLocationInSubject",
                query = "select s.location from Subject s where s.id = :subjectId")
})
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @NotNull
    private String name;
    @Size(max = 100)
    @ManyToMany
    @JoinTable(name = "USR_SUB", joinColumns = @JoinColumn(name = "FK_COURSE"),
            inverseJoinColumns = @JoinColumn(name = "FK_USER"))
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
}
