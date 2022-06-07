package com.pudongdevelopmentbank;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan(basePackages = "com.pudongdevelopmentbank.dao")
@EnableTransactionManagement
public class CommunityApplication {

    private static final Logger logger = LoggerFactory.getLogger(CommunityApplication.class);

    public static void main(String[] args) {
        logger.info("===========infoLogger=============");
        SpringApplication.run(CommunityApplication.class, args);
    }
}
