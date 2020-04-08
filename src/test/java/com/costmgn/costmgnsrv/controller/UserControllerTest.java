package com.costmgn.costmgnsrv.controller;

import com.costmgn.costmgnsrv.entity.User;
import com.costmgn.costmgnsrv.service.UserService;
import com.costmgn.costmgnsrv.utils.ChangePasswordBean;
import com.costmgn.costmgnsrv.utils.IdListBean;
import com.costmgn.costmgnsrv.utils.PostHelper;
import com.costmgn.costmgnsrv.utils.WebApiResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {
    private PostHelper postHelper;

    private static final String USER_ID = "20200301001";
    private static final int ID = 1;
    private static final String USER_NAME = "张三";
    private static final String PASSWORD = "123456";
    private static final String NEW_PASSWORD = "654321";

    private static final String URL_LOGIN = "/user/login";
    private static final String URL_ADD_USER = "/user/addUser";
    private static final String URL_REMOVE_USER = "/user/removeUser";
    private static final String URL_UPDATE_PASSWORD = "/user/updatePassword";
    private static final String URL_USER_LIST = "/user/getUserList";

    private User user;

    @MockBean
    UserService userService;
    @Autowired
    private WebApplicationContext context;

    @Before
    public void setUp() {
        postHelper = new PostHelper(context);
        user = new User();
        user.setId(ID);
        user.setName(USER_NAME);
        user.setUserid(USER_ID);
        user.setPassword(PASSWORD);
    }

    @Test
    public void shouldReturnFailedWhenUserIdOrPasswordNotRight()
            throws Exception {
        when(userService.login(USER_ID, PASSWORD)).thenReturn(false);
        WebApiResponse<User> webApiResponse =
                postHelper.getData(URL_LOGIN, user, User.class);

        assertNull(webApiResponse.getData());
        assertEquals(1, webApiResponse.getCode());
    }

    @Test
    public void shouldSuccessWhenLoginAllRight() throws Exception {
        when(userService.login(USER_ID, PASSWORD)).thenReturn(true);
        when(userService.getUser(USER_ID)).thenReturn(user);

        WebApiResponse<User> webApiResponse
                = postHelper.getData(URL_LOGIN, user, User.class);

        User returnUser = webApiResponse.getData();
        assertNotNull(returnUser);
        assertEquals(returnUser.getName(), user.getName());

        User sessionUser = (User) postHelper.getSessionItem("user");
        assertEquals(sessionUser.getUserid(), user.getUserid());
        assertEquals(0, webApiResponse.getCode());
    }

    @Test
    public void shouldCallAddWhenAddUser() throws Exception {
        postHelper.getBoolean(URL_ADD_USER, user);
        ArgumentCaptor<User> userArgumentCaptor =
                ArgumentCaptor.forClass(User.class);
        verify(userService).addUser(userArgumentCaptor.capture());

        assertEquals(userArgumentCaptor.getValue().getName(), user.getName());
    }

    @Test
    public void shouldCallRemoveWhenRemoveUser() throws Exception {
        IdListBean bean = new IdListBean();
        List<Integer> idList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            idList.add(i);
        }
        bean.setIdList(idList);

        postHelper.getBoolean(URL_REMOVE_USER, bean);
        ArgumentCaptor<User> userArgumentCaptor =
                ArgumentCaptor.forClass(User.class);

        verify(userService, times(10))
                .removeUser(anyInt());
    }

    @Test
    public void shouldCallRightWhenUpdatePassword() throws Exception {
        ChangePasswordBean bean = new ChangePasswordBean();
        bean.setId(user.getId());
        bean.setNewPassword(NEW_PASSWORD);
        bean.setPassword(user.getPassword());
        when(userService.getUser(ID)).thenReturn(user);
        postHelper.getBoolean(URL_UPDATE_PASSWORD, bean);
        verify(userService).getUser(ID);
        verify(userService).updatePassword(ID, NEW_PASSWORD);
    }

    @Test
    public void shouldFailedWhenUpdatePasswordWithWrongPassword()
            throws Exception {
        ChangePasswordBean bean = new ChangePasswordBean();
        bean.setId(ID);
        bean.setNewPassword(NEW_PASSWORD);
        bean.setPassword(NEW_PASSWORD);
        when(userService.getUser(ID)).thenReturn(user);
        WebApiResponse<Boolean> result =
                postHelper.getBoolean(URL_UPDATE_PASSWORD, bean);
        assertEquals(1, result.getCode());
        verify(userService, never()).updatePassword(anyInt(), anyString());
    }

    @Test
    public void shouldCallRightWhenGetUserList() throws Exception {
        postHelper.setSessionItem("user", user);
        postHelper.getList(URL_USER_LIST, null, User.class);
        ArgumentCaptor<User> argumentCaptor
                = ArgumentCaptor.forClass(User.class);
        verify(userService).getUsers(argumentCaptor.capture());
        assertEquals(argumentCaptor.getValue().getName(), user.getName());
    }
}
