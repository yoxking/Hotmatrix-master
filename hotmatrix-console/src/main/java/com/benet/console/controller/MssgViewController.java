package com.benet.console.controller;

import com.benet.common.utils.date.DateUtils;
import com.benet.console.common.BaseViewController;
import com.benet.console.utils.PageHelper;
import com.benet.console.utils.ShiroHelper;
import com.benet.console.vmodel.MssgInfoVo;
import com.benet.console.vmodel.PagerInfoVo;
import com.benet.system.domain.SysMessageinfo;
import com.benet.system.domain.SysRuserinfo;
import com.benet.system.service.ISysMessageinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * 消息中心
 *
 * @author yoxking
 * @date 2020-12-20
 */
@Controller
@RequestMapping("/mssgview")
public class MssgViewController extends BaseViewController {
    private String prefix = "mssgview";

    @Autowired
    private ISysMessageinfoService messageinfoService;

    /**
     * 首页
     */
    @GetMapping(value="/index")
    public String index(ModelMap model, @RequestParam(defaultValue = "1",value = "pageIndex") Integer pageIndex,
                        @RequestParam(defaultValue = "10",value = "pageSize") Integer pageSize)
    {
        SysRuserinfo loginUser = ShiroHelper.getLoginRuser().getUser();

        List<MssgInfoVo> mssgList=new ArrayList<>();
        MssgInfoVo mssg=null;

        //String condition=" mreceiver ='"+loginUser.getUserNo()+"' ";
        String condition="";
        int count=messageinfoService.getCountByCondition(loginUser.getAppCode(),condition);
        List<SysMessageinfo> infoList=messageinfoService.getRecordsByPaging(loginUser.getAppCode(),pageIndex,pageSize,condition,"id","desc");
        if(infoList!=null&&infoList.size()>0){
            for(SysMessageinfo info:infoList){
                mssg=new MssgInfoVo();
                mssg.setMsgNo(info.getMssgNo());
                mssg.setMtitle(info.getMtitle());
                mssg.setMsgType(info.getMssgType());
                mssg.setMsender(info.getMsender());
                mssg.setRecTime(DateUtils.dateTime(info.getReceiveTime()));
                mssg.setMcontent(info.getMcontent());
                mssg.setAttachfile(info.getAttachfile());
                mssg.setReadState(info.getReadState());

                mssgList.add(mssg);
            }
        }

        PagerInfoVo<MssgInfoVo> pagerInfo=new PagerInfoVo<>(pageIndex,pageSize,count,mssgList);

        model.put("loginer",getLoginer());
        model.put("pagerInfo",pagerInfo);
        model.put("mclassList", PageHelper.getExamClasses());
        return prefix +"/index";
    }

    /**
     * 首页
     */
    @GetMapping(value="/detail")
    public String detail(ModelMap model,@RequestParam("id") String id) {
        SysRuserinfo loginUser = ShiroHelper.getLoginRuser().getUser();

        SysMessageinfo info = messageinfoService.getRecordByNo(loginUser.getAppCode(), id);
        MssgInfoVo mssg=new MssgInfoVo();
        mssg.setMsgNo(info.getMssgNo());
        mssg.setMtitle(info.getMtitle());
        mssg.setMsgType(info.getMssgType());
        mssg.setMsender(info.getMsender());
        mssg.setRecTime(DateUtils.dateTime(info.getReceiveTime()));
        mssg.setMcontent(info.getMcontent());
        mssg.setAttachfile(info.getAttachfile());
        mssg.setReadState(info.getReadState());

        model.put("loginer", getLoginer());
        model.put("mssgInfo", mssg);
        model.put("mclassList", PageHelper.getExamClasses());

        return prefix +"/detail";
    }
}
