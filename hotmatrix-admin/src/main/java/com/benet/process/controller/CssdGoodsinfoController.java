package com.benet.process.controller;

import java.util.List;

import com.benet.common.core.pager.PageRequest;
import com.benet.common.core.pager.TableDataInfo;
import com.benet.common.utils.string.PinyinUtils;
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
import com.benet.process.domain.CssdGoodsinfo;
import com.benet.process.service.ICssdGoodsinfoService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;

/**
 * 物品包信息Controller
 * 
 * @author yoxking
 * @date 2020-12-13
 */
@Api(value = "process/goodsinfo", tags = "物品包信息控制器")
@RestController
@RequestMapping("/process/goodsinfo")
public class CssdGoodsinfoController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ICssdGoodsinfoService cssdGoodsinfoService;
    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('process:goodsinfo:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询物品包信息列表
     */
    @PreAuthorize("@ps.hasPermit('process:goodsinfo:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cssdGoodsinfoService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());
        List<CssdGoodsinfo> list = cssdGoodsinfoService.getRecordsByPaging(loginUser.getUser().getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), pRequest.getCondition(), "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增物品包信息
     */
    @PreAuthorize("@ps.hasPermit('process:goodsinfo:addnew')")
    @Oplog(title = "物品包信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody CssdGoodsinfo cssdGoodsinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        cssdGoodsinfo.setGoodsNo(UuidUtils.shortUUID());
        cssdGoodsinfo.setGoodsEname(PinyinUtils.getFirstSpell(cssdGoodsinfo.getGoodsCname()));
        cssdGoodsinfo.setCreateBy(loginUser.getUser().getUserNo());
        cssdGoodsinfo.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(cssdGoodsinfoService.AddNewRecord(loginUser.getUser().getAppCode(),cssdGoodsinfo));
    }

    /**
     * 编辑物品包信息
     */
    @PreAuthorize("@ps.hasPermit('process:goodsinfo:update')")
    @Oplog(title = "物品包信息", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody CssdGoodsinfo cssdGoodsinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        cssdGoodsinfo.setGoodsEname(PinyinUtils.getFirstSpell(cssdGoodsinfo.getGoodsCname()));
        cssdGoodsinfo.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(cssdGoodsinfoService.UpdateRecord(loginUser.getUser().getAppCode(), cssdGoodsinfo));
    }

    /**
     * 保存物品包信息
     */
    @PreAuthorize("@ps.hasPermit('process:goodsinfo:save')")
    @Oplog(title = "物品包信息", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody CssdGoodsinfo cssdGoodsinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(cssdGoodsinfoService.getRecordByNo(loginUser.getUser().getAppCode(),cssdGoodsinfo.getGoodsNo()))) {
            cssdGoodsinfo.setGoodsNo(UuidUtils.shortUUID());
            cssdGoodsinfo.setCreateBy(loginUser.getUser().getUserNo());
            cssdGoodsinfo.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cssdGoodsinfoService.AddNewRecord(loginUser.getUser().getAppCode(),cssdGoodsinfo));
        } else {
            cssdGoodsinfo.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cssdGoodsinfoService.UpdateRecord(loginUser.getUser().getAppCode(),cssdGoodsinfo));
        }
    }

    /**
     * 删除物品包信息
     */
    @PreAuthorize("@ps.hasPermit('process:goodsinfo:delete')")
    @Oplog(title = "物品包信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(cssdGoodsinfoService.SoftDeleteByNos(loginUser.getUser().getAppCode(),ids));
    }

    /**
     * 获取物品包信息详细信息
     */
    @PreAuthorize("@ps.hasPermit('process:goodsinfo:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(cssdGoodsinfoService.getRecordByNo(loginUser.getUser().getAppCode(),id));
    }

    /**
     * 导出物品包信息列表
     */
    @PreAuthorize("@ps.hasPermit('process:goodsinfo:export')")
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
