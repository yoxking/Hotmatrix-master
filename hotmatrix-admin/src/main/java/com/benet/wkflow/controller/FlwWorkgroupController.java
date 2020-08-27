package com.benet.wkflow.controller;

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
import com.benet.wkflow.domain.FlwWorkgroup;
import com.benet.wkflow.service.IFlwWorkgroupService;
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
public class FlwWorkgroupController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private IFlwWorkgroupService flwWorkgroupService;
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
        int count = flwWorkgroupService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());
        List<FlwWorkgroup> list = flwWorkgroupService.getRecordsByPaging(loginUser.getUser().getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), pRequest.getCondition(), "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增工作组
     */
    @PreAuthorize("@ps.hasPermit('wkflow:workgroup:addnew')")
    @Oplog(title = "工作组", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody FlwWorkgroup flwWorkgroup) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        flwWorkgroup.setGroupNo(UuidUtils.shortUUID());
        flwWorkgroup.setCreateBy(loginUser.getUser().getUserNo());
        flwWorkgroup.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(flwWorkgroupService.AddNewRecord(loginUser.getUser().getAppCode(),flwWorkgroup));
    }

    /**
     * 编辑工作组
     */
    @PreAuthorize("@ps.hasPermit('wkflow:workgroup:update')")
    @Oplog(title = "工作组", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody FlwWorkgroup flwWorkgroup) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        flwWorkgroup.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(flwWorkgroupService.UpdateRecord(loginUser.getUser().getAppCode(),flwWorkgroup));
        }

    /**
     * 保存工作组
     */
    @PreAuthorize("@ps.hasPermit('wkflow:workgroup:save')")
    @Oplog(title = "工作组", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody FlwWorkgroup flwWorkgroup) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(flwWorkgroupService.getRecordByNo(loginUser.getUser().getAppCode(),flwWorkgroup.getGroupNo()))) {
            flwWorkgroup.setGroupNo(UuidUtils.shortUUID());
            flwWorkgroup.setCreateBy(loginUser.getUser().getUserNo());
            flwWorkgroup.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(flwWorkgroupService.AddNewRecord(loginUser.getUser().getAppCode(),flwWorkgroup));
        } else {
            flwWorkgroup.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(flwWorkgroupService.UpdateRecord(loginUser.getUser().getAppCode(),flwWorkgroup));
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
        return toAjax(flwWorkgroupService.SoftDeleteByNos(loginUser.getUser().getAppCode(),ids));
    }

    /**
     * 获取工作组详细信息
     */
    @PreAuthorize("@ps.hasPermit('wkflow:workgroup:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(flwWorkgroupService.getRecordByNo(loginUser.getUser().getAppCode(),id));
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
        int count = flwWorkgroupService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());

        List<FlwWorkgroup> list = flwWorkgroupService.getRecordsByPaging(loginUser.getUser().getAppCode(),1,count,pRequest.getCondition(),"id","Asc");
        ExcelUtils<FlwWorkgroup> util = new ExcelUtils<FlwWorkgroup>(FlwWorkgroup.class);
        return util.exportExcel(list, "FlwWorkgroup");
    }

}
