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
import com.benet.system.domain.SysAppclass;
import com.benet.system.service.ISysAppclassService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.core.pager.TableDataInfo;

/**
 * 应用类型Controller
 * 
 * @author yoxking
 * @date 2020-04-20
 */
@RestController
@RequestMapping("/sys/appclass")
public class SysAppclassController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ISysAppclassService sysAppclassService;
    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('system:appclass:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询应用类型列表
     */
    //@PreAuthorize("@ps.hasPermit('system:appclass:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        int count = sysAppclassService.getCountByCondition(pRequest.getCondition());
        List<SysAppclass> list = sysAppclassService.getRecordsByPaging(pRequest.getPageIndex(), pRequest.getPageSize(), pRequest.getCondition(), "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增应用类型
     */
    //@PreAuthorize("@ps.hasPermit('system:appclass:insert')")
    @Oplog(title = "应用类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody SysAppclass sysAppclass) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        sysAppclass.setClassNo(UuidUtils.shortUUID());
        sysAppclass.setCreateBy(loginUser.getUser().getUserNo());
        sysAppclass.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(sysAppclassService.AddNewRecord(sysAppclass));
    }

    /**
     * 编辑应用类型
     */
    //@PreAuthorize("@ps.hasPermit('system:appclass:update')")
    @Oplog(title = "应用类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult update(@RequestBody SysAppclass sysAppclass) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        sysAppclass.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(sysAppclassService.UpdateRecord(sysAppclass));
    }

    /**
     * 保存应用类型
     */
    @PreAuthorize("@ps.hasPermit('system:appclass:save')")
    @Oplog(title = "应用类型", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody SysAppclass sysAppclass) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(sysAppclassService.getRecordByNo(sysAppclass.getClassNo()))) {
            sysAppclass.setClassNo(UuidUtils.shortUUID());
            sysAppclass.setCreateBy(loginUser.getUser().getUserNo());
            sysAppclass.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(sysAppclassService.AddNewRecord(sysAppclass));
        } else {
            sysAppclass.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(sysAppclassService.UpdateRecord(sysAppclass));
        }
    }

    /**
     * 删除应用类型
     */
    //@PreAuthorize("@ps.hasPermit('system:appclass:delete')")
    @Oplog(title = "应用类型", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        return toAjax(sysAppclassService.SoftDeleteByNos(ids));
    }

    /**
     * 获取应用类型详细信息
     */
    //@PreAuthorize("@ps.hasPermit('system:appclass:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        return AjaxResult.success(sysAppclassService.getRecordByNo(id));
    }

    /**
     * 导出应用类型列表
     */
    //@PreAuthorize("@ps.hasPermit('system:appclass:export')")
    @Oplog(title = "应用类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        int count = sysAppclassService.getCountByCondition(pRequest.getCondition());

        List<SysAppclass> list = sysAppclassService.getRecordsByPaging(1,count,pRequest.getCondition(),"id","Asc");
        ExcelUtils<SysAppclass> util = new ExcelUtils<SysAppclass>(SysAppclass.class);
        return util.exportExcel(list, "SysAppclass");
    }

}
