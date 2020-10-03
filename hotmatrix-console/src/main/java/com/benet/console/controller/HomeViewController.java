package com.benet.console.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 首页
 *
 * @author yoxking
 */
@Controller
public class HomeViewController {

    @GetMapping(value="/index")
    public  String index(){
        return "redirect:/mainview/index";
    }

    @GetMapping(value="/regist")
    public  String regist(){
        return "regist";
    }

    @GetMapping(value="/forget")
    public  String forget(){
        return "forget";
    }

    @GetMapping("/unauth")
    public String unauth()
    {
        return "unauth";
    }
}
