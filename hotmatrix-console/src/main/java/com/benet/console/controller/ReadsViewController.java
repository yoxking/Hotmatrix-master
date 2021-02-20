package com.benet.console.controller;

import com.benet.common.utils.date.DateUtils;
import com.benet.console.common.BaseViewController;
import com.benet.console.utils.PageHelper;
import com.benet.console.utils.ShiroHelper;
import com.benet.console.vmodel.ContentInfoVo;
import com.benet.console.vmodel.PagerInfoVo;
import com.benet.system.domain.SysContentinfo;
import com.benet.system.domain.SysRuserinfo;
import com.benet.system.service.ISysContentinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * 咨询与案例
 *
 * @author yoxking
 * @date 2020-04-20
 */
@Controller
@RequestMapping("/readsview")
public class ReadsViewController extends BaseViewController {
    private String prefix = "readsview";

    @Autowired
    private ISysContentinfoService contentinfoService;

    /**
     * 首页
     */
    @GetMapping(value="/index")
    public String index(ModelMap model,@RequestParam(defaultValue = "1",value = "pageIndex") Integer pageIndex,
                        @RequestParam(defaultValue = "10",value = "pageSize") Integer pageSize)
    {
        SysRuserinfo loginUser = ShiroHelper.getLoginRuser().getUser();

        List<ContentInfoVo> contzList=new ArrayList<>();
        ContentInfoVo contz=null;

        String condition=" class_no in ('6000001657568767','6000000885788162')  ";
        int count=contentinfoService.getCountByCondition(loginUser.getAppCode(),condition);
        List<SysContentinfo> infoList=contentinfoService.getRecordsByPaging(loginUser.getAppCode(),pageIndex,pageSize,condition,"pubdate","desc");
        if(infoList!=null&&infoList.size()>0){
            for(SysContentinfo info:infoList){
                contz=new ContentInfoVo();
                contz.setCntzNo(info.getContzNo());
                contz.setTitle(info.getTitle());
                contz.setClassName(info.getClassNo());
                contz.setAuthor(info.getAuthor());
                contz.setPoster(info.getPoster());
                contz.setPubdate(DateUtils.dateTime(info.getPubdate()));
                contz.setAbstracts(info.getAbstracts());
                contz.setNcontents(info.getNcontents());
                contz.setHitCount(info.getHitCount()+"");
                contz.setCheckState(info.getCheckState());
                contzList.add(contz);
            }
        }

        PagerInfoVo<ContentInfoVo> pagerInfo=new PagerInfoVo<>(pageIndex,pageSize,count,contzList);

        model.put("loginer",getLoginer());
        model.put("pagerInfo",pagerInfo);
        model.put("mclassList", PageHelper.getExamClasses());
        model.put("hotopReads", PageHelper.getHotopReads(3));
        return prefix +"/index";
    }

    /**
     * 详细
     */
    @GetMapping(value="/detail")
    public String detail(ModelMap model,@RequestParam("id") String id) {
        SysRuserinfo loginUser = ShiroHelper.getLoginRuser().getUser();

        SysContentinfo info = contentinfoService.getRecordByNo(loginUser.getAppCode(), id);
        ContentInfoVo contz = new ContentInfoVo();
        contz.setCntzNo(info.getContzNo());
        contz.setTitle(info.getTitle());
        contz.setClassName(info.getClassNo());
        contz.setAuthor(info.getAuthor());
        contz.setPoster(info.getPoster());
        contz.setPubdate(DateUtils.dateTime(info.getPubdate()));
        contz.setAbstracts(info.getAbstracts());
        contz.setNcontents(info.getNcontents());
        contz.setHitCount(info.getHitCount() + "");
        contz.setCheckState(info.getCheckState());

        model.put("loginer", getLoginer());
        model.put("contzInfo", contz);
        model.put("mclassList", PageHelper.getExamClasses());
        model.put("hotopReads", PageHelper.getHotopReads(3));
        return prefix +"/detail";
    }
}

