package com.benet.console.controller;

import com.benet.collect.domain.CctPaperinfo;
import com.benet.collect.service.ICctPaperinfoService;
import com.benet.console.common.BaseViewController;
import com.benet.console.utils.ShiroUtils;
import com.benet.console.vmodel.PaperInfoVo;
import com.benet.system.domain.SysRuserinfo;
import com.benet.system.service.ISysBranchinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;


/**
 * 主页
 *
 * @author yoxking
 * @date 2020-04-20
 */
@Controller
@RequestMapping("/mainview")
public class MainViewController extends BaseViewController
{
    private String prefix = "mainview";

    @Autowired
    private ISysBranchinfoService sysBranchinfoService;

    @Autowired
    private ICctPaperinfoService cctPaperinfoService;

    /**
     * 首页
     */
    @GetMapping(value="/index")
    public String index(ModelMap model)
    {
        SysRuserinfo loginUser = ShiroUtils.getLoginRuser().getUser();
        List<PaperInfoVo> paperList=new ArrayList<>();
        PaperInfoVo paper=null;
        List<CctPaperinfo> infoList=cctPaperinfoService.getAllRecords(loginUser.getAppCode());
        if(infoList!=null&&infoList.size()>0){
            for(CctPaperinfo info:infoList){
                paper=new PaperInfoVo();
                paper.setPaperNo(info.getPaperNo());
                paper.setPaperTitle(info.getPaperTitle());
                paper.setPaperPoster(info.getPaperPoster());
                paper.setPaperDesc(info.getPaperDesc());
                paper.setPaperPrice("免费");
                paper.setExamTimes("0");
                paper.setExDruation(info.getExDuration()+"分钟");

                paperList.add(paper);
            }
        }

        model.put("loginer",getLoginer());
        model.put("paperList",paperList);

        return prefix + "/index";
    }

    /**
     * 历史页
     */
    @GetMapping(value="/history")
    public String history(ModelMap model)
    {
        model.put("loginer",getLoginer());
        return prefix + "/history";
    }
}
