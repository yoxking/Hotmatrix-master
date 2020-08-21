package com.benet.web.controller;

import com.benet.common.annotation.Oplog;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.web.ServletUtils;
import com.benet.framework.security.LoginUser;
import com.benet.framework.security.service.MyJwtokenService;
import com.benet.system.service.ISysConfiginfoService;
import com.benet.web.vmodel.ConfigInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 配置请求处理
 *
 * @author yoxking
 */
@RestController
@RequestMapping("/web")
public class ConfigInfoController {

    @Autowired
    private ISysConfiginfoService sysConfiginfoService;

    @Autowired
    private MyJwtokenService tokenService;

    /**
     * 配置请求
     *
     */
    @GetMapping("/getConfigInfo")
    public AjaxResult getConfigInfo()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        ConfigInfoVo info=new ConfigInfoVo();
        info.setSiteName(sysConfiginfoService.getConfigValueByKey(loginUser.getUser().getAppCode(),"SiteName"));
        info.setSiteUrl(sysConfiginfoService.getConfigValueByKey(loginUser.getUser().getAppCode(),"SiteUrl"));
        info.setAppCode(sysConfiginfoService.getConfigValueByKey(loginUser.getUser().getAppCode(),"AppCode"));
        info.setConnStr(sysConfiginfoService.getConfigValueByKey(loginUser.getUser().getAppCode(),"ConnStr"));
        info.setSiteDesc(sysConfiginfoService.getConfigValueByKey(loginUser.getUser().getAppCode(),"SiteDesc"));
        info.setRunState(sysConfiginfoService.getConfigValueByKey(loginUser.getUser().getAppCode(),"RunState"));

        return AjaxResult.success(info);
    }

    /**
     * 保存配置信息
     */
    @Oplog(title = "保存配置信息", businessType = BusinessType.SAVE)
    @PostMapping("/saveConfigInfo")
    public AjaxResult saveConfigInfo(@RequestBody ConfigInfoVo contentinfo) {

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        sysConfiginfoService.saveConfigValueByKey(loginUser.getUser().getAppCode(),"SiteName",contentinfo.getSiteName(),"Y");
        sysConfiginfoService.saveConfigValueByKey(loginUser.getUser().getAppCode(),"SiteUrl",contentinfo.getSiteUrl(),"Y");
        sysConfiginfoService.saveConfigValueByKey(loginUser.getUser().getAppCode(),"AppCode",contentinfo.getAppCode(),"Y");
        sysConfiginfoService.saveConfigValueByKey(loginUser.getUser().getAppCode(),"ConnStr",contentinfo.getConnStr(),"Y");
        sysConfiginfoService.saveConfigValueByKey(loginUser.getUser().getAppCode(),"SiteDesc",contentinfo.getSiteDesc(),"Y");
        sysConfiginfoService.saveConfigValueByKey(loginUser.getUser().getAppCode(),"RunState",contentinfo.getRunState(),"Y");

        return  AjaxResult.success();
    }
}
