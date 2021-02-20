package com.benet.wkflow.controller;

import java.util.List;
import com.benet.common.core.pager.PageRequest;
import com.benet.common.core.pager.TableDataInfo;
import com.benet.common.utils.uuid.UuidUtils;
import com.benet.common.utils.web.ServletUtils;
import com.benet.framework.security.LoginUser;
import com.benet.framework.security.service.MyJwtokenService;
import com.benet.wkflow.domain.FlowTableform;
import com.benet.wkflow.service.IFlowTableformService;
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
 * 单设计Controller
 * 
 * @author yoxking
 * @date 2020-05-23
 */
@Api(value = "wkflow/tableform", tags = "表单设计控制器")
@RestController
@RequestMapping("/wkflow/tableform")
public class FlowTableformController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private IFlowTableformService flowTableformService;
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
        int count = flowTableformService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());
        List<FlowTableform> list = flowTableformService.getRecordsByPaging(loginUser.getUser().getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), pRequest.getCondition(), "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增表单设计
     */
    @PreAuthorize("@ps.hasPermit('wkflow:tableform:addnew')")
    @Oplog(title = "表单设计", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody FlowTableform flowTableform) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        flowTableform.setFormNo(UuidUtils.shortUUID());
        flowTableform.setCreateBy(loginUser.getUser().getUserNo());
        flowTableform.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(flowTableformService.AddNewRecord(loginUser.getUser().getAppCode(),flowTableform));
    }

    /**
     * 编辑表单设计
     */
    @PreAuthorize("@ps.hasPermit('wkflow:tableform:update')")
    @Oplog(title = "单设计", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody FlowTableform flowTableform) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        flowTableform.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(flowTableformService.UpdateRecord(loginUser.getUser().getAppCode(),flowTableform));
        }

    /**
     * 保存表单设计
     */
    @PreAuthorize("@ps.hasPermit('wkflow:tableform:save')")
    @Oplog(title = "表单设计", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody FlowTableform flowTableform) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(flowTableformService.getRecordByNo(loginUser.getUser().getAppCode(),flowTableform.getFormNo()))) {
            flowTableform.setFormNo(UuidUtils.shortUUID());
            flowTableform.setCreateBy(loginUser.getUser().getUserNo());
            flowTableform.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(flowTableformService.AddNewRecord(loginUser.getUser().getAppCode(),flowTableform));
        } else {
            flowTableform.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(flowTableformService.UpdateRecord(loginUser.getUser().getAppCode(),flowTableform));
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
        return toAjax(flowTableformService.SoftDeleteByNos(loginUser.getUser().getAppCode(),ids));
    }

    /**
     * 获取表单设计详细信息
     */
    @PreAuthorize("@ps.hasPermit('wkflow:tableform:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(flowTableformService.getRecordByNo(loginUser.getUser().getAppCode(),id));
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
        int count = flowTableformService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());

        List<FlowTableform> list = flowTableformService.getRecordsByPaging(loginUser.getUser().getAppCode(),1,count,pRequest.getCondition(),"id","Asc");
        ExcelUtils<FlowTableform> util = new ExcelUtils<FlowTableform>(FlowTableform.class);
        return util.exportExcel(list, "FlowTableform");
    }

}
