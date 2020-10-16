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
import com.benet.collect.domain.CctSalonflows;
import com.benet.collect.service.ICctSalonflowsService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.core.pager.TableDataInfo;

/**
 * 活动过程Controller
 * 
 * @author yoxking
 * @date 2020-10-14
 */
@Api(value = "collect/salonflows", tags = "活动过程控制器")
@RestController
@RequestMapping("/collect/salonflows")
public class CctSalonflowsController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ICctSalonflowsService cctSalonflowsService;
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
        int count = cctSalonflowsService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());
        List<CctSalonflows> list = cctSalonflowsService.getRecordsByPaging(loginUser.getUser().getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), pRequest.getCondition(), "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增活动过程
     */
    @PreAuthorize("@ps.hasPermit('collect:salonflows:addnew')")
    @Oplog(title = "活动过程", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody CctSalonflows cctSalonflows) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        cctSalonflows.setSflowNo(UuidUtils.shortUUID());
        cctSalonflows.setCreateBy(loginUser.getUser().getUserNo());
        cctSalonflows.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(cctSalonflowsService.AddNewRecord(loginUser.getUser().getAppCode(),cctSalonflows));
    }

    /**
     * 编辑活动过程
     */
    @PreAuthorize("@ps.hasPermit('collect:salonflows:update')")
    @Oplog(title = "活动过程", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody CctSalonflows cctSalonflows) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        cctSalonflows.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cctSalonflowsService.UpdateRecord(loginUser.getUser().getAppCode(),cctSalonflows));
        }

    /**
     * 保存活动过程
     */
    @PreAuthorize("@ps.hasPermit('collect:salonflows:save')")
    @Oplog(title = "活动过程", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody CctSalonflows cctSalonflows) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(cctSalonflowsService.getRecordByNo(loginUser.getUser().getAppCode(),cctSalonflows.getSflowNo()))) {
            cctSalonflows.setSflowNo(UuidUtils.shortUUID());
            cctSalonflows.setCreateBy(loginUser.getUser().getUserNo());
            cctSalonflows.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cctSalonflowsService.AddNewRecord(loginUser.getUser().getAppCode(),cctSalonflows));
        } else {
            cctSalonflows.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cctSalonflowsService.UpdateRecord(loginUser.getUser().getAppCode(),cctSalonflows));
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
        return toAjax(cctSalonflowsService.SoftDeleteByNos(loginUser.getUser().getAppCode(),ids));
    }

    /**
     * 获取活动过程详细信息
     */
    @PreAuthorize("@ps.hasPermit('collect:salonflows:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(cctSalonflowsService.getRecordByNo(loginUser.getUser().getAppCode(),id));
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
        int count = cctSalonflowsService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());

        List<CctSalonflows> list = cctSalonflowsService.getRecordsByPaging(loginUser.getUser().getAppCode(),1,count,pRequest.getCondition(),"id","Asc");
        ExcelUtils<CctSalonflows> util = new ExcelUtils<CctSalonflows>(CctSalonflows.class);
        return util.exportExcel(list, "CctSalonflows");
    }

}
