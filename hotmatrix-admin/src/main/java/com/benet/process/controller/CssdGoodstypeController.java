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
import com.benet.process.domain.CssdGoodstype;
import com.benet.process.service.ICssdGoodstypeService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;

/**
 * 系统类型Controller
 * 
 * @author yoxking
 * @date 2020-12-13
 */
@Api(value = "process/goodstype", tags = "系统类型控制器")
@RestController
@RequestMapping("/process/goodstype")
public class CssdGoodstypeController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ICssdGoodstypeService cssdGoodstypeService;
    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('process:goodstype:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询系统类型列表
     */
    @PreAuthorize("@ps.hasPermit('process:goodstype:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cssdGoodstypeService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());
        List<CssdGoodstype> list = cssdGoodstypeService.getRecordsByPaging(loginUser.getUser().getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), pRequest.getCondition(), "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 查询内容类型列表
     */
    @PreAuthorize("@ps.hasPermit('process:goodstype:list')")
    @GetMapping(value = "/tree")
    public TableDataInfo tree()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cssdGoodstypeService.getCountByCondition(loginUser.getUser().getAppCode(),"");
        List<ItemObjectVo> list = buildItemTree(loginUser.getUser().getAppCode());
        return getDataTable(list, count);
    }

    private List<ItemObjectVo> buildItemTree(String appCode) {

        List<ItemObjectVo> itemTree = null;
        ItemObjectVo item = null;
        List<CssdGoodstype> infoList = cssdGoodstypeService.getAllRecords(appCode);

        if (infoList != null && infoList.size() > 0) {
            itemTree = new ArrayList<>();
            for (CssdGoodstype info : infoList) {
                item = new ItemObjectVo();
                item.setId(info.getTypeNo());
                item.setKey(info.getTypeNo());
                item.setTitle(info.getTypeName());
                item.setValue(info.getTypeNo());
                item.setChildren(null);

                itemTree.add(item);
            }
        }
        return itemTree;
    }

    /**
     * 新增系统类型
     */
    @PreAuthorize("@ps.hasPermit('process:goodstype:addnew')")
    @Oplog(title = "系统类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody CssdGoodstype cssdGoodstype) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        cssdGoodstype.setTypeNo(UuidUtils.shortUUID());
        cssdGoodstype.setCreateBy(loginUser.getUser().getUserNo());
        cssdGoodstype.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(cssdGoodstypeService.AddNewRecord(loginUser.getUser().getAppCode(),cssdGoodstype));
    }

    /**
     * 编辑系统类型
     */
    @PreAuthorize("@ps.hasPermit('process:goodstype:update')")
    @Oplog(title = "系统类型", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody CssdGoodstype cssdGoodstype) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        cssdGoodstype.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cssdGoodstypeService.UpdateRecord(loginUser.getUser().getAppCode(),cssdGoodstype));
        }

    /**
     * 保存系统类型
     */
    @PreAuthorize("@ps.hasPermit('process:goodstype:save')")
    @Oplog(title = "系统类型", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody CssdGoodstype cssdGoodstype) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(cssdGoodstypeService.getRecordByNo(loginUser.getUser().getAppCode(),cssdGoodstype.getTypeNo()))) {
            cssdGoodstype.setTypeNo(UuidUtils.shortUUID());
            cssdGoodstype.setCreateBy(loginUser.getUser().getUserNo());
            cssdGoodstype.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cssdGoodstypeService.AddNewRecord(loginUser.getUser().getAppCode(),cssdGoodstype));
        } else {
            cssdGoodstype.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cssdGoodstypeService.UpdateRecord(loginUser.getUser().getAppCode(),cssdGoodstype));
        }
    }

    /**
     * 删除系统类型
     */
    @PreAuthorize("@ps.hasPermit('process:goodstype:delete')")
    @Oplog(title = "系统类型", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(cssdGoodstypeService.SoftDeleteByNos(loginUser.getUser().getAppCode(),ids));
    }

    /**
     * 获取系统类型详细信息
     */
    @PreAuthorize("@ps.hasPermit('process:goodstype:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(cssdGoodstypeService.getRecordByNo(loginUser.getUser().getAppCode(),id));
    }

    /**
     * 导出系统类型列表
     */
    @PreAuthorize("@ps.hasPermit('process:goodstype:export')")
    @Oplog(title = "系统类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cssdGoodstypeService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());

        List<CssdGoodstype> list = cssdGoodstypeService.getRecordsByPaging(loginUser.getUser().getAppCode(),1,count,pRequest.getCondition(),"id","Asc");
        ExcelUtils<CssdGoodstype> util = new ExcelUtils<CssdGoodstype>(CssdGoodstype.class);
        return util.exportExcel(list, "CssdGoodstype");
    }

}
