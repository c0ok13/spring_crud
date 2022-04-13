package web.service;


import web.entity.User;

import java.util.List;

public interface UserService {
    void add(User user);
    List<User> listUsers();
}
