package com.benet.system.controller;

import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.core.pager.PageRequest;
import com.benet.common.core.pager.TableDataInfo;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.uuid.UuidUtils;
import com.benet.common.utils.web.ServletUtils;
import com.benet.framework.security.LoginUser;
import com.benet.framework.security.service.MyJwtokenService;
import com.benet.system.vmodel.DeptmentVo;
import com.benet.system.domain.SysDepartment;
import com.benet.system.service.ISysDepartmentService;
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
@RequestMapping("/system/department")
public class SysDepartmentController extends BaseController {

    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ISysDepartmentService sysDepartmentService;

    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('system:department:index')")
    @GetMapping(value = "/index")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

    /**
     * 查询部门信息列表
     */
    @PreAuthorize("@ps.hasPermit('system:department:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = sysDepartmentService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());
        List<SysDepartment> list = sysDepartmentService.getRecordsByPaging(loginUser.getUser().getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), pRequest.getCondition(), "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 查询部门信息树形列表
     */
    @PreAuthorize("@ps.hasPermit('system:department:list')")
    @GetMapping(value = "/tree")
    public TableDataInfo tree() {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = sysDepartmentService.getCountByCondition(loginUser.getUser().getAppCode(),"");
        List<DeptmentVo> list = buildDeptTree(loginUser.getUser().getAppCode(),"0");
        return getDataTable(list, count);
    }

    private List<DeptmentVo> buildDeptTree(String appCode,String parentNo) {

        List<DeptmentVo> deptTree = null;
        DeptmentVo dept = null;
        List<SysDepartment> infoList = sysDepartmentService.getRecordsByClassNo(appCode,parentNo);

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
                dept.setChildren(buildDeptTree(appCode,info.getDeptNo()));
                deptTree.add(dept);
            }
        }
        return deptTree;
    }

    /**
     * 新增部门信息
     */
    @PreAuthorize("@ps.hasPermit('system:department:addnew')")
    @Oplog(title = "部门信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody SysDepartment sysDepartment) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        sysDepartment.setDeptNo(UuidUtils.shortUUID());
        sysDepartment.setCreateBy(loginUser.getUser().getUserNo());
        sysDepartment.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(sysDepartmentService.AddNewRecord(loginUser.getUser().getAppCode(),sysDepartment));
    }

    /**
     * 编辑部门信息
     */
    @PreAuthorize("@ps.hasPermit('system:department:update')")
    @Oplog(title = "部门信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult update(@RequestBody SysDepartment sysDepartment) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        sysDepartment.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(sysDepartmentService.UpdateRecord(loginUser.getUser().getAppCode(),sysDepartment));
    }

    /**
     * 保存部门信息
     */
    @PreAuthorize("@ps.hasPermit('system:department:save')")
    @Oplog(title = "部门信息", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody SysDepartment sysDepartment) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(sysDepartmentService.getRecordByNo(loginUser.getUser().getAppCode(),sysDepartment.getDeptNo()))) {
            sysDepartment.setDeptNo(UuidUtils.shortUUID());
            sysDepartment.setCreateBy(loginUser.getUser().getUserNo());
            sysDepartment.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(sysDepartmentService.AddNewRecord(loginUser.getUser().getAppCode(),sysDepartment));
        } else {
            sysDepartment.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(sysDepartmentService.UpdateRecord(loginUser.getUser().getAppCode(),sysDepartment));
        }
    }

    /**
     * 删除部门信息
     */
    @PreAuthorize("@ps.hasPermit('system:department:delete')")
    @Oplog(title = "部门信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(sysDepartmentService.SoftDeleteByNos(loginUser.getUser().getAppCode(),ids));
    }

    /**
     * 获取部门信息详细信息
     */
    @PreAuthorize("@ps.hasPermit('system:department:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(sysDepartmentService.getRecordByNo(loginUser.getUser().getAppCode(),id));
    }

    /**
     * 导出部门信息列表
     */
    @PreAuthorize("@ps.hasPermit('system:department:export')")
    @Oplog(title = "部门信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest) {

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = sysDepartmentService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());

        List<SysDepartment> list = sysDepartmentService.getRecordsByPaging(loginUser.getUser().getAppCode(),1,count,pRequest.getCondition(),"id","Asc");
        ExcelUtils<SysDepartment> util = new ExcelUtils<SysDepartment>(SysDepartment.class);
        return util.exportExcel(list, "department");
    }

}
