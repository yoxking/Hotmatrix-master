package com.benet.console.controller;

import com.benet.collect.domain.CoctSalonflows;
import com.benet.collect.domain.CoctSalonsinfo;
import com.benet.collect.service.ICoctSalonflowsService;
import com.benet.collect.service.ICoctSalonsinfoService;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.utils.date.DateUtils;
import com.benet.common.utils.uuid.UuidUtils;
import com.benet.console.common.BaseViewController;
import com.benet.console.utils.PageHelper;
import com.benet.console.utils.ShiroHelper;
import com.benet.console.vmodel.PagerInfoVo;
import com.benet.console.vmodel.SalonInfoVo;
import com.benet.system.domain.SysRuserinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 主题活动
 *
 * @author yoxking
 * @date 2020-04-20
 */
@Controller
@RequestMapping("/salonsview")
public class SalonsViewController extends BaseViewController {
    private String prefix = "salonsview";

    @Autowired
    private ICoctSalonsinfoService salonsinfoService;

    @Autowired
    private ICoctSalonflowsService salonflowsService;

    /**
     * 首页
     */
    @GetMapping(value="/index")
    public String index(ModelMap model,@RequestParam(defaultValue = "1",value = "pageIndex") Integer pageIndex,
                        @RequestParam(defaultValue = "10",value = "pageSize") Integer pageSize)
    {
        SysRuserinfo loginUser = ShiroHelper.getLoginRuser().getUser();
        List<SalonInfoVo> salonsList=new ArrayList<>();
        SalonInfoVo salon=null;

        String condition="";
        int count=salonsinfoService.getCountByCondition(loginUser.getAppCode(),condition);
        List<CoctSalonsinfo> infoList=salonsinfoService.getRecordsByPaging(loginUser.getAppCode(),pageIndex,pageSize,condition,"id","desc");
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

        PagerInfoVo<SalonInfoVo> pagerInfo=new PagerInfoVo<SalonInfoVo>(pageIndex,pageSize,count,salonsList);

        model.put("loginer",getLoginer());
        model.put("pagerInfo",pagerInfo);
        model.put("mclassList", PageHelper.getExamClasses());
        model.put("topExperts",PageHelper.getTopExperts(3));
        return prefix +"/index";
    }
    /**
     * 历史列表
     */
    @GetMapping(value="/history")
    public String history(ModelMap model,@RequestParam(defaultValue = "1",value = "pageIndex") Integer pageIndex,
                        @RequestParam(defaultValue = "10",value = "pageSize") Integer pageSize)
    {
        SysRuserinfo loginUser = ShiroHelper.getLoginRuser().getUser();
        List<SalonInfoVo> salonsList=new ArrayList<>();
        SalonInfoVo salon=null;

        String condition="";
        int count=salonsinfoService.getCountByCondition(loginUser.getAppCode(),condition);
        List<CoctSalonsinfo> infoList=salonsinfoService.getRecordsByPaging(loginUser.getAppCode(),pageIndex,pageSize,condition,"id","desc");
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

        PagerInfoVo<SalonInfoVo> pagerInfo=new PagerInfoVo<SalonInfoVo>(pageIndex,pageSize,count,salonsList);

        model.put("loginer",getLoginer());
        model.put("pagerInfo",pagerInfo);
        model.put("mclassList", PageHelper.getExamClasses());
        model.put("topExperts",PageHelper.getTopExperts(3));
        return prefix +"/history";
    }

    /**
     * 详细
     */
    @GetMapping(value="/detail")
    public String detail(ModelMap model,@RequestParam("id") String id) {

        SysRuserinfo loginUser = ShiroHelper.getLoginRuser().getUser();

        CoctSalonsinfo info = salonsinfoService.getRecordByNo(loginUser.getAppCode(), id);
        SalonInfoVo  salon=new SalonInfoVo();
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

        model.put("loginer", getLoginer());
        model.put("salonInfo", salon);
        model.put("mclassList", PageHelper.getExamClasses());
        model.put("topExperts", PageHelper.getTopExperts(3));
        return prefix +"/detail";
    }

    /**
     * 主题活动报名
     */
    @GetMapping(value="/regist")
    public String regist(ModelMap model,@RequestParam("id") String id) {

        SysRuserinfo loginUser = ShiroHelper.getLoginRuser().getUser();

        CoctSalonsinfo info = salonsinfoService.getRecordByNo(loginUser.getAppCode(), id);
        SalonInfoVo  salon=new SalonInfoVo();
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

        model.put("loginer", getLoginer());
        model.put("salonInfo", salon);
        return prefix +"/regist";
    }


    @PostMapping("/saveRegist")
    @ResponseBody
    public AjaxResult saveRegist(String salonNo) {
        SysRuserinfo loginUser = ShiroHelper.getLoginRuser().getUser();

        CoctSalonflows salonflows = new CoctSalonflows();
        salonflows.setSflowNo(UuidUtils.shortUUID());
        salonflows.setSalonNo(salonNo);
        salonflows.setRuserNo(loginUser.getUserNo());
        salonflows.setSflowDesc("");
        salonflows.setCheckState("1");
        salonflows.setCreateBy(loginUser.getUserNo());
        salonflows.setUpdateBy(loginUser.getUserNo());
        salonflows.setDeleteFlag("1");
        salonflows.setComments("");
        return toAjax(salonflowsService.AddNewRecord(loginUser.getAppCode(), salonflows));
    }
}
