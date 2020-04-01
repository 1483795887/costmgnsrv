package com.costmgn.costmgnsrv.service;

import com.costmgn.costmgnsrv.bean.UserBean;
import com.costmgn.costmgnsrv.utils.ChangePasswordBean;

import java.util.List;

public interface UserService {
    void addUser(UserBean bean);

    void removeUser(int id);

    void updatePassword(ChangePasswordBean bean);

    boolean login(UserBean bean);

    UserBean getUser(String userid);

    List<UserBean> getUsers();
}
