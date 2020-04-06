package com.costmgn.costmgnsrv.mapper;

import com.costmgn.costmgnsrv.entity.*;
import com.costmgn.costmgnsrv.utils.Department;
import com.costmgn.costmgnsrv.utils.Post;
import com.costmgn.costmgnsrv.utils.Status;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OtherMapperTert {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private WorkMapper workMapper;
    @Autowired
    private PlanMapper planMapper;
    @Autowired
    private BudgetMapper budgetMapper;
    @Autowired
    private ReceiptMapper receiptMapper;
    @Autowired
    private ContractMapper contractMapper;


    private User user;
    private Work work;
    private Plan plan;
    private Contract contract;
    private Budget budget;
    private Receipt receipt;

    @Before
    public void setUp() {
        user = new User();
        user.setPost(Post.SalesMan.ordinal());
        user.setDepartment(Department.PRODUCE.ordinal());
        user.setName("张三");
        user.setInpost(false);
        user.setUserid("123456");
        user.setPassword("123456");
        userMapper.insert(user);

        work = new Work();
        work.setStatus(Status.NOT_SUBMITTED.ordinal());
        work.setDate(new Date());
        work.setTitle("test");
        work.setUser(user);
        work.setDepartment(user.getDepartment());
        workMapper.insert(work);

        plan = new Plan();
        plan.setDescription("test");
        plan.setTitle("asdfs");
        plan.setWork(work);
        planMapper.insert(plan);

        contract = new Contract();
        contract.setCompany("A");
        contract.setContractDate(new Date());
        contract.setContractNo("123");
        contract.setDescription(" ");
        contract.setLastMonth(1);
        contract.setLegalPerson("aaa");
        contract.setMoney(new BigDecimal(123));
        contract.setPayMethod("sdaf");
        contract.setPayRequest("23");
        contract.setTitle("sadfasdf");
        contract.setWork(work);
        contract.setPlanId(plan.getId());
        contractMapper.insert(contract);

        budget = new Budget();
        budget.setMoney(new BigDecimal(100));
        budget.setOccupyMoney(new BigDecimal(100));
        budget.setType("123");
        budget.setMonth(10);
        budget.setYear(1234);
        budget.setWork(work);
        budgetMapper.insert(budget);

        receipt = new Receipt();
        receipt.setBudgetId(budget.getId());
        receipt.setType("123");
        receipt.setMoney(new BigDecimal(100));
        receipt.setWork(work);
        receiptMapper.insert(receipt);
    }

    @Test
    @Transactional
    public void shouldSelectPlanAndUserWhenSelectWork() {
        Plan thePlan = planMapper.selectByPrimaryKey(plan.getId());

        assertNotNull(thePlan.getWork());
        assertEquals(thePlan.getWork().getTitle(), work.getTitle());
        assertNotNull(thePlan.getWork().getUser());
        assertEquals(thePlan.getWork().getUser().getName(), user.getName());
    }

    @Test
    @Transactional
    public void shouldSelectContractAndUserWhenSelectWork() {
        Contract theContract = contractMapper.selectByPrimaryKey(plan.getId());

        assertNotNull(theContract.getWork());
        assertEquals(theContract.getWork().getTitle(), work.getTitle());
        assertNotNull(theContract.getWork().getUser());
        assertEquals(theContract.getWork().getUser().getName(), user.getName());
    }

    @Test
    @Transactional
    public void shouldSelectBudgetAndUserWhenSelectWork() {
        Budget theBudget = budgetMapper.selectByPrimaryKey(plan.getId());

        assertNotNull(theBudget.getWork());
        assertEquals(theBudget.getWork().getTitle(), work.getTitle());
        assertNotNull(theBudget.getWork().getUser());
        assertEquals(theBudget.getWork().getUser().getName(), user.getName());
    }

    @Test
    @Transactional
    public void shouldSelectReceiptAndUserWhenSelectWork() {
        Receipt receipt = receiptMapper.selectByPrimaryKey(plan.getId());

        assertNotNull(receipt.getWork());
        assertEquals(receipt.getWork().getTitle(), work.getTitle());
        assertNotNull(receipt.getWork().getUser());
        assertEquals(receipt.getWork().getUser().getName(), user.getName());
    }
}
