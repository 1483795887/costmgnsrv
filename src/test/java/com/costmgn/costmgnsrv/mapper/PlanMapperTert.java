package com.costmgn.costmgnsrv.mapper;

import com.costmgn.costmgnsrv.entity.Plan;
import com.costmgn.costmgnsrv.entity.User;
import com.costmgn.costmgnsrv.entity.Work;
import com.costmgn.costmgnsrv.utils.Department;
import com.costmgn.costmgnsrv.utils.Post;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlanMapperTert {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private WorkMapper workMapper;
    @Autowired
    private PlanMapper planMapper;

    @Test
    @Transactional
    public void shouldSelectWorkAndUserWhenSelectWork() {
        User user = new User();
        user.setPost(Post.SalesMan.toString());
        user.setDepartment(Department.PRODUCE.toString());
        user.setName("张三");
        user.setInpost(false);
        user.setUserid("123456");
        user.setPassword("123456");
        userMapper.insert(user);

        Work work = new Work();
        work.setStatus("未完成");
        work.setDate(new Date());
        work.setTitle("test");
        work.setUser(user);

        workMapper.insert(work);

        Plan plan = new Plan();
        plan.setDescription("test");
        plan.setTitle("asdfs");
        plan.setWork(work);

        planMapper.insert(plan);

        Plan thePlan = planMapper.selectByPrimaryKey(plan.getId());

        assertNotNull(thePlan.getWork());
        assertEquals(thePlan.getWork().getTitle(), work.getTitle());
        assertNotNull(thePlan.getWork().getUser());
        assertEquals(thePlan.getWork().getUser().getName(), user.getName());
    }
}
