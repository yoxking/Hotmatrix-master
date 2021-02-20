package com.benet.process.controller;

import java.util.List;
import com.benet.common.core.pager.PageRequest;
import com.benet.common.core.pager.TableDataInfo;
import com.benet.common.utils.date.DateUtils;
import com.benet.common.utils.uuid.UuidUtils;
import com.benet.common.utils.web.ServletUtils;
import com.benet.framework.security.LoginUser;
import com.benet.framework.security.service.MyJwtokenService;
import com.benet.process.common.CssdConstants;
import com.benet.process.domain.CssdStockflows;
import com.benet.system.vmodel.ItemObjectVo;
import io.swagger.annotations.Api;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.benet.process.domain.CssdGoodsflows;
import com.benet.process.service.ICssdGoodsflowsService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;

/**
 * 物品操作Controller
 * 
 * @author yoxking
 * @date 2020-12-13
 */
@Api(value = "process/goodsflows", tags = "物品操作控制器")
@RestController
@RequestMapping("/process/goodsflows")
public class CssdGoodsflowsController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ICssdGoodsflowsService cssdGoodsflowsService;
    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('process:goodsflows:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询物品操作列表
     */
    @PreAuthorize("@ps.hasPermit('process:goodsflows:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        String condition=" goods_state='"+pRequest.getDataParam()+"' ";
        if(StringUtils.isNotEmpty(pRequest.getCondition())){
            condition+=" And "+pRequest.getCondition();
        }
        int count = cssdGoodsflowsService.getCountByCondition(loginUser.getUser().getAppCode(),condition);
        List<CssdGoodsflows> list = cssdGoodsflowsService.getRecordsByPaging(loginUser.getUser().getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), condition, "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增物品操作
     */
    @PreAuthorize("@ps.hasPermit('process:goodsflows:addnew')")
    @Oplog(title = "物品操作", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody CssdGoodsflows cssdGoodsflows) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        cssdGoodsflows.setGflowNo(UuidUtils.shortUUID());
        cssdGoodsflows.setCreateBy(loginUser.getUser().getUserNo());
        cssdGoodsflows.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(cssdGoodsflowsService.AddNewRecord(loginUser.getUser().getAppCode(),cssdGoodsflows));
    }

    /**
     * 编辑物品操作
     */
    @PreAuthorize("@ps.hasPermit('process:goodsflows:update')")
    @Oplog(title = "物品操作", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody CssdGoodsflows cssdGoodsflows) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        cssdGoodsflows.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(cssdGoodsflowsService.UpdateRecord(loginUser.getUser().getAppCode(), cssdGoodsflows));
    }

    /**
     * 保存物品操作
     */
    @PreAuthorize("@ps.hasPermit('process:goodsflows:save')")
    @Oplog(title = "物品操作", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody CssdGoodsflows cssdGoodsflows) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(cssdGoodsflowsService.getRecordByNo(loginUser.getUser().getAppCode(),cssdGoodsflows.getGflowNo()))) {
            cssdGoodsflows.setGflowNo(UuidUtils.shortUUID());
            cssdGoodsflows.setCreateBy(loginUser.getUser().getUserNo());
            cssdGoodsflows.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cssdGoodsflowsService.AddNewRecord(loginUser.getUser().getAppCode(),cssdGoodsflows));
        } else {
            cssdGoodsflows.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cssdGoodsflowsService.UpdateRecord(loginUser.getUser().getAppCode(),cssdGoodsflows));
        }
    }

    /**
     * 删除物品操作
     */
    @PreAuthorize("@ps.hasPermit('process:goodsflows:delete')")
    @Oplog(title = "物品操作", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(cssdGoodsflowsService.SoftDeleteByNos(loginUser.getUser().getAppCode(),ids));
    }

    /**
     * 获取物品操作详细信息
     */
    @PreAuthorize("@ps.hasPermit('process:goodsflows:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(cssdGoodsflowsService.getRecordByNo(loginUser.getUser().getAppCode(),id));
    }

    /**
     * 导出物品操作列表
     */
    @PreAuthorize("@ps.hasPermit('process:goodsflows:export')")
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

    /**
     * 添加物品包
     */
    @PreAuthorize("@ps.hasPermit('process:goodsflows:internal')")
    @PostMapping(value = "/insertInternal")
    public AjaxResult insertInternal(@RequestBody String ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        String[] goodsIds=ids.split(",");

        CssdGoodsflows cssdGoodsflows=null;

        if(goodsIds!=null&&goodsIds.length>0){
            for(String goodsId:goodsIds){

                cssdGoodsflows=new CssdGoodsflows();

                cssdGoodsflows.setGflowNo(UuidUtils.shortUUID());
                cssdGoodsflows.setGoodsNo(goodsId);
                cssdGoodsflows.setGoodsState(CssdConstants.goodsState1);
                cssdGoodsflows.setDepteNo("");
                cssdGoodsflows.setOpertNo("");
                cssdGoodsflows.setOpertDate(DateUtils.getNowDate());
                cssdGoodsflows.setCheckNo("");
                cssdGoodsflows.setCheckDate(DateUtils.getNowDate());
                cssdGoodsflows.setExpireDays(1);
                cssdGoodsflows.setExpireDate(DateUtils.getNowDate());
                cssdGoodsflows.setPackeClass("");
                cssdGoodsflows.setCleanClass("");
                cssdGoodsflows.setCleanType("");
                cssdGoodsflows.setCleanNum(1);
                cssdGoodsflows.setSteryType("");
                cssdGoodsflows.setSteryClass("");
                cssdGoodsflows.setSteryNum(1);
                cssdGoodsflows.setPrintNo("");
                cssdGoodsflows.setPrintStyle("");
                cssdGoodsflows.setPriority(1);
                cssdGoodsflows.setHardflag("0");
                cssdGoodsflows.setLendflag("0");
                cssdGoodsflows.setBranchNo("");

                cssdGoodsflows.setCreateBy(loginUser.getUser().getUserNo());
                cssdGoodsflows.setUpdateBy(loginUser.getUser().getUserNo());
                cssdGoodsflowsService.AddNewRecord(loginUser.getUser().getAppCode(),cssdGoodsflows);
            }
        }
        return AjaxResult.success();
    }

    /**
     * 添加物品包
     */
    @PreAuthorize("@ps.hasPermit('process:goodsflows:external')")
    @PostMapping(value = "/insertExternal")
    public AjaxResult insertExternal(@RequestBody String ids)
    {
        return AjaxResult.success();
    }

    /**
     * 更新物品设置
     */
    @PreAuthorize("@ps.hasPermit('process:goodsflows:update')")
    @PostMapping(value = "/saveSetting")
    public AjaxResult saveSetting(@RequestBody CssdGoodsflows cssdGoodsflows)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());

        String condition=" goods_state="+cssdGoodsflows.getGoodsState();
        List<CssdGoodsflows> flowsList=cssdGoodsflowsService.getRecordsByPaging(loginUser.getUser().getAppCode(),1,100,condition,"id","asc");

        if(flowsList!=null&&flowsList.size()>0){
            for(CssdGoodsflows flowsInfo:flowsList){

                flowsInfo.setGoodsState(cssdGoodsflows.getGoodsState()+1);
                flowsInfo.setDepteNo(cssdGoodsflows.getDepteNo());
                flowsInfo.setOpertNo(cssdGoodsflows.getOpertNo());
                flowsInfo.setOpertDate(cssdGoodsflows.getOpertDate());
                flowsInfo.setCheckNo(cssdGoodsflows.getCheckNo());
                flowsInfo.setCheckDate(cssdGoodsflows.getCheckDate());
                flowsInfo.setExpireDays(cssdGoodsflows.getExpireDays());
                flowsInfo.setExpireDate(cssdGoodsflows.getExpireDate());
                flowsInfo.setPackeClass(cssdGoodsflows.getPackeClass());
                flowsInfo.setCleanType(cssdGoodsflows.getCleanType());
                flowsInfo.setCleanClass(cssdGoodsflows.getCleanClass());
                flowsInfo.setCleanNum(cssdGoodsflows.getCleanNum());
                flowsInfo.setSteryType(cssdGoodsflows.getSteryType());
                flowsInfo.setSteryClass(cssdGoodsflows.getSteryClass());
                flowsInfo.setSteryNum(cssdGoodsflows.getSteryNum());
                flowsInfo.setPrintStyle(cssdGoodsflows.getPrintStyle());
                flowsInfo.setPrintNo(cssdGoodsflows.getPrintNo());
                flowsInfo.setPriority(cssdGoodsflows.getPriority());
                flowsInfo.setHardflag(cssdGoodsflows.getHardflag());
                flowsInfo.setLendflag(cssdGoodsflows.getLendflag());

                flowsInfo.setUpdateBy(loginUser.getUser().getUserNo());

                cssdGoodsflowsService.UpdateRecord(loginUser.getUser().getAppCode(),flowsInfo);
            }
        }

        return AjaxResult.success();
    }

}
