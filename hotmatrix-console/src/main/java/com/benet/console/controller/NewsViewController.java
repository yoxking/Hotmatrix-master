package com.benet.console.controller;

import com.benet.collect.domain.CctPaperinfo;
import com.benet.common.utils.date.DateUtils;
import com.benet.console.common.BaseViewController;
import com.benet.console.utils.ShiroUtils;
import com.benet.console.vmodel.ContentInfoVo;
import com.benet.console.vmodel.PagerInfoVo;
import com.benet.console.vmodel.PaperInfoVo;
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
 * 咨询
 *
 * @author yoxking
 * @date 2020-04-20
 */
@Controller
@RequestMapping("/newsview")
public class NewsViewController extends BaseViewController {
    private String prefix = "newsview";

    @Autowired
    private ISysContentinfoService contentinfoService;

    /**
     * 首页
     */
    @GetMapping(value="/index")
    public String index(ModelMap model,@RequestParam(defaultValue = "1",value = "pageIndex") Integer pageIndex,
                        @RequestParam(defaultValue = "10",value = "pageSize") Integer pageSize)
    {
        SysRuserinfo loginUser = ShiroUtils.getLoginRuser().getUser();

        List<ContentInfoVo> contzList=new ArrayList<>();
        ContentInfoVo contz=null;

        String condition="";
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
        return prefix +"/index";
    }

    /**
     * 详细
     */
    @GetMapping(value="/detail")
    public String detail(ModelMap model,@RequestParam("id") String id) {
        SysRuserinfo loginUser = ShiroUtils.getLoginRuser().getUser();

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
        return prefix + "/detail";
    }
}

