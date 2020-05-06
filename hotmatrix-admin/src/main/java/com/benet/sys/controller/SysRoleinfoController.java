package com.benet.sys.controller;

import java.util.ArrayList;
import java.util.List;

import com.benet.common.core.pager.PageRequest;
import com.benet.common.utils.uuid.UuidUtils;
import com.benet.common.utils.web.ServletUtils;
import com.benet.framework.security.LoginUser;
import com.benet.framework.security.service.MyJwtokenService;
import com.benet.sys.vmodel.PermitInfoVo;
import com.benet.sys.vmodel.RolePermitVo;
import com.benet.sys.vmodel.RoleSusersVo;
import com.benet.system.domain.SysPermitinfo;
import com.benet.system.domain.SysSuserinfo;
import com.benet.system.service.ISysPermitinfoService;
import com.benet.system.service.ISysSuserinfoService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.benet.system.domain.SysRoleinfo;
import com.benet.system.service.ISysRoleinfoService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.core.pager.TableDataInfo;

/**
 * 角色信息Controller
 *
 * @author yoxking
 * @date 2020-04-20
 */
@RestController
@RequestMapping("/sys/roleinfo")
public class SysRoleinfoController extends BaseController {
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ISysSuserinfoService sysSuserinfoService;

    @Autowired
    private ISysRoleinfoService sysRoleinfoService;

    @Autowired
    private ISysPermitinfoService sysPermitinfoService;

    /**
     * 首页
     */
    //@PreAuthorize("@ps.hasPermit('system:roleinfo:index')")
    @GetMapping(value = "/index")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

    /**
     * 查询角色信息列表
     */
    //@PreAuthorize("@ps.hasPermit('system:roleinfo:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest) {
        int count = sysRoleinfoService.getCountByCondition(pRequest.getCondition());
        List<SysRoleinfo> list = sysRoleinfoService.getRecordsByPaging(pRequest.getPageIndex(), pRequest.getPageSize(), pRequest.getCondition(), "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增角色信息
     */
    //@PreAuthorize("@ps.hasPermit('system:roleinfo:insert')")
    @Oplog(title = "角色信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody SysRoleinfo sysRoleinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        sysRoleinfo.setRoleNo(UuidUtils.shortUUID());
        sysRoleinfo.setCreateBy(loginUser.getUser().getUserNo());
        sysRoleinfo.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(sysRoleinfoService.AddNewRecord(sysRoleinfo));
    }

    /**
     * 编辑角色信息
     */
    //@PreAuthorize("@ps.hasPermit('system:roleinfo:update')")
    @Oplog(title = "角色信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult update(@RequestBody SysRoleinfo sysRoleinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        sysRoleinfo.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(sysRoleinfoService.UpdateRecord(sysRoleinfo));
    }

    /**
     * 保存角色信息
     */
    //@PreAuthorize("@ps.hasPermit('system:roleinfo:save')")
    @Oplog(title = "角色信息", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody SysRoleinfo sysRoleinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(sysRoleinfoService.getRecordByNo(sysRoleinfo.getRoleNo()))) {
            sysRoleinfo.setRoleNo(UuidUtils.shortUUID());
            sysRoleinfo.setCreateBy(loginUser.getUser().getUserNo());
            sysRoleinfo.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(sysRoleinfoService.AddNewRecord(sysRoleinfo));
        } else {
            sysRoleinfo.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(sysRoleinfoService.UpdateRecord(sysRoleinfo));
        }
    }

    /**
     * 删除角色信息
     */
    //@PreAuthorize("@ps.hasPermit('system:roleinfo:delete')")
    @Oplog(title = "角色信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids) {
        return toAjax(sysRoleinfoService.SoftDeleteByNos(ids));
    }

