package com.costmgn.costmgnsrv.service;

import com.costmgn.costmgnsrv.entity.Plan;
import com.costmgn.costmgnsrv.entity.User;
import com.costmgn.costmgnsrv.entity.Work;
import com.costmgn.costmgnsrv.mapper.PlanMapper;
import com.costmgn.costmgnsrv.mapper.WorkMapper;
import com.costmgn.costmgnsrv.service.impl.PlanServiceImpl;
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

public class PlanServiceTest {

    private PlanService planService;
    private PlanMapper planMapper;
    private WorkMapper workMapper;
    private EntitySelector selector;

    private User salesman;

    @Before
    public void setUp() {
        planMapper = mock(PlanMapper.class);
        workMapper = mock(WorkMapper.class);
        selector = mock(EntitySelector.class);
        planService = new PlanServiceImpl(planMapper, workMapper, selector);

        salesman = new User();
        salesman.setId(1);
        salesman.setDepartment(Department.SALES.ordinal());
        salesman.setPost(Post.SalesMan.ordinal());
    }

    @Test
    public void shouldCallPlanInsertWhenAddPlan() {
        Plan plan = new Plan();
        plan.setId(1);
        planService.addPlan(plan, new User());
        verify(planMapper).insert(any(Plan.class));
        ArgumentCaptor<Work> workArgumentCaptor = ArgumentCaptor.forClass(Work.class);
        verify(workMapper).updateByPrimaryKey(workArgumentCaptor.capture());
        assertEquals(workArgumentCaptor.getValue().getEntityId(), 1);
        assertEquals(workArgumentCaptor.getValue().getType(), EntityType.PLAN.ordinal());
    }

    @Test
    public void shouldCallWorkInsertWhenAddPlan() {
        ArgumentCaptor<Work> workArgumentCaptor = ArgumentCaptor.forClass(Work.class);
        planService.addPlan(new Plan(), salesman);
        verify(workMapper).insert(workArgumentCaptor.capture());
        Work work = workArgumentCaptor.getValue();
        assertEquals(work.getStatus(), Status.NOT_SUBMITTED.ordinal());
        assertEquals(work.getDepartment(), Department.SALES.ordinal());
    }

    @Test
    public void shouldCallSelectWhenGetPlan() {
        planService.getPlan(1);
        verify(planMapper).selectByPrimaryKey(1);
    }

    @Test
    public void shouldProcedureRightWhenUpdate() {
        Plan plan = new Plan();
        plan.setId(1);
        planService.updatePlan(plan);
        verify(planMapper).updateByPrimaryKey(any(Plan.class));
    }

    @Test
    public void shouldCallSelectorWhenGetPlans() {
        planService.getPlans(salesman, 1);
        verify(selector).getIds(salesman, 1, EntityType.PLAN.ordinal());
    }
}