package com.benet.sys.controller;

import java.util.List;
import com.benet.common.core.pager.PageRequest;
import com.benet.common.utils.uuid.UuidUtils;
import com.benet.common.utils.web.ServletUtils;
import com.benet.framework.security.LoginUser;
import com.benet.framework.security.service.MyJwtokenService;
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
import com.benet.system.domain.SysBranchinfo;
import com.benet.system.service.ISysBranchinfoService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.core.pager.TableDataInfo;

/**
 * 分支信息Controller
 * 
 * @author yoxking
 * @date 2020-04-20
 */
@RestController
@RequestMapping("/sys/branchinfo")
public class SysBranchinfoController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ISysBranchinfoService sysBranchinfoService;

    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('system:branchinfo:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询分支信息列表
     */
    //@PreAuthorize("@ps.hasPermit('system:branchinfo:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        int count = sysBranchinfoService.getCountByCondition(pRequest.getCondition());
        List<SysBranchinfo> list = sysBranchinfoService.getRecordsByPaging(pRequest.getPageIndex(), pRequest.getPageSize(), pRequest.getCondition(), "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增分支信息
     */
    //@PreAuthorize("@ps.hasPermit('system:branchinfo:addnew')")
    @Oplog(title = "分支信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody SysBranchinfo sysBranchinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        sysBranchinfo.setBranchNo(UuidUtils.shortUUID());
        sysBranchinfo.setCreateBy(loginUser.getUser().getUserNo());
        sysBranchinfo.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(sysBranchinfoService.AddNewRecord(sysBranchinfo));
    }

    /**
     * 编辑分支信息
     */
    //@PreAuthorize("@ps.hasPermit('system:branchinfo:update')")
    @Oplog(title = "分支信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult update(@RequestBody SysBranchinfo sysBranchinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        sysBranchinfo.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(sysBranchinfoService.UpdateRecord(sysBranchinfo));
    }

    /**
     * 保存分支信息
     */
    @PreAuthorize("@ps.hasPermit('system:branchinfo:save')")
    @Oplog(title = "分支信息", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody SysBranchinfo sysBranchinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(sysBranchinfoService.getRecordByNo(sysBranchinfo.getBranchNo()))) {
            sysBranchinfo.setBranchNo(UuidUtils.shortUUID());
            sysBranchinfo.setCreateBy(loginUser.getUser().getUserNo());
            sysBranchinfo.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(sysBranchinfoService.AddNewRecord(sysBranchinfo));
        } else {
            sysBranchinfo.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(sysBranchinfoService.UpdateRecord(sysBranchinfo));
        }
    }

    /**
     * 删除分支信息
     */
    //@PreAuthorize("@ps.hasPermit('system:branchinfo:delete')")
    @Oplog(title = "分支信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        return toAjax(sysBranchinfoService.SoftDeleteByNos(ids));
    }

    /**
     * 获取分支信息详细信息
     */
    //@PreAuthorize("@ps.hasPermit('system:branchinfo:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        return AjaxResult.success(sysBranchinfoService.getRecordByNo(id));
    }

    /**
     * 导出分支信息列表
     */
    //@PreAuthorize("@ps.hasPermit('system:branchinfo:export')")
    @Oplog(title = "分支信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        int count = sysBranchinfoService.getCountByCondition(pRequest.getCondition());

        List<SysBranchinfo> list = sysBranchinfoService.getRecordsByPaging(1,count,pRequest.getCondition(),"id","Asc");
        ExcelUtils<SysBranchinfo> util = new ExcelUtils<SysBranchinfo>(SysBranchinfo.class);
        return util.exportExcel(list, "SysBranchinfo");
    }

}
