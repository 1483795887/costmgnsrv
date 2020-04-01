package com.costmgn.costmgnsrv.service;

import com.costmgn.costmgnsrv.entity.User;
import com.costmgn.costmgnsrv.utils.ChangePasswordBean;

import java.util.List;

public interface UserService {
    void addUser(User bean);

    void removeUser(int id);

    void updatePassword(ChangePasswordBean bean);

    boolean login(User bean);

    User getUser(String userid);

    List<User> getUsers(User user);
}
