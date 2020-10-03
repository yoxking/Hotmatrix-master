package com.benet.console.controller;

import com.benet.system.service.ISysBranchinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 主页
 *
 * @author yoxking
 * @date 2020-04-20
 */
@Controller
@RequestMapping("/mainview")
public class MainViewController
{
    private String prefix = "mainview";

    @Autowired
    private ISysBranchinfoService sysBranchinfoService;

    /**
     * 首页
     */
    @GetMapping(value="/index")
    public String index()
    {
        return prefix + "/index";
    }
}
