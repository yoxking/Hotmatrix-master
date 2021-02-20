package com.benet.process.controller;

import java.util.ArrayList;
import java.util.List;
import com.benet.common.core.pager.PageRequest;
import com.benet.common.core.pager.TableDataInfo;
import com.benet.common.utils.uuid.UuidUtils;
import com.benet.common.utils.web.ServletUtils;
import com.benet.framework.security.LoginUser;
import com.benet.framework.security.service.MyJwtokenService;
import com.benet.system.vmodel.ItemObjectVo;
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
import com.benet.process.domain.CssdSupplierinfo;
import com.benet.process.service.ICssdSupplierinfoService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;

/**
 * 供应商信息Controller
 * 
 * @author yoxking
 * @date 2020-12-13
 */
@Api(value = "process/supplierinfo", tags = "供应商信息控制器")
@RestController
@RequestMapping("/process/supplierinfo")
public class CssdSupplierinfoController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ICssdSupplierinfoService cssdSupplierinfoService;
    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('process:supplierinfo:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询供应商信息列表
     */
    @PreAuthorize("@ps.hasPermit('process:supplierinfo:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cssdSupplierinfoService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());
        List<CssdSupplierinfo> list = cssdSupplierinfoService.getRecordsByPaging(loginUser.getUser().getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), pRequest.getCondition(), "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 查询内容类型列表
     */
    @PreAuthorize("@ps.hasPermit('process:supplierinfo:list')")
    @GetMapping(value = "/tree")
    public TableDataInfo tree()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cssdSupplierinfoService.getCountByCondition(loginUser.getUser().getAppCode(),"");
        List<ItemObjectVo> list = buildItemTree(loginUser.getUser().getAppCode());
        return getDataTable(list, count);
    }

    private List<ItemObjectVo> buildItemTree(String appCode) {

        List<ItemObjectVo> itemTree = null;
        ItemObjectVo item = null;
        List<CssdSupplierinfo> infoList = cssdSupplierinfoService.getAllRecords(appCode);

        if (infoList != null && infoList.size() > 0) {
            itemTree = new ArrayList<>();
            for (CssdSupplierinfo info : infoList) {
                item = new ItemObjectVo();
                item.setId(info.getSuppNo());
                item.setKey(info.getSuppNo());
                item.setTitle(info.getSuppName());
                item.setValue(info.getSuppNo());
                item.setChildren(null);

                itemTree.add(item);
            }
        }
        return itemTree;
    }

    /**
     * 新增供应商信息
     */
    @PreAuthorize("@ps.hasPermit('process:supplierinfo:addnew')")
    @Oplog(title = "供应商信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody CssdSupplierinfo cssdSupplierinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        cssdSupplierinfo.setSuppNo(UuidUtils.shortUUID());
        cssdSupplierinfo.setCreateBy(loginUser.getUser().getUserNo());
        cssdSupplierinfo.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(cssdSupplierinfoService.AddNewRecord(loginUser.getUser().getAppCode(),cssdSupplierinfo));
    }

    /**
     * 编辑供应商信息
     */
    @PreAuthorize("@ps.hasPermit('process:supplierinfo:update')")
    @Oplog(title = "供应商信息", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody CssdSupplierinfo cssdSupplierinfo) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        cssdSupplierinfo.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cssdSupplierinfoService.UpdateRecord(loginUser.getUser().getAppCode(),cssdSupplierinfo));
        }

    /**
     * 保存供应商信息
     */
    @PreAuthorize("@ps.hasPermit('process:supplierinfo:save')")
    @Oplog(title = "供应商信息", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody CssdSupplierinfo cssdSupplierinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(cssdSupplierinfoService.getRecordByNo(loginUser.getUser().getAppCode(),cssdSupplierinfo.getSuppNo()))) {
            cssdSupplierinfo.setSuppNo(UuidUtils.shortUUID());
            cssdSupplierinfo.setCreateBy(loginUser.getUser().getUserNo());
            cssdSupplierinfo.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cssdSupplierinfoService.AddNewRecord(loginUser.getUser().getAppCode(),cssdSupplierinfo));
        } else {
            cssdSupplierinfo.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cssdSupplierinfoService.UpdateRecord(loginUser.getUser().getAppCode(),cssdSupplierinfo));
        }
    }

    /**
     * 删除供应商信息
     */
    @PreAuthorize("@ps.hasPermit('process:supplierinfo:delete')")
    @Oplog(title = "供应商信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(cssdSupplierinfoService.SoftDeleteByNos(loginUser.getUser().getAppCode(),ids));
    }

    /**
     * 获取供应商信息详细信息
     */
    @PreAuthorize("@ps.hasPermit('process:supplierinfo:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(cssdSupplierinfoService.getRecordByNo(loginUser.getUser().getAppCode(),id));
    }

    /**
     * 导出供应商信息列表
     */
    @PreAuthorize("@ps.hasPermit('process:supplierinfo:export')")
    @Oplog(title = "供应商信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cssdSupplierinfoService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());

        List<CssdSupplierinfo> list = cssdSupplierinfoService.getRecordsByPaging(loginUser.getUser().getAppCode(),1,count,pRequest.getCondition(),"id","Asc");
        ExcelUtils<CssdSupplierinfo> util = new ExcelUtils<CssdSupplierinfo>(CssdSupplierinfo.class);
        return util.exportExcel(list, "CssdSupplierinfo");
    }

}
