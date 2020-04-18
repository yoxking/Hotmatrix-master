package com.benet.sys.controller;

import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.core.pager.PageRequest;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.core.pager.TableDataInfo;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.uuid.UuidUtils;
import com.benet.system.domain.SysDepartment;
import com.benet.system.service.ISysDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 部门信息Controller
 *
 * @author yoxking
 * @date 2020-03-29
 */
@RestController
@RequestMapping("/sys/deptment")
public class SysDeptmentController extends BaseController {
    @Autowired
    private ISysDepartmentService sysDepartmentService;

    /**
     * 首页
     */
    //@PreAuthorize("@ps.hasPermit('system:department:index')")
    @GetMapping(value = "/index")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

    /**
     * 查询部门信息列表
     */
    //@PreAuthorize("@ps.hasPermit('system:department:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest) {
        int count = sysDepartmentService.getCountByCondition(pRequest.getCondition());
        List<SysDepartment> list = sysDepartmentService.getRecordsByPaging(pRequest.getPageIndex(), pRequest.getPageSize(), pRequest.getCondition(), "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增部门信息
     */
    //@PreAuthorize("@ps.hasPermit('system:department:add')")
    @PostMapping
    public AjaxResult add(@RequestBody SysDepartment sysDepartment) {
        sysDepartment.setDeptNo(UuidUtils.shortUUID());
        return toAjax(sysDepartmentService.AddNewRecord(sysDepartment));
    }

    /**
     * 编辑部门信息
     */
    // @PreAuthorize("@ps.hasPermit('system:department:edit')")
    //@PostMapping(value = "/edit")
    @PutMapping
    public AjaxResult edit(@RequestBody SysDepartment sysDepartment) {
        return toAjax(sysDepartmentService.UpdateRecord(sysDepartment));
    }

    /**
     * 保存部门信息
     */
    //@PreAuthorize("@ps.hasPermit('system:department:save')")
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
    //@PreAuthorize("@ps.hasPermit('system:department:delete')")
    @Oplog(title = "部门信息", businessType = BusinessType.DELETE)
    //@GetMapping("/delete/{ids}")
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids) {
        return toAjax(sysDepartmentService.SoftDeleteByNos(ids));
    }

    /**
     * 获取部门信息详细信息
     */
    //@PreAuthorize("@ps.hasPermit('system:department:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id) {
        return AjaxResult.success(sysDepartmentService.getRecordByNo(id));
    }

    /**
     * 导出部门信息列表
     */
    //@PreAuthorize("@ps.hasPermit('system:department:export')")
    @Oplog(title = "部门信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export/{condition}")
    public AjaxResult export(@PathVariable("condition") String condition) {
        PagingModel model = new PagingModel();
        model.setPageIndex(1);
        model.setPageSize(1000);
        model.setOrderField("id");
        model.setOrderType("Asc");
        model.setCondition(condition);

        List<SysDepartment> list = sysDepartmentService.getRecordsByPaging(model);
        ExcelUtils<SysDepartment> util = new ExcelUtils<SysDepartment>(SysDepartment.class);
        return util.exportExcel(list, "menu");
    }

}
