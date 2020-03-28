package com.benet.gen.utils;

import java.util.Properties;

import com.benet.common.constant.PubConstants;
import org.apache.velocity.app.Velocity;

/**
 * VelocityEngine工厂
 * 
 * @author yoxking
 */
public class VelocityInitializer
{
    /**
     * 初始化vm方法
     */
    public static void initVelocity()
    {
        Properties p = new Properties();
        try
        {
            // 加载classpath目录下的vm文件
            p.setProperty("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
            // 定义字符集
            p.setProperty(Velocity.ENCODING_DEFAULT, PubConstants.UTF8);
            p.setProperty(Velocity.OUTPUT_ENCODING, PubConstants.UTF8);
            // 初始化Velocity引擎，指定配置Properties
            Velocity.init(p);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
