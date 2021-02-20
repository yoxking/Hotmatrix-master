package com.benet.console.controller;

import com.benet.collect.domain.CoctBlogsinfo;
import com.benet.collect.service.ICoctBlogsinfoService;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.utils.date.DateUtils;
import com.benet.common.utils.uuid.UuidUtils;
import com.benet.console.common.BaseViewController;
import com.benet.console.utils.PageHelper;
import com.benet.console.utils.ShiroHelper;
import com.benet.console.vmodel.BlogInfoVo;
import com.benet.console.vmodel.PagerInfoVo;
import com.benet.system.domain.SysRuserinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

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
    private ICoctBlogsinfoService blogsinfoService;

    /**
     * 首页
     */
    @GetMapping(value="/index")
    public String index(ModelMap model,@RequestParam(defaultValue = "1",value = "pageIndex") Integer pageIndex,
                        @RequestParam(defaultValue = "10",value = "pageSize") Integer pageSize)
    {
        SysRuserinfo loginUser = ShiroHelper.getLoginRuser().getUser();
        List<BlogInfoVo> blogsList=new ArrayList<>();
        BlogInfoVo blog=null;

        String condition="";
        int count=blogsinfoService.getCountByCondition(loginUser.getAppCode(),condition);
        List<CoctBlogsinfo> infoList=blogsinfoService.getRecordsByPaging(loginUser.getAppCode(),pageIndex,pageSize,condition,"id","desc");
        if(infoList!=null&&infoList.size()>0){
            for(CoctBlogsinfo info:infoList){
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
        model.put("mclassList", PageHelper.getExamClasses());
        model.put("topExperts", PageHelper.getTopExperts(3));
        return prefix +"/index";
    }

    @PostMapping("/saveBlog")
    @ResponseBody
    public AjaxResult saveBlog(String title, String content)
    {
        SysRuserinfo loginUser = ShiroHelper.getLoginRuser().getUser();

        CoctBlogsinfo blogInfo=new CoctBlogsinfo();
        blogInfo.setBlogNo(UuidUtils.shortUUID());
        blogInfo.setBlogTitle(title);
        blogInfo.setClassNo("");
        blogInfo.setRuserNo(loginUser.getUserNo());
        blogInfo.setBlogContent(content);
        blogInfo.setDolikeHit(0);
        blogInfo.setRepostHit(0);
        blogInfo.setCheckState("1");
        blogInfo.setCreateBy(loginUser.getUserNo());
        blogInfo.setUpdateBy(loginUser.getUserNo());
        blogInfo.setDeleteFlag("1");
        blogInfo.setComments("");

        return toAjax(blogsinfoService.AddNewRecord(loginUser.getAppCode(),blogInfo));
    }
}
