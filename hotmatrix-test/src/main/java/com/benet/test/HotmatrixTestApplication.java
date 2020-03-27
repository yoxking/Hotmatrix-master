package com.benet.test;


import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.benet")
@MapperScan(annotationClass = Mapper.class,basePackages = "com.benet.test.mapper")
public class HotmatrixTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotmatrixTestApplication.class, args);
    }

}
