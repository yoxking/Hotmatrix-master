package com.benet.sys.controller;

import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.core.pager.TableDataInfo;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;
import com.benet.system.domain.SysAppclass;
import com.benet.system.service.ISysAppclassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 应用类型Controller
 *
 * @author yoxking
 * @date 2020-03-29
 */
@RestController
@RequestMapping("/sys/appclass")
public class SysAppClassController  extends BaseController {

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
    @GetMapping(value="/list")
    public TableDataInfo list()
    {
        List<SysAppclass> list = sysAppclassService.getRecordsByPaging(0,10,"","id","asc");
        return getDataTable(list);
    }

    /**
     * 查询应用类型列表
     */
    //@PreAuthorize("@ps.hasPermit('system:appclass:search')")
    @GetMapping(value="/search/{condition}")
    public TableDataInfo search(@PathVariable("condition") String condition)
    {
        List<SysAppclass> list = sysAppclassService.getRecordsByPaging(1,10,condition,"id","asc");
        return getDataTable(list);
    }

    /**
     * 新增应用类型
     */
    //@PreAuthorize("@ps.hasPermit('system:appclass:add')")
    @GetMapping(value="/add")
    public AjaxResult add()
    {
        SysAppclass info=new SysAppclass();
        return AjaxResult.success(info);
    }

    /**
     * 编辑应用类型
     */
    //@PreAuthorize("@ps.hasPermit('system:appclass:edit')")
    @GetMapping(value="/edit/{id}")
    public AjaxResult edit(@PathVariable("id") String id)
    {
        return AjaxResult.success(sysAppclassService.getRecordByNo(id));
    }

    /**
     * 保存应用类型
     */
    //@PreAuthorize("@ps.hasPermit('system:appclass:save')")
    @Oplog(title = "应用类型", businessType = BusinessType.UPDATE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody SysAppclass sysAppclass) {
        if (StringUtils.isNull(sysAppclassService.getRecordByNo(sysAppclass.getClassNo()))) {
            return toAjax(sysAppclassService.AddNewRecord(sysAppclass));
        } else {
            return toAjax(sysAppclassService.UpdateRecord(sysAppclass));
        }
    }

    /**
     * 删除应用类型
     */
    //@PreAuthorize("@ps.hasPermit('system:appclass:delete')")
    @Oplog(title = "应用类型", businessType = BusinessType.DELETE)
    @GetMapping("/delete/{id}")
    //@DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("id") String id)
    {
        return toAjax(sysAppclassService.SoftDeleteByNos(new String[]{"2323233","344434"}));
    }

    /**
     * 获取应用类型详细信息
     */
    //@PreAuthorize("@ps.hasPermit('system:appclass:detail')")
    @GetMapping(value = "/detail/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        return AjaxResult.success(sysAppclassService.getRecordByNo(id));
    }

    /**
     * 导出应用类型列表
     */
    //@PreAuthorize("@ps.hasPermit('system:appclass:export')")
    @Oplog(title = "应用类型", businessType = BusinessType.EXPORT)
    @GetMapping("/export/{condition}")
    public AjaxResult export(@PathVariable("condition") String condition)
    {
        List<SysAppclass> list = sysAppclassService.getRecordsByPaging(1,10,condition,"id","asc");
        ExcelUtils<SysAppclass> util = new ExcelUtils<SysAppclass>(SysAppclass.class);
        return util.exportExcel(list, "menu");
    }
}
