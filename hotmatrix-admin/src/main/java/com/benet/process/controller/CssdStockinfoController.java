package com.benet.process.controller;

import java.util.ArrayList;
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
import com.benet.process.service.ICssdStockflowsService;
import com.benet.process.vmodel.StockInfoVo;
import com.benet.system.vmodel.ItemObjectVo;
import io.swagger.annotations.Api;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.benet.process.domain.CssdStockinfo;
import com.benet.process.service.ICssdStockinfoService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;

/**
 * 库存信息Controller
 * 
 * @author yoxking
 * @date 2020-12-13
 */
@Api(value = "process/stockinfo", tags = "库存信息控制器")
@RestController
@RequestMapping("/process/stockinfo")
public class CssdStockinfoController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ICssdStockinfoService cssdStockinfoService;
    @Autowired
    private ICssdStockflowsService cssdStockflowsService;
    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('process:stockinfo:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询库存信息列表
     */
    @PreAuthorize("@ps.hasPermit('process:stockinfo:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        String condition=" equip_type='"+pRequest.getDataParam()+"' ";
        if(StringUtils.isNotEmpty(pRequest.getCondition())){
            condition+=" And "+pRequest.getCondition();
        }

        int count = cssdStockinfoService.getCountByCondition(loginUser.getUser().getAppCode(),condition);
        List<CssdStockinfo> list = cssdStockinfoService.getRecordsByPaging(loginUser.getUser().getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), condition, "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 查询内容类型列表
     */
    @PreAuthorize("@ps.hasPermit('process:stockinfo:list')")
    @GetMapping(value = "/tree}")
    public TableDataInfo tree(@RequestParam("equipType") String equipType)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());

        String condition=" equip_type='"+equipType+"'";
        int count = cssdStockinfoService.getCountByCondition(loginUser.getUser().getAppCode(),condition);
        List<ItemObjectVo> list = buildItemTree(loginUser.getUser().getAppCode(),equipType);
        return getDataTable(list, count);
    }

    private List<ItemObjectVo> buildItemTree(String appCode,String equipType) {

        List<ItemObjectVo> itemTree = null;
        ItemObjectVo item = null;
        String condition=" equip_type='"+equipType+"'";
        List<CssdStockinfo> infoList = cssdStockinfoService.getRecordsByPaging(appCode,1,100,condition,"id","asc");

        if (infoList != null && infoList.size() > 0) {
            itemTree = new ArrayList<>();
            for (CssdStockinfo info : infoList) {
                item = new ItemObjectVo();
                item.setId(info.getStockNo());
                item.setKey(info.getStockNo());
                item.setTitle(info.getEquipNo()+" "+info.getProduceNumber()+" "+info.getEquipNum()+" "+info.getStoreNo());
                item.setValue(info.getStockNo());
                item.setChildren(null);

                itemTree.add(item);
            }
        }
        return itemTree;
    }

    /**
     * 新增库存信息
     */
    @PreAuthorize("@ps.hasPermit('process:stockinfo:addnew')")
    @Oplog(title = "库存信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody CssdStockinfo cssdStockinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        cssdStockinfo.setStockNo(UuidUtils.shortUUID());
        cssdStockinfo.setCreateBy(loginUser.getUser().getUserNo());
        cssdStockinfo.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(cssdStockinfoService.AddNewRecord(loginUser.getUser().getAppCode(),cssdStockinfo));
    }

    /**
     * 编辑库存信息
     */
    @PreAuthorize("@ps.hasPermit('process:stockinfo:update')")
    @Oplog(title = "库存信息", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody CssdStockinfo cssdStockinfo) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        cssdStockinfo.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cssdStockinfoService.UpdateRecord(loginUser.getUser().getAppCode(),cssdStockinfo));
        }

    /**
     * 保存库存信息
     */
    @PreAuthorize("@ps.hasPermit('process:stockinfo:save')")
    @Oplog(title = "库存信息", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody CssdStockinfo cssdStockinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(cssdStockinfoService.getRecordByNo(loginUser.getUser().getAppCode(),cssdStockinfo.getStockNo()))) {
            cssdStockinfo.setStockNo(UuidUtils.shortUUID());
            cssdStockinfo.setCreateBy(loginUser.getUser().getUserNo());
            cssdStockinfo.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cssdStockinfoService.AddNewRecord(loginUser.getUser().getAppCode(),cssdStockinfo));
        } else {
            cssdStockinfo.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cssdStockinfoService.UpdateRecord(loginUser.getUser().getAppCode(),cssdStockinfo));
        }
    }

    /**
     * 删除库存信息
     */
    @PreAuthorize("@ps.hasPermit('process:stockinfo:delete')")
    @Oplog(title = "库存信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(cssdStockinfoService.SoftDeleteByNos(loginUser.getUser().getAppCode(),ids));
    }

    /**
     * 获取库存信息详细信息
     */
    @PreAuthorize("@ps.hasPermit('process:stockinfo:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(cssdStockinfoService.getRecordByNo(loginUser.getUser().getAppCode(),id));
    }

    /**
     * 获取库存信息详细信息
     */
    @PreAuthorize("@ps.hasPermit('process:stockinfo:detail')")
    @GetMapping(value = "/info/{id}")
    public AjaxResult info(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());

        CssdStockinfo info=cssdStockinfoService.getRecordByNo(loginUser.getUser().getAppCode(),id);
        StockInfoVo infoVo=new StockInfoVo();
        infoVo.setStockNo(info.getStockNo());
        infoVo.setStoreNo(info.getStoreNo());
        infoVo.setRuserNo("");
        infoVo.setEquipNo(info.getEquipNo());
        infoVo.setEquipNum(1);
        infoVo.setStockNum(info.getEquipNum());
        infoVo.setProduceNumber(info.getProduceNumber());
        infoVo.setProduceDate(info.getProduceDate());
        infoVo.setExpireDate(info.getExpireDate());
        infoVo.setCheckState(info.getCheckState());
        infoVo.setComments(info.getComments());

        return AjaxResult.success(infoVo);
    }

    /**
     * 导出库存信息列表
     */
    @PreAuthorize("@ps.hasPermit('process:stockinfo:export')")
    @Oplog(title = "库存信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cssdStockinfoService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());

        List<CssdStockinfo> list = cssdStockinfoService.getRecordsByPaging(loginUser.getUser().getAppCode(),1,count,pRequest.getCondition(),"id","Asc");
        ExcelUtils<CssdStockinfo> util = new ExcelUtils<CssdStockinfo>(CssdStockinfo.class);
        return util.exportExcel(list, "CssdStockinfo");
    }

    /**
     * 入库信息
     */
    @PreAuthorize("@ps.hasPermit('process:stockinfo:inbound')")
    @Oplog(title = "入库信息", businessType = BusinessType.UPDATE)
    @PostMapping("/inbound")
    public AjaxResult inbound(@RequestBody StockInfoVo stockinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());

        CssdStockflows stockflow = new CssdStockflows();

        stockflow.setSflowNo(UuidUtils.shortUUID());
        stockflow.setSflowType(CssdConstants.inbound);//入库
        stockflow.setStoreNo(stockinfo.getStoreNo());
        stockflow.setEquipNo(stockinfo.getEquipNo());
        stockflow.setEquipType(stockinfo.getEquipType());
        stockflow.setEquipNum(stockinfo.getEquipNum());
        stockflow.setProduceNumber(stockinfo.getProduceNumber());
        stockflow.setProduceDate(stockinfo.getProduceDate());
        stockflow.setExpireDate(stockinfo.getExpireDate());
        stockflow.setBranchNo("");
        stockflow.setCreateBy(loginUser.getUser().getUserNo());
        stockflow.setUpdateBy(loginUser.getUser().getUserNo());

        if (cssdStockflowsService.AddNewRecord(loginUser.getUser().getAppCode(), stockflow) > 0) {

            String condition=" store_no='"+stockinfo.getStoreNo()+"' And equip_no ='"+stockinfo.getEquipNo()+"' And produce_number='"+stockinfo.getProduceNumber()+"' And produce_date='"+DateUtils.parseDateToStr("yyyy-MM-dd",stockinfo.getProduceDate())+"' And expire_date='"+DateUtils.parseDateToStr("yyyy-MM-dd",stockinfo.getExpireDate())+"' ";
            List<CssdStockinfo> list=cssdStockinfoService.getRecordsByPaging(loginUser.getUser().getAppCode(),1,1,condition,"id","asc");

            CssdStockinfo info = null;

            if (list != null&&list.size()>0) {
                info=list.get(0);
                info.setEquipNum(info.getEquipNum() + stockinfo.getEquipNum());

                info.setUpdateBy(loginUser.getUser().getUserNo());

                cssdStockinfoService.UpdateRecord(loginUser.getUser().getAppCode(), info);
            } else {
                info = new CssdStockinfo();
                info.setStockNo(UuidUtils.shortUUID());
                info.setStoreNo(stockinfo.getStoreNo());
                info.setEquipNo(stockinfo.getEquipNo());
                info.setEquipType(stockinfo.getEquipType());
                info.setEquipNum(stockinfo.getEquipNum());
                info.setProduceNumber(stockinfo.getProduceNumber());
                info.setProduceDate(stockinfo.getProduceDate());
                info.setExpireDate(stockinfo.getExpireDate());
                info.setCheckState("1");
                info.setBranchNo("");
                info.setCreateBy(loginUser.getUser().getUserNo());
                info.setUpdateBy(loginUser.getUser().getUserNo());

                cssdStockinfoService.AddNewRecord(loginUser.getUser().getAppCode(), info);
            }
            return AjaxResult.success();
        }
        return AjaxResult.error("入库失败！");
    }

    /**
     * 出库信息
     */
    @PreAuthorize("@ps.hasPermit('process:stockinfo:outbound')")
    @Oplog(title = "出库信息", businessType = BusinessType.UPDATE)
    @PostMapping("/outbound")
    public AjaxResult outbound(@RequestBody StockInfoVo stockinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());

        CssdStockflows stockflow = new CssdStockflows();

        stockflow.setSflowNo(UuidUtils.shortUUID());
        stockflow.setSflowType(CssdConstants.outbound);//入库
        stockflow.setStoreNo(stockinfo.getStoreNo());
        stockflow.setEquipNo(stockinfo.getEquipNo());
        stockflow.setEquipType(stockinfo.getEquipType());
        stockflow.setEquipNum(stockinfo.getEquipNum());
        stockflow.setProduceNumber(stockinfo.getProduceNumber());
        stockflow.setProduceDate(stockinfo.getProduceDate());
        stockflow.setExpireDate(stockinfo.getExpireDate());
        stockflow.setBranchNo("");
        stockflow.setCreateBy(loginUser.getUser().getUserNo());
        stockflow.setUpdateBy(loginUser.getUser().getUserNo());

        if (cssdStockflowsService.AddNewRecord(loginUser.getUser().getAppCode(), stockflow) > 0) {
            CssdStockinfo info = cssdStockinfoService.getRecordByNo(loginUser.getUser().getAppCode(), stockinfo.getStockNo());

            if (info != null&&info.getEquipNum()>=stockinfo.getEquipNum()) {
                info.setEquipNum(info.getEquipNum() - stockinfo.getEquipNum());

                info.setUpdateBy(loginUser.getUser().getUserNo());

                cssdStockinfoService.UpdateRecord(loginUser.getUser().getAppCode(), info);
                return toAjax(true);
            }
        }
        return AjaxResult.error("出库失败：出库数量不能大于库存数量！");
    }

}
