package com.costmgn.costmgnsrv.service.impl;

import com.costmgn.costmgnsrv.entity.User;
import com.costmgn.costmgnsrv.entity.UserExample;
import com.costmgn.costmgnsrv.mapper.UserMapper;
import com.costmgn.costmgnsrv.service.UserService;
import com.costmgn.costmgnsrv.utils.Department;
import com.costmgn.costmgnsrv.utils.Post;
import com.costmgn.costmgnsrv.utils.UserIdMaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final static String DEFAULT_PASSWORD = "123456";


    private UserMapper mapper;

    @Autowired
    public UserServiceImpl(UserMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void addUser(User bean) {
        bean.setInpost(true);
        bean.setPassword(DEFAULT_PASSWORD);
        bean.setUserid(UserIdMaker.makeId(Department.values()[bean.getDepartment()],
                Post.values()[bean.getPost()], mapper.selectMaxId() + 1));
        mapper.insert(bean);
    }

    @Override
    public void removeUser(int id) {
        User user = mapper.selectByPrimaryKey(id);
        user.setInpost(false);
        mapper.updateByPrimaryKey(user);
    }

    @Override
    public void updatePassword(int id, String password) {
        User user = mapper.selectByPrimaryKey(id);
        user.setPassword(password);
        mapper.updateByPrimaryKey(user);
    }

    @Override
    public boolean login(String userId, String password) {
        User user = getUser(userId);
        if (user == null)
            return false;
        else {
            return user.getPassword().equals(password);
        }
    }

    //由于userId的唯一性，导致userList只会有一个或另个
    @Override
    public User getUser(String userId) {
        UserExample example = new UserExample();
        example.createCriteria().andUseridEqualTo(userId);
        List<User> userList = mapper.selectByExample(example);
        if (userList.size() == 0)
            return null;
        else
            return userList.get(0);
    }

    @Override
    public List<User> getUsers(User user) {
        UserExample example = new UserExample();
        if (user.getPost().equals(Post.DepartmentManager.ordinal()))
            example.createCriteria().andDepartmentEqualTo(user.getDepartment());
        else if (!user.getPost().equals(Post.SystemManager.ordinal()))
            return new ArrayList<>();
        return mapper.selectByExample(example);
    }
}
