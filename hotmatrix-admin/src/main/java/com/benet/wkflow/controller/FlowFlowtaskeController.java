package com.benet.wkflow.controller;

import java.util.List;
import com.benet.common.core.pager.PageRequest;
import com.benet.common.core.pager.TableDataInfo;
import com.benet.common.utils.uuid.UuidUtils;
import com.benet.common.utils.web.ServletUtils;
import com.benet.framework.security.LoginUser;
import com.benet.framework.security.service.MyJwtokenService;
import com.benet.wkflow.domain.FlowFlowtaske;
import com.benet.wkflow.service.IFlowFlowtaskeService;
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
 * 流程任务Controller
 * 
 * @author yoxking
 * @date 2020-05-17
 */
@Api(value = "wkflow/flowtaske", tags = "流程任务控制器")
@RestController
@RequestMapping("/wkflow/flowtaske")
public class FlowFlowtaskeController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private IFlowFlowtaskeService flowFlowtaskeService;
    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('wkflow:flowtaske:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询流程任务列表
     */
    @PreAuthorize("@ps.hasPermit('wkflow:flowtaske:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = flowFlowtaskeService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());
        List<FlowFlowtaske> list = flowFlowtaskeService.getRecordsByPaging(loginUser.getUser().getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), pRequest.getCondition(), "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增流程任务
     */
    @PreAuthorize("@ps.hasPermit('wkflow:flowtaske:insert')")
    @Oplog(title = "流程任务", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody FlowFlowtaske flowFlowtaske) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        flowFlowtaske.setTaskNo(UuidUtils.shortUUID());
        flowFlowtaske.setCreateBy(loginUser.getUser().getUserNo());
        flowFlowtaske.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(flowFlowtaskeService.AddNewRecord(loginUser.getUser().getAppCode(),flowFlowtaske));
    }

    /**
     * 编辑流程任务
     */
    @PreAuthorize("@ps.hasPermit('wkflow:flowtaske:update')")
    @Oplog(title = "流程任务", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody FlowFlowtaske flowFlowtaske) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        flowFlowtaske.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(flowFlowtaskeService.UpdateRecord(loginUser.getUser().getAppCode(),flowFlowtaske));
        }

    /**
     * 保存流程任务
     */
    @PreAuthorize("@ps.hasPermit('wkflow:flowtaske:save')")
    @Oplog(title = "流程任务", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody FlowFlowtaske flowFlowtaske) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(flowFlowtaskeService.getRecordByNo(loginUser.getUser().getAppCode(),flowFlowtaske.getTaskNo()))) {
            flowFlowtaske.setTaskNo(UuidUtils.shortUUID());
            flowFlowtaske.setCreateBy(loginUser.getUser().getUserNo());
            flowFlowtaske.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(flowFlowtaskeService.AddNewRecord(loginUser.getUser().getAppCode(),flowFlowtaske));
        } else {
            flowFlowtaske.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(flowFlowtaskeService.UpdateRecord(loginUser.getUser().getAppCode(),flowFlowtaske));
        }
    }

    /**
     * 删除流程任务
     */
    @PreAuthorize("@ps.hasPermit('wkflow:flowtaske:delete')")
    @Oplog(title = "流程任务", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(flowFlowtaskeService.SoftDeleteByNos(loginUser.getUser().getAppCode(),ids));
    }

    /**
     * 获取流程任务详细信息
     */
    @PreAuthorize("@ps.hasPermit('wkflow:flowtaske:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(flowFlowtaskeService.getRecordByNo(loginUser.getUser().getAppCode(),id));
    }

    /**
     * 导出流程任务列表
     */
    @PreAuthorize("@ps.hasPermit('wkflow:flowtaske:export')")
    @Oplog(title = "流程任务", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = flowFlowtaskeService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());

        List<FlowFlowtaske> list = flowFlowtaskeService.getRecordsByPaging(loginUser.getUser().getAppCode(),1,count,pRequest.getCondition(),"id","Asc");
        ExcelUtils<FlowFlowtaske> util = new ExcelUtils<FlowFlowtaske>(FlowFlowtaske.class);
        return util.exportExcel(list, "FlowFlowtaske");
    }

}
