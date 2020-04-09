package com.costmgn.costmgnsrv;

import com.costmgn.costmgnsrv.controller.*;
import com.costmgn.costmgnsrv.mapper.OtherMapperTert;
import com.costmgn.costmgnsrv.mapper.UserMapperTest;
import com.costmgn.costmgnsrv.mapper.WorkMapperTest;
import com.costmgn.costmgnsrv.service.*;
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
        PlanServiceTest.class,
        ContractServiceTest.class,
        EntitySelectorTest.class,

        UserControllerTest.class,
        WorkControllerTest.class,
        PlanControllerTest.class,
        ContractControllerTest.class,
        BudgetControllerTest.class
})
@SpringBootTest
public class CostmgnsrvApplicationTests {

}
