package com.costmgn.costmgnsrv.mapper;

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
public class WorkMapperTest {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private WorkMapper workMapper;

    @Test
    @Transactional
    public void shouldSelectUserWhenSelectWork() {
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

        Work theWork = workMapper.selectByPrimaryKey(work.getId());

        assertNotNull(theWork.getUser());
        assertEquals(theWork.getUser().getName(), user.getName());
    }
}
