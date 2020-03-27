package com.benet.test.controller;

import java.util.List;
import java.util.Set;

import com.benet.common.constant.JwtConstants;
import com.benet.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录验证
 * 
 * @author ruoyi
 */
@RestController
public class SysLoginController
{

    /**
     * 登录方法
     * 
     * @param username 用户名
     * @param password 密码
     * @param code 验证码
     * @param uuid 唯一标识
     * @return 结果
     */
    @PostMapping("/login")
    public AjaxResult login(String username, String password, String code, String uuid)
    {
        AjaxResult ajax = AjaxResult.success();
        return ajax;
    }

    @GetMapping("/test")
    public AjaxResult test()
    {
        AjaxResult ajax = AjaxResult.success();
        return ajax;
    }

    @GetMapping("/test2")
    public AjaxResult test2()
    {
        AjaxResult ajax = AjaxResult.success();
        return ajax;
    }

}
