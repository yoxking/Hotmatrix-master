package com.benet;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(scanBasePackages = "com.benet",exclude = { DataSourceAutoConfiguration.class })
@MapperScan(annotationClass = Mapper.class,basePackages = "com.benet")
public class MainConsole {

    public static void main(String[] args) {
        SpringApplication.run(MainConsole.class, args);
    }
}
