package com.benet.wkflow.controller;

import java.util.List;
import com.benet.common.core.pager.PageRequest;
import com.benet.common.core.pager.TableDataInfo;
import com.benet.common.utils.uuid.UuidUtils;
import com.benet.common.utils.web.ServletUtils;
import com.benet.framework.security.LoginUser;
import com.benet.framework.security.service.MyJwtokenService;
import com.benet.wkflow.domain.FlowFlowbutton;
import com.benet.wkflow.service.IFlowFlowbuttonService;
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
 * 工作流程按钮Controller
 * 
 * @author yoxking
 * @date 2020-05-17
 */
@Api(value = "wkflow/flowbutton", tags = "工作流程按钮控制器")
@RestController
@RequestMapping("/wkflow/flowbutton")
public class FlowFlowbuttonController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private IFlowFlowbuttonService flowFlowbuttonService;
    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('wkflow:flowbutton:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询工作流程按钮列表
     */
    @PreAuthorize("@ps.hasPermit('wkflow:flowbutton:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = flowFlowbuttonService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());
        List<FlowFlowbutton> list = flowFlowbuttonService.getRecordsByPaging(loginUser.getUser().getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), pRequest.getCondition(), "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增工作流程按钮
     */
    @PreAuthorize("@ps.hasPermit('wkflow:flowbutton:addnew')")
    @Oplog(title = "工作流程按钮", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody FlowFlowbutton flowFlowbutton) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        flowFlowbutton.setBtnNo(UuidUtils.shortUUID());
        flowFlowbutton.setCreateBy(loginUser.getUser().getUserNo());
        flowFlowbutton.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(flowFlowbuttonService.AddNewRecord(loginUser.getUser().getAppCode(),flowFlowbutton));
    }

    /**
     * 编辑工作流程按钮
     */
    @PreAuthorize("@ps.hasPermit('wkflow:flowbutton:update')")
    @Oplog(title = "工作流程按钮", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody FlowFlowbutton flowFlowbutton) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        flowFlowbutton.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(flowFlowbuttonService.UpdateRecord(loginUser.getUser().getAppCode(),flowFlowbutton));
        }

    /**
     * 保存工作流程按钮
     */
    @PreAuthorize("@ps.hasPermit('wkflow:flowbutton:save')")
    @Oplog(title = "工作流程按钮", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody FlowFlowbutton flowFlowbutton) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(flowFlowbuttonService.getRecordByNo(loginUser.getUser().getAppCode(),flowFlowbutton.getBtnNo()))) {
            flowFlowbutton.setBtnNo(UuidUtils.shortUUID());
            flowFlowbutton.setCreateBy(loginUser.getUser().getUserNo());
            flowFlowbutton.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(flowFlowbuttonService.AddNewRecord(loginUser.getUser().getAppCode(),flowFlowbutton));
        } else {
            flowFlowbutton.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(flowFlowbuttonService.UpdateRecord(loginUser.getUser().getAppCode(),flowFlowbutton));
        }
    }

    /**
     * 删除工作流程按钮
     */
    @PreAuthorize("@ps.hasPermit('wkflow:flowbutton:delete')")
    @Oplog(title = "工作流程按钮", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(flowFlowbuttonService.SoftDeleteByNos(loginUser.getUser().getAppCode(),ids));
    }

    /**
     * 获取工作流程按钮详细信息
     */
    @PreAuthorize("@ps.hasPermit('wkflow:flowbutton:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(flowFlowbuttonService.getRecordByNo(loginUser.getUser().getAppCode(),id));
    }

    /**
     * 导出工作流程按钮列表
     */
    @PreAuthorize("@ps.hasPermit('wkflow:flowbutton:export')")
    @Oplog(title = "工作流程按钮", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = flowFlowbuttonService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());

        List<FlowFlowbutton> list = flowFlowbuttonService.getRecordsByPaging(loginUser.getUser().getAppCode(),1,count,pRequest.getCondition(),"id","Asc");
        ExcelUtils<FlowFlowbutton> util = new ExcelUtils<FlowFlowbutton>(FlowFlowbutton.class);
        return util.exportExcel(list, "FlowFlowbutton");
    }

}
