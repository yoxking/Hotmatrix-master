package com.benet.console.controller;

import com.benet.collect.service.ICctBlogsinfoService;
import com.benet.collect.service.ICctSalonsinfoService;
import com.benet.console.common.BaseViewController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 活动
 *
 * @author yoxking
 * @date 2020-04-20
 */
@Controller
@RequestMapping("/salonview")
public class SalonViewController extends BaseViewController {
    private String prefix = "salonview";

    @Autowired
    private ICctSalonsinfoService salonsinfoService;

    /**
     * 首页
     */
    @GetMapping(value="/index")
    public String index(ModelMap model)
    {
        return prefix +"index";
    }
}
