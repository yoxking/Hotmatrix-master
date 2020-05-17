package com.benet.sys.controller;

import java.util.List;

import com.benet.common.core.pager.PageRequest;
import com.benet.common.utils.uuid.UuidUtils;
import com.benet.common.utils.web.ServletUtils;
import com.benet.framework.security.LoginUser;
import com.benet.framework.security.service.MyJwtokenService;
import io.swagger.annotations.Api;
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
import com.benet.system.domain.SysRenteclass;
import com.benet.system.service.ISysRenteclassService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.core.pager.TableDataInfo;

/**
 * 租户类型Controller
 * 
 * @author yoxking
 * @date 2020-04-20
 */
@Api(value = "sys/renteclass", tags = "租户类型Controller")
@RestController
@RequestMapping("/sys/renteclass")
public class SysRenteclassController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ISysRenteclassService sysRenteclassService;
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
     * 查询租户类型列表
     */
    //@PreAuthorize("@ps.hasPermit('system:appclass:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        int count = sysRenteclassService.getCountByCondition(pRequest.getCondition());
        List<SysRenteclass> list = sysRenteclassService.getRecordsByPaging(pRequest.getPageIndex(), pRequest.getPageSize(), pRequest.getCondition(), "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增租户类型
     */
    //@PreAuthorize("@ps.hasPermit('system:appclass:insert')")
    @Oplog(title = "租户类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody SysRenteclass sysAppclass) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        sysAppclass.setClassNo(UuidUtils.shortUUID());
        sysAppclass.setCreateBy(loginUser.getUser().getUserNo());
        sysAppclass.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(sysRenteclassService.AddNewRecord(sysAppclass));
    }

    /**
     * 编辑租户类型
     */
    //@PreAuthorize("@ps.hasPermit('system:appclass:update')")
    @Oplog(title = "租户类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult update(@RequestBody SysRenteclass sysAppclass) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        sysAppclass.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(sysRenteclassService.UpdateRecord(sysAppclass));
    }

    /**
     * 保存租户类型
     */
    @PreAuthorize("@ps.hasPermit('system:appclass:save')")
    @Oplog(title = "租户类型", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody SysRenteclass sysAppclass) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(sysRenteclassService.getRecordByNo(sysAppclass.getClassNo()))) {
            sysAppclass.setClassNo(UuidUtils.shortUUID());
            sysAppclass.setCreateBy(loginUser.getUser().getUserNo());
            sysAppclass.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(sysRenteclassService.AddNewRecord(sysAppclass));
        } else {
            sysAppclass.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(sysRenteclassService.UpdateRecord(sysAppclass));
        }
    }

    /**
     * 删除租户类型
     */
    //@PreAuthorize("@ps.hasPermit('system:appclass:delete')")
    @Oplog(title = "租户类型", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        return toAjax(sysRenteclassService.SoftDeleteByNos(ids));
    }

    /**
     * 获取租户类型详细信息
     */
    //@PreAuthorize("@ps.hasPermit('system:appclass:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        return AjaxResult.success(sysRenteclassService.getRecordByNo(id));
    }

    /**
     * 导出租户类型列表
     */
    //@PreAuthorize("@ps.hasPermit('system:appclass:export')")
    @Oplog(title = "租户类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        int count = sysRenteclassService.getCountByCondition(pRequest.getCondition());

        List<SysRenteclass> list = sysRenteclassService.getRecordsByPaging(1,count,pRequest.getCondition(),"id","Asc");
        ExcelUtils<SysRenteclass> util = new ExcelUtils<SysRenteclass>(SysRenteclass.class);
        return util.exportExcel(list, "SysRenteclass");
    }

}
