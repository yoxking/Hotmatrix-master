package com.benet.console.controller;

import com.benet.collect.domain.CoctReportinfo;
import com.benet.collect.service.ICoctReportinfoService;
import com.benet.common.utils.date.DateUtils;
import com.benet.console.common.BaseViewController;
import com.benet.console.utils.PageHelper;
import com.benet.console.utils.ShiroHelper;
import com.benet.console.vmodel.PagerInfoVo;
import com.benet.console.vmodel.ReportInfoVo;
import com.benet.system.domain.SysRuserinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

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
    private ICoctReportinfoService reportinfoService;

    /**
     * 首页
     */
    @GetMapping(value="/index")
    public String index(ModelMap model,@RequestParam(defaultValue = "1",value = "pageIndex") Integer pageIndex,
                        @RequestParam(defaultValue = "10",value = "pageSize") Integer pageSize)
    {
        SysRuserinfo loginUser = ShiroHelper.getLoginRuser().getUser();
        List<ReportInfoVo> reportList=new ArrayList<>();
        ReportInfoVo report=null;

        String condition="";
        int count=reportinfoService.getCountByCondition(loginUser.getAppCode(),condition);
        List<CoctReportinfo> infoList=reportinfoService.getRecordsByPaging(loginUser.getAppCode(),pageIndex,pageSize,condition,"id","desc");
        if(infoList!=null&&infoList.size()>0){
            for(CoctReportinfo info:infoList){
                report=new ReportInfoVo();
                report.setReptNo(info.getReportNo());
                report.setReptName("我的测评报告");
                report.setReptImage("");
                report.setReptType(info.getReportType()+"");
                report.setReptDesc(info.getReportDesc());
                report.setCheckState(info.getCheckState());
                report.setPubDate(DateUtils.dateTime(info.getCreateTime()));
                reportList.add(report);
            }
        }

        PagerInfoVo<ReportInfoVo> pagerInfo=new PagerInfoVo<ReportInfoVo>(pageIndex,pageSize,count,reportList);

        model.put("loginer",getLoginer());
        model.put("mclassList", PageHelper.getExamClasses());
        model.put("pagerInfo",pagerInfo);
        return prefix +"/index";
    }

    /**
     * 详细
     */
    @GetMapping(value="/detail")
    public String detail(ModelMap model,@RequestParam("id") String id) {
        SysRuserinfo loginUser = ShiroHelper.getLoginRuser().getUser();

        CoctReportinfo info = reportinfoService.getRecordByNo(loginUser.getAppCode(), id);
        ReportInfoVo report=new ReportInfoVo();
        report.setReptNo(info.getReportNo());
        report.setReptName("我的测评报告");
        report.setReptType(info.getReportType()+"");
        report.setReptDesc(info.getReportDesc());
        report.setCheckState(info.getCheckState());
        report.setPubDate(DateUtils.dateTime(info.getCreateTime()));

        model.put("loginer", getLoginer());
        model.put("reptInfo", report);
        return prefix +"/detail";
    }
}
