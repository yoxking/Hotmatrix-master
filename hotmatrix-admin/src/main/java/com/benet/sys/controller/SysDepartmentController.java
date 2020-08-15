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
import com.benet.common.utils.web.ServletUtils;
import com.benet.framework.security.LoginUser;
import com.benet.framework.security.service.MyJwtokenService;
import com.benet.sys.vmodel.DeptmentVo;
import com.benet.system.domain.SysDepartment;
import com.benet.system.service.ISysDepartmentService;
import com.benet.system.service.impl.SysDepartmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * 部门信息Controller
 *
 * @author yoxking
 * @date 2020-03-29
 */
@RestController
@RequestMapping("/sys/department")
public class SysDepartmentController extends BaseController {

    @Autowired
    private MyJwtokenService tokenService;

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
     * 查询部门信息树形列表
     */
    //@PreAuthorize("@ps.hasPermit('system:department:tree')")
    @GetMapping(value = "/tree")
    public TableDataInfo tree() {
        int count = sysDepartmentService.getCountByCondition("");
        List<DeptmentVo> list = buildDeptTree("0");
        return getDataTable(list, count);
    }

    private List<DeptmentVo> buildDeptTree(String parentNo) {

        List<DeptmentVo> deptTree = null;
        DeptmentVo dept = null;
        List<SysDepartment> infoList = sysDepartmentService.getRecordsByClassNo(parentNo);

        if (infoList != null && infoList.size() > 0) {
            deptTree = new ArrayList<>();
            for (SysDepartment info : infoList) {
                dept = new DeptmentVo();
                dept.setId(info.getDeptNo());
                dept.setKey(info.getDeptNo());
                dept.setTitle(info.getDeptName());
                dept.setValue(info.getDeptNo());
                dept.setDeptNo(info.getDeptNo());
                dept.setDeptName(info.getDeptName());
                dept.setParentNo(info.getParentNo());
                dept.setOrderNo(info.getOrderNo());
                dept.setLeader(info.getLeader());
                dept.setTelephone(info.getTelephone());
                dept.setEmail(info.getEmail());
                dept.setComments(info.getComments());
                dept.setChildren(buildDeptTree(info.getDeptNo()));
                deptTree.add(dept);
            }
        }
        return deptTree;
    }

    /**
     * 新增部门信息
     */
    //@PreAuthorize("@ps.hasPermit('system:department:insert')")
    //@Oplog(title = "部门信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody SysDepartment sysDepartment) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        sysDepartment.setDeptNo(UuidUtils.shortUUID());
        sysDepartment.setCreateBy(loginUser.getUser().getUserNo());
        sysDepartment.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(sysDepartmentService.AddNewRecord(sysDepartment));
    }

    /**
     * 编辑部门信息
     */
    // @PreAuthorize("@ps.hasPermit('system:department:update')")
    //@Oplog(title = "部门信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult update(@RequestBody SysDepartment sysDepartment) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        sysDepartment.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(sysDepartmentService.UpdateRecord(sysDepartment));
    }

    /**
     * 保存部门信息
     */
    //@PreAuthorize("@ps.hasPermit('system:department:save')")
    //@Oplog(title = "部门信息", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody SysDepartment sysDepartment) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(sysDepartmentService.getRecordByNo(sysDepartment.getDeptNo()))) {
            sysDepartment.setDeptNo(UuidUtils.shortUUID());
            sysDepartment.setCreateBy(loginUser.getUser().getUserNo());
            sysDepartment.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(sysDepartmentService.AddNewRecord(sysDepartment));
        } else {
            sysDepartment.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(sysDepartmentService.UpdateRecord(sysDepartment));
        }
    }

    /**
     * 删除部门信息
     */
    //@PreAuthorize("@ps.hasPermit('system:department:delete')")
    //@Oplog(title = "部门信息", businessType = BusinessType.DELETE)
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
    //@Oplog(title = "部门信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest) {

        int count = sysDepartmentService.getCountByCondition(pRequest.getCondition());

        List<SysDepartment> list = sysDepartmentService.getRecordsByPaging(1,count,pRequest.getCondition(),"id","Asc");
        ExcelUtils<SysDepartment> util = new ExcelUtils<SysDepartment>(SysDepartment.class);
        return util.exportExcel(list, "department");
    }

}
