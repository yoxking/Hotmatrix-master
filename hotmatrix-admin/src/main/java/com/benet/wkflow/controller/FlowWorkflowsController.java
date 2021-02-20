package com.benet.wkflow.controller;

import java.util.List;
import com.benet.common.core.pager.PageRequest;
import com.benet.common.core.pager.TableDataInfo;
import com.benet.common.utils.uuid.UuidUtils;
import com.benet.common.utils.web.ServletUtils;
import com.benet.framework.security.LoginUser;
import com.benet.framework.security.service.MyJwtokenService;
import com.benet.wkflow.domain.FlowWorkflows;
import com.benet.wkflow.service.IFlowWorkflowsService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
 * 工作流Controller
 * 
 * @author yoxking
 * @date 2020-05-17
 */
@Api(value = "wkflow/workflows", tags = "工作流控制器")
@RestController
@RequestMapping("/wkflow/workflows")
public class FlowWorkflowsController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private IFlowWorkflowsService flowWorkflowsService;
    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('wkflow:workflows:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询工作流列表
     */
    @PreAuthorize("@ps.hasPermit('wkflow:workflows:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = flowWorkflowsService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());
        List<FlowWorkflows> list = flowWorkflowsService.getRecordsByPaging(loginUser.getUser().getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), pRequest.getCondition(), "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增工作流
     */
    @PreAuthorize("@ps.hasPermit('wkflow:workflows:addnew')")
    @Oplog(title = "工作流", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody FlowWorkflows flwWorkflow) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        flwWorkflow.setFlowNo(UuidUtils.shortUUID());
        flwWorkflow.setCreateBy(loginUser.getUser().getUserNo());
        flwWorkflow.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(flowWorkflowsService.AddNewRecord(loginUser.getUser().getAppCode(),flwWorkflow));
    }

    /**
     * 编辑工作流
     */
    @PreAuthorize("@ps.hasPermit('wkflow:workflows:update')")
    @Oplog(title = "工作流", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody FlowWorkflows flwWorkflow) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        flwWorkflow.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(flowWorkflowsService.UpdateRecord(loginUser.getUser().getAppCode(),flwWorkflow));
        }

    /**
     * 保存工作流
     */
    @PreAuthorize("@ps.hasPermit('wkflow:workflows:save')")
    @Oplog(title = "工作流", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody FlowWorkflows flwWorkflow) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(flowWorkflowsService.getRecordByNo(loginUser.getUser().getAppCode(),flwWorkflow.getFlowNo()))) {
            flwWorkflow.setFlowNo(UuidUtils.shortUUID());
            flwWorkflow.setCreateBy(loginUser.getUser().getUserNo());
            flwWorkflow.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(flowWorkflowsService.AddNewRecord(loginUser.getUser().getAppCode(),flwWorkflow));
        } else {
            flwWorkflow.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(flowWorkflowsService.UpdateRecord(loginUser.getUser().getAppCode(),flwWorkflow));
        }
    }

    /**
     * 删除工作流
     */
    @PreAuthorize("@ps.hasPermit('wkflow:workflows:delete')")
    @Oplog(title = "工作流", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(flowWorkflowsService.SoftDeleteByNos(loginUser.getUser().getAppCode(),ids));
    }

    /**
     * 获取工作流详细信息
     */
    @PreAuthorize("@ps.hasPermit('wkflow:workflows:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(flowWorkflowsService.getRecordByNo(loginUser.getUser().getAppCode(),id));
    }

    /**
     * 导出工作流列表
     */
    @PreAuthorize("@ps.hasPermit('wkflow:workflows:export')")
    @Oplog(title = "工作流", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = flowWorkflowsService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());

        List<FlowWorkflows> list = flowWorkflowsService.getRecordsByPaging(loginUser.getUser().getAppCode(),1,count,pRequest.getCondition(),"id","Asc");
        ExcelUtils<FlowWorkflows> util = new ExcelUtils<FlowWorkflows>(FlowWorkflows.class);
        return util.exportExcel(list, "FlwWorkflow");
    }

}
