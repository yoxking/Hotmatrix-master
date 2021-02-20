package com.benet.collect.controller;

import java.util.List;

import com.benet.collect.domain.CoctSalonflows;
import com.benet.collect.service.ICoctSalonflowsService;
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
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;

/**
 * 活动过程Controller
 * 
 * @author yoxking
 * @date 2020-10-14
 */
@Api(value = "collect/salonflows", tags = "活动过程控制器")
@RestController
@RequestMapping("/collect/salonflows")
public class CoctSalonflowsController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ICoctSalonflowsService coctSalonflowsService;
    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('collect:salonflows:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询活动过程列表
     */
    @PreAuthorize("@ps.hasPermit('collect:salonflows:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = coctSalonflowsService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());
        List<CoctSalonflows> list = coctSalonflowsService.getRecordsByPaging(loginUser.getUser().getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), pRequest.getCondition(), "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增活动过程
     */
    @PreAuthorize("@ps.hasPermit('collect:salonflows:addnew')")
    @Oplog(title = "活动过程", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody CoctSalonflows coctSalonflows) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        coctSalonflows.setSflowNo(UuidUtils.shortUUID());
        coctSalonflows.setCreateBy(loginUser.getUser().getUserNo());
        coctSalonflows.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(coctSalonflowsService.AddNewRecord(loginUser.getUser().getAppCode(),coctSalonflows));
    }

    /**
     * 编辑活动过程
     */
    @PreAuthorize("@ps.hasPermit('collect:salonflows:update')")
    @Oplog(title = "活动过程", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody CoctSalonflows coctSalonflows) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        coctSalonflows.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(coctSalonflowsService.UpdateRecord(loginUser.getUser().getAppCode(),coctSalonflows));
        }

    /**
     * 保存活动过程
     */
    @PreAuthorize("@ps.hasPermit('collect:salonflows:save')")
    @Oplog(title = "活动过程", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody CoctSalonflows coctSalonflows) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(coctSalonflowsService.getRecordByNo(loginUser.getUser().getAppCode(),coctSalonflows.getSflowNo()))) {
            coctSalonflows.setSflowNo(UuidUtils.shortUUID());
            coctSalonflows.setCreateBy(loginUser.getUser().getUserNo());
            coctSalonflows.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(coctSalonflowsService.AddNewRecord(loginUser.getUser().getAppCode(),coctSalonflows));
        } else {
            coctSalonflows.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(coctSalonflowsService.UpdateRecord(loginUser.getUser().getAppCode(),coctSalonflows));
        }
    }

    /**
     * 删除活动过程
     */
    @PreAuthorize("@ps.hasPermit('collect:salonflows:delete')")
    @Oplog(title = "活动过程", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(coctSalonflowsService.SoftDeleteByNos(loginUser.getUser().getAppCode(),ids));
    }

    /**
     * 获取活动过程详细信息
     */
    @PreAuthorize("@ps.hasPermit('collect:salonflows:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(coctSalonflowsService.getRecordByNo(loginUser.getUser().getAppCode(),id));
    }

    /**
     * 导出活动过程列表
     */
    @PreAuthorize("@ps.hasPermit('collect:salonflows:export')")
    @Oplog(title = "活动过程", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = coctSalonflowsService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());

        List<CoctSalonflows> list = coctSalonflowsService.getRecordsByPaging(loginUser.getUser().getAppCode(),1,count,pRequest.getCondition(),"id","Asc");
        ExcelUtils<CoctSalonflows> util = new ExcelUtils<CoctSalonflows>(CoctSalonflows.class);
        return util.exportExcel(list, "CoctSalonflows");
    }

}
