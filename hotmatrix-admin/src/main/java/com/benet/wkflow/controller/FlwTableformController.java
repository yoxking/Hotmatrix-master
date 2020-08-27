package com.benet.wkflow.controller;

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
import com.benet.wkflow.domain.FlwTableform;
import com.benet.wkflow.service.IFlwTableformService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;

/**
 * 单设计Controller
 * 
 * @author yoxking
 * @date 2020-05-23
 */
@Api(value = "wkflow/tableform", tags = "表单设计控制器")
@RestController
@RequestMapping("/wkflow/tableform")
public class FlwTableformController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private IFlwTableformService flwTableformService;
    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('wkflow:tableform:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询表单设计列表
     */
    @PreAuthorize("@ps.hasPermit('wkflow:tableform:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = flwTableformService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());
        List<FlwTableform> list = flwTableformService.getRecordsByPaging(loginUser.getUser().getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), pRequest.getCondition(), "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增表单设计
     */
    @PreAuthorize("@ps.hasPermit('wkflow:tableform:addnew')")
    @Oplog(title = "表单设计", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody FlwTableform flwTableform) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        flwTableform.setFormNo(UuidUtils.shortUUID());
        flwTableform.setCreateBy(loginUser.getUser().getUserNo());
        flwTableform.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(flwTableformService.AddNewRecord(loginUser.getUser().getAppCode(),flwTableform));
    }

    /**
     * 编辑表单设计
     */
    @PreAuthorize("@ps.hasPermit('wkflow:tableform:update')")
    @Oplog(title = "单设计", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody FlwTableform flwTableform) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        flwTableform.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(flwTableformService.UpdateRecord(loginUser.getUser().getAppCode(),flwTableform));
        }

    /**
     * 保存表单设计
     */
    @PreAuthorize("@ps.hasPermit('wkflow:tableform:save')")
    @Oplog(title = "表单设计", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody FlwTableform flwTableform) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(flwTableformService.getRecordByNo(loginUser.getUser().getAppCode(),flwTableform.getFormNo()))) {
            flwTableform.setFormNo(UuidUtils.shortUUID());
            flwTableform.setCreateBy(loginUser.getUser().getUserNo());
            flwTableform.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(flwTableformService.AddNewRecord(loginUser.getUser().getAppCode(),flwTableform));
        } else {
            flwTableform.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(flwTableformService.UpdateRecord(loginUser.getUser().getAppCode(),flwTableform));
        }
    }

    /**
     * 删除表单设计
     */
    @PreAuthorize("@ps.hasPermit('wkflow:tableform:delete')")
    @Oplog(title = "表单设计", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(flwTableformService.SoftDeleteByNos(loginUser.getUser().getAppCode(),ids));
    }

    /**
     * 获取表单设计详细信息
     */
    @PreAuthorize("@ps.hasPermit('wkflow:tableform:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(flwTableformService.getRecordByNo(loginUser.getUser().getAppCode(),id));
    }

    /**
     * 导出表单设计列表
     */
    @PreAuthorize("@ps.hasPermit('wkflow:tableform:export')")
    @Oplog(title = "表单设计", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = flwTableformService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());

        List<FlwTableform> list = flwTableformService.getRecordsByPaging(loginUser.getUser().getAppCode(),1,count,pRequest.getCondition(),"id","Asc");
        ExcelUtils<FlwTableform> util = new ExcelUtils<FlwTableform>(FlwTableform.class);
        return util.exportExcel(list, "FlwTableform");
    }

}
