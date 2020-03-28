package com.benet;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动程序
 * 
 * @author yoxking
 */
@SpringBootApplication(scanBasePackages = "com.benet")
@MapperScan(annotationClass = Mapper.class,basePackages = "com.benet")
public class MainApplication
{
    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(MainApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  基云平台启动成功   ლ(´ڡ`ლ)ﾞ  \n" );
    }
}