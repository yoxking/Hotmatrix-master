package com.benet.wkflow.controller;

import java.util.List;
import com.benet.common.core.pager.PageRequest;
import com.benet.common.core.pager.TableDataInfo;
import com.benet.common.utils.uuid.UuidUtils;
import com.benet.common.utils.web.ServletUtils;
import com.benet.framework.security.LoginUser;
import com.benet.framework.security.service.MyJwtokenService;
import com.benet.wkflow.domain.FlowFlowentrust;
import com.benet.wkflow.service.IFlowFlowentrustService;
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
 * 流程委托Controller
 * 
 * @author yoxking
 * @date 2020-05-17
 */
@Api(value = "wkflow/flowentrust", tags = "流程委托控制器")
@RestController
@RequestMapping("/wkflow/flowentrust")
public class FlowFlowentrustController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private IFlowFlowentrustService flowFlowentrustService;
    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('wkflow:flowentrust:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询流程委托列表
     */
    @PreAuthorize("@ps.hasPermit('wkflow:flowentrust:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = flowFlowentrustService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());
        List<FlowFlowentrust> list = flowFlowentrustService.getRecordsByPaging(loginUser.getUser().getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), pRequest.getCondition(), "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增流程委托
     */
    @PreAuthorize("@ps.hasPermit('wkflow:flowentrust:addnew')")
    @Oplog(title = "流程委托", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody FlowFlowentrust flowFlowentrust) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        flowFlowentrust.setEntrustNo(UuidUtils.shortUUID());
        flowFlowentrust.setCreateBy(loginUser.getUser().getUserNo());
        flowFlowentrust.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(flowFlowentrustService.AddNewRecord(loginUser.getUser().getAppCode(),flowFlowentrust));
    }

    /**
     * 编辑流程委托
     */
    @PreAuthorize("@ps.hasPermit('wkflow:flowentrust:update')")
    @Oplog(title = "流程委托", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody FlowFlowentrust flowFlowentrust) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        flowFlowentrust.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(flowFlowentrustService.UpdateRecord(loginUser.getUser().getAppCode(),flowFlowentrust));
        }

    /**
     * 保存流程委托
     */
    @PreAuthorize("@ps.hasPermit('wkflow:flowentrust:save')")
    @Oplog(title = "流程委托", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody FlowFlowentrust flowFlowentrust) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(flowFlowentrustService.getRecordByNo(loginUser.getUser().getAppCode(),flowFlowentrust.getEntrustNo()))) {
            flowFlowentrust.setEntrustNo(UuidUtils.shortUUID());
            flowFlowentrust.setCreateBy(loginUser.getUser().getUserNo());
            flowFlowentrust.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(flowFlowentrustService.AddNewRecord(loginUser.getUser().getAppCode(),flowFlowentrust));
        } else {
            flowFlowentrust.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(flowFlowentrustService.UpdateRecord(loginUser.getUser().getAppCode(),flowFlowentrust));
        }
    }

    /**
     * 删除流程委托
     */
    @PreAuthorize("@ps.hasPermit('wkflow:flowentrust:delete')")
    @Oplog(title = "流程委托", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(flowFlowentrustService.SoftDeleteByNos(loginUser.getUser().getAppCode(),ids));
    }

    /**
     * 获取流程委托详细信息
     */
    @PreAuthorize("@ps.hasPermit('wkflow:flowentrust:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(flowFlowentrustService.getRecordByNo(loginUser.getUser().getAppCode(),id));
    }

    /**
     * 导出流程委托列表
     */
    @PreAuthorize("@ps.hasPermit('wkflow:flowentrust:export')")
    @Oplog(title = "流程委托", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = flowFlowentrustService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());

        List<FlowFlowentrust> list = flowFlowentrustService.getRecordsByPaging(loginUser.getUser().getAppCode(),1,count,pRequest.getCondition(),"id","Asc");
        ExcelUtils<FlowFlowentrust> util = new ExcelUtils<FlowFlowentrust>(FlowFlowentrust.class);
        return util.exportExcel(list, "FlowFlowentrust");
    }

}
