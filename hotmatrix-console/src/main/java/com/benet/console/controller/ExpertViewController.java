package com.benet.console.controller;

import com.benet.console.common.BaseViewController;
import com.benet.console.utils.ShiroUtils;
import com.benet.console.vmodel.ExpertInfoVo;
import com.benet.console.vmodel.PagerInfoVo;
import com.benet.system.domain.SysRuserinfo;
import com.benet.system.service.ISysRuserinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    /**
     * 首页
     */
    @GetMapping(value="/index")
    public String index(ModelMap model, @RequestParam(defaultValue = "1",value = "pageIndex") Integer pageIndex,
                        @RequestParam(defaultValue = "10",value = "pageSize") Integer pageSize)
    {
        SysRuserinfo loginUser = ShiroUtils.getLoginRuser().getUser();

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
                expertList.add(expert);
            }
        }

        PagerInfoVo<ExpertInfoVo> pagerInfo=new PagerInfoVo<ExpertInfoVo>(pageIndex,pageSize,count,expertList);

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

        model.put("loginer", getLoginer());
        model.put("exptInfo", expert);
        return prefix + "/detail";
    }
}
