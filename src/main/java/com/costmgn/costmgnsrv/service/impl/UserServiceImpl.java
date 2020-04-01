package com.costmgn.costmgnsrv.service.impl;

import com.costmgn.costmgnsrv.entity.User;
import com.costmgn.costmgnsrv.mapper.UserMapper;
import com.costmgn.costmgnsrv.service.UserService;
import com.costmgn.costmgnsrv.utils.ChangePasswordBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserMapper mapper;

    @Autowired
    public UserServiceImpl(UserMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void addUser(User bean) {

    }

    @Override
    public void removeUser(int id) {

    }

    @Override
    public void updatePassword(ChangePasswordBean bean) {

    }

    @Override
    public boolean login(User bean) {
        return false;
    }

    @Override
    public User getUser(String userid) {
        return null;
    }

    @Override
    public List<User> getUsers(User user) {
        return null;
    }
}
