package com.benet.flow.controller;

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
import com.benet.workflow.domain.FlwFlowentrust;
import com.benet.workflow.service.IFlwFlowentrustService;
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
@Api(value = "flow/flowentrust", tags = "流程委托控制器")
@RestController
@RequestMapping("/flow/flowentrust")
public class FlwFlowentrustController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private IFlwFlowentrustService flwFlowentrustService;
    /**
     * 首页
     */
    //@PreAuthorize("@ps.hasPermit('system:flowentrust:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询流程委托列表
     */
    //@PreAuthorize("@ps.hasPermit('system:flowentrust:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        int count = flwFlowentrustService.getCountByCondition(pRequest.getCondition());
        List<FlwFlowentrust> list = flwFlowentrustService.getRecordsByPaging(pRequest.getPageIndex(), pRequest.getPageSize(), pRequest.getCondition(), "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增流程委托
     */
    //@PreAuthorize("@ps.hasPermit('system:flowentrust:insert')")
    @Oplog(title = "流程委托", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody FlwFlowentrust flwFlowentrust) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        flwFlowentrust.setEntrustNo(UuidUtils.shortUUID());
        flwFlowentrust.setCreateBy(loginUser.getUser().getUserNo());
        flwFlowentrust.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(flwFlowentrustService.AddNewRecord(flwFlowentrust));
    }

    /**
     * 编辑流程委托
     */
    //@PreAuthorize("@ps.hasPermit('system:flowentrust:update')")
    @Oplog(title = "流程委托", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody FlwFlowentrust flwFlowentrust) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        flwFlowentrust.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(flwFlowentrustService.UpdateRecord(flwFlowentrust));
        }

    /**
     * 保存流程委托
     */
    //@PreAuthorize("@ps.hasPermit('system:flowentrust:save')")
    @Oplog(title = "流程委托", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody FlwFlowentrust flwFlowentrust) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(flwFlowentrustService.getRecordByNo(flwFlowentrust.getEntrustNo()))) {
            flwFlowentrust.setEntrustNo(UuidUtils.shortUUID());
            flwFlowentrust.setCreateBy(loginUser.getUser().getUserNo());
            flwFlowentrust.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(flwFlowentrustService.AddNewRecord(flwFlowentrust));
        } else {
            flwFlowentrust.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(flwFlowentrustService.UpdateRecord(flwFlowentrust));
        }
    }

    /**
     * 删除流程委托
     */
    //@PreAuthorize("@ps.hasPermit('system:flowentrust:delete')")
    @Oplog(title = "流程委托", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        return toAjax(flwFlowentrustService.SoftDeleteByNos(ids));
    }

    /**
     * 获取流程委托详细信息
     */
    //@PreAuthorize("@ps.hasPermit('system:flowentrust:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        return AjaxResult.success(flwFlowentrustService.getRecordByNo(id));
    }

    /**
     * 导出流程委托列表
     */
    //@PreAuthorize("@ps.hasPermit('system:flowentrust:export')")
    @Oplog(title = "流程委托", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        int count = flwFlowentrustService.getCountByCondition(pRequest.getCondition());

        List<FlwFlowentrust> list = flwFlowentrustService.getRecordsByPaging(1,count,pRequest.getCondition(),"id","Asc");
        ExcelUtils<FlwFlowentrust> util = new ExcelUtils<FlwFlowentrust>(FlwFlowentrust.class);
        return util.exportExcel(list, "FlwFlowentrust");
    }

}