    /**
     * 获取角色信息详细信息
     */
    //@PreAuthorize("@ps.hasPermit('system:roleinfo:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id) {
        return AjaxResult.success(sysRoleinfoService.getRecordByNo(id));
    }

    /**
     * 导出角色信息列表
     */
    //@PreAuthorize("@ps.hasPermit('system:roleinfo:export')")
    @Oplog(title = "角色信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest) {
        int count = sysRoleinfoService.getCountByCondition(pRequest.getCondition());

        List<SysRoleinfo> list = sysRoleinfoService.getRecordsByPaging(1, count, pRequest.getCondition(), "id", "Asc");
        ExcelUtils<SysRoleinfo> util = new ExcelUtils<SysRoleinfo>(SysRoleinfo.class);
        return util.exportExcel(list, "SysRoleinfo");
    }

    /**
     * 查询权限树形列表
     */
    //@PreAuthorize("@ps.hasPermit('system:permitinfo:tree')")
    @GetMapping(value = "/permittree/{roleNo}")
    public AjaxResult permittree(@PathVariable("roleNo") String roleNo) {

        AjaxResult result=AjaxResult.success();
        result.put("pmttreeData",buildPermitTree("0"));
        result.put("checkedKeys",getPermitKeys(roleNo));

        return AjaxResult.success(result);
    }

    private List<RolePermitVo> buildPermitTree(String parentNo) {

        List<RolePermitVo> permitTree = null;
        RolePermitVo permit = null;
        List<SysPermitinfo> infoList = sysPermitinfoService.getRecordsByClassNo(parentNo);

        if (infoList != null && infoList.size() > 0) {
            permitTree = new ArrayList<>();
            for (SysPermitinfo info : infoList) {
                permit = new RolePermitVo();
                permit.setKey(info.getPermitNo());
                permit.setTitle(info.getPermitName());
                permit.setDesc(info.getComments());
                permit.setChildren(buildPermitTree(info.getPermitNo()));
                permitTree.add(permit);
            }
        }
        return permitTree;
    }

    private List<String> getPermitKeys(String roleNo) {

        return sysRoleinfoService.getPermitNosByRoleNo(roleNo);
    }


    /**
     * 查询用户树形列表
     */
    //@PreAuthorize("@ps.hasPermit('system:permitinfo:tree')")
    @GetMapping(value = "/suersource/{roleNo}")
    public AjaxResult suersource(@PathVariable("roleNo") String roleNo) {

        AjaxResult result=AjaxResult.success();
        result.put("sourceData",buildSuersData());
        result.put("targetKeys",getSuserKeys(roleNo));

        return AjaxResult.success(result);
    }

    private List<RoleSusersVo> buildSuersData() {

        List<RoleSusersVo> susersData = null;
        RoleSusersVo ssuer = null;
        List<SysSuserinfo> infoList = sysSuserinfoService.getAllRecords();

        if (infoList != null && infoList.size() > 0) {
            susersData = new ArrayList<>();
            for (SysSuserinfo info : infoList) {
                ssuer = new RoleSusersVo();
                ssuer.setKey(info.getUserNo());
                ssuer.setTitle(info.getUserCnname());
                ssuer.setDesc(info.getComments());
                ssuer.setChosen(false);
                susersData.add(ssuer);
            }
        }
        return susersData;
    }

    private List<String> getSuserKeys(String roleNo) {

        return sysRoleinfoService.getSuserNosByRoleNo(roleNo);
    }

    /**
     * 保存角色权限信息
     */
    //@PreAuthorize("@ps.hasPermit('system:roleinfo:save')")
    @Oplog(title = "更新角色用户信息", businessType = BusinessType.UPDATE)
    @PostMapping(value = "/updateSusers")
    public AjaxResult updateSusers(@RequestParam("roleNo") String roleNo,@RequestParam("suserNos") String[] suserNos) {
        return toAjax(sysRoleinfoService.UpdateSusers(roleNo,suserNos));
    }

    /**
     * 保存角色权限信息
     */
    //@PreAuthorize("@ps.hasPermit('system:roleinfo:save')")
    @Oplog(title = "更新角色权限信息", businessType = BusinessType.UPDATE)
    @PostMapping(value = "/updatePermits")
    public AjaxResult updatePermits(@RequestParam("roleNo") String roleNo,@RequestParam("permitNos") String[] permitNos) {
        return toAjax(sysRoleinfoService.UpdatePermits(roleNo,permitNos));
    }

}
