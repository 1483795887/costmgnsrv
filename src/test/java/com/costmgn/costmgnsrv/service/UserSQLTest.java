package com.costmgn.costmgnsrv.service;

import com.costmgn.costmgnsrv.entity.User;
import com.costmgn.costmgnsrv.utils.Department;
import com.costmgn.costmgnsrv.utils.Post;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserSQLTest {

    @Autowired
    private UserService service;
    private User testUser;

    @Before
    public void setUp() {
        testUser = new User();
    }

    private List<User> getUsersForDifferentDepartment() {
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            int department = Department.SALES.ordinal();
            User aUser = new User();
            aUser.setName(String.valueOf(i));
            aUser.setDepartment(department);
            if (i == 0)
                aUser.setPost(Post.DepartmentManager.ordinal());
            else
                aUser.setPost(Post.SalesMan.ordinal());
            userList.add(aUser);
        }

        for (int i = 0; i < 8; i++) {
            int department = Department.PRODUCE.ordinal();
            User aUser = new User();
            aUser.setName(String.valueOf(i));
            aUser.setDepartment(department);
            if (i == 0)
                aUser.setPost(Post.DepartmentManager.ordinal());
            else
                aUser.setPost(Post.SalesMan.ordinal());
            userList.add(aUser);
        }

        return userList;
    }

    @Test
    @Transactional
    public void shouldUsersSameDepWhenUserIsDM() {
        testUser.setDepartment(Department.PRODUCE.ordinal());
        testUser.setPost(Post.DepartmentManager.ordinal());
        int oriCount = service.getUsers(testUser).size();

        List<User> userList = getUsersForDifferentDepartment();
        for (User user : userList) {
            service.addUser(user);
        }

        assertEquals(oriCount + 8, service.getUsers(testUser).size());
    }

    @Test
    @Transactional
    public void shouldUsersAllWhenUserIsSM() {
        testUser.setDepartment(Department.MANAGEMENT.ordinal());
        testUser.setPost(Post.SystemManager.ordinal());
        int oriCount = service.getUsers(testUser).size();

        List<User> userList = getUsersForDifferentDepartment();
        for (User user : userList) {
            service.addUser(user);
        }

        assertEquals(oriCount + 14, service.getUsers(testUser).size());
    }

    @Test
    @Transactional
    public void shouldNoUsersWhenUserIsSaleMan() {
        testUser.setDepartment(Department.PRODUCE.ordinal());
        testUser.setPost(Post.SalesMan.ordinal());
        int oriCount = service.getUsers(testUser).size();

        List<User> userList = getUsersForDifferentDepartment();
        for (User user : userList) {
            service.addUser(user);
        }

        assertEquals(oriCount, service.getUsers(testUser).size());
    }
}
