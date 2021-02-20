package com.benet.console.controller;

import com.benet.collect.domain.CoctExamsinfo;
import com.benet.collect.domain.CoctSalonsinfo;
import com.benet.collect.service.ICoctExamsinfoService;
import com.benet.collect.service.ICoctSalonsinfoService;
import com.benet.common.utils.date.DateUtils;
import com.benet.console.common.BaseViewController;
import com.benet.console.utils.PageHelper;
import com.benet.console.utils.ShiroHelper;
import com.benet.console.vmodel.ExamsInfoVo;
import com.benet.console.vmodel.SalonInfoVo;
import com.benet.system.domain.SysRuserinfo;
import com.benet.system.service.ISysBranchinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    private ISysBranchinfoService branchinfoService;

    @Autowired
    private ICoctExamsinfoService examsinfoService;

    @Autowired
    private ICoctSalonsinfoService salonsinfoService;

    /**
     * 首页
     */
    @GetMapping(value="/index")
    public String index(ModelMap model)
    {

        model.put("loginer",getLoginer());
        model.put("mclassList", PageHelper.getExamClasses());
        model.put("examsList",getExamsList());
        model.put("salonsList",getSalonsList());

        return prefix + "/index";
    }

    private List<ExamsInfoVo> getExamsList(){

        SysRuserinfo loginUser = ShiroHelper.getLoginRuser().getUser();
        List<ExamsInfoVo> examsList=new ArrayList<>();
        ExamsInfoVo exams=null;

        String condition="";
        List<CoctExamsinfo> infoList=examsinfoService.getRecordsByPaging(loginUser.getAppCode(),1,4,condition,"id","desc");
        if(infoList!=null&&infoList.size()>0){
            for(CoctExamsinfo info:infoList){
                exams=new ExamsInfoVo();
                exams.setExamsNo(info.getExamsNo());
                exams.setExamsTitle(info.getExamsTitle());
                exams.setExamsPoster(info.getExamsPoster());
                exams.setExamsDesc(info.getExamsDesc());
                exams.setDataFrom(info.getDataFrom());
                exams.setClassName(info.getClassNo());
                exams.setExamsPrice("免费");
                exams.setExamsQrcode(info.getExamsQrcode());
                exams.setExamsTimes(info.getExamsTimes().toString());
                exams.setExamsDruation(info.getExamsDuration().toString());
                exams.setExamsAdmin(info.getExamsAdmin());

                examsList.add(exams);
            }
        }
        return examsList;
    }

    private List<SalonInfoVo> getSalonsList(){

        SysRuserinfo loginUser = ShiroHelper.getLoginRuser().getUser();
        List<SalonInfoVo> salonsList=new ArrayList<>();
        SalonInfoVo salon=null;

        String condition="";
        List<CoctSalonsinfo> infoList=salonsinfoService.getRecordsByPaging(loginUser.getAppCode(),1,4,condition,"id","desc");
        if(infoList!=null&&infoList.size()>0){
            for(CoctSalonsinfo info:infoList){
                salon=new SalonInfoVo();
                salon.setSalonNo(info.getSalonNo());
                salon.setSalonName(info.getSalonName());
                salon.setSalonPoster(info.getSalonPoster());
                salon.setClassName(info.getClassNo());
                salon.setStartTime(DateUtils.dateTime(info.getStartTime()));
                salon.setEnditTime(DateUtils.dateTime(info.getEnditTime()));
                salon.setSalonAddress(info.getSalonAddress());
                salon.setSalonContent(info.getSalonContent());
                salon.setSalonRusers(info.getSalonRusers());
                salon.setCheckState(info.getCheckState());
                salonsList.add(salon);
            }
        }
        return salonsList;
    }
    /**
     * 关于页
     */
    @GetMapping(value="/about")
    public String about()
    {
        return prefix + "/about";
    }

    @GetMapping(value="/helper")
    public  String helper(){
        return prefix + "/helper";
    }

    @GetMapping(value="/privacy")
    public  String privacy(){
        return prefix + "/privacy";
    }

    @GetMapping(value="/legal")
    public  String legal(){
        return prefix + "/legal";
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
