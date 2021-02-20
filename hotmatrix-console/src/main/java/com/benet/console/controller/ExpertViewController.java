package com.benet.console.controller;

import com.benet.collect.domain.CoctRegistinfo;
import com.benet.collect.service.ICoctRegistinfoService;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.utils.uuid.UuidUtils;
import com.benet.console.common.BaseViewController;
import com.benet.console.utils.PageHelper;
import com.benet.console.utils.ShiroHelper;
import com.benet.console.vmodel.ExpertInfoVo;
import com.benet.console.vmodel.PagerInfoVo;
import com.benet.system.domain.SysRuserinfo;
import com.benet.system.service.ISysRuserinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 专家
 *
 * @author yoxking
 * @date 2020-04-20
 */
@Controller
@RequestMapping("/expertview")
public class ExpertViewController extends BaseViewController {
    private String prefix = "expertview";

    @Autowired
    private ISysRuserinfoService ruserinfoService;
    @Autowired
    private ICoctRegistinfoService registinfoService;

    /**
     * 首页
     */
    @GetMapping(value="/index")
    public String index(ModelMap model, @RequestParam(defaultValue = "1",value = "pageIndex") Integer pageIndex,
                        @RequestParam(defaultValue = "10",value = "pageSize") Integer pageSize)
    {
        SysRuserinfo loginUser = ShiroHelper.getLoginRuser().getUser();

        List<ExpertInfoVo> expertList=new ArrayList<>();
        ExpertInfoVo expert=null;

        String condition="";
        int count=ruserinfoService.getCountByCondition(loginUser.getAppCode(),condition);
        List<SysRuserinfo> infoList=ruserinfoService.getRecordsByPaging(loginUser.getAppCode(),pageIndex,pageSize,condition,"id","desc");
        if(infoList!=null&&infoList.size()>0){
            for(SysRuserinfo info:infoList){
                expert=new ExpertInfoVo();
                expert.setRuserNo(info.getUserNo());
                expert.setRuserName(info.getLoginName());
                expert.setRnickName(info.getUserEnname());
                expert.setRuserType(info.getUserType());
                expert.setSex(info.getSex());
                expert.setTelephone(info.getTelephone());
                expert.setEmail(info.getEmail());
                expert.setAvatar(info.getAvatar());
                expert.setProfile(info.getComments());

                expertList.add(expert);
            }
        }

        PagerInfoVo<ExpertInfoVo> pagerInfo=new PagerInfoVo<ExpertInfoVo>(pageIndex,pageSize,count,expertList);

        model.put("loginer",getLoginer());
        model.put("pagerInfo",pagerInfo);
        model.put("mclassList", PageHelper.getExamClasses());
        model.put("topExperts", PageHelper.getTopExperts(3));
        return prefix +"/index";
    }


    /**
     * 历史列表
     */
    @GetMapping(value="/history")
    public String history(ModelMap model, @RequestParam(defaultValue = "1",value = "pageIndex") Integer pageIndex,
                        @RequestParam(defaultValue = "10",value = "pageSize") Integer pageSize)
    {
        SysRuserinfo loginUser = ShiroHelper.getLoginRuser().getUser();

        List<ExpertInfoVo> expertList=new ArrayList<>();
        ExpertInfoVo expert=null;

        String condition="";
        int count=ruserinfoService.getCountByCondition(loginUser.getAppCode(),condition);
        List<SysRuserinfo> infoList=ruserinfoService.getRecordsByPaging(loginUser.getAppCode(),pageIndex,pageSize,condition,"id","desc");
        if(infoList!=null&&infoList.size()>0){
            for(SysRuserinfo info:infoList){
                expert=new ExpertInfoVo();
                expert.setRuserNo(info.getUserNo());
                expert.setRuserName(info.getLoginName());
                expert.setRnickName(info.getUserEnname());
                expert.setRuserType(info.getUserType());
                expert.setSex(info.getSex());
                expert.setTelephone(info.getTelephone());
                expert.setEmail(info.getEmail());
                expert.setAvatar(info.getAvatar());
                expert.setProfile(info.getComments());

                expertList.add(expert);
            }
        }

        PagerInfoVo<ExpertInfoVo> pagerInfo=new PagerInfoVo<ExpertInfoVo>(pageIndex,pageSize,count,expertList);

        model.put("loginer",getLoginer());
        model.put("pagerInfo",pagerInfo);
        model.put("mclassList", PageHelper.getExamClasses());
        model.put("topExperts", PageHelper.getTopExperts(3));
        return prefix +"/history";
    }

    /**
     * 详细
     */
    @GetMapping(value="/detail")
    public String detail(ModelMap model,@RequestParam("id") String id) {
        SysRuserinfo loginUser = ShiroHelper.getLoginRuser().getUser();

        SysRuserinfo info = ruserinfoService.getRecordByNo(loginUser.getAppCode(), id);
        ExpertInfoVo expert=new ExpertInfoVo();
        expert.setRuserNo(info.getUserNo());
        expert.setRuserName(info.getLoginName());
        expert.setRnickName(info.getUserEnname());
        expert.setRuserType(info.getUserType());
        expert.setSex(info.getSex());
        expert.setTelephone(info.getTelephone());
        expert.setEmail(info.getEmail());
        expert.setAvatar(info.getAvatar());
        expert.setProfile(info.getComments());

        model.put("loginer", getLoginer());
        model.put("exptInfo", expert);
        model.put("mclassList", PageHelper.getExamClasses());
        model.put("topExperts",PageHelper.getTopExperts(3));
        return prefix + "/detail";
    }

    /**
     * 心理咨询预约
     */
    @GetMapping(value="/regist")
    public String regist(ModelMap model,@RequestParam("id") String id) {

        SysRuserinfo loginUser = ShiroHelper.getLoginRuser().getUser();

        SysRuserinfo info = ruserinfoService.getRecordByNo(loginUser.getAppCode(), id);
        ExpertInfoVo expert=new ExpertInfoVo();
        expert.setRuserNo(info.getUserNo());
        expert.setRuserName(info.getLoginName());
        expert.setRnickName(info.getUserEnname());
        expert.setRuserType(info.getUserType());
        expert.setSex(info.getSex());
        expert.setTelephone(info.getTelephone());
        expert.setEmail(info.getEmail());
        expert.setAvatar(info.getAvatar());
        expert.setProfile(info.getComments());

        model.put("loginer", getLoginer());
        model.put("exptInfo", expert);
        return prefix +"/regist";
    }


    @PostMapping("/saveRegist")
    @ResponseBody
    public AjaxResult saveRegist(String ruserNo,String registTime) {
        SysRuserinfo loginUser = ShiroHelper.getLoginRuser().getUser();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟

        try {

            CoctRegistinfo registinfo = new CoctRegistinfo();
            registinfo.setRegistNo(UuidUtils.shortUUID());
            registinfo.setRegistType(1);
            registinfo.setRegistTime(sdf.parse(registTime));
            registinfo.setDataFrom("前台预约");
            registinfo.setExpertNo(ruserNo);
            registinfo.setRuserNo(loginUser.getUserNo());
            registinfo.setCheckState("1");
            registinfo.setCreateBy(loginUser.getUserNo());
            registinfo.setUpdateBy(loginUser.getUserNo());
            registinfo.setDeleteFlag("1");
            registinfo.setComments("");
            return toAjax(registinfoService.AddNewRecord(loginUser.getAppCode(), registinfo));
        } catch (Exception e) {
            return error("添加失败!");
        }
    }
}
