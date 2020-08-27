package com.benet.system.controller;

import java.util.List;
import com.benet.common.core.pager.PageRequest;
import com.benet.common.utils.uuid.UuidUtils;
import com.benet.common.utils.web.ServletUtils;
import com.benet.framework.security.LoginUser;
import com.benet.framework.security.service.MyJwtokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.benet.system.domain.SysOperatelog;
import com.benet.system.service.ISysOperatelogService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.core.pager.TableDataInfo;

/**
 * 操作日志记录Controller
 * 
 * @author yoxking
 * @date 2020-04-20
 */
@RestController
@RequestMapping("/system/operatelog")
public class SysOperatelogController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ISysOperatelogService sysOperatelogService;
    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('system:operatelog:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询操作日志记录列表
     */
    @PreAuthorize("@ps.hasPermit('system:operatelog:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = sysOperatelogService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());
        List<SysOperatelog> list = sysOperatelogService.getRecordsByPaging(loginUser.getUser().getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), pRequest.getCondition(), "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增操作日志记录
     */
    @PreAuthorize("@ps.hasPermit('system:operatelog:addnew')")
    @Oplog(title = "操作日志记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody SysOperatelog sysOperatelogs) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        sysOperatelogs.setOplogNo(UuidUtils.shortUUID());
        sysOperatelogs.setCreateBy(loginUser.getUser().getUserNo());
        sysOperatelogs.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(sysOperatelogService.AddNewRecord(loginUser.getUser().getAppCode(),sysOperatelogs));
    }

    /**
     * 编辑操作日志记录
     */
    @PreAuthorize("@ps.hasPermit('system:operatelog:update')")
    @Oplog(title = "操作日志记录", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody SysOperatelog sysOperatelogs) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        sysOperatelogs.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(sysOperatelogService.UpdateRecord(loginUser.getUser().getAppCode(),sysOperatelogs));
        }

    /**
     * 保存操作日志记录
     */
    @PreAuthorize("@ps.hasPermit('system:operatelog:save')")
    @Oplog(title = "操作日志记录", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody SysOperatelog sysOperatelogs) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(sysOperatelogService.getRecordByNo(loginUser.getUser().getAppCode(),sysOperatelogs.getOplogNo()))) {
            sysOperatelogs.setOplogNo(UuidUtils.shortUUID());
            sysOperatelogs.setCreateBy(loginUser.getUser().getUserNo());
            sysOperatelogs.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(sysOperatelogService.AddNewRecord(loginUser.getUser().getAppCode(),sysOperatelogs));
        } else {
            sysOperatelogs.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(sysOperatelogService.UpdateRecord(loginUser.getUser().getAppCode(),sysOperatelogs));
        }
    }

    /**
     * 删除操作日志记录
     */
    @PreAuthorize("@ps.hasPermit('system:operatelog:delete')")
    @Oplog(title = "操作日志记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(sysOperatelogService.HardDeleteByNos(loginUser.getUser().getAppCode(),ids));
    }

    /**
     * 获取操作日志记录详细信息
     */
    @PreAuthorize("@ps.hasPermit('system:operatelog:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(sysOperatelogService.getRecordByNo(loginUser.getUser().getAppCode(),id));
    }

    /**
     * 导出操作日志记录列表
     */
    @PreAuthorize("@ps.hasPermit('system:operatelog:export')")
    @Oplog(title = "操作日志记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = sysOperatelogService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());

        List<SysOperatelog> list = sysOperatelogService.getRecordsByPaging(loginUser.getUser().getAppCode(),1,count,pRequest.getCondition(),"id","Asc");
        ExcelUtils<SysOperatelog> util = new ExcelUtils<SysOperatelog>(SysOperatelog.class);
        return util.exportExcel(list, "SysOperatelogs");
    }

}
