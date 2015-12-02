package controller;

import ejb.SubjectRegistryBean;
import entity.Location;
import entity.Subject;
import entity.User;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.List;

@Model
public class SubjectController {
    private SubjectRegistryBean subjectRegistry;
    private String name;
    private List<User> users;

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

    @Inject
    public void setSubjectRegistry(SubjectRegistryBean subjectRegistry) {
        this.subjectRegistry = subjectRegistry;
    }

    public void save(String name, Location location, List<User> users) {
        subjectRegistry.save(name, location, users);
    }

    public List<Subject> get() {
        return subjectRegistry.getAll();
    }
}
