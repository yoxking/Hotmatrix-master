package com.benet.web.controller;

import com.benet.common.core.domain.AjaxResult;
import com.benet.common.utils.web.ServletUtils;
import com.benet.framework.security.LoginUser;
import com.benet.framework.security.service.MyJwtokenService;
import com.benet.framework.utils.RouterUtils;
import com.benet.framework.web.vmodel.RouterVo;
import com.benet.system.domain.SysPermitinfo;
import com.benet.system.domain.SysSuserinfo;
import com.benet.system.service.ISysPermitinfoService;
import com.benet.system.service.ISysRoleinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
public class SysUserController {

    @Autowired
    private ISysRoleinfoService roleService;

    @Autowired
    private ISysPermitinfoService permitService;


    @Autowired
    private MyJwtokenService tokenService;

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping("getInfo")
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
     * 获取路由信息
     *
     * @return 路由信息
     */
    @GetMapping("getRouters/{menuId}")
    public AjaxResult getRouters(@PathVariable("menuId") String menuId)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        // 用户信息
        SysSuserinfo user = loginUser.getUser();
        List<SysPermitinfo> menus = permitService.getTreeMenuByUserNo(menuId,user.getUserNo());
        List<RouterVo> v=RouterUtils.buildMenus(menus);
        return AjaxResult.success(v);
    }

    /**
     * 获取顶部菜单信息
     *
     * @return 路由信息
     */
    @GetMapping("getTopMenu")
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
