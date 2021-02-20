package com.benet.collect.controller;

import java.util.List;

import com.benet.collect.domain.CoctBlogsinfo;
import com.benet.collect.service.ICoctBlogsinfoService;
import com.benet.common.core.pager.PageRequest;
import com.benet.common.core.pager.TableDataInfo;
import com.benet.common.utils.uuid.UuidUtils;
import com.benet.common.utils.web.ServletUtils;
import com.benet.framework.security.LoginUser;
import com.benet.framework.security.service.MyJwtokenService;
import io.swagger.annotations.Api;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;

/**
 * 日记信息Controller
 * 
 * @author yoxking
 * @date 2020-10-14
 */
@Api(value = "collect/blogsinfo", tags = "日记信息控制器")
@RestController
@RequestMapping("/collect/blogsinfo")
public class CoctBlogsinfoController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ICoctBlogsinfoService coctBlogsinfoService;
    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('collect:blogsinfo:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询日记信息列表
     */
    @PreAuthorize("@ps.hasPermit('collect:blogsinfo:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = coctBlogsinfoService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());
        List<CoctBlogsinfo> list = coctBlogsinfoService.getRecordsByPaging(loginUser.getUser().getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), pRequest.getCondition(), "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增日记信息
     */
    @PreAuthorize("@ps.hasPermit('collect:blogsinfo:addnew')")
    @Oplog(title = "日记信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody CoctBlogsinfo coctBlogsinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        coctBlogsinfo.setBlogNo(UuidUtils.shortUUID());
        coctBlogsinfo.setCreateBy(loginUser.getUser().getUserNo());
        coctBlogsinfo.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(coctBlogsinfoService.AddNewRecord(loginUser.getUser().getAppCode(),coctBlogsinfo));
    }

    /**
     * 编辑日记信息
     */
    @PreAuthorize("@ps.hasPermit('collect:blogsinfo:update')")
    @Oplog(title = "日记信息", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody CoctBlogsinfo coctBlogsinfo) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        coctBlogsinfo.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(coctBlogsinfoService.UpdateRecord(loginUser.getUser().getAppCode(),coctBlogsinfo));
        }

    /**
     * 保存日记信息
     */
    @PreAuthorize("@ps.hasPermit('collect:blogsinfo:save')")
    @Oplog(title = "日记信息", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody CoctBlogsinfo coctBlogsinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(coctBlogsinfoService.getRecordByNo(loginUser.getUser().getAppCode(),coctBlogsinfo.getBlogNo()))) {
            coctBlogsinfo.setBlogNo(UuidUtils.shortUUID());
            coctBlogsinfo.setCreateBy(loginUser.getUser().getUserNo());
            coctBlogsinfo.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(coctBlogsinfoService.AddNewRecord(loginUser.getUser().getAppCode(),coctBlogsinfo));
        } else {
            coctBlogsinfo.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(coctBlogsinfoService.UpdateRecord(loginUser.getUser().getAppCode(),coctBlogsinfo));
        }
    }

    /**
     * 删除日记信息
     */
    @PreAuthorize("@ps.hasPermit('collect:blogsinfo:delete')")
    @Oplog(title = "日记信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(coctBlogsinfoService.SoftDeleteByNos(loginUser.getUser().getAppCode(),ids));
    }

    /**
     * 获取日记信息详细信息
     */
    @PreAuthorize("@ps.hasPermit('collect:blogsinfo:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(coctBlogsinfoService.getRecordByNo(loginUser.getUser().getAppCode(),id));
    }

    /**
     * 导出日记信息列表
     */
    @PreAuthorize("@ps.hasPermit('collect:blogsinfo:export')")
    @Oplog(title = "日记信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = coctBlogsinfoService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());

        List<CoctBlogsinfo> list = coctBlogsinfoService.getRecordsByPaging(loginUser.getUser().getAppCode(),1,count,pRequest.getCondition(),"id","Asc");
        ExcelUtils<CoctBlogsinfo> util = new ExcelUtils<CoctBlogsinfo>(CoctBlogsinfo.class);
        return util.exportExcel(list, "CoctBlogsinfo");
    }

}
