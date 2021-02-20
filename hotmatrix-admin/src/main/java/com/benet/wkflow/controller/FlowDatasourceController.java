package com.benet.wkflow.controller;

import java.util.List;
import com.benet.common.core.pager.PageRequest;
import com.benet.common.core.pager.TableDataInfo;
import com.benet.common.utils.uuid.UuidUtils;
import com.benet.common.utils.web.ServletUtils;
import com.benet.framework.security.LoginUser;
import com.benet.framework.security.service.MyJwtokenService;
import com.benet.wkflow.domain.FlowDatasource;
import com.benet.wkflow.service.IFlowDatasourceService;
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
 * 数据源信息Controller
 * 
 * @author yoxking
 * @date 2020-05-23
 */
@Api(value = "wkflow/datasource", tags = "数据源信息控制器")
@RestController
@RequestMapping("/wkflow/datasource")
public class FlowDatasourceController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private IFlowDatasourceService FlowDatasourceService;
    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('wkflow:datasource:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询数据源信息列表
     */
    @PreAuthorize("@ps.hasPermit('wkflow:datasource:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = FlowDatasourceService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());
        List<FlowDatasource> list = FlowDatasourceService.getRecordsByPaging(loginUser.getUser().getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), pRequest.getCondition(), "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增数据源信息
     */
    @PreAuthorize("@ps.hasPermit('wkflow:datasource:addnew')")
    @Oplog(title = "数据源信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody FlowDatasource flowDatasource) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        flowDatasource.setDtsrcNo(UuidUtils.shortUUID());
        flowDatasource.setCreateBy(loginUser.getUser().getUserNo());
        flowDatasource.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(FlowDatasourceService.AddNewRecord(loginUser.getUser().getAppCode(),flowDatasource));
    }

    /**
     * 编辑数据源信息
     */
    @PreAuthorize("@ps.hasPermit('wkflow:datasource:update')")
    @Oplog(title = "数据源信息", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody FlowDatasource flowDatasource) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        flowDatasource.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(FlowDatasourceService.UpdateRecord(loginUser.getUser().getAppCode(),flowDatasource));
        }

    /**
     * 保存数据源信息
     */
    @PreAuthorize("@ps.hasPermit('wkflow:datasource:save')")
    @Oplog(title = "数据源信息", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody FlowDatasource flowDatasource) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(FlowDatasourceService.getRecordByNo(loginUser.getUser().getAppCode(),flowDatasource.getDtsrcNo()))) {
            flowDatasource.setDtsrcNo(UuidUtils.shortUUID());
            flowDatasource.setCreateBy(loginUser.getUser().getUserNo());
            flowDatasource.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(FlowDatasourceService.AddNewRecord(loginUser.getUser().getAppCode(),flowDatasource));
        } else {
            flowDatasource.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(FlowDatasourceService.UpdateRecord(loginUser.getUser().getAppCode(),flowDatasource));
        }
    }

    /**
     * 删除数据源信息
     */
    @PreAuthorize("@ps.hasPermit('wkflow:datasource:delete')")
    @Oplog(title = "数据源信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(FlowDatasourceService.SoftDeleteByNos(loginUser.getUser().getAppCode(),ids));
    }

    /**
     * 获取数据源信息详细信息
     */
    @PreAuthorize("@ps.hasPermit('wkflow:datasource:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(FlowDatasourceService.getRecordByNo(loginUser.getUser().getAppCode(),id));
    }

    /**
     * 导出数据源信息列表
     */
    @PreAuthorize("@ps.hasPermit('wkflow:datasource:export')")
    @Oplog(title = "数据源信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = FlowDatasourceService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());

        List<FlowDatasource> list = FlowDatasourceService.getRecordsByPaging(loginUser.getUser().getAppCode(),1,count,pRequest.getCondition(),"id","Asc");
        ExcelUtils<FlowDatasource> util = new ExcelUtils<FlowDatasource>(FlowDatasource.class);
        return util.exportExcel(list, "FlowDatasource");
    }

}
