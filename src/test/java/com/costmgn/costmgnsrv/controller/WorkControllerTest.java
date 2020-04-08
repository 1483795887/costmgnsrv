package com.costmgn.costmgnsrv.controller;

import com.costmgn.costmgnsrv.entity.User;
import com.costmgn.costmgnsrv.entity.Work;
import com.costmgn.costmgnsrv.service.WorkService;
import com.costmgn.costmgnsrv.utils.PostHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WorkControllerTest {
    private PostHelper postHelper;

    private static final String URL_GET_CUR_WORKS = "/work/getCurWorks";
    private static final String URL_GET_TO_DO_WORKS = "/work/getToDoWorks";

    private User user;

    @MockBean
    WorkService workService;
    @Autowired
    private WebApplicationContext context;

    @Before
    public void setUp() {
        postHelper = new PostHelper(context);
        user = new User();
        user.setName("张三");
    }

    @Test
    public void shouldCallRightWhenGetCurWorks() throws Exception {
        postHelper.setSessionItem("user", user);
        postHelper.getList(URL_GET_CUR_WORKS, null, Work.class);
        ArgumentCaptor<User> argumentCaptor
                = ArgumentCaptor.forClass(User.class);
        verify(workService).getCurWorks(argumentCaptor.capture());
        assertEquals(argumentCaptor.getValue().getName(), user.getName());
    }

    @Test
    public void shouldCallRightWhenGetToDoWorks() throws Exception {
        postHelper.setSessionItem("user", user);
        postHelper.getList(URL_GET_TO_DO_WORKS, null, Work.class);
        ArgumentCaptor<User> argumentCaptor
                = ArgumentCaptor.forClass(User.class);
        verify(workService).getToDoWorks(argumentCaptor.capture());
        assertEquals(argumentCaptor.getValue().getName(), user.getName());
    }

}
