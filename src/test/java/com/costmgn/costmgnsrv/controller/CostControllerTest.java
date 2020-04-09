package com.costmgn.costmgnsrv.controller;

import com.costmgn.costmgnsrv.entity.Receipt;
import com.costmgn.costmgnsrv.entity.User;
import com.costmgn.costmgnsrv.service.CostService;
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
public class CostControllerTest {
    private PostHelper postHelper;

    private User user;
    private Receipt cost;
    private ArgumentCaptor<User> userArgumentCaptor;
    private ArgumentCaptor<Receipt> costArgumentCaptor;

    private static final String URL_ADD_COST = "/cost/addCost";
    private static final String URL_UPDATE_COST = "/cost/updateCost";
    private static final String URL_GET_COST = "/cost/getCost";
    private static final String URL_GET_COSTS = "/cost/getCosts";
    private static final String URL_GET_COSTS_FOR_BUDGET = "/cost/getCostsForBudget";
    private static final String URL_SUBMIT_COST = "/cost/submitCost";
    private static final String URL_APPROVE_COST = "/cost/approveCost";
    private static final String URL_REFUSE_COST = "/cost/refuseCost";

    @MockBean
    private CostService service;
    @MockBean
    private WorkService workService;
    @Autowired
    private WebApplicationContext context;


    @Before
    public void setUp() {
        postHelper = new PostHelper(context);
        user = new User();
        user.setName("张三");

        cost = new Receipt();
        cost.setType("testCost");

        userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        costArgumentCaptor = ArgumentCaptor.forClass(Receipt.class);
    }

    @Test
    public void shouldCallRightWhenAddCost() throws Exception {
        postHelper.setSessionItem("user", user);
        postHelper.getBoolean(URL_ADD_COST, cost);

        verify(service).addCost(costArgumentCaptor.capture(), userArgumentCaptor.capture());
        assertEquals(cost.getType(), costArgumentCaptor.getValue().getType());
        assertEquals(user.getName(), userArgumentCaptor.getValue().getName());
    }

    @Test
    public void shouldCallRightWhenUpdateCost() throws Exception {
        cost.setType("test321");

        postHelper.getBoolean(URL_UPDATE_COST, cost);
        verify(service).updateCost(costArgumentCaptor.capture());
        assertEquals(costArgumentCaptor.getValue().getType(), "test321");
    }

    @Test
    public void shouldCallRightWhenGetCost() throws Exception {
        postHelper.getData(URL_GET_COST, 1, Receipt.class);
        verify(service).getCost(1);
    }

    @Test
    public void shouldCallRightWhenSubmitCost() throws Exception {
        IdListBean bean = new IdListBean();
        List<Integer> idList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            idList.add(i);
        }
        bean.setIdList(idList);

        postHelper.getBoolean(URL_SUBMIT_COST, bean);
        ArgumentCaptor<User> userArgumentCaptor =
                ArgumentCaptor.forClass(User.class);

        verify(workService, times(10))
                .updateStatus(anyInt(), eq(Status.NOT_AUDITED.ordinal()));
    }

    @Test
    public void shouldCallRightWhenRefuseCost() throws Exception {
        IdListBean bean = new IdListBean();
        List<Integer> idList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            idList.add(i);
        }
        bean.setIdList(idList);

        postHelper.getBoolean(URL_REFUSE_COST, bean);
        ArgumentCaptor<User> userArgumentCaptor =
                ArgumentCaptor.forClass(User.class);

        verify(workService, times(10))
                .updateStatus(anyInt(), eq(Status.REFUSED.ordinal()));
    }

    @Test
    public void shouldCallRightWhenApproveCost() throws Exception {
        IdListBean bean = new IdListBean();
        List<Integer> idList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            idList.add(i);
        }
        bean.setIdList(idList);

        postHelper.getBoolean(URL_APPROVE_COST, bean);
        ArgumentCaptor<User> userArgumentCaptor =
                ArgumentCaptor.forClass(User.class);

        verify(workService, times(10))
                .updateStatus(anyInt(), eq(Status.FINISHED.ordinal()));
    }

    @Test
    public void shouldCallRightWhenGetCosts() throws Exception {
        postHelper.setSessionItem("user", user);
        postHelper.getList(URL_GET_COSTS, 1, Receipt.class);
        verify(service).getCosts(userArgumentCaptor.capture(), eq(1));
        assertEquals(userArgumentCaptor.getValue().getName(), user.getName());
    }

    @Test
    public void shouldCallRightWhenGetCostsForBudget() throws Exception {
        postHelper.getList(URL_GET_COSTS_FOR_BUDGET, 1, Receipt.class);
        verify(service).getCosts(1);
    }
}
