package com.costmgn.costmgnsrv;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.costmgn.costmgnsrv.mapper")
@SpringBootApplication()
public class CostmgnsrvApplication {

    public static void main(String[] args) {
        SpringApplication.run(CostmgnsrvApplication.class, args);
    }

}
