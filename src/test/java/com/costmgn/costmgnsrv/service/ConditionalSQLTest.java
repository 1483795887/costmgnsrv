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
public class ConditionalSQLTest {

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
            String department = "营销";
            User aUser = new User();
            aUser.setName(String.valueOf(i));
            aUser.setDepartment(department);
            if (i == 0)
                aUser.setPost(Post.DepartmentManager.toString());
            else
                aUser.setPost(Post.SalesMan.toString());
            userList.add(aUser);
        }

        for (int i = 0; i < 8; i++) {
            String department = "生产";
            User aUser = new User();
            aUser.setName(String.valueOf(i));
            aUser.setDepartment(department);
            if (i == 0)
                aUser.setPost(Post.DepartmentManager.toString());
            else
                aUser.setPost(Post.SalesMan.toString());
            userList.add(aUser);
        }

        return userList;
    }

    @Test
    @Transactional
    public void shouldUsersSameDepWhenUserIsDM() {
        testUser.setDepartment(Department.PRODUCE.toString());
        testUser.setPost(Post.DepartmentManager.toString());
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
        testUser.setDepartment(Department.MANAGEMENT.toString());
        testUser.setPost(Post.SystemManager.toString());
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
        testUser.setDepartment(Department.PRODUCE.toString());
        testUser.setPost(Post.SalesMan.toString());
        int oriCount = service.getUsers(testUser).size();

        List<User> userList = getUsersForDifferentDepartment();
        for (User user : userList) {
            service.addUser(user);
        }

        assertEquals(oriCount, service.getUsers(testUser).size());
    }
}
