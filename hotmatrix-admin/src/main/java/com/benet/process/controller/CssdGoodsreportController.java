package com.benet.process.controller;

import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.core.pager.PageRequest;
import com.benet.common.core.pager.TableDataInfo;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.web.ServletUtils;
import com.benet.framework.security.LoginUser;
import com.benet.framework.security.service.MyJwtokenService;
import com.benet.process.domain.CssdGoodsflows;
import com.benet.process.service.ICssdGoodsflowsService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 追溯统计报表Controller
 *
 * @author yoxking
 * @date 2020-12-13
 */
@Api(value = "process/goodsreport", tags = "追溯统计报表控制器")
@RestController
@RequestMapping("/process/goodsreport")
public class CssdGoodsreportController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ICssdGoodsflowsService cssdGoodsflowsService;
    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('process:goodsreport:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询物品操作列表
     */
    @PreAuthorize("@ps.hasPermit('process:goodsreport:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cssdGoodsflowsService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());
        List<CssdGoodsflows> list = cssdGoodsflowsService.getRecordsByPaging(loginUser.getUser().getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), pRequest.getCondition(), "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 获取物品操作详细信息
     */
    @PreAuthorize("@ps.hasPermit('process:goodsreport:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(cssdGoodsflowsService.getRecordByNo(loginUser.getUser().getAppCode(),id));
    }

    /**
     * 导出物品操作列表
     */
    @PreAuthorize("@ps.hasPermit('process:goodsreport:export')")
    @Oplog(title = "物品操作", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cssdGoodsflowsService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());

        List<CssdGoodsflows> list = cssdGoodsflowsService.getRecordsByPaging(loginUser.getUser().getAppCode(),1,count,pRequest.getCondition(),"id","Asc");
        ExcelUtils<CssdGoodsflows> util = new ExcelUtils<CssdGoodsflows>(CssdGoodsflows.class);
        return util.exportExcel(list, "CssdGoodsflows");
    }

}
