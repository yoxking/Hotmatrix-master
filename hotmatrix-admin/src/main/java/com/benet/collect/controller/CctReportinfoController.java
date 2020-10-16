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
import com.benet.collect.domain.CctReportinfo;
import com.benet.collect.service.ICctReportinfoService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;

/**
 * 报告信息Controller
 * 
 * @author yoxking
 * @date 2020-10-14
 */
@Api(value = "collect/reportinfo", tags = "报告信息控制器")
@RestController
@RequestMapping("/collect/reportinfo")
public class CctReportinfoController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ICctReportinfoService cctReportinfoService;
    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('collect:reportinfo:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询报告信息列表
     */
    @PreAuthorize("@ps.hasPermit('collect:reportinfo:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cctReportinfoService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());
        List<CctReportinfo> list = cctReportinfoService.getRecordsByPaging(loginUser.getUser().getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), pRequest.getCondition(), "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增报告信息
     */
    @PreAuthorize("@ps.hasPermit('collect:reportinfo:addnew')")
    @Oplog(title = "报告信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody CctReportinfo cctReportinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        cctReportinfo.setReportNo(UuidUtils.shortUUID());
        cctReportinfo.setCreateBy(loginUser.getUser().getUserNo());
        cctReportinfo.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(cctReportinfoService.AddNewRecord(loginUser.getUser().getAppCode(),cctReportinfo));
    }

    /**
     * 编辑报告信息
     */
    @PreAuthorize("@ps.hasPermit('collect:reportinfo:update')")
    @Oplog(title = "报告信息", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody CctReportinfo cctReportinfo) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        cctReportinfo.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cctReportinfoService.UpdateRecord(loginUser.getUser().getAppCode(),cctReportinfo));
        }

    /**
     * 保存报告信息
     */
    @PreAuthorize("@ps.hasPermit('collect:reportinfo:save')")
    @Oplog(title = "报告信息", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody CctReportinfo cctReportinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(cctReportinfoService.getRecordByNo(loginUser.getUser().getAppCode(),cctReportinfo.getReportNo()))) {
            cctReportinfo.setReportNo(UuidUtils.shortUUID());
            cctReportinfo.setCreateBy(loginUser.getUser().getUserNo());
            cctReportinfo.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cctReportinfoService.AddNewRecord(loginUser.getUser().getAppCode(),cctReportinfo));
        } else {
            cctReportinfo.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cctReportinfoService.UpdateRecord(loginUser.getUser().getAppCode(),cctReportinfo));
        }
    }

    /**
     * 删除报告信息
     */
    @PreAuthorize("@ps.hasPermit('collect:reportinfo:delete')")
    @Oplog(title = "报告信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(cctReportinfoService.SoftDeleteByNos(loginUser.getUser().getAppCode(),ids));
    }

    /**
     * 获取报告信息详细信息
     */
    @PreAuthorize("@ps.hasPermit('collect:reportinfo:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(cctReportinfoService.getRecordByNo(loginUser.getUser().getAppCode(),id));
    }

    /**
     * 导出报告信息列表
     */
    @PreAuthorize("@ps.hasPermit('collect:reportinfo:export')")
    @Oplog(title = "报告信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cctReportinfoService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());

        List<CctReportinfo> list = cctReportinfoService.getRecordsByPaging(loginUser.getUser().getAppCode(),1,count,pRequest.getCondition(),"id","Asc");
        ExcelUtils<CctReportinfo> util = new ExcelUtils<CctReportinfo>(CctReportinfo.class);
        return util.exportExcel(list, "CctReportinfo");
    }

}
