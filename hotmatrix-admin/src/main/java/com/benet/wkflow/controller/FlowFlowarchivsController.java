package com.benet.wkflow.controller;

import java.util.List;
import com.benet.common.core.pager.PageRequest;
import com.benet.common.core.pager.TableDataInfo;
import com.benet.common.utils.uuid.UuidUtils;
import com.benet.common.utils.web.ServletUtils;
import com.benet.framework.security.LoginUser;
import com.benet.framework.security.service.MyJwtokenService;
import com.benet.wkflow.domain.FlowFlowarchivs;
import com.benet.wkflow.service.IFlowFlowarchivsService;
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
 * 【请填写功能名称】Controller
 * 
 * @author yoxking
 * @date 2020-05-17
 */
@Api(value = "wkflow/flowarchivs", tags = "流程归档控制器")
@RestController
@RequestMapping("/wkflow/flowarchivs")
public class FlowFlowarchivsController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private IFlowFlowarchivsService flowFlowarchivsService;
    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('wkflow:flowarchivs:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询【请填写功能名称】列表
     */
    @PreAuthorize("@ps.hasPermit('wkflow:flowarchivs:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = flowFlowarchivsService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());
        List<FlowFlowarchivs> list = flowFlowarchivsService.getRecordsByPaging(loginUser.getUser().getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), pRequest.getCondition(), "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增【请填写功能名称】
     */
    @PreAuthorize("@ps.hasPermit('wkflow:flowarchivs:addnew')")
    @Oplog(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody FlowFlowarchivs flwFlowarchives) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        flwFlowarchives.setArchvNo(UuidUtils.shortUUID());
        flwFlowarchives.setCreateBy(loginUser.getUser().getUserNo());
        flwFlowarchives.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(flowFlowarchivsService.AddNewRecord(loginUser.getUser().getAppCode(),flwFlowarchives));
    }

    /**
     * 编辑【请填写功能名称】
     */
    @PreAuthorize("@ps.hasPermit('wkflow:flowarchivs:update')")
    @Oplog(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody FlowFlowarchivs flwFlowarchives) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        flwFlowarchives.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(flowFlowarchivsService.UpdateRecord(loginUser.getUser().getAppCode(),flwFlowarchives));
        }

    /**
     * 保存【请填写功能名称】
     */
    @PreAuthorize("@ps.hasPermit('wkflow:flowarchivs:save')")
    @Oplog(title = "【请填写功能名称】", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody FlowFlowarchivs flwFlowarchives) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(flowFlowarchivsService.getRecordByNo(loginUser.getUser().getAppCode(),flwFlowarchives.getArchvNo()))) {
            flwFlowarchives.setArchvNo(UuidUtils.shortUUID());
            flwFlowarchives.setCreateBy(loginUser.getUser().getUserNo());
            flwFlowarchives.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(flowFlowarchivsService.AddNewRecord(loginUser.getUser().getAppCode(),flwFlowarchives));
        } else {
            flwFlowarchives.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(flowFlowarchivsService.UpdateRecord(loginUser.getUser().getAppCode(),flwFlowarchives));
        }
    }

    /**
     * 删除【请填写功能名称】
     */
    @PreAuthorize("@ps.hasPermit('wkflow:flowarchivs:delete')")
    @Oplog(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(flowFlowarchivsService.SoftDeleteByNos(loginUser.getUser().getAppCode(),ids));
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @PreAuthorize("@ps.hasPermit('wkflow:flowarchivs:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(flowFlowarchivsService.getRecordByNo(loginUser.getUser().getAppCode(),id));
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @PreAuthorize("@ps.hasPermit('wkflow:flowarchivs:export')")
    @Oplog(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = flowFlowarchivsService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());

        List<FlowFlowarchivs> list = flowFlowarchivsService.getRecordsByPaging(loginUser.getUser().getAppCode(),1,count,pRequest.getCondition(),"id","Asc");
        ExcelUtils<FlowFlowarchivs> util = new ExcelUtils<FlowFlowarchivs>(FlowFlowarchivs.class);
        return util.exportExcel(list, "FlwFlowarchives");
    }

}
