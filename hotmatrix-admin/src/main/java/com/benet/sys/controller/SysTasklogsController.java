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
import com.benet.system.domain.SysTasklogs;
import com.benet.system.service.ISysTasklogsService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.core.pager.TableDataInfo;

/**
 * 定时任务调度日志Controller
 * 
 * @author yoxking
 * @date 2020-04-20
 */
@RestController
@RequestMapping("/sys/tasklogs")
public class SysTasklogsController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ISysTasklogsService sysTasklogsService;
    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('system:tasklogs:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询定时任务调度日志列表
     */
    @PreAuthorize("@ps.hasPermit('system:tasklogs:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        int count = sysTasklogsService.getCountByCondition(pRequest.getCondition());
        List<SysTasklogs> list = sysTasklogsService.getRecordsByPaging(pRequest.getPageIndex(), pRequest.getPageSize(), pRequest.getCondition(), "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增定时任务调度日志
     */
    @PreAuthorize("@ps.hasPermit('system:tasklogs:insert')")
    @Oplog(title = "定时任务调度日志", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody SysTasklogs sysTasklogs) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        sysTasklogs.setTaskLogno(UuidUtils.shortUUID());
        sysTasklogs.setCreateBy(loginUser.getUser().getUserNo());
        sysTasklogs.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(sysTasklogsService.AddNewRecord(sysTasklogs));
    }

    /**
     * 编辑定时任务调度日志
     */
    @PreAuthorize("@ps.hasPermit('system:tasklogs:update')")
    @Oplog(title = "定时任务调度日志", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody SysTasklogs sysTasklogs) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        sysTasklogs.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(sysTasklogsService.UpdateRecord(sysTasklogs));
        }

    /**
     * 保存定时任务调度日志
     */
    @PreAuthorize("@ps.hasPermit('system:tasklogs:save')")
    @Oplog(title = "定时任务调度日志", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody SysTasklogs sysTasklogs) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(sysTasklogsService.getRecordByNo(sysTasklogs.getTaskLogno()))) {
            sysTasklogs.setTaskLogno(UuidUtils.shortUUID());
            sysTasklogs.setCreateBy(loginUser.getUser().getUserNo());
            sysTasklogs.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(sysTasklogsService.AddNewRecord(sysTasklogs));
        } else {
            sysTasklogs.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(sysTasklogsService.UpdateRecord(sysTasklogs));
        }
    }

    /**
     * 删除定时任务调度日志
     */
    @PreAuthorize("@ps.hasPermit('system:tasklogs:delete')")
    @Oplog(title = "定时任务调度日志", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        return toAjax(sysTasklogsService.SoftDeleteByNos(ids));
    }

    /**
     * 获取定时任务调度日志详细信息
     */
    @PreAuthorize("@ps.hasPermit('system:tasklogs:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        return AjaxResult.success(sysTasklogsService.getRecordByNo(id));
    }

    /**
     * 导出定时任务调度日志列表
     */
    @PreAuthorize("@ps.hasPermit('system:tasklogs:export')")
    @Oplog(title = "定时任务调度日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        int count = sysTasklogsService.getCountByCondition(pRequest.getCondition());

        List<SysTasklogs> list = sysTasklogsService.getRecordsByPaging(1,count,pRequest.getCondition(),"id","Asc");
        ExcelUtils<SysTasklogs> util = new ExcelUtils<SysTasklogs>(SysTasklogs.class);
        return util.exportExcel(list, "SysTasklogs");
    }

}
