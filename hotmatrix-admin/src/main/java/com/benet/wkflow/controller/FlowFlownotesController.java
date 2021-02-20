package com.benet.wkflow.controller;

import java.util.List;
import com.benet.common.core.pager.PageRequest;
import com.benet.common.core.pager.TableDataInfo;
import com.benet.common.utils.uuid.UuidUtils;
import com.benet.common.utils.web.ServletUtils;
import com.benet.framework.security.LoginUser;
import com.benet.framework.security.service.MyJwtokenService;
import com.benet.wkflow.domain.FlowFlownotes;
import com.benet.wkflow.service.IFlowFlownotesService;
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
 * 流程处理意见Controller
 * 
 * @author yoxking
 * @date 2020-05-17
 */
@Api(value = "wkflow/flownotes", tags = "流程处理意见控制器")
@RestController
@RequestMapping("/wkflow/flownotes")
public class FlowFlownotesController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private IFlowFlownotesService flowFlownotesService;
    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('wkflow:flownotes:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询流程处理意见列表
     */
    @PreAuthorize("@ps.hasPermit('wkflow:flownotes:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = flowFlownotesService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());
        List<FlowFlownotes> list = flowFlownotesService.getRecordsByPaging(loginUser.getUser().getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), pRequest.getCondition(), "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增流程处理意见
     */
    @PreAuthorize("@ps.hasPermit('wkflow:flownotes:addnew')")
    @Oplog(title = "流程处理意见", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody FlowFlownotes flowFlownotes) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        flowFlownotes.setNoteNo(UuidUtils.shortUUID());
        flowFlownotes.setCreateBy(loginUser.getUser().getUserNo());
        flowFlownotes.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(flowFlownotesService.AddNewRecord(loginUser.getUser().getAppCode(),flowFlownotes));
    }

    /**
     * 编辑流程处理意见
     */
    @PreAuthorize("@ps.hasPermit('wkflow:flownotes:update')")
    @Oplog(title = "流程处理意见", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody FlowFlownotes flowFlownotes) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        flowFlownotes.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(flowFlownotesService.UpdateRecord(loginUser.getUser().getAppCode(),flowFlownotes));
        }

    /**
     * 保存流程处理意见
     */
    @PreAuthorize("@ps.hasPermit('wkflow:flownotes:save')")
    @Oplog(title = "流程处理意见", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody FlowFlownotes flowFlownotes) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(flowFlownotesService.getRecordByNo(loginUser.getUser().getAppCode(),flowFlownotes.getNoteNo()))) {
            flowFlownotes.setNoteNo(UuidUtils.shortUUID());
            flowFlownotes.setCreateBy(loginUser.getUser().getUserNo());
            flowFlownotes.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(flowFlownotesService.AddNewRecord(loginUser.getUser().getAppCode(),flowFlownotes));
        } else {
            flowFlownotes.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(flowFlownotesService.UpdateRecord(loginUser.getUser().getAppCode(),flowFlownotes));
        }
    }

    /**
     * 删除流程处理意见
     */
    @PreAuthorize("@ps.hasPermit('wkflow:flownotes:delete')")
    @Oplog(title = "流程处理意见", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(flowFlownotesService.SoftDeleteByNos(loginUser.getUser().getAppCode(),ids));
    }

    /**
     * 获取流程处理意见详细信息
     */
    @PreAuthorize("@ps.hasPermit('wkflow:flownotes:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(flowFlownotesService.getRecordByNo(loginUser.getUser().getAppCode(),id));
    }

    /**
     * 导出流程处理意见列表
     */
    @PreAuthorize("@ps.hasPermit('wkflow:flownotes:export')")
    @Oplog(title = "流程处理意见", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = flowFlownotesService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());

        List<FlowFlownotes> list = flowFlownotesService.getRecordsByPaging(loginUser.getUser().getAppCode(),1,count,pRequest.getCondition(),"id","Asc");
        ExcelUtils<FlowFlownotes> util = new ExcelUtils<FlowFlownotes>(FlowFlownotes.class);
        return util.exportExcel(list, "FlowFlownotes");
    }

}
