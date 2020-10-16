package com.benet.collect.controller;

import java.util.List;
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
import com.benet.collect.domain.CctRemarkinfo;
import com.benet.collect.service.ICctRemarkinfoService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;

/**
 * 评论信息Controller
 * 
 * @author yoxking
 * @date 2020-10-14
 */
@Api(value = "collect/remarkinfo", tags = "评论信息控制器")
@RestController
@RequestMapping("/collect/remarkinfo")
public class CctRemarkinfoController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ICctRemarkinfoService cctRemarkinfoService;
    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('collect:remarkinfo:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询评论信息列表
     */
    @PreAuthorize("@ps.hasPermit('collect:remarkinfo:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cctRemarkinfoService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());
        List<CctRemarkinfo> list = cctRemarkinfoService.getRecordsByPaging(loginUser.getUser().getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), pRequest.getCondition(), "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增评论信息
     */
    @PreAuthorize("@ps.hasPermit('collect:remarkinfo:addnew')")
    @Oplog(title = "评论信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody CctRemarkinfo cctRemarkinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        cctRemarkinfo.setRemarkNo(UuidUtils.shortUUID());
        cctRemarkinfo.setCreateBy(loginUser.getUser().getUserNo());
        cctRemarkinfo.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(cctRemarkinfoService.AddNewRecord(loginUser.getUser().getAppCode(),cctRemarkinfo));
    }

    /**
     * 编辑评论信息
     */
    @PreAuthorize("@ps.hasPermit('collect:remarkinfo:update')")
    @Oplog(title = "评论信息", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody CctRemarkinfo cctRemarkinfo) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        cctRemarkinfo.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cctRemarkinfoService.UpdateRecord(loginUser.getUser().getAppCode(),cctRemarkinfo));
        }

    /**
     * 保存评论信息
     */
    @PreAuthorize("@ps.hasPermit('collect:remarkinfo:save')")
    @Oplog(title = "评论信息", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody CctRemarkinfo cctRemarkinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(cctRemarkinfoService.getRecordByNo(loginUser.getUser().getAppCode(),cctRemarkinfo.getRemarkNo()))) {
            cctRemarkinfo.setRemarkNo(UuidUtils.shortUUID());
            cctRemarkinfo.setCreateBy(loginUser.getUser().getUserNo());
            cctRemarkinfo.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cctRemarkinfoService.AddNewRecord(loginUser.getUser().getAppCode(),cctRemarkinfo));
        } else {
            cctRemarkinfo.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cctRemarkinfoService.UpdateRecord(loginUser.getUser().getAppCode(),cctRemarkinfo));
        }
    }

    /**
     * 删除评论信息
     */
    @PreAuthorize("@ps.hasPermit('collect:remarkinfo:delete')")
    @Oplog(title = "评论信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(cctRemarkinfoService.SoftDeleteByNos(loginUser.getUser().getAppCode(),ids));
    }

    /**
     * 获取评论信息详细信息
     */
    @PreAuthorize("@ps.hasPermit('collect:remarkinfo:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(cctRemarkinfoService.getRecordByNo(loginUser.getUser().getAppCode(),id));
    }

    /**
     * 导出评论信息列表
     */
    @PreAuthorize("@ps.hasPermit('collect:remarkinfo:export')")
    @Oplog(title = "评论信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cctRemarkinfoService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());

        List<CctRemarkinfo> list = cctRemarkinfoService.getRecordsByPaging(loginUser.getUser().getAppCode(),1,count,pRequest.getCondition(),"id","Asc");
        ExcelUtils<CctRemarkinfo> util = new ExcelUtils<CctRemarkinfo>(CctRemarkinfo.class);
        return util.exportExcel(list, "CctRemarkinfo");
    }

}
