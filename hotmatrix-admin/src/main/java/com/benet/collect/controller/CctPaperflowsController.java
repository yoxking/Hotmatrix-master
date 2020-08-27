package com.benet.collect.controller;

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
import com.benet.collect.domain.CctPaperflows;
import com.benet.collect.service.ICctPaperflowsService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;

/**
 * 测评结果Controller
 * 
 * @author yoxking
 * @date 2020-08-27
 */
@Api(value = "collect/paperflows", tags = "测评结果控制器")
@RestController
@RequestMapping("/collect/paperflows")
public class CctPaperflowsController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ICctPaperflowsService cctPaperflowsService;
    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('collect:paperflows:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询测评结果列表
     */
    @PreAuthorize("@ps.hasPermit('collect:paperflows:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cctPaperflowsService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());
        List<CctPaperflows> list = cctPaperflowsService.getRecordsByPaging(loginUser.getUser().getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), pRequest.getCondition(), "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增测评结果
     */
    @PreAuthorize("@ps.hasPermit('collect:paperflows:addnew')")
    @Oplog(title = "测评结果", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody CctPaperflows cctPaperflows) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        cctPaperflows.setPflowNo(UuidUtils.shortUUID());
        cctPaperflows.setCreateBy(loginUser.getUser().getUserNo());
        cctPaperflows.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(cctPaperflowsService.AddNewRecord(loginUser.getUser().getAppCode(),cctPaperflows));
    }

    /**
     * 编辑测评结果
     */
    @PreAuthorize("@ps.hasPermit('collect:paperflows:update')")
    @Oplog(title = "测评结果", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody CctPaperflows cctPaperflows) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        cctPaperflows.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cctPaperflowsService.UpdateRecord(loginUser.getUser().getAppCode(),cctPaperflows));
        }

    /**
     * 保存测评结果
     */
    @PreAuthorize("@ps.hasPermit('collect:paperflows:save')")
    @Oplog(title = "测评结果", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody CctPaperflows cctPaperflows) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(cctPaperflowsService.getRecordByNo(loginUser.getUser().getAppCode(),cctPaperflows.getPflowNo()))) {
            cctPaperflows.setPflowNo(UuidUtils.shortUUID());
            cctPaperflows.setCreateBy(loginUser.getUser().getUserNo());
            cctPaperflows.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cctPaperflowsService.AddNewRecord(loginUser.getUser().getAppCode(),cctPaperflows));
        } else {
            cctPaperflows.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cctPaperflowsService.UpdateRecord(loginUser.getUser().getAppCode(),cctPaperflows));
        }
    }

    /**
     * 删除测评结果
     */
    @PreAuthorize("@ps.hasPermit('collect:paperflows:delete')")
    @Oplog(title = "测评结果", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(cctPaperflowsService.SoftDeleteByNos(loginUser.getUser().getAppCode(),ids));
    }

    /**
     * 获取测评结果详细信息
     */
    @PreAuthorize("@ps.hasPermit('collect:paperflows:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(cctPaperflowsService.getRecordByNo(loginUser.getUser().getAppCode(),id));
    }

    /**
     * 导出测评结果列表
     */
    @PreAuthorize("@ps.hasPermit('collect:paperflows:export')")
    @Oplog(title = "测评结果", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cctPaperflowsService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());

        List<CctPaperflows> list = cctPaperflowsService.getRecordsByPaging(loginUser.getUser().getAppCode(),1,count,pRequest.getCondition(),"id","Asc");
        ExcelUtils<CctPaperflows> util = new ExcelUtils<CctPaperflows>(CctPaperflows.class);
        return util.exportExcel(list, "CctPaperflows");
    }

}
