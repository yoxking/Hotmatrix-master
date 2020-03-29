package com.benet.web.controller;

import java.util.List;

import com.benet.common.core.pager.PagingModel;
import com.github.pagehelper.Page;
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
import com.benet.system.domain.SysDepartment;
import com.benet.system.service.ISysDepartmentService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.core.pager.TableDataInfo;

/**
 * 部门信息Controller
 * 
 * @author yoxking
 * @date 2020-03-29
 */
@RestController
@RequestMapping("/system/department")
public class SysDepartmentController extends BaseController
{
    @Autowired
    private ISysDepartmentService sysDepartmentService;
    /**
         * 首页
         */
    @PreAuthorize("@ps.hasPermit('system:department:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询部门信息列表
     */
    @PreAuthorize("@ps.hasPermit('system:department:list')")
    @GetMapping(value="/list")
    public TableDataInfo list()
    {
        startPage();
        List<SysDepartment> list = sysDepartmentService.getAllRecords();
        return getDataTable(list);
    }

    /**
     * 查询部门信息列表
     */
    @PreAuthorize("@ps.hasPermit('system:department:search')")
    @GetMapping(value="/search/{condition}")
    public TableDataInfo search(@PathVariable("condition") String condition)
    {

        List<SysDepartment> list = sysDepartmentService.getRecordsByPaging(1,10,"","","");
        return getDataTable(list);
    }

    /**
     * 新增部门信息
     */
    @PreAuthorize("@ps.hasPermit('system:department:add')")
    @GetMapping(value="/add")
    public AjaxResult add()
    {
        SysDepartment info=new SysDepartment();
        return AjaxResult.success(info);
    }

    /**
     * 编辑部门信息
     */
    @PreAuthorize("@ps.hasPermit('system:department:edit')")
    @GetMapping(value="/edit/{id}")
    public AjaxResult edit(@PathVariable("id") String id)
    {
        return AjaxResult.success(sysDepartmentService.getRecordByNo(id));
    }

    /**
     * 保存部门信息
     */
    @PreAuthorize("@ps.hasPermit('system:department:save')")
    @Oplog(title = "部门信息", businessType = BusinessType.UPDATE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody SysDepartment sysDepartment) {
        if (StringUtils.isNull(sysDepartmentService.getRecordByNo(sysDepartment.getDeptNo()))) {
            return toAjax(sysDepartmentService.AddNewRecord(sysDepartment));
        } else {
            return toAjax(sysDepartmentService.UpdateRecord(sysDepartment));
        }
    }

    /**
     * 删除部门信息
     */
    @PreAuthorize("@ps.hasPermit('system:department:delete')")
    @Oplog(title = "部门信息", businessType = BusinessType.DELETE)
    //@GetMapping("/delete/{ids}")
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        return toAjax(sysDepartmentService.SoftDeleteByNos(ids));
    }

    /**
     * 获取部门信息详细信息
     */
    @PreAuthorize("@ps.hasPermit('system:department:detail')")
    @GetMapping(value = "/detail/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        return AjaxResult.success(sysDepartmentService.getRecordByNo(id));
    }

    /**
     * 导出部门信息列表
     */
    @PreAuthorize("@ps.hasPermit('system:department:export')")
    @Oplog(title = "部门信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export/{condition}")
    public AjaxResult export(@PathVariable("condition") String condition)
    {
        PagingModel model=new PagingModel();

        List<SysDepartment> list = sysDepartmentService.getRecordsByPaging(model);
        ExcelUtils<SysDepartment> util = new ExcelUtils<SysDepartment>(SysDepartment.class);
        return util.exportExcel(list, "menu");
    }

}
