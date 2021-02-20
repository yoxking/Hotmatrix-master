package com.benet.console.controller;

import com.benet.common.core.domain.AjaxResult;
import com.benet.console.common.BaseViewController;
import com.benet.system.domain.SysSuserinfo;
import com.benet.system.service.ISysSuserinfoService;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 后台管理
 *
 * @author yoxking
 * @date 2020-04-20
 */
@Controller
@RequestMapping("/adminview")
public class AdminViewController extends BaseViewController {
    private String prefix = "adminview";

    @Autowired
    private ISysSuserinfoService suserinfoService;

    /**
     * 登录
     */
    @GetMapping(value="/sulogin")
    public String sulogin()
    {
        return prefix +"/sulogin";
    }

    @PostMapping("/ajaxLogin")
    @ResponseBody
    public AjaxResult ajaxLogin(String username, String password)
    {
        String msg = "用户名或密码错误!";
        try
        {
            BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
            //passwordEncoder.encode(password);
            //password= EncDesUtils.encryptBasedDes(password);

            SysSuserinfo info=suserinfoService.getRecordByLoginName(username);
            if(info!=null&&passwordEncoder.matches(password,info.getPassword())){
                return success();
            }
            return error(msg);
        }
        catch (AuthenticationException e)
        {
            return error(msg);
        }
    }

}
