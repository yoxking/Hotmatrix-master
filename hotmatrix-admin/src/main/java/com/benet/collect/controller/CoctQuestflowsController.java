package com.benet.collect.controller;

import java.util.List;

import com.benet.collect.domain.CoctQuestflows;
import com.benet.collect.service.ICoctQuestflowsService;
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
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;

/**
 * 答题结果Controller
 * 
 * @author yoxking
 * @date 2020-11-12
 */
@Api(value = "collect/questflows", tags = "答题结果控制器")
@RestController
@RequestMapping("/collect/questflows")
public class CoctQuestflowsController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ICoctQuestflowsService coctQuestflowsService;
    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('collect:questflows:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询答题结果列表
     */
    @PreAuthorize("@ps.hasPermit('collect:questflows:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = coctQuestflowsService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());
        List<CoctQuestflows> list = coctQuestflowsService.getRecordsByPaging(loginUser.getUser().getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), pRequest.getCondition(), "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增答题结果
     */
    @PreAuthorize("@ps.hasPermit('collect:questflows:addnew')")
    @Oplog(title = "答题结果", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody CoctQuestflows coctQuestflows) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        coctQuestflows.setQflowNo(UuidUtils.shortUUID());
        coctQuestflows.setCreateBy(loginUser.getUser().getUserNo());
        coctQuestflows.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(coctQuestflowsService.AddNewRecord(loginUser.getUser().getAppCode(),coctQuestflows));
    }

    /**
     * 编辑答题结果
     */
    @PreAuthorize("@ps.hasPermit('collect:questflows:update')")
    @Oplog(title = "答题结果", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody CoctQuestflows coctQuestflows) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        coctQuestflows.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(coctQuestflowsService.UpdateRecord(loginUser.getUser().getAppCode(),coctQuestflows));
        }

    /**
     * 保存答题结果
     */
    @PreAuthorize("@ps.hasPermit('collect:questflows:save')")
    @Oplog(title = "答题结果", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody CoctQuestflows coctQuestflows) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(coctQuestflowsService.getRecordByNo(loginUser.getUser().getAppCode(),coctQuestflows.getQflowNo()))) {
            coctQuestflows.setQflowNo(UuidUtils.shortUUID());
            coctQuestflows.setCreateBy(loginUser.getUser().getUserNo());
            coctQuestflows.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(coctQuestflowsService.AddNewRecord(loginUser.getUser().getAppCode(),coctQuestflows));
        } else {
            coctQuestflows.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(coctQuestflowsService.UpdateRecord(loginUser.getUser().getAppCode(),coctQuestflows));
        }
    }

    /**
     * 删除答题结果
     */
    @PreAuthorize("@ps.hasPermit('collect:questflows:delete')")
    @Oplog(title = "答题结果", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(coctQuestflowsService.SoftDeleteByNos(loginUser.getUser().getAppCode(),ids));
    }

    /**
     * 获取答题结果详细信息
     */
    @PreAuthorize("@ps.hasPermit('collect:questflows:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(coctQuestflowsService.getRecordByNo(loginUser.getUser().getAppCode(),id));
    }

    /**
     * 导出答题结果列表
     */
    @PreAuthorize("@ps.hasPermit('collect:questflows:export')")
    @Oplog(title = "答题结果", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = coctQuestflowsService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());

        List<CoctQuestflows> list = coctQuestflowsService.getRecordsByPaging(loginUser.getUser().getAppCode(),1,count,pRequest.getCondition(),"id","Asc");
        ExcelUtils<CoctQuestflows> util = new ExcelUtils<CoctQuestflows>(CoctQuestflows.class);
        return util.exportExcel(list, "CoctQuestflows");
    }

}
