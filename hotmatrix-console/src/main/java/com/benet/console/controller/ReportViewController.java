package com.benet.console.controller;

import com.benet.collect.service.ICctBlogsinfoService;
import com.benet.collect.service.ICctReportinfoService;
import com.benet.console.common.BaseViewController;
import com.benet.console.utils.ShiroUtils;
import com.benet.system.domain.SysRuserinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 报告
 *
 * @author yoxking
 * @date 2020-04-20
 */
@Controller
@RequestMapping("/reportview")
public class ReportViewController extends BaseViewController {
    private String prefix = "reportview";

    @Autowired
    private ICctReportinfoService reportinfoService;

    /**
     * 首页
     */
    @GetMapping(value="/index")
    public String index(ModelMap model)
    {
        SysRuserinfo loginUser = ShiroUtils.getLoginRuser().getUser();
        model.put("loginer",getLoginer());
        return prefix +"/index";
    }

    /**
     * 详细
     */
    @GetMapping(value="/detail")
    public String detail(ModelMap model)
    {
        SysRuserinfo loginUser = ShiroUtils.getLoginRuser().getUser();
        model.put("loginer",getLoginer());
        return prefix +"/detail";
    }
}
