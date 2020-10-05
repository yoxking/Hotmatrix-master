package com.benet.console.controller;

import com.benet.common.core.domain.AjaxResult;
import com.benet.common.utils.date.DateUtils;
import com.benet.common.utils.security.EncDesUtils;
import com.benet.common.utils.string.StringUtils;
import com.benet.console.common.BaseViewController;
import com.benet.console.utils.ShiroUtils;
import com.benet.console.vmodel.ConfigInfoVo;
import com.benet.system.domain.SysRuserinfo;
import com.benet.system.service.ISysConfiginfoService;
import com.benet.system.service.ISysRuserinfoService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * 用户信息
 *
 * @author yoxking
 */
@Controller
@RequestMapping("/ruserview")
public class RuserViewController extends BaseViewController {
    private String prefix = "ruserview";

    @Autowired
    private ISysRuserinfoService ruserinfoService;

    @Autowired
    private ISysConfiginfoService sysConfiginfoService;

    @GetMapping(value="/profile")
    public  String profile(ModelMap model)
    {
        model.put("loginer",getLoginer());
        return prefix + "/profile";
    }

    @PostMapping("/ajaxProfile")
    @ResponseBody
    public AjaxResult ajaxProfile(String ruserNo,String rnickName, String ruserSex,String telephone, String ruserMail) {

        SysRuserinfo loginUser = ShiroUtils.getLoginRuser().getUser();
        SysRuserinfo ruserInfo = ruserinfoService.getRecordByNo(loginUser.getAppCode(), ruserNo);

        if (ruserInfo != null) {
            ruserInfo.setUserCnname(rnickName);
            ruserInfo.setSex(ruserSex);
            ruserInfo.setTelephone(telephone);
            ruserInfo.setEmail(ruserMail);
            ruserInfo.setUpdateBy(loginUser.getUserNo());
            ruserInfo.setUpdateTime(DateUtils.getNowDate());

            if (ruserinfoService.UpdateRecord(loginUser.getAppCode(), ruserInfo) > 0) {
                return success("用户信息更新成功!");
            }
        }
        return error("用户信息更新失败!");
    }

    @PostMapping("/ajaxPassword")
    @ResponseBody
    public AjaxResult ajaxPassword(String ruserNo, String oldpassword, String newpassword)
    {
        oldpassword= EncDesUtils.encryptBasedDes(oldpassword);
        newpassword=EncDesUtils.encryptBasedDes(newpassword);

        SysRuserinfo loginUser = ShiroUtils.getLoginRuser().getUser();
        SysRuserinfo ruserInfo = ruserinfoService.getRecordByNo(loginUser.getAppCode(), ruserNo);

        if (ruserInfo != null&&ruserInfo.getPassword().equals(oldpassword)) {
            if(ruserinfoService.resetUserPassword(ruserNo,newpassword)>0){
                return success("密码修改成功!");
            }
        }
        return error("用户不存在或原密码错误!");
    }

    @GetMapping(value="/swtrole")
    public  String swtrole(ModelMap model)
    {
        model.put("loginer",getLoginer());
        return prefix + "/swtrole";
    }

    @GetMapping(value="/setting")
    public  String setting(ModelMap model)
    {
        SysRuserinfo loginUser = ShiroUtils.getLoginRuser().getUser();

        ConfigInfoVo info=new ConfigInfoVo();
        info.setSiteName(sysConfiginfoService.getConfigValueByKey(loginUser.getAppCode(),"SiteName"));
        info.setSiteUrl(sysConfiginfoService.getConfigValueByKey(loginUser.getAppCode(),"SiteUrl"));
        info.setAppCode(sysConfiginfoService.getConfigValueByKey(loginUser.getAppCode(),"AppCode"));
        info.setConnStr(sysConfiginfoService.getConfigValueByKey(loginUser.getAppCode(),"ConnStr"));
        info.setSiteDesc(sysConfiginfoService.getConfigValueByKey(loginUser.getAppCode(),"SiteDesc"));
        info.setRunState(sysConfiginfoService.getConfigValueByKey(loginUser.getAppCode(),"RunState"));

        model.put("configInfo",info);

        return prefix + "/setting";
    }

    @PostMapping("/ajaxSetting")
    @ResponseBody
    public AjaxResult ajaxSetting(String siteName,String siteUrl,String siteDesc)
    {
        SysRuserinfo loginUser = ShiroUtils.getLoginRuser().getUser();

        sysConfiginfoService.saveConfigValueByKey(loginUser.getAppCode(),"SiteName",siteName,"Y");
        sysConfiginfoService.saveConfigValueByKey(loginUser.getAppCode(),"SiteUrl",siteUrl,"Y");
        //sysConfiginfoService.saveConfigValueByKey(loginUser.getAppCode(),"AppCode",configInfo.getAppCode(),"Y");
        //sysConfiginfoService.saveConfigValueByKey(loginUser.getAppCode(),"ConnStr",configInfo.getConnStr(),"Y");
        sysConfiginfoService.saveConfigValueByKey(loginUser.getAppCode(),"SiteDesc",siteDesc,"Y");
        //sysConfiginfoService.saveConfigValueByKey(loginUser.getAppCode(),"RunState",configInfo.getRunState(),"Y");

        return  success("保存成功!");
    }

    @GetMapping(value="/message")
    public  String message(ModelMap model)
    {
        model.put("loginer",getLoginer());
        return prefix + "/message";
    }
}
