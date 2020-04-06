package com.costmgn.costmgnsrv;

import com.costmgn.costmgnsrv.mapper.OtherMapperTert;
import com.costmgn.costmgnsrv.mapper.UserMapperTest;
import com.costmgn.costmgnsrv.mapper.WorkMapperTest;
import com.costmgn.costmgnsrv.service.PlanServiceTest;
import com.costmgn.costmgnsrv.service.UserSQLTest;
import com.costmgn.costmgnsrv.service.UserServiceTest;
import com.costmgn.costmgnsrv.service.WorkServiceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        OtherMapperTert.class,
        UserMapperTest.class,
        WorkMapperTest.class,
        UserServiceTest.class,
        UserSQLTest.class,
        WorkServiceTest.class,
        PlanServiceTest.class
})
@SpringBootTest
public class CostmgnsrvApplicationTests {

}
