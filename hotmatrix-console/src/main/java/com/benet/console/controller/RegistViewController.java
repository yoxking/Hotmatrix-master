package com.benet.console.controller;

import com.benet.collect.service.ICctBlogsinfoService;
import com.benet.collect.service.ICctRegistflowsService;
import com.benet.console.common.BaseViewController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 预约
 *
 * @author yoxking
 * @date 2020-04-20
 */
@Controller
@RequestMapping("/registview")
public class RegistViewController extends BaseViewController {
    private String prefix = "registview";

    @Autowired
    private ICctRegistflowsService registflowsService;

    /**
     * 首页
     */
    @GetMapping(value="/index")
    public String index(ModelMap model)
    {
        return prefix +"index";
    }
}
