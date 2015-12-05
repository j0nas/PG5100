package controller;

import dto.Subject;
import dto.User;
import dto.UserType;
import infrastructure.user.JpaUser;
import infrastructure.user.UserDao;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Model
public class UserController {
    @Inject
    @JpaUser
    private UserDao dao;

    private int selectedId;
    private User user;

    @PostConstruct
    public void init() {
        user = new User();
    }

    public void persist() {
        dao.persist(user);
    }

    public List<User> getAll() {
        return dao.getAll();
    }

    public void initUser() {
        user = dao.findById(selectedId);
    }

    public int getSelectedId() {
        return selectedId;
    }

    public void setSelectedId(int selectedId) {
        this.selectedId = selectedId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void delete(int id) {
        dao.removeById(id);
    }

    public List<SelectItem> getUserTypes() {
        return Arrays.asList(UserType.values()).stream().map(t -> new SelectItem(t, t.name())).collect(Collectors.toList());
    }

    public List<String> getSelectedSubjects() {
        return user.getSubjects().stream().map(Subject::getName).collect(Collectors.toList());
    }
}
