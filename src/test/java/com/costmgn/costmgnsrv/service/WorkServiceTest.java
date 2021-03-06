package com.costmgn.costmgnsrv.service;

import com.costmgn.costmgnsrv.entity.User;
import com.costmgn.costmgnsrv.entity.Work;
import com.costmgn.costmgnsrv.utils.Department;
import com.costmgn.costmgnsrv.utils.EntityType;
import com.costmgn.costmgnsrv.utils.Post;
import com.costmgn.costmgnsrv.utils.Status;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WorkServiceTest {
    @Autowired
    private WorkService workService;
    @Autowired
    private UserService service;
    private User salesman, salesman2, departmentManager, systemManager;

    private User getDefaultUser() {
        User user = new User();
        user.setPassword("123456");
        user.setName("张三");
        user.setUserid("U123456");
        user.setInpost(true);

        return user;
    }

    @Before
    public void setUp() {
        systemManager = getDefaultUser();
        systemManager.setDepartment(Department.MANAGEMENT.ordinal());
        systemManager.setPost(Post.SystemManager.ordinal());
    }

    private void addWorkData(User user, int n, Status status) {
        for (int i = 0; i < n; i++) {
            Work work = new Work();
            work.setUser(user);
            work.setType(EntityType.PLAN.ordinal());
            workService.addWork(work);
            workService.updateStatus(work.getId(), status.ordinal());
        }
    }

    private void addTestDataForWorks() {
        salesman = getDefaultUser();
        salesman.setUserid("salesman");
        salesman.setDepartment(Department.PRODUCE.ordinal());
        salesman.setPost(Post.SalesMan.ordinal());

        service.addUser(salesman);

        addWorkData(salesman, 1, Status.NOT_AUDITED);
        addWorkData(salesman, 2, Status.NOT_SUBMITTED);
        addWorkData(salesman, 3, Status.REFUSED);

        salesman2 = getDefaultUser();
        salesman2.setUserid("salesman2");
        salesman2.setDepartment(Department.PRODUCE.ordinal());
        salesman2.setPost(Post.SalesMan.ordinal());

        service.addUser(salesman2);

        addWorkData(salesman2, 4, Status.NOT_AUDITED);
        addWorkData(salesman2, 5, Status.NOT_SUBMITTED);
        addWorkData(salesman2, 6, Status.REFUSED);
        addWorkData(salesman2, 7, Status.FINISHED);

        departmentManager = getDefaultUser();
        departmentManager.setUserid("departmentManager");
        departmentManager.setPost(Post.DepartmentManager.ordinal());
        departmentManager.setDepartment(Department.PRODUCE.ordinal());

        service.addUser(departmentManager);

        addWorkData(departmentManager, 7, Status.NOT_PASSED);

    }

    @Test
    @Transactional
    public void shouldCountRightWhenGetCurWorksForSalesman() {
        addTestDataForWorks();
        List<Work> workList = workService.getCurWorks(salesman);
        assertEquals(workList.size(), 1 + 2 + 3);
    }

    @Test
    @Transactional
    public void shouldCountZeroWhenGetCurWorksForSM() {
        addTestDataForWorks();
        List<Work> workList = workService.getCurWorks(systemManager);
        assertEquals(workList.size(), 0);
    }


    @Test
    @Transactional
    public void shouldFinishedNotIncludeWhenGetCurWorks() {
        addTestDataForWorks();
        List<Work> workList = workService.getCurWorks(salesman2);
        assertEquals(workList.size(), 4 + 5 + 6);
    }

    @Test
    @Transactional
    public void shouldGetTheWholeDepWhenGetToDoWorks() {
        departmentManager = getDefaultUser();
        departmentManager.setUserid("departmentManager");
        departmentManager.setPost(Post.DepartmentManager.ordinal());
        departmentManager.setDepartment(Department.PRODUCE.ordinal());
        int count = workService.getToDoWorks(departmentManager).size();
        addTestDataForWorks();
        List<Work> workList = workService.getToDoWorks(departmentManager);
        assertEquals(count + 1 + 4, workList.size());
    }

    @Test
    @Transactional
    public void shouldNotGetOtherDepWhenGetToDoWorks() {
        addTestDataForWorks();
        departmentManager.setDepartment(Department.MANAGEMENT.ordinal());
        List<Work> workList = workService.getToDoWorks(departmentManager);
        assertEquals(0, workList.size());
    }

    @Test
    @Transactional
    public void shouldCountRightWhenGetToDoWorksForSystemManager() {
        int count = workService.getToDoWorks(systemManager).size();
        addTestDataForWorks();

        List<Work> workList = workService.getToDoWorks(systemManager);
        assertEquals(7 + count, workList.size());
    }
}
