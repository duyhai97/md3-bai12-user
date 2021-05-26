package service;

import model.User;

import java.sql.SQLException;
import java.util.List;

public interface Service {
    List<User> findAll();

    void save(User user);

    User findByID(int id);

    void update(int id, User user) throws SQLException;

    void remote(int id);

}
