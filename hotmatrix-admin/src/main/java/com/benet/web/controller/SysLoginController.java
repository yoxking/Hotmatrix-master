package com.benet.web.controller;

import com.benet.common.constant.JwtConstants;
import com.benet.common.core.domain.AjaxResult;
import com.benet.system.domain.SysMenu;
import com.benet.system.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 登录验证
 * 
 * @author yoxking
 */
@RestController
public class SysLoginController
{
    @Autowired
    ISysMenuService sysMenuService;
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
        List<SysMenu> myList=sysMenuService.selectMenuAll(2L);

        ajax.put(JwtConstants.TOKEN, "xxxxxxxxxx");
        return ajax;
    }

    @GetMapping("/test2")
    public AjaxResult test2()
    {
        AjaxResult ajax = AjaxResult.success();

        ajax.put(JwtConstants.TOKEN, "xxxxxxxxxx");
        return ajax;
    }

}
