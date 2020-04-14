package com.costmgn.costmgnsrv.controller;

import com.costmgn.costmgnsrv.entity.Budget;
import com.costmgn.costmgnsrv.entity.User;
import com.costmgn.costmgnsrv.service.BudgetService;
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
public class BudgetControllerTest {
    private PostHelper postHelper;

    private User user;
    private Budget budget;
    private ArgumentCaptor<User> userArgumentCaptor;
    private ArgumentCaptor<Budget> budgetArgumentCaptor;

    private static final String URL_ADD_BUDGET = "/budget/addBudget";
    private static final String URL_UPDATE_BUDGET = "/budget/updateBudget";
    private static final String URL_GET_BUDGET = "/budget/getBudget";
    private static final String URL_GET_BUDGETS = "/budget/getBudgets";
    private static final String URL_SUBMIT_BUDGET = "/budget/submitBudget";
    private static final String URL_APPROVE_BUDGET = "/budget/approveBudget";
    private static final String URL_REFUSE_BUDGET = "/budget/refuseBudget";

    @MockBean
    private BudgetService service;
    @MockBean
    private WorkService workService;
    @Autowired
    private WebApplicationContext context;


    @Before
    public void setUp() {
        postHelper = new PostHelper(context);
        user = new User();
        user.setName("张三");

        budget = new Budget();
        budget.setType("testBudget");

        userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        budgetArgumentCaptor = ArgumentCaptor.forClass(Budget.class);
    }

    @Test
    public void shouldCallRightWhenAddBudget() throws Exception {
        postHelper.setSessionItem("user", user);
        postHelper.getBoolean(URL_ADD_BUDGET, budget);

        verify(service).addBudget(budgetArgumentCaptor.capture(), userArgumentCaptor.capture());
        assertEquals(budget.getType(), budgetArgumentCaptor.getValue().getType());
        assertEquals(user.getName(), userArgumentCaptor.getValue().getName());
    }

    @Test
    public void shouldCallRightWhenUpdateBudget() throws Exception {
        budget.setType("test321");

        postHelper.getBoolean(URL_UPDATE_BUDGET, budget);
        verify(service).updateBudget(budgetArgumentCaptor.capture());
        assertEquals(budgetArgumentCaptor.getValue().getType(), "test321");
    }

    @Test
    public void shouldCallRightWhenGetBudget() throws Exception {
        postHelper.getData(URL_GET_BUDGET, 1, Budget.class);
        verify(service).getBudget(1);
    }

    @Test
    public void shouldCallRightWhenSubmitBudget() throws Exception {
        IdListBean bean = new IdListBean();
        List<Integer> idList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            idList.add(i);
        }
        bean.setIdList(idList);

        postHelper.getBoolean(URL_SUBMIT_BUDGET, bean);
        ArgumentCaptor<User> userArgumentCaptor =
                ArgumentCaptor.forClass(User.class);

        verify(workService, times(10))
                .updateStatus(anyInt(), eq(Status.NOT_PASSED.ordinal()));
    }

    @Test
    public void shouldCallRightWhenRefuseBudget() throws Exception {
        IdListBean bean = new IdListBean();
        List<Integer> idList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            idList.add(i);
        }
        bean.setIdList(idList);

        postHelper.getBoolean(URL_REFUSE_BUDGET, bean);
        ArgumentCaptor<User> userArgumentCaptor =
                ArgumentCaptor.forClass(User.class);

        verify(workService, times(10))
                .updateStatus(anyInt(), eq(Status.REFUSED.ordinal()));
    }

    @Test
    public void shouldCallRightWhenApproveBudget() throws Exception {
        IdListBean bean = new IdListBean();
        List<Integer> idList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            idList.add(i);
        }
        bean.setIdList(idList);

        postHelper.getBoolean(URL_APPROVE_BUDGET, bean);
        ArgumentCaptor<User> userArgumentCaptor =
                ArgumentCaptor.forClass(User.class);

        verify(workService, times(10))
                .updateStatus(anyInt(), eq(Status.FINISHED.ordinal()));
    }

    @Test
    public void shouldCallRightWhenGetBudgets() throws Exception {
        postHelper.setSessionItem("user", user);
        postHelper.getList(URL_GET_BUDGETS, 1, Budget.class);
        verify(service).getBudgets(userArgumentCaptor.capture(), eq(1));
        assertEquals(userArgumentCaptor.getValue().getName(), user.getName());
    }
}
