package com.benet.job.controller;

import java.util.List;
import com.benet.common.core.pager.PageRequest;
import com.benet.common.utils.uuid.UuidUtils;
import com.benet.common.utils.web.ServletUtils;
import com.benet.framework.security.LoginUser;
import com.benet.framework.security.service.MyJwtokenService;
import com.benet.job.domain.SysTaskelog;
import com.benet.job.service.ISysTaskelogService;
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
import com.benet.common.core.pager.TableDataInfo;

/**
 * 定时任务调度日志Controller
 * 
 * @author yoxking
 * @date 2020-04-20
 */
@RestController
@RequestMapping("/job/taskelog")
public class SysTaskelogController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ISysTaskelogService sysTaskelogService;
    /**
     * 首页
     */
    //@PreAuthorize("@ps.hasPermit('system:tasklogs:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询定时任务调度日志列表
     */
    //@PreAuthorize("@ps.hasPermit('system:tasklogs:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        int count = sysTaskelogService.getCountByCondition(pRequest.getCondition());
        List<SysTaskelog> list = sysTaskelogService.getRecordsByPaging(pRequest.getPageIndex(), pRequest.getPageSize(), pRequest.getCondition(), "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增定时任务调度日志
     */
    //@PreAuthorize("@ps.hasPermit('system:tasklogs:insert')")
    @Oplog(title = "定时任务调度日志", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody SysTaskelog sysTasklogs) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        sysTasklogs.setTaskLogno(UuidUtils.shortUUID());
        sysTasklogs.setCreateBy(loginUser.getUser().getUserNo());
        sysTasklogs.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(sysTaskelogService.AddNewRecord(sysTasklogs));
    }

    /**
     * 编辑定时任务调度日志
     */
    //@PreAuthorize("@ps.hasPermit('system:tasklogs:update')")
    @Oplog(title = "定时任务调度日志", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody SysTaskelog sysTasklogs) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        sysTasklogs.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(sysTaskelogService.UpdateRecord(sysTasklogs));
        }

    /**
     * 保存定时任务调度日志
     */
    //@PreAuthorize("@ps.hasPermit('system:tasklogs:save')")
    @Oplog(title = "定时任务调度日志", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody SysTaskelog sysTasklogs) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(sysTaskelogService.getRecordByNo(sysTasklogs.getTaskLogno()))) {
            sysTasklogs.setTaskLogno(UuidUtils.shortUUID());
            sysTasklogs.setCreateBy(loginUser.getUser().getUserNo());
            sysTasklogs.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(sysTaskelogService.AddNewRecord(sysTasklogs));
        } else {
            sysTasklogs.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(sysTaskelogService.UpdateRecord(sysTasklogs));
        }
    }

    /**
     * 删除定时任务调度日志
     */
    //@PreAuthorize("@ps.hasPermit('system:tasklogs:delete')")
    @Oplog(title = "定时任务调度日志", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        return toAjax(sysTaskelogService.HardDeleteByNos(ids));
    }

    /**
     * 获取定时任务调度日志详细信息
     */
    //@PreAuthorize("@ps.hasPermit('system:tasklogs:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        return AjaxResult.success(sysTaskelogService.getRecordByNo(id));
    }

    /**
     * 导出定时任务调度日志列表
     */
    //@PreAuthorize("@ps.hasPermit('system:tasklogs:export')")
    @Oplog(title = "定时任务调度日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        int count = sysTaskelogService.getCountByCondition(pRequest.getCondition());

        List<SysTaskelog> list = sysTaskelogService.getRecordsByPaging(1,count,pRequest.getCondition(),"id","Asc");
        ExcelUtils<SysTaskelog> util = new ExcelUtils<SysTaskelog>(SysTaskelog.class);
        return util.exportExcel(list, "SysTasklogs");
    }

}
