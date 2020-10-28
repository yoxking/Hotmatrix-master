package com.benet.console.controller;

import com.benet.collect.domain.CctBlogsinfo;
import com.benet.collect.service.ICctBlogsinfoService;
import com.benet.common.utils.date.DateUtils;
import com.benet.console.common.BaseViewController;
import com.benet.console.utils.ShiroUtils;
import com.benet.console.vmodel.BlogInfoVo;
import com.benet.console.vmodel.PagerInfoVo;
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
 * 日记
 *
 * @author yoxking
 * @date 2020-04-20
 */
@Controller
@RequestMapping("/blogsview")
public class BlogsViewController extends BaseViewController {
    private String prefix = "blogsview";

    @Autowired
    private ICctBlogsinfoService blogsinfoService;

    /**
     * 首页
     */
    @GetMapping(value="/index")
    public String index(ModelMap model,@RequestParam(defaultValue = "1",value = "pageIndex") Integer pageIndex,
                        @RequestParam(defaultValue = "10",value = "pageSize") Integer pageSize)
    {
        SysRuserinfo loginUser = ShiroUtils.getLoginRuser().getUser();
        List<BlogInfoVo> blogsList=new ArrayList<>();
        BlogInfoVo blog=null;

        String condition="";
        int count=blogsinfoService.getCountByCondition(loginUser.getAppCode(),condition);
        List<CctBlogsinfo> infoList=blogsinfoService.getRecordsByPaging(loginUser.getAppCode(),pageIndex,pageSize,condition,"id","desc");
        if(infoList!=null&&infoList.size()>0){
            for(CctBlogsinfo info:infoList){
                blog=new BlogInfoVo();
                blog.setBlogNo(info.getBlogNo());
                blog.setBlogTitle(info.getBlogTitle());
                blog.setClassName(info.getClassNo());
                blog.setBlogContent(info.getBlogContent());
                blog.setDolikeHit(info.getDolikeHit());
                blog.setRepostHit(info.getRepostHit());
                blog.setCheckState(info.getCheckState());
                blog.setPubTime(DateUtils.dateTime(info.getCreateTime()));
                blogsList.add(blog);
            }
        }

        PagerInfoVo<BlogInfoVo> pagerInfo=new PagerInfoVo<BlogInfoVo>(pageIndex,pageSize,count,blogsList);

        model.put("loginer",getLoginer());
        model.put("pagerInfo",pagerInfo);
        return prefix +"/index";
    }
}
