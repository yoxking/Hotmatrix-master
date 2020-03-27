package com.benet.framework.aspect;

import com.benet.common.annotation.Oplog;
import com.benet.common.enums.BusinessStatus;
import com.benet.common.json.JsonHelper;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.web.ServletUtils;
import com.benet.framework.manager.AsyncManager;
import com.benet.framework.manager.factory.AsyncFactory;
import com.benet.system.domain.SysUser;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * 操作日志记录处理
 * 
 * @author yoxking
 */
@Aspect
@Component
public class OperatelogAspect
{
    private static final Logger log = LoggerFactory.getLogger(OperatelogAspect.class);

    // 配置织入点
    @Pointcut("@annotation(com.benet.common.annotation.Oplog)")
    public void logPointCut()
    {
    }

    /**
     * 处理完请求后执行
     *
     * @param joinPoint 切点
     */
    @AfterReturning(pointcut = "logPointCut()", returning = "jsonResult")
    public void doAfterReturning(JoinPoint joinPoint, Object jsonResult)
    {
        handleLog(joinPoint, null, jsonResult);
    }

    /**
     * 拦截异常操作
     * 
     * @param joinPoint 切点
     * @param e 异常
     */
    @AfterThrowing(value = "logPointCut()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e)
    {
        handleLog(joinPoint, e, null);
    }

    protected void handleLog(final JoinPoint joinPoint, final Exception e, Object jsonResult)
    {
        try
        {
            // 获得注解
            Oplog controllerLog = getAnnotationLog(joinPoint);
            if (controllerLog == null)
            {
                return;
            }


        }
        catch (Exception exp)
        {
            // 记录本地异常日志
            log.error("==前置通知异常==");
            log.error("异常信息:{}", exp.getMessage());
            exp.printStackTrace();
        }
    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     * 
     * @param log 日志
     * @param operLog 操作日志
     * @throws Exception
     */
    public void getControllerMethodDescription(Oplog log, Object operLog) throws Exception
    {

    }

    /**
     * 获取请求的参数，放到log中
     * 
     * @param operLog 操作日志
     * @throws Exception 异常
     */
    private void setRequestValue(Object operLog) throws Exception
    {
    }

    /**
     * 是否存在注解，如果存在就获取
     */
    private Oplog getAnnotationLog(JoinPoint joinPoint) throws Exception
    {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        if (method != null)
        {
            return method.getAnnotation(Oplog.class);
        }
        return null;
    }
}
