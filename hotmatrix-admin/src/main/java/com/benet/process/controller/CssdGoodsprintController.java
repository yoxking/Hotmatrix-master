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
import com.benet.process.domain.CssdGoodsinfo;
import com.benet.process.service.ICssdGoodsinfoService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 物品标签打印Controller
 *
 * @author yoxking
 * @date 2020-12-13
 */
@Api(value = "process/goodsprint", tags = "物品标签打印控制器")
@RestController
@RequestMapping("/process/goodsprint")
public class CssdGoodsprintController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ICssdGoodsinfoService cssdGoodsinfoService;
    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('process:goodsprint:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询物品包信息列表
     */
    @PreAuthorize("@ps.hasPermit('process:goodsprint:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cssdGoodsinfoService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());
        List<CssdGoodsinfo> list = cssdGoodsinfoService.getRecordsByPaging(loginUser.getUser().getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), pRequest.getCondition(), "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 获取物品包信息详细信息
     */
    @PreAuthorize("@ps.hasPermit('process:goodsprint:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(cssdGoodsinfoService.getRecordByNo(loginUser.getUser().getAppCode(),id));
    }

    /**
     * 导出物品包信息列表
     */
    @PreAuthorize("@ps.hasPermit('process:goodsprint:export')")
    @Oplog(title = "物品包信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cssdGoodsinfoService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());

        List<CssdGoodsinfo> list = cssdGoodsinfoService.getRecordsByPaging(loginUser.getUser().getAppCode(),1,count,pRequest.getCondition(),"id","Asc");
        ExcelUtils<CssdGoodsinfo> util = new ExcelUtils<CssdGoodsinfo>(CssdGoodsinfo.class);
        return util.exportExcel(list, "CssdGoodsinfo");
    }

}
