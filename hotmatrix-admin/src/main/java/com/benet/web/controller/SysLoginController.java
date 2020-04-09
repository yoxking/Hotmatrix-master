package com.benet.web.controller;

import com.benet.common.annotation.Oplog;
import com.benet.common.constant.JwtConstants;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.framework.security.service.SysLoginService;
import com.benet.system.domain.SysPermitinfo;
import com.benet.system.service.ISysPermitinfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 登录验证
 * 
 * @author yoxking
 */
@Api(tags = "SysLogin")
@RestController
public class SysLoginController
{
    @Autowired
    private SysLoginService loginService;


    @Autowired
    ISysPermitinfoService sysPermitService;
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
        try {
            AjaxResult ajax = AjaxResult.success();

            // 生成令牌
            String token = loginService.login(username, password, code, uuid);
            ajax.put(JwtConstants.TOKEN, token);
            return ajax;
        }
        catch (Exception e){
            return AjaxResult.error(e.getMessage());
        }
    }


    @Oplog(title = "用户管理", businessType = BusinessType.INSERT)
    @GetMapping("/test")
    public AjaxResult test()
    {
        AjaxResult ajax = new AjaxResult(AjaxResult.Type.SUCCESS,"","yyyyyyyy");
        return ajax;
    }

    @PreAuthorize("@ps.hasPermit('system:user:list')")
    @GetMapping("/test2")
    public AjaxResult test2()
    {
        AjaxResult ajax = AjaxResult.success();

        ajax.put(JwtConstants.TOKEN, "xxxxxxxxxx");
        return ajax;
    }

    @ApiOperation(value="发送解析文本", notes="发送解析文本", produces="application/json")
    @GetMapping("/test3")
    public AjaxResult test3()
    {
        AjaxResult ajax = AjaxResult.success();
        List<SysPermitinfo> myList=sysPermitService.getAllRecords();

        ajax.put(JwtConstants.TOKEN, "xxxxxxxxxx");
        return ajax;
    }

}
