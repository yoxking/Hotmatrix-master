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
import com.benet.system.domain.SysLogininfo;
import com.benet.system.service.ISysLogininfoService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.core.pager.TableDataInfo;

/**
 * 系统访问记录Controller
 * 
 * @author yoxking
 * @date 2020-04-20
 */
@RestController
@RequestMapping("/sys/logininfo")
public class SysLogininfoController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ISysLogininfoService sysLogininfoService;
    /**
     * 首页
     */
    //@PreAuthorize("@ps.hasPermit('system:logininfo:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询系统访问记录列表
     */
    //@PreAuthorize("@ps.hasPermit('system:logininfo:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        int count = sysLogininfoService.getCountByCondition(pRequest.getCondition());
        List<SysLogininfo> list = sysLogininfoService.getRecordsByPaging(pRequest.getPageIndex(), pRequest.getPageSize(), pRequest.getCondition(), "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增系统访问记录
     */
    //@PreAuthorize("@ps.hasPermit('system:logininfo:insert')")
    @Oplog(title = "系统访问记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody SysLogininfo sysLogininfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        sysLogininfo.setLoginNo(UuidUtils.shortUUID());
        sysLogininfo.setCreateBy(loginUser.getUser().getUserNo());
        sysLogininfo.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(sysLogininfoService.AddNewRecord(sysLogininfo));
    }

    /**
     * 编辑系统访问记录
     */
    //@PreAuthorize("@ps.hasPermit('system:logininfo:update')")
    @Oplog(title = "系统访问记录", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody SysLogininfo sysLogininfo) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        sysLogininfo.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(sysLogininfoService.UpdateRecord(sysLogininfo));
        }

    /**
     * 保存系统访问记录
     */
    //@PreAuthorize("@ps.hasPermit('system:logininfo:save')")
    @Oplog(title = "系统访问记录", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody SysLogininfo sysLogininfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(sysLogininfoService.getRecordByNo(sysLogininfo.getLoginNo()))) {
            sysLogininfo.setLoginNo(UuidUtils.shortUUID());
            sysLogininfo.setCreateBy(loginUser.getUser().getUserNo());
            sysLogininfo.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(sysLogininfoService.AddNewRecord(sysLogininfo));
        } else {
            sysLogininfo.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(sysLogininfoService.UpdateRecord(sysLogininfo));
        }
    }

    /**
     * 删除系统访问记录
     */
    //@PreAuthorize("@ps.hasPermit('system:logininfo:delete')")
    @Oplog(title = "系统访问记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        return toAjax(sysLogininfoService.HardDeleteByNos(ids));
    }

    /**
     * 获取系统访问记录详细信息
     */
    //@PreAuthorize("@ps.hasPermit('system:logininfo:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        return AjaxResult.success(sysLogininfoService.getRecordByNo(id));
    }

    /**
     * 导出系统访问记录列表
     */
    //@PreAuthorize("@ps.hasPermit('system:logininfo:export')")
    @Oplog(title = "系统访问记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        int count = sysLogininfoService.getCountByCondition(pRequest.getCondition());

        List<SysLogininfo> list = sysLogininfoService.getRecordsByPaging(1,count,pRequest.getCondition(),"id","Asc");
        ExcelUtils<SysLogininfo> util = new ExcelUtils<SysLogininfo>(SysLogininfo.class);
        return util.exportExcel(list, "SysLogininfo");
    }

}
