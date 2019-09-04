package com.samoy.fabiaoqing;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * SpringBoot启动类
 *
 * @author Samoy
 * @date 2019-05-23
 */
@SpringBootApplication
@EnableScheduling
@MapperScan(basePackages = {"com.samoy.fabiaoqing.dao"})
public class FabiaoqingApplication {

    public static void main(String[] args) {
        SpringApplication.run(FabiaoqingApplication.class, args);
    }

}
