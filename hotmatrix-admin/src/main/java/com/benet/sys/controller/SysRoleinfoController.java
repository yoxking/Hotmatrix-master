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
public class SysRoleinfoController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ISysRoleinfoService sysRoleinfoService;
    /**
     * 首页
     */
    //@PreAuthorize("@ps.hasPermit('system:roleinfo:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询角色信息列表
     */
    //@PreAuthorize("@ps.hasPermit('system:roleinfo:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
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
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        return toAjax(sysRoleinfoService.SoftDeleteByNos(ids));
    }

    /**
     * 获取角色信息详细信息
     */
    //@PreAuthorize("@ps.hasPermit('system:roleinfo:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        return AjaxResult.success(sysRoleinfoService.getRecordByNo(id));
    }

    /**
     * 导出角色信息列表
     */
    //@PreAuthorize("@ps.hasPermit('system:roleinfo:export')")
    @Oplog(title = "角色信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        int count = sysRoleinfoService.getCountByCondition(pRequest.getCondition());

        List<SysRoleinfo> list = sysRoleinfoService.getRecordsByPaging(1,count,pRequest.getCondition(),"id","Asc");
        ExcelUtils<SysRoleinfo> util = new ExcelUtils<SysRoleinfo>(SysRoleinfo.class);
        return util.exportExcel(list, "SysRoleinfo");
    }

}
