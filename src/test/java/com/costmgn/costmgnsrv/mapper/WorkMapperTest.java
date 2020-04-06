package com.costmgn.costmgnsrv.mapper;

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

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WorkMapperTest {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private WorkMapper workMapper;

    private int firstId;

    @Before
    public void setUp() {
        firstId = 0;
    }

    @Test
    @Transactional
    public void shouldSelectUserWhenSelectWork() {
        User user = new User();
        user.setPost(Post.SalesMan.ordinal());
        user.setDepartment(Department.PRODUCE.ordinal());
        user.setName("张三");
        user.setInpost(false);
        user.setUserid("123456");
        user.setPassword("123456");
        userMapper.insert(user);

        Work work = new Work();
        work.setStatus(Status.NOT_SUBMITTED.ordinal());
        work.setDate(new Date());
        work.setType(EntityType.PLAN.ordinal());
        work.setUser(user);
        work.setDepartment(user.getDepartment());
        workMapper.insert(work);

        Work theWork = workMapper.selectByPrimaryKey(work.getId());

        assertNotNull(theWork.getUser());
        assertEquals(theWork.getUser().getName(), user.getName());
    }

    private void addTestDataForSelect(int usern, int departmentn, int statusn, int typen, int in) {
        for (int i = 0; i < usern; i++) {
            User user = new User();
            user.setPost(Post.SalesMan.ordinal());
            user.setDepartment(Department.PRODUCE.ordinal());
            user.setName("张三");
            user.setInpost(false);
            user.setUserid("123456" + i + in);
            user.setPassword("123456");
            userMapper.insert(user);
            if (firstId == 0)
                firstId = user.getId();
            for (int department = 0; department < departmentn; department++) {
                for (int status = 0; status < statusn; status++) {
                    for (int type = 0; type < typen; type++) {
                        for (int j = 0;
                             j < (i + 1) * (department + 1) * (status + 1) * (type + 1);
                             j++) {
                            Work work = new Work();
                            work.setEntityId(j);
                            work.setUser(user);
                            work.setStatus(status);
                            work.setDepartment(department);
                            work.setDate(new Date());
                            work.setType(type);
                            workMapper.insert(work);
                        }
                    }
                }
            }
        }
    }

    @Test
    @Transactional
    public void shouldCountRightWhenSelectByUser() {
        addTestDataForSelect(2, 1, 3, 2, 1);
        List<Integer> ids = workMapper.selectByUserId(
                1 + firstId, 2, 1);
        assertEquals(ids.size(), (1 + 1) * 1 * (2 + 1) * (1 + 1));
    }

    @Test
    @Transactional
    public void shouldCountRightWhenSelectByDepartment() {
        addTestDataForSelect(1, 3, 3, 3, 2);
        List<Integer> ids = workMapper.selectByDepartment(1, 2, 2);
        assertEquals(ids.size(), 1 * (1 + 1) * (2 + 1) * (2 + 1));
    }

    @Test
    @Transactional
    public void shouldCountRightWhenSelectAll() {
        addTestDataForSelect(1, 1, 3, 3, 3);
        List<Integer> ids = workMapper.selectAll(2, 1);
        assertEquals(ids.size(), 1 * 1 * (2 + 1) * (1 + 1));
    }
}
