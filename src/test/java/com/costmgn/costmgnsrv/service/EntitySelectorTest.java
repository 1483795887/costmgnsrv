package com.costmgn.costmgnsrv.service;

import com.costmgn.costmgnsrv.entity.User;
import com.costmgn.costmgnsrv.mapper.WorkMapper;
import com.costmgn.costmgnsrv.utils.Department;
import com.costmgn.costmgnsrv.utils.EntityType;
import com.costmgn.costmgnsrv.utils.Post;
import com.costmgn.costmgnsrv.utils.Status;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class EntitySelectorTest {
    private WorkMapper workMapper;
    private EntitySelector selector;

    private User salesman, departmentManager, systemManager;

    @Before
    public void setUp() {
        workMapper = mock(WorkMapper.class);
        selector = new EntitySelector(workMapper);

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
    public void shouldCallRightWhenGetManageIntegersBySM() {
        selector.getIds(salesman, 1, EntityType.PLAN.ordinal());
        ArgumentCaptor<Integer> statusCaptor = ArgumentCaptor.forClass(Integer.class);
        verify(workMapper, times(2))
                .selectByUserId(anyInt(), statusCaptor.capture(), eq(EntityType.PLAN.ordinal()));

        List<Integer> params = statusCaptor.getAllValues();

        List<Integer> statuses = new ArrayList<>();
        statuses.add(Status.NOT_SUBMITTED.ordinal());
        statuses.add(Status.REFUSED.ordinal());

        assertEquals(statuses.size(), params.size());
        assertTrue(statuses.containsAll(params));
    }

    @Test
    public void shouldCallRightWhenGetManageIntegersByDM() {
        doThrow(new RuntimeException()).when(workMapper).selectByUserId(anyInt(), anyInt(), anyInt());

        List<Integer> ids = selector.getIds(departmentManager, 1, EntityType.PLAN.ordinal());
        assertEquals(ids.size(), 0);
    }

    @Test
    public void shouldCallRightWhenGetManageIntegersBySMgr() {
        doThrow(new RuntimeException()).when(workMapper).selectByUserId(anyInt(), anyInt(), anyInt());

        List<Integer> ids = selector.getIds(systemManager, 1, EntityType.PLAN.ordinal());
        assertEquals(ids.size(), 0);
    }

    @Test
    public void shouldCallRightWhenGetAuditIntegersBySM() {
        doThrow(new RuntimeException()).when(workMapper).selectByDepartment(anyInt(), anyInt(), anyInt());
        doThrow(new RuntimeException()).when(workMapper).selectAll(anyInt(), anyInt());
        assertEquals(selector.getIds(salesman, 2,
                EntityType.CONTRACT.ordinal()).size(), 0);
    }

    @Test
    public void shouldCallRightWhenGetAuditIntegersByDM() {
        selector.getIds(departmentManager, 2, EntityType.RECEIPT.ordinal());
        verify(workMapper).selectByDepartment(departmentManager.getDepartment(),
                Status.NOT_AUDITED.ordinal(), EntityType.RECEIPT.ordinal());
    }

    @Test
    public void shouldCallRightWhenGetAuditIntegersBySMgr() {
        selector.getIds(systemManager, 2, EntityType.BUDGET.ordinal());
        verify(workMapper).selectAll(Status.NOT_PASSED.ordinal(), EntityType.BUDGET.ordinal());
    }

    @Test
    public void shouldCallRightWhenGetFinishedIntegersBySM() {
        selector.getIds(salesman, 3, EntityType.PLAN.ordinal());
        ArgumentCaptor<Integer> statusCaptor = ArgumentCaptor.forClass(Integer.class);
        verify(workMapper, times(1))
                .selectByUserId(anyInt(), statusCaptor.capture(), eq(EntityType.PLAN.ordinal()));

        List<Integer> params = statusCaptor.getAllValues();

        List<Integer> statuses = new ArrayList<>();
        statuses.add(Status.FINISHED.ordinal());

        assertEquals(statuses.size(), params.size());
        assertTrue(statuses.containsAll(params));
    }

    @Test
    public void shouldCallRightWhenGetFinishedIntegersByDM() {
        selector.getIds(departmentManager, 3, EntityType.RECEIPT.ordinal());
        verify(workMapper).selectByDepartment(departmentManager.getDepartment(),
                Status.FINISHED.ordinal(), EntityType.RECEIPT.ordinal());
    }

    @Test
    public void shouldCallRightWhenGetFinishedIntegersBySMgr() {
        selector.getIds(systemManager, 3, EntityType.CONTRACT.ordinal());
        verify(workMapper).selectAll(Status.FINISHED.ordinal(), EntityType.CONTRACT.ordinal());
    }
}