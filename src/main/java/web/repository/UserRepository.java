package web.repository;

import web.entity.User;

import java.util.List;


public interface UserRepository {
    void add(User user);
    List<User> listUsers();
    User userById(long id);
    void updateUser(User user);
    void deleteUser(long id);
}
