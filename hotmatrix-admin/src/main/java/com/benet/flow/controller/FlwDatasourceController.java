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
import com.benet.workflow.domain.FlwDatasource;
import com.benet.workflow.service.IFlwDatasourceService;
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
@Api(value = "flow/datasource", tags = "数据源信息控制器")
@RestController
@RequestMapping("/flow/datasource")
public class FlwDatasourceController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private IFlwDatasourceService flwDatasourceService;
    /**
     * 首页
     */
    //@PreAuthorize("@ps.hasPermit('system:datasource:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询数据源信息列表
     */
    //@PreAuthorize("@ps.hasPermit('system:datasource:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        int count = flwDatasourceService.getCountByCondition(pRequest.getCondition());
        List<FlwDatasource> list = flwDatasourceService.getRecordsByPaging(pRequest.getPageIndex(), pRequest.getPageSize(), pRequest.getCondition(), "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增数据源信息
     */
    //@PreAuthorize("@ps.hasPermit('system:datasource:insert')")
    @Oplog(title = "数据源信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody FlwDatasource flwDatasource) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        flwDatasource.setDtsrcNo(UuidUtils.shortUUID());
        flwDatasource.setCreateBy(loginUser.getUser().getUserNo());
        flwDatasource.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(flwDatasourceService.AddNewRecord(flwDatasource));
    }

    /**
     * 编辑数据源信息
     */
    //@PreAuthorize("@ps.hasPermit('system:datasource:update')")
    @Oplog(title = "数据源信息", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody FlwDatasource flwDatasource) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        flwDatasource.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(flwDatasourceService.UpdateRecord(flwDatasource));
        }

    /**
     * 保存数据源信息
     */
    //@PreAuthorize("@ps.hasPermit('system:datasource:save')")
    @Oplog(title = "数据源信息", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody FlwDatasource flwDatasource) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(flwDatasourceService.getRecordByNo(flwDatasource.getDtsrcNo()))) {
            flwDatasource.setDtsrcNo(UuidUtils.shortUUID());
            flwDatasource.setCreateBy(loginUser.getUser().getUserNo());
            flwDatasource.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(flwDatasourceService.AddNewRecord(flwDatasource));
        } else {
            flwDatasource.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(flwDatasourceService.UpdateRecord(flwDatasource));
        }
    }

    /**
     * 删除数据源信息
     */
    //@PreAuthorize("@ps.hasPermit('system:datasource:delete')")
    @Oplog(title = "数据源信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        return toAjax(flwDatasourceService.SoftDeleteByNos(ids));
    }

    /**
     * 获取数据源信息详细信息
     */
    //@PreAuthorize("@ps.hasPermit('system:datasource:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        return AjaxResult.success(flwDatasourceService.getRecordByNo(id));
    }

    /**
     * 导出数据源信息列表
     */
    //@PreAuthorize("@ps.hasPermit('system:datasource:export')")
    @Oplog(title = "数据源信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        int count = flwDatasourceService.getCountByCondition(pRequest.getCondition());

        List<FlwDatasource> list = flwDatasourceService.getRecordsByPaging(1,count,pRequest.getCondition(),"id","Asc");
        ExcelUtils<FlwDatasource> util = new ExcelUtils<FlwDatasource>(FlwDatasource.class);
        return util.exportExcel(list, "FlwDatasource");
    }

}
