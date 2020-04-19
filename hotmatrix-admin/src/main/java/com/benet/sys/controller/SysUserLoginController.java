package com.benet.sys.controller;

import com.benet.common.constant.JwtConstants;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.utils.web.ServletUtils;
import com.benet.framework.security.LoginUser;
import com.benet.framework.security.service.MyJwtokenService;
import com.benet.framework.security.service.SysLoginService;
import com.benet.framework.utils.RouterUtils;
import com.benet.framework.web.vmodel.RouterVo;
import com.benet.system.domain.SysPermitinfo;
import com.benet.system.domain.SysSuserinfo;
import com.benet.system.service.ISysPermitinfoService;
import com.benet.system.service.ISysRoleinfoService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * 登录验证
 * 
 * @author yoxking
 */
@Api(tags = "SysLogin")
@RestController
@RequestMapping("/sys")
public class SysUserLoginController
{
    @Autowired
    private SysLoginService loginService;

    @Autowired
    private ISysRoleinfoService roleService;

    @Autowired
    private ISysPermitinfoService permitService;

    @Autowired
    private MyJwtokenService tokenService;

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

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping("/getInfo")
    public AjaxResult getInfo()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        SysSuserinfo userInfo = loginUser.getUser();
        // 角色集合
        Set<String> roles = roleService.getRoleCodesByUserNo(userInfo.getUserNo());
        // 权限集合
        Set<String> permits = permitService.getPermitCodesByUserNo(userInfo.getUserNo());
        AjaxResult ajax = AjaxResult.success();
        ajax.put("user", userInfo);
        ajax.put("roles", roles);
        ajax.put("permits", permits);
        return ajax;
    }


    /**
     * 按父菜单ID获取路由信息
     *
     * @param parentId 父菜单ID
     * @return 路由信息
     */
    @GetMapping("/getRouters/{parentId}")
    public AjaxResult getRouters(@PathVariable("parentId") String parentId)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        List<SysPermitinfo> permitTree = permitService.getPermitTreeByUserNo(parentId,loginUser.getUser().getUserNo());
        return AjaxResult.success(RouterUtils.buildMenus(permitTree));
    }

    /**
     * 获取顶部菜单信息
     *
     * @return 路由信息
     */
    @GetMapping("/getTopMenu")
    public AjaxResult getTopMenu()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        // 用户信息
        SysSuserinfo user = loginUser.getUser();
        List<SysPermitinfo> menus = permitService.getChildrenByUserNo("0",user.getUserNo());
        List<RouterVo> v=RouterUtils.buildMenus(menus);
        return AjaxResult.success(v);
    }
}
