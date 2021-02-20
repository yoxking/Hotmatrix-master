package com.benet.wkflow.controller;

import java.util.List;
import com.benet.common.core.pager.PageRequest;
import com.benet.common.core.pager.TableDataInfo;
import com.benet.common.utils.uuid.UuidUtils;
import com.benet.common.utils.web.ServletUtils;
import com.benet.framework.security.LoginUser;
import com.benet.framework.security.service.MyJwtokenService;
import com.benet.wkflow.domain.FlowWorkgroup;
import com.benet.wkflow.service.IFlowWorkgroupService;
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
 * 工作组Controller
 * 
 * @author yoxking
 * @date 2020-05-17
 */
@Api(value = "wkflow/workgroup", tags = "工作组控制器")
@RestController
@RequestMapping("/wkflow/workgroup")
public class FlowWorkgroupController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private IFlowWorkgroupService flowWorkgroupService;
    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('wkflow:workgroup:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询工作组列表
     */
    @PreAuthorize("@ps.hasPermit('wkflow:workgroup:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = flowWorkgroupService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());
        List<FlowWorkgroup> list = flowWorkgroupService.getRecordsByPaging(loginUser.getUser().getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), pRequest.getCondition(), "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增工作组
     */
    @PreAuthorize("@ps.hasPermit('wkflow:workgroup:addnew')")
    @Oplog(title = "工作组", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody FlowWorkgroup flowWorkgroup) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        flowWorkgroup.setGroupNo(UuidUtils.shortUUID());
        flowWorkgroup.setCreateBy(loginUser.getUser().getUserNo());
        flowWorkgroup.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(flowWorkgroupService.AddNewRecord(loginUser.getUser().getAppCode(),flowWorkgroup));
    }

    /**
     * 编辑工作组
     */
    @PreAuthorize("@ps.hasPermit('wkflow:workgroup:update')")
    @Oplog(title = "工作组", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody FlowWorkgroup flowWorkgroup) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        flowWorkgroup.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(flowWorkgroupService.UpdateRecord(loginUser.getUser().getAppCode(),flowWorkgroup));
        }

    /**
     * 保存工作组
     */
    @PreAuthorize("@ps.hasPermit('wkflow:workgroup:save')")
    @Oplog(title = "工作组", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody FlowWorkgroup flowWorkgroup) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(flowWorkgroupService.getRecordByNo(loginUser.getUser().getAppCode(),flowWorkgroup.getGroupNo()))) {
            flowWorkgroup.setGroupNo(UuidUtils.shortUUID());
            flowWorkgroup.setCreateBy(loginUser.getUser().getUserNo());
            flowWorkgroup.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(flowWorkgroupService.AddNewRecord(loginUser.getUser().getAppCode(),flowWorkgroup));
        } else {
            flowWorkgroup.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(flowWorkgroupService.UpdateRecord(loginUser.getUser().getAppCode(),flowWorkgroup));
        }
    }

    /**
     * 删除工作组
     */
    @PreAuthorize("@ps.hasPermit('wkflow:workgroup:delete')")
    @Oplog(title = "工作组", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(flowWorkgroupService.SoftDeleteByNos(loginUser.getUser().getAppCode(),ids));
    }

    /**
     * 获取工作组详细信息
     */
    @PreAuthorize("@ps.hasPermit('wkflow:workgroup:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(flowWorkgroupService.getRecordByNo(loginUser.getUser().getAppCode(),id));
    }

    /**
     * 导出工作组列表
     */
    @PreAuthorize("@ps.hasPermit('wkflow:workgroup:export')")
    @Oplog(title = "工作组", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = flowWorkgroupService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());

        List<FlowWorkgroup> list = flowWorkgroupService.getRecordsByPaging(loginUser.getUser().getAppCode(),1,count,pRequest.getCondition(),"id","Asc");
        ExcelUtils<FlowWorkgroup> util = new ExcelUtils<FlowWorkgroup>(FlowWorkgroup.class);
        return util.exportExcel(list, "FlowWorkgroup");
    }

}
