package com.benet.wkflow.controller;

import java.util.List;
import com.benet.common.core.pager.PageRequest;
import com.benet.common.core.pager.TableDataInfo;
import com.benet.common.utils.uuid.UuidUtils;
import com.benet.common.utils.web.ServletUtils;
import com.benet.framework.security.LoginUser;
import com.benet.framework.security.service.MyJwtokenService;
import com.benet.wkflow.domain.FlowTabcolumn;
import com.benet.wkflow.service.IFlowTabcolumnService;
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
 * 表单字段Controller
 * 
 * @author yoxking
 * @date 2020-05-23
 */
@Api(value = "wkflow/tabcolumn", tags = "表单字段控制器")
@RestController
@RequestMapping("/wkflow/tabcolumn")
public class FlowTabcolumnController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private IFlowTabcolumnService flowTabcolumnService;
    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('wkflow:tabcolumn:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询表单字段列表
     */
    @PreAuthorize("@ps.hasPermit('wkflow:tabcolumn:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = flowTabcolumnService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());
        List<FlowTabcolumn> list = flowTabcolumnService.getRecordsByPaging(loginUser.getUser().getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), pRequest.getCondition(), "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增表单字段
     */
    @PreAuthorize("@ps.hasPermit('wkflow:tabcolumn:addnew')")
    @Oplog(title = "表单字段", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody FlowTabcolumn flowTabcolumn) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        flowTabcolumn.setColumnNo(UuidUtils.shortUUID());
        flowTabcolumn.setCreateBy(loginUser.getUser().getUserNo());
        flowTabcolumn.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(flowTabcolumnService.AddNewRecord(loginUser.getUser().getAppCode(),flowTabcolumn));
    }

    /**
     * 编辑表单字段
     */
    @PreAuthorize("@ps.hasPermit('wkflow:tabcolumn:update')")
    @Oplog(title = "表单字段", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody FlowTabcolumn flowTabcolumn) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        flowTabcolumn.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(flowTabcolumnService.UpdateRecord(loginUser.getUser().getAppCode(),flowTabcolumn));
        }

    /**
     * 保存表单字段
     */
    @PreAuthorize("@ps.hasPermit('wkflow:tabcolumn:save')")
    @Oplog(title = "表单字段", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody FlowTabcolumn flowTabcolumn) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(flowTabcolumnService.getRecordByNo(loginUser.getUser().getAppCode(),flowTabcolumn.getColumnNo()))) {
            flowTabcolumn.setColumnNo(UuidUtils.shortUUID());
            flowTabcolumn.setCreateBy(loginUser.getUser().getUserNo());
            flowTabcolumn.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(flowTabcolumnService.AddNewRecord(loginUser.getUser().getAppCode(),flowTabcolumn));
        } else {
            flowTabcolumn.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(flowTabcolumnService.UpdateRecord(loginUser.getUser().getAppCode(),flowTabcolumn));
        }
    }

    /**
     * 删除表单字段
     */
    @PreAuthorize("@ps.hasPermit('wkflow:tabcolumn:delete')")
    @Oplog(title = "表单字段", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(flowTabcolumnService.SoftDeleteByNos(loginUser.getUser().getAppCode(),ids));
    }

    /**
     * 获取表单字段详细信息
     */
    @PreAuthorize("@ps.hasPermit('wkflow:tabcolumn:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(flowTabcolumnService.getRecordByNo(loginUser.getUser().getAppCode(),id));
    }

    /**
     * 导出表单字段列表
     */
    @PreAuthorize("@ps.hasPermit('wkflow:tabcolumn:export')")
    @Oplog(title = "表单字段", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = flowTabcolumnService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());

        List<FlowTabcolumn> list = flowTabcolumnService.getRecordsByPaging(loginUser.getUser().getAppCode(),1,count,pRequest.getCondition(),"id","Asc");
        ExcelUtils<FlowTabcolumn> util = new ExcelUtils<FlowTabcolumn>(FlowTabcolumn.class);
        return util.exportExcel(list, "FlowTabcolumn");
    }

}
