package com.costmgn.costmgnsrv.service;

import com.costmgn.costmgnsrv.entity.User;

import java.util.List;

public interface UserService {
    void addUser(User bean);

    void removeUser(int id);

    void updatePassword(int id, String password);

    boolean login(String userId, String password);

    User getUser(String userId);

    List<User> getUsers(User user);
}
