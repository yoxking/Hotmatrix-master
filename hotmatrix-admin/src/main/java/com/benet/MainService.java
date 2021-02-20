package com.benet;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ImportResource;

/**
 * 启动程序
 *
 * @author yoxking
 */
@SpringBootApplication(scanBasePackages = "com.benet",exclude = { DataSourceAutoConfiguration.class })
@MapperScan(annotationClass = Mapper.class,basePackages = "com.benet")
@ImportResource(locations = {"classpath:FireflowContext.xml","classpath:FireflowBeanfactory.xml"})
public class MainService {

    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(MainService.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  基云平台启动成功   ლ(´ڡ`ლ)ﾞ  \n" );
    }
}
