package com.costmgn.costmgnsrv.service;

import com.costmgn.costmgnsrv.entity.Budget;
import com.costmgn.costmgnsrv.entity.Receipt;
import com.costmgn.costmgnsrv.entity.User;
import com.costmgn.costmgnsrv.entity.Work;
import com.costmgn.costmgnsrv.mapper.BudgetMapper;
import com.costmgn.costmgnsrv.mapper.ReceiptMapper;
import com.costmgn.costmgnsrv.mapper.WorkMapper;
import com.costmgn.costmgnsrv.service.impl.CostServiceImpl;
import com.costmgn.costmgnsrv.utils.Department;
import com.costmgn.costmgnsrv.utils.EntityType;
import com.costmgn.costmgnsrv.utils.Post;
import com.costmgn.costmgnsrv.utils.Status;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CostServiceTest {

    private CostService costService;
    private ReceiptMapper receiptMapper;
    private WorkMapper workMapper;
    private BudgetMapper budgetMapper;
    private EntitySelector selector;

    private User salesman;

    @Before
    public void setUp() {
        receiptMapper = mock(ReceiptMapper.class);
        workMapper = mock(WorkMapper.class);
        budgetMapper = mock(BudgetMapper.class);
        selector = mock(EntitySelector.class);
        costService = new CostServiceImpl(receiptMapper,
                workMapper, budgetMapper, selector);

        salesman = new User();
        salesman.setId(1);
        salesman.setDepartment(Department.SALES.ordinal());
        salesman.setPost(Post.SalesMan.ordinal());
    }

    @Test
    public void shouldCallReceiptInsertWhenAddReceipt() {
        Receipt receipt = new Receipt();
        receipt.setId(1);
        receipt.setMoney(new BigDecimal(10));
        receipt.setBudgetId(21);
        Budget budget = new Budget();
        budget.setOccupyMoney(new BigDecimal(10));
        when(budgetMapper.selectByPrimaryKey(21)).thenReturn(budget);
        costService.addCost(receipt, new User());
        verify(receiptMapper).insert(any(Receipt.class));
        ArgumentCaptor<Work> workArgumentCaptor = ArgumentCaptor.forClass(Work.class);
        verify(workMapper).updateByPrimaryKey(workArgumentCaptor.capture());
        assertEquals(workArgumentCaptor.getValue().getEntityId(), 1);
        assertEquals(workArgumentCaptor.getValue().getType(), EntityType.RECEIPT.ordinal());
    }

    @Test
    public void shouldUpdateBudgetWhenOccupyMoney() {
        Receipt receipt = new Receipt();
        receipt.setMoney(new BigDecimal(20));
        receipt.setBudgetId(1);
        Budget budget = new Budget();
        budget.setMoney(new BigDecimal(100));
        budget.setOccupyMoney(new BigDecimal(0));
        budget.setId(1);

        when(budgetMapper.selectByPrimaryKey(1)).thenReturn(budget);
        costService.occupyMoney(receipt);
        verify(budgetMapper).updateByPrimaryKey(any(Budget.class));

        assertEquals(budget.getOccupyMoney(), new BigDecimal(20));
    }

    @Test
    public void shouldCallRightWhenGetReceiptsByBudget() {
        int id = 1;
        costService.getCosts(id);
        verify(receiptMapper).selectByBudgetId(id);
    }

    @Test
    public void shouldCallWorkInsertWhenAddReceipt() {
        ArgumentCaptor<Work> workArgumentCaptor = ArgumentCaptor.forClass(Work.class);

        Receipt receipt = new Receipt();
        receipt.setMoney(new BigDecimal(20));
        receipt.setBudgetId(1);
        Budget budget = new Budget();
        budget.setMoney(new BigDecimal(100));
        budget.setOccupyMoney(new BigDecimal(0));
        budget.setId(1);

        when(budgetMapper.selectByPrimaryKey(1)).thenReturn(budget);

        costService.addCost(receipt, salesman);
        verify(workMapper).insert(workArgumentCaptor.capture());
        Work work = workArgumentCaptor.getValue();
        assertEquals(work.getStatus(), Status.NOT_SUBMITTED.ordinal());
        assertEquals(work.getDepartment(), Department.SALES.ordinal());
    }

    @Test
    public void shouldCallSelectWhenGetReceipt() {
        costService.getCost(1);
        verify(receiptMapper).selectByPrimaryKey(1);
    }

    @Test
    public void shouldProcedureRightWhenUpdate() {
        Receipt receipt = new Receipt();
        receipt.setId(1);
        costService.updateCost(receipt);
        verify(receiptMapper).updateByPrimaryKey(any(Receipt.class));
    }

    @Test
    public void shouldCallSelectorWhenGetReceipts() {
        costService.getCosts(salesman, 1);
        verify(selector).getIds(salesman, 1, EntityType.RECEIPT.ordinal());
    }
}