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
import com.benet.system.domain.SysOperatelogs;
import com.benet.system.service.ISysOperatelogsService;
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
@RequestMapping("/sys/operatelogs")
public class SysOperatelogsController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ISysOperatelogsService sysOperatelogsService;
    /**
     * 首页
     */
    //@PreAuthorize("@ps.hasPermit('system:operatelogs:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询操作日志记录列表
     */
    //@PreAuthorize("@ps.hasPermit('system:operatelogs:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        int count = sysOperatelogsService.getCountByCondition(pRequest.getCondition());
        List<SysOperatelogs> list = sysOperatelogsService.getRecordsByPaging(pRequest.getPageIndex(), pRequest.getPageSize(), pRequest.getCondition(), "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增操作日志记录
     */
    //@PreAuthorize("@ps.hasPermit('system:operatelogs:insert')")
    @Oplog(title = "操作日志记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody SysOperatelogs sysOperatelogs) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        sysOperatelogs.setOplogNo(UuidUtils.shortUUID());
        sysOperatelogs.setCreateBy(loginUser.getUser().getUserNo());
        sysOperatelogs.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(sysOperatelogsService.AddNewRecord(sysOperatelogs));
    }

    /**
     * 编辑操作日志记录
     */
    //@PreAuthorize("@ps.hasPermit('system:operatelogs:update')")
    @Oplog(title = "操作日志记录", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody SysOperatelogs sysOperatelogs) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        sysOperatelogs.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(sysOperatelogsService.UpdateRecord(sysOperatelogs));
        }

    /**
     * 保存操作日志记录
     */
    //@PreAuthorize("@ps.hasPermit('system:operatelogs:save')")
    @Oplog(title = "操作日志记录", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody SysOperatelogs sysOperatelogs) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(sysOperatelogsService.getRecordByNo(sysOperatelogs.getOplogNo()))) {
            sysOperatelogs.setOplogNo(UuidUtils.shortUUID());
            sysOperatelogs.setCreateBy(loginUser.getUser().getUserNo());
            sysOperatelogs.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(sysOperatelogsService.AddNewRecord(sysOperatelogs));
        } else {
            sysOperatelogs.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(sysOperatelogsService.UpdateRecord(sysOperatelogs));
        }
    }

    /**
     * 删除操作日志记录
     */
    //@PreAuthorize("@ps.hasPermit('system:operatelogs:delete')")
    @Oplog(title = "操作日志记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        return toAjax(sysOperatelogsService.HardDeleteByNos(ids));
    }

    /**
     * 获取操作日志记录详细信息
     */
    //@PreAuthorize("@ps.hasPermit('system:operatelogs:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        return AjaxResult.success(sysOperatelogsService.getRecordByNo(id));
    }

    /**
     * 导出操作日志记录列表
     */
    //@PreAuthorize("@ps.hasPermit('system:operatelogs:export')")
    @Oplog(title = "操作日志记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        int count = sysOperatelogsService.getCountByCondition(pRequest.getCondition());

        List<SysOperatelogs> list = sysOperatelogsService.getRecordsByPaging(1,count,pRequest.getCondition(),"id","Asc");
        ExcelUtils<SysOperatelogs> util = new ExcelUtils<SysOperatelogs>(SysOperatelogs.class);
        return util.exportExcel(list, "SysOperatelogs");
    }

}
