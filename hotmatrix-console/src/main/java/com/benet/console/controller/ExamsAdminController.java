package com.benet.console.controller;

import com.alibaba.fastjson.JSONObject;
import com.benet.collect.domain.*;
import com.benet.collect.service.*;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.utils.uuid.UuidUtils;
import com.benet.console.common.BaseViewController;
import com.benet.console.utils.ShiroHelper;
import com.benet.console.vmodel.ItemObjectVo;
import com.benet.console.vmodel.ResultInfoVo;
import com.benet.system.domain.SysRuserinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 心理测评管理
 *
 * @author yoxking
 * @date 2020-04-20
 */
@Controller
@RequestMapping("/adminview")
public class ExamsAdminController extends BaseViewController {
    private String prefix = "adminview";

    @Autowired
    private ICoctExamsflowsService examsflowsService;
    @Autowired
    private ICoctExamsinfoService examsinfoService;
    @Autowired
    private ICoctExamclassService examclassService;
    @Autowired
    private ICoctPaperinfoService paperinfoService;

    /**
     * 首页
     */
    @GetMapping(value="/exams")
    public String exams(ModelMap model)
    {
        SysRuserinfo loginUser = ShiroHelper.getLoginRuser().getUser();

        model.put("loginer",getLoginer());
        return prefix +"/exams";
    }


    /**
     * 数据
     */
    @GetMapping(value="/getExamsList")
    @ResponseBody
    public String getExamsList(@RequestParam(defaultValue = "1",value = "page") Integer pageIndex,
                               @RequestParam(defaultValue = "10",value = "limit") Integer pageSize)
    {
        SysRuserinfo loginUser = ShiroHelper.getLoginRuser().getUser();

        String condition="";
        int count=examsinfoService.getCountByCondition(loginUser.getAppCode(),condition);
        List<CoctExamsinfo> infoList=examsinfoService.getRecordsByPaging(loginUser.getAppCode(),pageIndex,pageSize,condition,"id","desc");

        ResultInfoVo resultInfo=new ResultInfoVo();
        resultInfo.setCode(0);
        resultInfo.setMsg("SUCCESS");
        resultInfo.setCount(count);
        resultInfo.setData(infoList);

        return JSONObject.toJSONString(resultInfo);
    }

    /**
     * 数据
     */
    @GetMapping(value="/getExamsFlows")
    @ResponseBody
    public String getExamsFlows(@RequestParam(defaultValue = "1",value = "page") Integer pageIndex,
                               @RequestParam(defaultValue = "10",value = "limit") Integer pageSize)
    {
        SysRuserinfo loginUser = ShiroHelper.getLoginRuser().getUser();

        String condition="";
        int count=examsflowsService.getCountByCondition(loginUser.getAppCode(),condition);
        List<CoctExamsflows> infoList=examsflowsService.getRecordsByPaging(loginUser.getAppCode(),pageIndex,pageSize,condition,"id","desc");

        ResultInfoVo resultInfo=new ResultInfoVo();
        resultInfo.setCode(0);
        resultInfo.setMsg("SUCCESS");
        resultInfo.setCount(count);
        resultInfo.setData(infoList);

        return JSONObject.toJSONString(resultInfo);
    }

    /**
     * 新增
     */
    @GetMapping(value="/exams_edit")
    public String editExams(ModelMap model)
    {
        model.put("classList",getClassList());
        model.put("paperList",getPaperList());
        return prefix +"/exams_edit";
    }

    private List<ItemObjectVo> getClassList(){

        SysRuserinfo loginUser = ShiroHelper.getLoginRuser().getUser();

        List<ItemObjectVo> itemList=new ArrayList<>();
        ItemObjectVo item=null;
        List<CoctExamclass> infoList=examclassService.getAllRecords(loginUser.getAppCode());

        if(infoList!=null&&infoList.size()>0){
            for(CoctExamclass info:infoList){
                item=new ItemObjectVo();
                item.setKey(info.getClassNo());
                item.setValue(info.getClassName());
                itemList.add(item);
            }
        }

        return itemList;
    }

    private List<ItemObjectVo> getPaperList(){

        SysRuserinfo loginUser = ShiroHelper.getLoginRuser().getUser();

        List<ItemObjectVo> itemList=new ArrayList<>();
        ItemObjectVo item=null;
        List<CoctPaperinfo> infoList=paperinfoService.getAllRecords(loginUser.getAppCode());

        if(infoList!=null&&infoList.size()>0){
            for(CoctPaperinfo info:infoList){
                item=new ItemObjectVo();
                item.setKey(info.getPaperNo());
                item.setValue(info.getPaperTitle());
                itemList.add(item);
            }
        }

        return itemList;
    }

    @PostMapping("/saveExams")
    @ResponseBody
    public AjaxResult saveExams(String title, String classNo, String paperNo, String startDate, String enditDate, String times, String rusers, String poster, String desc)
    {
        SysRuserinfo loginUser = ShiroHelper.getLoginRuser().getUser();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟

        try {
            CoctExamsinfo examsInfo = new CoctExamsinfo();
            examsInfo.setExamsNo(UuidUtils.shortUUID());
            examsInfo.setExamsTitle(title);
            examsInfo.setExamsPoster(poster);
            examsInfo.setExamsDesc(desc);
            examsInfo.setExamsType("1");
            examsInfo.setDataFrom("前端");
            examsInfo.setViewType("1");
            examsInfo.setClassNo(classNo);
            examsInfo.setOrderNo(1);
            examsInfo.setPaperNo(paperNo);
            examsInfo.setStartTime(sdf.parse(startDate));
            examsInfo.setEnditTime(sdf.parse(enditDate));
            examsInfo.setExamsTimes(Integer.parseInt(times));
            examsInfo.setExamsProfile("");
            examsInfo.setExamsQrcode("");
            examsInfo.setExamsDuration(0);
            examsInfo.setExamsPassmark(100);
            examsInfo.setExamsRules(rusers);
            examsInfo.setExamsState("1");
            examsInfo.setCheckState("1");
            examsInfo.setCreateBy(loginUser.getUserNo());
            examsInfo.setUpdateBy(loginUser.getUserNo());
            examsInfo.setDeleteFlag("1");
            examsInfo.setComments("");
            return toAjax(examsinfoService.AddNewRecord(loginUser.getAppCode(),examsInfo));
        }
        catch (Exception e){
            return error("添加失败!");
        }
    }
}
