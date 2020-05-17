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
import com.benet.workflow.domain.FlwFlowbutton;
import com.benet.workflow.service.IFlwFlowbuttonService;
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
@Api(value = "flow/flowbutton", tags = "工作流程按钮控制器")
@RestController
@RequestMapping("/flow/flowbutton")
public class FlwFlowbuttonController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private IFlwFlowbuttonService flwFlowbuttonService;
    /**
     * 首页
     */
    ////@PreAuthorize("@ps.hasPermit('system:flowbutton:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询工作流程按钮列表
     */
    ////@PreAuthorize("@ps.hasPermit('system:flowbutton:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        int count = flwFlowbuttonService.getCountByCondition(pRequest.getCondition());
        List<FlwFlowbutton> list = flwFlowbuttonService.getRecordsByPaging(pRequest.getPageIndex(), pRequest.getPageSize(), pRequest.getCondition(), "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增工作流程按钮
     */
    ////@PreAuthorize("@ps.hasPermit('system:flowbutton:insert')")
    @Oplog(title = "工作流程按钮", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody FlwFlowbutton flwFlowbutton) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        flwFlowbutton.setBtnNo(UuidUtils.shortUUID());
        flwFlowbutton.setCreateBy(loginUser.getUser().getUserNo());
        flwFlowbutton.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(flwFlowbuttonService.AddNewRecord(flwFlowbutton));
    }

    /**
     * 编辑工作流程按钮
     */
    //@PreAuthorize("@ps.hasPermit('system:flowbutton:update')")
    @Oplog(title = "工作流程按钮", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody FlwFlowbutton flwFlowbutton) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        flwFlowbutton.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(flwFlowbuttonService.UpdateRecord(flwFlowbutton));
        }

    /**
     * 保存工作流程按钮
     */
    //@PreAuthorize("@ps.hasPermit('system:flowbutton:save')")
    @Oplog(title = "工作流程按钮", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody FlwFlowbutton flwFlowbutton) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(flwFlowbuttonService.getRecordByNo(flwFlowbutton.getBtnNo()))) {
            flwFlowbutton.setBtnNo(UuidUtils.shortUUID());
            flwFlowbutton.setCreateBy(loginUser.getUser().getUserNo());
            flwFlowbutton.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(flwFlowbuttonService.AddNewRecord(flwFlowbutton));
        } else {
            flwFlowbutton.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(flwFlowbuttonService.UpdateRecord(flwFlowbutton));
        }
    }

    /**
     * 删除工作流程按钮
     */
    //@PreAuthorize("@ps.hasPermit('system:flowbutton:delete')")
    @Oplog(title = "工作流程按钮", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        return toAjax(flwFlowbuttonService.SoftDeleteByNos(ids));
    }

    /**
     * 获取工作流程按钮详细信息
     */
    //@PreAuthorize("@ps.hasPermit('system:flowbutton:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        return AjaxResult.success(flwFlowbuttonService.getRecordByNo(id));
    }

    /**
     * 导出工作流程按钮列表
     */
    //@PreAuthorize("@ps.hasPermit('system:flowbutton:export')")
    @Oplog(title = "工作流程按钮", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        int count = flwFlowbuttonService.getCountByCondition(pRequest.getCondition());

        List<FlwFlowbutton> list = flwFlowbuttonService.getRecordsByPaging(1,count,pRequest.getCondition(),"id","Asc");
        ExcelUtils<FlwFlowbutton> util = new ExcelUtils<FlwFlowbutton>(FlwFlowbutton.class);
        return util.exportExcel(list, "FlwFlowbutton");
    }

}
