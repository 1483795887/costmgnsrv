package com.costmgn.costmgnsrv.mapper;

import com.costmgn.costmgnsrv.entity.User;
import com.costmgn.costmgnsrv.entity.UserExample;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {
    @Autowired
    private UserMapper mapper;

    @Test
    @Transactional
    public void shouldCountIncWhenAdd() {
        User user = new User();
        user.setUserid("20200302");
        user.setName("张三");
        user.setPassword("123456");
        user.setDepartment("营销");
        user.setInpost(false);
        user.setPost("业务员");
        long count = mapper.countByExample(new UserExample());
        mapper.insert(user);
        long acount = mapper.countByExample(new UserExample());
        assertEquals(count + 1, acount);
    }
}
