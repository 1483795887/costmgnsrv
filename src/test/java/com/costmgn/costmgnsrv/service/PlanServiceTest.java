package com.costmgn.costmgnsrv.service;

import com.costmgn.costmgnsrv.entity.Plan;
import com.costmgn.costmgnsrv.entity.User;
import com.costmgn.costmgnsrv.entity.Work;
import com.costmgn.costmgnsrv.mapper.PlanMapper;
import com.costmgn.costmgnsrv.mapper.WorkMapper;
import com.costmgn.costmgnsrv.service.impl.PlanServiceImpl;
import com.costmgn.costmgnsrv.utils.Department;
import com.costmgn.costmgnsrv.utils.Post;
import com.costmgn.costmgnsrv.utils.Status;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class PlanServiceTest {

    private PlanService planService;
    private PlanMapper planMapper;
    private WorkMapper workMapper;

    private User salesman, departmentManager, systemManager;

    @Before
    public void setUp() {
        planMapper = mock(PlanMapper.class);
        workMapper = mock(WorkMapper.class);
        planService = new PlanServiceImpl(planMapper, workMapper);

        salesman = new User();
        salesman.setId(1);
        salesman.setDepartment(Department.SALES.ordinal());
        salesman.setPost(Post.SalesMan.ordinal());

        departmentManager = new User();
        departmentManager.setId(2);
        departmentManager.setPost(Post.DepartmentManager.ordinal());
        departmentManager.setDepartment(Department.SALES.ordinal());

        systemManager = new User();
        systemManager.setId(3);
        systemManager.setDepartment(Department.MANAGEMENT.ordinal());
        systemManager.setPost(Post.SystemManager.ordinal());
    }

    @Test
    public void shouldCallPlanInsertWhenAddPlan() {
        planService.addPlan(new Plan(), new User());
        verify(planMapper).insert(any(Plan.class));
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
    public void shouldCallRightWhenGetManagePlansBySM() {
        planService.getPlans(salesman, 1);
        ArgumentCaptor<Integer> statusCaptor = ArgumentCaptor.forClass(Integer.class);
        verify(planMapper, times(2))
                .selectByUserId(anyInt(), statusCaptor.capture());

        List<Integer> params = statusCaptor.getAllValues();

        List<Integer> statuses = new ArrayList<>();
        statuses.add(Status.NOT_SUBMITTED.ordinal());
        statuses.add(Status.REFUSED.ordinal());

        assertEquals(statuses.size(), params.size());
        assertTrue(statuses.containsAll(params));
    }

    @Test
    public void shouldCallRightWhenGetManagePlansByDM() {
        doThrow(new RuntimeException()).when(planMapper).selectByUserId(anyInt(), anyInt());

        List<Plan> plans = planService.getPlans(departmentManager, 1);
        assertEquals(plans.size(), 0);
    }

    @Test
    public void shouldCallRightWhenGetManagePlansBySMgr() {
        doThrow(new RuntimeException()).when(planMapper).selectByUserId(anyInt(), anyInt());

        List<Plan> plans = planService.getPlans(systemManager, 1);
        assertEquals(plans.size(), 0);
    }

    @Test
    public void shouldCallRightWhenGetAuditPlansBySM() {
        doThrow(new RuntimeException()).when(planMapper).selectByDepartment(anyInt(), anyInt());
        doThrow(new RuntimeException()).when(planMapper).selectAll(anyInt());
        assertEquals(planService.getPlans(salesman, 2).size(), 0);
    }

    @Test
    public void shouldCallRightWhenGetAuditPlansByDM() {
        planService.getPlans(departmentManager, 2);
        verify(planMapper).selectByDepartment(departmentManager.getDepartment(),
                Status.NOT_AUDITED.ordinal());
    }

    @Test
    public void shouldCallRightWhenGetAuditPlansBySMgr() {
        planService.getPlans(systemManager, 2);
        verify(planMapper).selectAll(Status.NOT_PASSED.ordinal());
    }

    @Test
    public void shouldCallRightWhenGetFinishedPlansBySM() {
        planService.getPlans(salesman, 3);
        ArgumentCaptor<Integer> statusCaptor = ArgumentCaptor.forClass(Integer.class);
        verify(planMapper, times(1))
                .selectByUserId(anyInt(), statusCaptor.capture());

        List<Integer> params = statusCaptor.getAllValues();

        List<Integer> statuses = new ArrayList<>();
        statuses.add(Status.FINISHED.ordinal());

        assertEquals(statuses.size(), params.size());
        assertTrue(statuses.containsAll(params));
    }

    @Test
    public void shouldCallRightWhenGetFinishedPlansByDM() {
        planService.getPlans(departmentManager, 3);
        verify(planMapper).selectByDepartment(departmentManager.getDepartment(),
                Status.FINISHED.ordinal());
    }

    @Test
    public void shouldCallRightWhenGetFinishedPlansBySMgr() {
        planService.getPlans(systemManager, 3);
        verify(planMapper).selectAll(Status.FINISHED.ordinal());
    }
}