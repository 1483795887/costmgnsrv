package com.costmgn.costmgnsrv.service;

import com.costmgn.costmgnsrv.entity.Budget;
import com.costmgn.costmgnsrv.entity.User;
import com.costmgn.costmgnsrv.entity.Work;
import com.costmgn.costmgnsrv.mapper.BudgetMapper;
import com.costmgn.costmgnsrv.mapper.WorkMapper;
import com.costmgn.costmgnsrv.service.impl.BudgetServiceImpl;
import com.costmgn.costmgnsrv.utils.Department;
import com.costmgn.costmgnsrv.utils.EntityType;
import com.costmgn.costmgnsrv.utils.Post;
import com.costmgn.costmgnsrv.utils.Status;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class BudgetServiceTest {

    private BudgetService budgetService;
    private BudgetMapper budgetMapper;
    private WorkMapper workMapper;
    private EntitySelector selector;

    private User salesman;

    @Before
    public void setUp() {
        budgetMapper = mock(BudgetMapper.class);
        workMapper = mock(WorkMapper.class);
        selector = mock(EntitySelector.class);
        budgetService = new BudgetServiceImpl(budgetMapper, workMapper, selector);

        salesman = new User();
        salesman.setId(1);
        salesman.setDepartment(Department.SALES.ordinal());
        salesman.setPost(Post.SalesMan.ordinal());
    }

    @Test
    public void shouldCallBudgetInsertWhenAddBudget() {
        Budget budget = new Budget();
        budget.setId(1);
        budgetService.addBudget(budget, new User());
        verify(budgetMapper).insert(any(Budget.class));
        ArgumentCaptor<Work> workArgumentCaptor = ArgumentCaptor.forClass(Work.class);
        verify(workMapper).updateByPrimaryKey(workArgumentCaptor.capture());
        assertEquals(workArgumentCaptor.getValue().getEntityId(), 1);
        assertEquals(workArgumentCaptor.getValue().getType(), EntityType.BUDGET.ordinal());
    }

    @Test
    public void shouldCallWorkInsertWhenAddBudget() {
        ArgumentCaptor<Work> workArgumentCaptor = ArgumentCaptor.forClass(Work.class);
        budgetService.addBudget(new Budget(), salesman);
        verify(workMapper).insert(workArgumentCaptor.capture());
        Work work = workArgumentCaptor.getValue();
        assertEquals(work.getStatus(), Status.NOT_SUBMITTED.ordinal());
        assertEquals(work.getDepartment(), Department.SALES.ordinal());
    }

    @Test
    public void shouldCallSelectWhenGetBudget() {
        budgetService.getBudget(1);
        verify(budgetMapper).selectByPrimaryKey(1);
    }

    @Test
    public void shouldProcedureRightWhenUpdate() {
        Budget budget = new Budget();
        budget.setId(1);
        budgetService.updateBudget(budget);
        verify(budgetMapper).updateByPrimaryKey(any(Budget.class));
    }

    @Test
    public void shouldCallSelectorWhenGetBudgets() {
        budgetService.getBudgets(salesman, 1);
        verify(selector).getIds(salesman, 1, EntityType.BUDGET.ordinal());
    }
}