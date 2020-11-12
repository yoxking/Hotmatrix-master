package com.benet.collect.controller;

import java.util.List;
import com.benet.common.core.pager.PageRequest;
import com.benet.common.core.pager.TableDataInfo;
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
import com.benet.collect.domain.CctRegistinfo;
import com.benet.collect.service.ICctRegistinfoService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.core.pager.TableDataInfo;

/**
 * 预约信息Controller
 * 
 * @author yoxking
 * @date 2020-11-10
 */
@Api(value = "collect/registinfo", tags = "预约信息控制器")
@RestController
@RequestMapping("/collect/registinfo")
public class CctRegistinfoController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ICctRegistinfoService cctRegistinfoService;
    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('collect:registinfo:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询预约信息列表
     */
    @PreAuthorize("@ps.hasPermit('collect:registinfo:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cctRegistinfoService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());
        List<CctRegistinfo> list = cctRegistinfoService.getRecordsByPaging(loginUser.getUser().getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), pRequest.getCondition(), "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增预约信息
     */
    @PreAuthorize("@ps.hasPermit('collect:registinfo:addnew')")
    @Oplog(title = "预约信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody CctRegistinfo cctRegistinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        cctRegistinfo.setRegistNo(UuidUtils.shortUUID());
        cctRegistinfo.setCreateBy(loginUser.getUser().getUserNo());
        cctRegistinfo.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(cctRegistinfoService.AddNewRecord(loginUser.getUser().getAppCode(),cctRegistinfo));
    }

    /**
     * 编辑预约信息
     */
    @PreAuthorize("@ps.hasPermit('collect:registinfo:update')")
    @Oplog(title = "预约信息", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody CctRegistinfo cctRegistinfo) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        cctRegistinfo.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cctRegistinfoService.UpdateRecord(loginUser.getUser().getAppCode(),cctRegistinfo));
        }

    /**
     * 保存预约信息
     */
    @PreAuthorize("@ps.hasPermit('collect:registinfo:save')")
    @Oplog(title = "预约信息", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody CctRegistinfo cctRegistinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(cctRegistinfoService.getRecordByNo(loginUser.getUser().getAppCode(),cctRegistinfo.getRegistNo()))) {
            cctRegistinfo.setRegistNo(UuidUtils.shortUUID());
            cctRegistinfo.setCreateBy(loginUser.getUser().getUserNo());
            cctRegistinfo.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cctRegistinfoService.AddNewRecord(loginUser.getUser().getAppCode(),cctRegistinfo));
        } else {
            cctRegistinfo.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cctRegistinfoService.UpdateRecord(loginUser.getUser().getAppCode(),cctRegistinfo));
        }
    }

    /**
     * 删除预约信息
     */
    @PreAuthorize("@ps.hasPermit('collect:registinfo:delete')")
    @Oplog(title = "预约信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(cctRegistinfoService.SoftDeleteByNos(loginUser.getUser().getAppCode(),ids));
    }

    /**
     * 获取预约信息详细信息
     */
    @PreAuthorize("@ps.hasPermit('collect:registinfo:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(cctRegistinfoService.getRecordByNo(loginUser.getUser().getAppCode(),id));
    }

    /**
     * 导出预约信息列表
     */
    @PreAuthorize("@ps.hasPermit('collect:registinfo:export')")
    @Oplog(title = "预约信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cctRegistinfoService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());

        List<CctRegistinfo> list = cctRegistinfoService.getRecordsByPaging(loginUser.getUser().getAppCode(),1,count,pRequest.getCondition(),"id","Asc");
        ExcelUtils<CctRegistinfo> util = new ExcelUtils<CctRegistinfo>(CctRegistinfo.class);
        return util.exportExcel(list, "CctRegistinfo");
    }

}
