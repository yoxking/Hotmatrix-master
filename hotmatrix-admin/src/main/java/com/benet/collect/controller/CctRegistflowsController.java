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
import com.benet.collect.domain.CctRegistflows;
import com.benet.collect.service.ICctRegistflowsService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;

/**
 * 预约信息Controller
 * 
 * @author yoxking
 * @date 2020-10-14
 */
@Api(value = "collect/registflows", tags = "预约信息控制器")
@RestController
@RequestMapping("/collect/registflows")
public class CctRegistflowsController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ICctRegistflowsService cctRegistflowsService;
    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('collect:registflows:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询预约信息列表
     */
    @PreAuthorize("@ps.hasPermit('collect:registflows:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cctRegistflowsService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());
        List<CctRegistflows> list = cctRegistflowsService.getRecordsByPaging(loginUser.getUser().getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), pRequest.getCondition(), "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增预约信息
     */
    @PreAuthorize("@ps.hasPermit('collect:registflows:addnew')")
    @Oplog(title = "预约信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody CctRegistflows cctRegistflows) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        cctRegistflows.setRegistNo(UuidUtils.shortUUID());
        cctRegistflows.setCreateBy(loginUser.getUser().getUserNo());
        cctRegistflows.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(cctRegistflowsService.AddNewRecord(loginUser.getUser().getAppCode(),cctRegistflows));
    }

    /**
     * 编辑预约信息
     */
    @PreAuthorize("@ps.hasPermit('collect:registflows:update')")
    @Oplog(title = "预约信息", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody CctRegistflows cctRegistflows) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        cctRegistflows.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cctRegistflowsService.UpdateRecord(loginUser.getUser().getAppCode(),cctRegistflows));
        }

    /**
     * 保存预约信息
     */
    @PreAuthorize("@ps.hasPermit('collect:registflows:save')")
    @Oplog(title = "预约信息", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody CctRegistflows cctRegistflows) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(cctRegistflowsService.getRecordByNo(loginUser.getUser().getAppCode(),cctRegistflows.getRegistNo()))) {
            cctRegistflows.setRegistNo(UuidUtils.shortUUID());
            cctRegistflows.setCreateBy(loginUser.getUser().getUserNo());
            cctRegistflows.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cctRegistflowsService.AddNewRecord(loginUser.getUser().getAppCode(),cctRegistflows));
        } else {
            cctRegistflows.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cctRegistflowsService.UpdateRecord(loginUser.getUser().getAppCode(),cctRegistflows));
        }
    }

    /**
     * 删除预约信息
     */
    @PreAuthorize("@ps.hasPermit('collect:registflows:delete')")
    @Oplog(title = "预约信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(cctRegistflowsService.SoftDeleteByNos(loginUser.getUser().getAppCode(),ids));
    }

    /**
     * 获取预约信息详细信息
     */
    @PreAuthorize("@ps.hasPermit('collect:registflows:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(cctRegistflowsService.getRecordByNo(loginUser.getUser().getAppCode(),id));
    }

    /**
     * 导出预约信息列表
     */
    @PreAuthorize("@ps.hasPermit('collect:registflows:export')")
    @Oplog(title = "预约信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cctRegistflowsService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());

        List<CctRegistflows> list = cctRegistflowsService.getRecordsByPaging(loginUser.getUser().getAppCode(),1,count,pRequest.getCondition(),"id","Asc");
        ExcelUtils<CctRegistflows> util = new ExcelUtils<CctRegistflows>(CctRegistflows.class);
        return util.exportExcel(list, "CctRegistflows");
    }

}
