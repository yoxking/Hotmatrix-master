package com.benet.console.controller;

import com.benet.collect.domain.CctBlogsinfo;
import com.benet.collect.domain.CctSalonsinfo;
import com.benet.collect.service.ICctBlogsinfoService;
import com.benet.collect.service.ICctSalonsinfoService;
import com.benet.common.utils.date.DateUtils;
import com.benet.console.common.BaseViewController;
import com.benet.console.utils.ShiroUtils;
import com.benet.console.vmodel.BlogInfoVo;
import com.benet.console.vmodel.ContentInfoVo;
import com.benet.console.vmodel.PagerInfoVo;
import com.benet.console.vmodel.SalonInfoVo;
import com.benet.system.domain.SysContentinfo;
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
    private ICctSalonsinfoService salonsinfoService;

    /**
     * 首页
     */
    @GetMapping(value="/index")
    public String index(ModelMap model,@RequestParam(defaultValue = "1",value = "pageIndex") Integer pageIndex,
                        @RequestParam(defaultValue = "10",value = "pageSize") Integer pageSize)
    {
        SysRuserinfo loginUser = ShiroUtils.getLoginRuser().getUser();
        List<SalonInfoVo> salonsList=new ArrayList<>();
        SalonInfoVo salon=null;

        String condition="";
        int count=salonsinfoService.getCountByCondition(loginUser.getAppCode(),condition);
        List<CctSalonsinfo> infoList=salonsinfoService.getRecordsByPaging(loginUser.getAppCode(),pageIndex,pageSize,condition,"id","desc");
        if(infoList!=null&&infoList.size()>0){
            for(CctSalonsinfo info:infoList){
                salon=new SalonInfoVo();
                salon.setSalonNo(info.getSalonNo());
                salon.setSalonName(info.getSalonName());
                salon.setClassName(info.getClassNo());
                salon.setStartTime(DateUtils.dateTime(info.getStartTime()));
                salon.setEnditTime(DateUtils.dateTime(info.getEnditTime()));
                salon.setSalonAddress(info.getSalonAddress());
                salon.setSalonContent(info.getSalonContent());
                salon.setRegistRusers(info.getRegistRusers());
                salon.setCheckState(info.getCheckState());
                salonsList.add(salon);
            }
        }

        PagerInfoVo<SalonInfoVo> pagerInfo=new PagerInfoVo<SalonInfoVo>(pageIndex,pageSize,count,salonsList);

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

        CctSalonsinfo info = salonsinfoService.getRecordByNo(loginUser.getAppCode(), id);
        SalonInfoVo  salon=new SalonInfoVo();
        salon.setSalonNo(info.getSalonNo());
        salon.setSalonName(info.getSalonName());
        salon.setClassName(info.getClassNo());
        salon.setStartTime(DateUtils.dateTime(info.getStartTime()));
        salon.setEnditTime(DateUtils.dateTime(info.getEnditTime()));
        salon.setSalonAddress(info.getSalonAddress());
        salon.setSalonContent(info.getSalonContent());
        salon.setRegistRusers(info.getRegistRusers());
        salon.setCheckState(info.getCheckState());

        model.put("loginer", getLoginer());
        model.put("salonInfo", salon);
        return prefix +"/detail";
    }
}
