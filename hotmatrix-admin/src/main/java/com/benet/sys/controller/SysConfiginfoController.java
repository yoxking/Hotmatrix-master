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
import com.benet.system.domain.SysConfiginfo;
import com.benet.system.service.ISysConfiginfoService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.core.pager.TableDataInfo;

/**
 * 参数配置Controller
 * 
 * @author yoxking
 * @date 2020-04-20
 */
@RestController
@RequestMapping("/sys/configinfo")
public class SysConfiginfoController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ISysConfiginfoService sysConfiginfoService;
    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('system:configinfo:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询参数配置列表
     */
    @PreAuthorize("@ps.hasPermit('system:configinfo:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = sysConfiginfoService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());
        List<SysConfiginfo> list = sysConfiginfoService.getRecordsByPaging(loginUser.getUser().getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), pRequest.getCondition(), "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增参数配置
     */
    @PreAuthorize("@ps.hasPermit('system:configinfo:addnew')")
    @Oplog(title = "参数配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody SysConfiginfo sysConfiginfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        sysConfiginfo.setConfigNo(UuidUtils.shortUUID());
        sysConfiginfo.setCreateBy(loginUser.getUser().getUserNo());
        sysConfiginfo.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(sysConfiginfoService.AddNewRecord(loginUser.getUser().getAppCode(),sysConfiginfo));
    }

    /**
     * 编辑参数配置
     */
    @PreAuthorize("@ps.hasPermit('system:configinfo:update')")
    @Oplog(title = "参数配置", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody SysConfiginfo sysConfiginfo) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        sysConfiginfo.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(sysConfiginfoService.UpdateRecord(loginUser.getUser().getAppCode(),sysConfiginfo));
        }

    /**
     * 保存参数配置
     */
    @PreAuthorize("@ps.hasPermit('system:configinfo:save')")
    @Oplog(title = "参数配置", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody SysConfiginfo sysConfiginfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(sysConfiginfoService.getRecordByNo(loginUser.getUser().getAppCode(),sysConfiginfo.getConfigNo()))) {
            sysConfiginfo.setConfigNo(UuidUtils.shortUUID());
            sysConfiginfo.setCreateBy(loginUser.getUser().getUserNo());
            sysConfiginfo.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(sysConfiginfoService.AddNewRecord(loginUser.getUser().getAppCode(),sysConfiginfo));
        } else {
            sysConfiginfo.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(sysConfiginfoService.UpdateRecord(loginUser.getUser().getAppCode(),sysConfiginfo));
        }
    }

    /**
     * 删除参数配置
     */
    @PreAuthorize("@ps.hasPermit('system:configinfo:delete')")
    @Oplog(title = "参数配置", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(sysConfiginfoService.SoftDeleteByNos(loginUser.getUser().getAppCode(),ids));
    }

    /**
     * 获取参数配置详细信息
     */
    @PreAuthorize("@ps.hasPermit('system:configinfo:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(sysConfiginfoService.getRecordByNo(loginUser.getUser().getAppCode(),id));
    }

    /**
     * 导出参数配置列表
     */
    @PreAuthorize("@ps.hasPermit('system:configinfo:export')")
    @Oplog(title = "参数配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = sysConfiginfoService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());

        List<SysConfiginfo> list = sysConfiginfoService.getRecordsByPaging(loginUser.getUser().getAppCode(),1,count,pRequest.getCondition(),"id","Asc");
        ExcelUtils<SysConfiginfo> util = new ExcelUtils<SysConfiginfo>(SysConfiginfo.class);
        return util.exportExcel(list, "SysConfiginfo");
    }

}
