package com.costmgn.costmgnsrv.service;

import com.costmgn.costmgnsrv.entity.User;
import com.costmgn.costmgnsrv.entity.UserExample;
import com.costmgn.costmgnsrv.mapper.UserMapper;
import com.costmgn.costmgnsrv.service.impl.UserServiceImpl;
import com.costmgn.costmgnsrv.utils.Department;
import com.costmgn.costmgnsrv.utils.Post;
import com.costmgn.costmgnsrv.utils.UserIdMaker;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {
    private final static int TEST_ID = 1;
    private final static String OLD_PASSWORD = "123456";//默认密码
    private final static String NEW_PASSWORD = "abcdedf";
    private final static String USER_ID = "20200302001";
    private UserService service;
    private UserMapper mapper;
    private ArgumentCaptor<User> userArgumentCaptor;
    private User testUser;

    @Before
    public void setUp() {
        testUser = new User();
        testUser.setPassword(OLD_PASSWORD);
        testUser.setId(TEST_ID);
        testUser.setUserid(USER_ID);

        testUser.setName("张三");

        mapper = mock(UserMapper.class);
        service = new UserServiceImpl(mapper);

        when(mapper.selectByPrimaryKey(TEST_ID)).thenReturn(testUser);
        List<User> userList = new ArrayList<>();
        userList.add(testUser);
        when(mapper.selectByExample(any(UserExample.class))).thenReturn(userList);
        userArgumentCaptor = ArgumentCaptor.forClass(User.class);
    }

    @Test
    public void shouldCallSelectWhenGetUser() {
        User user = service.getUser(USER_ID);
        verify(mapper).selectByExample(any(UserExample.class));
        assertEquals(user.getName(), testUser.getName());
    }

    @Test
    public void shouldCallInsertWhenAddUser() {
        testUser.setPost(Post.SalesMan.ordinal());
        testUser.setDepartment(Department.PRODUCE.ordinal());
        service.addUser(testUser);
        verify(mapper).insert(any(User.class));
    }

    @Test
    public void shouldSetDefaultValueWhenAddUser() {
        testUser.setPost(Post.SalesMan.ordinal());
        testUser.setDepartment(Department.PRODUCE.ordinal());
        testUser.setPassword(null);
        testUser.setInpost(false);
        when(mapper.selectMaxId()).thenReturn(1);
        service.addUser(testUser);

        assertEquals(testUser.getPassword(), OLD_PASSWORD);
        assertTrue(testUser.getInpost());
        assertEquals(testUser.getUserid(), UserIdMaker.makeId(
                Department.PRODUCE, Post.SalesMan, 1 + 1));
    }

    @Test
    public void shouldCallSelectWhenRemove() {
        service.removeUser(TEST_ID);
        verify(mapper).selectByPrimaryKey(TEST_ID);
    }

    @Test
    public void shouldCallUpdateWhenRemove() {
        service.removeUser(TEST_ID);
        verify(mapper).updateByPrimaryKey(userArgumentCaptor.capture());
        assertEquals(userArgumentCaptor.getValue().getInpost(), false);
        assertEquals(userArgumentCaptor.getValue().getName(), testUser.getName());
    }

    @Test
    public void shouldCallSelectAndUpdateWhenUpdatePassword() {
        service.updatePassword(TEST_ID, NEW_PASSWORD);
        verify(mapper).selectByPrimaryKey(TEST_ID);
        verify(mapper).updateByPrimaryKey(userArgumentCaptor.capture());
        assertEquals(userArgumentCaptor.getValue().getName(), testUser.getName());
        assertEquals(userArgumentCaptor.getValue().getPassword(), NEW_PASSWORD);
    }

    @Test
    public void shouldFalseWhenUserNotExist() {
        when(mapper.selectByExample(any(UserExample.class))).thenReturn(new ArrayList<>());
        boolean result = service.login("123456", OLD_PASSWORD);
        assertFalse(result);
    }

    @Test
    public void shouldFalseWhenPasswordWrong() {
        assertFalse(service.login(USER_ID, NEW_PASSWORD));
    }

    @Test
    public void shouldTrueWhenPasswordRight() {
        assertTrue(service.login(USER_ID, OLD_PASSWORD));
    }

    @Test
    public void shouldCallRightWhenGetUserById() {
        service.getUser(1);
        verify(mapper).selectByPrimaryKey(1);
    }
}
