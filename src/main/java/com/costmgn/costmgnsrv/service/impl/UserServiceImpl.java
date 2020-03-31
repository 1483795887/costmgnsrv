package com.costmgn.costmgnsrv.service.impl;

import com.costmgn.costmgnsrv.entity.User;
import com.costmgn.costmgnsrv.mapper.UserMapper;
import com.costmgn.costmgnsrv.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private UserMapper mapper;

    @Autowired
    public UserServiceImpl(UserMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public User getUser(String userid) {
        return null;
    }
}
