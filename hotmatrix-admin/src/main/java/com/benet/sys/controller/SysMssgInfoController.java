package com.benet.sys.controller;

import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.core.pager.TableDataInfo;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;
import com.benet.system.domain.SysMessageinfo;
import com.benet.system.service.ISysMessageinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 消息信息Controller
 *
 * @author yoxking
 * @date 2020-03-29
 */
@RestController
@RequestMapping("/sys/mssginfo")
public class SysMssgInfoController extends BaseController
{
    @Autowired
    private ISysMessageinfoService sysMessageinfoService;
    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('system:messageinfo:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询消息信息列表
     */
    @PreAuthorize("@ps.hasPermit('system:messageinfo:list')")
    @GetMapping(value="/list")
    public TableDataInfo list()
    {
        List<SysMessageinfo> list = sysMessageinfoService.getAllRecords();
        return getDataTable(list);
    }

    /**
     * 查询消息信息列表
     */
    @PreAuthorize("@ps.hasPermit('system:messageinfo:search')")
    @GetMapping(value="/search/{condition}")
    public TableDataInfo search(@PathVariable("condition") String condition)
    {
        List<SysMessageinfo> list = sysMessageinfoService.getRecordsByPaging(1,10,condition,"id","asc");
        return getDataTable(list);
    }

    /**
     * 新增消息信息
     */
    @PreAuthorize("@ps.hasPermit('system:messageinfo:add')")
    @GetMapping(value="/add")
    public AjaxResult add()
    {
        SysMessageinfo info=new SysMessageinfo();
        return AjaxResult.success(info);
    }

    /**
     * 编辑消息信息
     */
    @PreAuthorize("@ps.hasPermit('system:messageinfo:edit')")
    @GetMapping(value="/edit/{id}")
    public AjaxResult edit(@PathVariable("id") String id)
    {
        return AjaxResult.success(sysMessageinfoService.getRecordByNo(id));
    }

    /**
     * 保存消息信息
     */
    @PreAuthorize("@ps.hasPermit('system:messageinfo:save')")
    @Oplog(title = "消息信息", businessType = BusinessType.UPDATE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody SysMessageinfo sysMessageinfo) {
        if (StringUtils.isNull(sysMessageinfoService.getRecordByNo(sysMessageinfo.getMssgNo()))) {
            return toAjax(sysMessageinfoService.AddNewRecord(sysMessageinfo));
        } else {
            return toAjax(sysMessageinfoService.UpdateRecord(sysMessageinfo));
        }
    }

    /**
     * 删除消息信息
     */
    @PreAuthorize("@ps.hasPermit('system:messageinfo:delete')")
    @Oplog(title = "消息信息", businessType = BusinessType.DELETE)
    //@GetMapping("/delete/{ids}")
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        return toAjax(sysMessageinfoService.SoftDeleteByNos(ids));
    }

    /**
     * 获取消息信息详细信息
     */
    @PreAuthorize("@ps.hasPermit('system:messageinfo:detail')")
    @GetMapping(value = "/detail/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        return AjaxResult.success(sysMessageinfoService.getRecordByNo(id));
    }

    /**
     * 导出消息信息列表
     */
    @PreAuthorize("@ps.hasPermit('system:messageinfo:export')")
    @Oplog(title = "消息信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export/{condition}")
    public AjaxResult export(@PathVariable("condition") String condition)
    {
        List<SysMessageinfo> list = sysMessageinfoService.getRecordsByPaging(1,10,condition,"id","asc");
        ExcelUtils<SysMessageinfo> util = new ExcelUtils<SysMessageinfo>(SysMessageinfo.class);
        return util.exportExcel(list, "menu");
    }

}
