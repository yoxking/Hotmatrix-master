package com.benet.web.controller;

import com.benet.common.annotation.Oplog;
import com.benet.common.configure.ServerConfig;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.uuid.UuidUtils;
import com.benet.common.utils.web.ServletUtils;
import com.benet.framework.security.LoginUser;
import com.benet.system.domain.SysContentinfo;
import com.benet.system.service.ISysConfiginfoService;
import com.benet.system.service.ISysContzclassService;
import com.benet.web.vmodel.ConfigInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    /**
     * 配置请求
     *
     */
    @GetMapping("/getConfigInfo")
    public AjaxResult getConfigInfo()
    {
        ConfigInfoVo info=new ConfigInfoVo();
        info.setSiteName(sysConfiginfoService.getConfigValueByKey("SiteName"));
        info.setSiteUrl(sysConfiginfoService.getConfigValueByKey("SiteUrl"));
        info.setAppCode(sysConfiginfoService.getConfigValueByKey("AppCode"));
        info.setConnStr(sysConfiginfoService.getConfigValueByKey("ConnStr"));
        info.setSiteDesc(sysConfiginfoService.getConfigValueByKey("SiteDesc"));
        info.setRunState(sysConfiginfoService.getConfigValueByKey("RunState"));

        return AjaxResult.success(info);
    }

    /**
     * 保存配置信息
     */
    @Oplog(title = "保存配置信息", businessType = BusinessType.SAVE)
    @PostMapping("/saveConfigInfo")
    public AjaxResult saveConfigInfo(@RequestBody ConfigInfoVo contentinfo) {

        sysConfiginfoService.saveConfigValueByKey("SiteName",contentinfo.getSiteName(),"Y");
        sysConfiginfoService.saveConfigValueByKey("SiteUrl",contentinfo.getSiteUrl(),"Y");
        sysConfiginfoService.saveConfigValueByKey("AppCode",contentinfo.getAppCode(),"Y");
        sysConfiginfoService.saveConfigValueByKey("ConnStr",contentinfo.getConnStr(),"Y");
        sysConfiginfoService.saveConfigValueByKey("SiteDesc",contentinfo.getSiteDesc(),"Y");
        sysConfiginfoService.saveConfigValueByKey("RunState",contentinfo.getRunState(),"Y");

        return  AjaxResult.success();
    }
}
