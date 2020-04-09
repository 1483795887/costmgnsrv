package com.costmgn.costmgnsrv.controller;

import com.costmgn.costmgnsrv.entity.Plan;
import com.costmgn.costmgnsrv.entity.User;
import com.costmgn.costmgnsrv.service.PlanService;
import com.costmgn.costmgnsrv.service.WorkService;
import com.costmgn.costmgnsrv.utils.IdListBean;
import com.costmgn.costmgnsrv.utils.PostHelper;
import com.costmgn.costmgnsrv.utils.Status;
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

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlanControllerTest {
    private PostHelper postHelper;

    private User user;
    private Plan plan;
    private ArgumentCaptor<User> userArgumentCaptor;
    private ArgumentCaptor<Plan> planArgumentCaptor;

    private static final String URL_ADD_PLAN = "/plan/addPlan";
    private static final String URL_UPDATE_PLAN = "/plan/updatePlan";
    private static final String URL_GET_PLAN = "/plan/getPlan";
    private static final String URL_GET_PLANS = "/plan/getPlans";
    private static final String URL_SUBMIT_PLAN = "/plan/submitPlan";
    private static final String URL_APPROVE_PLAN = "/plan/approvePlan";
    private static final String URL_REFUSE_PLAN = "/plan/refusePlan";

    @MockBean
    private PlanService service;
    @MockBean
    private WorkService workService;
    @Autowired
    private WebApplicationContext context;


    @Before
    public void setUp() {
        postHelper = new PostHelper(context);
        user = new User();
        user.setName("张三");

        plan = new Plan();
        plan.setTitle("testPlan");

        userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        planArgumentCaptor = ArgumentCaptor.forClass(Plan.class);
    }

    @Test
    public void shouldCallRightWhenAddPlan() throws Exception {
        postHelper.setSessionItem("user", user);
        postHelper.getBoolean(URL_ADD_PLAN, plan);

        verify(service).addPlan(planArgumentCaptor.capture(), userArgumentCaptor.capture());
        assertEquals(plan.getTitle(), planArgumentCaptor.getValue().getTitle());
        assertEquals(user.getName(), userArgumentCaptor.getValue().getName());
    }

    @Test
    public void shouldCallRightWhenUpdatePlan() throws Exception {
        plan.setTitle("test321");

        postHelper.getBoolean(URL_UPDATE_PLAN, plan);
        verify(service).updatePlan(planArgumentCaptor.capture());
        assertEquals(planArgumentCaptor.getValue().getTitle(), "test321");
    }

    @Test
    public void shouldCallRightWhenGetPlan() throws Exception {
        postHelper.getData(URL_GET_PLAN, 1, Plan.class);
        verify(service).getPlan(1);
    }

    @Test
    public void shouldCallRightWhenSubmitPlan() throws Exception {
        IdListBean bean = new IdListBean();
        List<Integer> idList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            idList.add(i);
        }
        bean.setIdList(idList);

        postHelper.getBoolean(URL_SUBMIT_PLAN, bean);
        ArgumentCaptor<User> userArgumentCaptor =
                ArgumentCaptor.forClass(User.class);

        verify(workService, times(10))
                .updateStatus(anyInt(), eq(Status.NOT_AUDITED.ordinal()));
    }

    @Test
    public void shouldCallRightWhenRefusePlan() throws Exception {
        IdListBean bean = new IdListBean();
        List<Integer> idList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            idList.add(i);
        }
        bean.setIdList(idList);

        postHelper.getBoolean(URL_REFUSE_PLAN, bean);
        ArgumentCaptor<User> userArgumentCaptor =
                ArgumentCaptor.forClass(User.class);

        verify(workService, times(10))
                .updateStatus(anyInt(), eq(Status.REFUSED.ordinal()));
    }

    @Test
    public void shouldCallRightWhenApprovePlan() throws Exception {
        IdListBean bean = new IdListBean();
        List<Integer> idList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            idList.add(i);
        }
        bean.setIdList(idList);

        postHelper.getBoolean(URL_APPROVE_PLAN, bean);
        ArgumentCaptor<User> userArgumentCaptor =
                ArgumentCaptor.forClass(User.class);

        verify(workService, times(10))
                .updateStatus(anyInt(), eq(Status.FINISHED.ordinal()));
    }

    @Test
    public void shouldCallRightWhenGetPlans() throws Exception {
        postHelper.setSessionItem("user", user);
        postHelper.getList(URL_GET_PLANS, 1, Plan.class);
        verify(service).getPlans(userArgumentCaptor.capture(), eq(1));
        assertEquals(userArgumentCaptor.getValue().getName(), user.getName());
    }
}
