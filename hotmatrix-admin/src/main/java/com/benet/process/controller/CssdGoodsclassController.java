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
import com.benet.process.domain.CssdGoodsclass;
import com.benet.process.service.ICssdGoodsclassService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;

/**
 * 物品包类型Controller
 * 
 * @author yoxking
 * @date 2020-12-13
 */
@Api(value = "process/goodsclass", tags = "物品包类型控制器")
@RestController
@RequestMapping("/process/goodsclass")
public class CssdGoodsclassController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ICssdGoodsclassService cssdGoodsclassService;
    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('process:goodsclass:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询物品包类型列表
     */
    @PreAuthorize("@ps.hasPermit('process:goodsclass:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cssdGoodsclassService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());
        List<CssdGoodsclass> list = cssdGoodsclassService.getRecordsByPaging(loginUser.getUser().getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), pRequest.getCondition(), "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 查询内容类型列表
     */
    @PreAuthorize("@ps.hasPermit('process:goodsclass:list')")
    @GetMapping(value = "/tree")
    public TableDataInfo tree()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cssdGoodsclassService.getCountByCondition(loginUser.getUser().getAppCode(),"");
        List<ItemObjectVo> list = buildItemTree(loginUser.getUser().getAppCode());
        return getDataTable(list, count);
    }

    private List<ItemObjectVo> buildItemTree(String appCode) {

        List<ItemObjectVo> itemTree = null;
        ItemObjectVo item = null;
        List<CssdGoodsclass> infoList = cssdGoodsclassService.getAllRecords(appCode);

        if (infoList != null && infoList.size() > 0) {
            itemTree = new ArrayList<>();
            for (CssdGoodsclass info : infoList) {
                item = new ItemObjectVo();
                item.setId(info.getClassNo());
                item.setKey(info.getClassNo());
                item.setTitle(info.getClassName());
                item.setValue(info.getClassNo());
                item.setChildren(null);

                itemTree.add(item);
            }
        }
        return itemTree;
    }

    /**
     * 新增物品包类型
     */
    @PreAuthorize("@ps.hasPermit('process:goodsclass:addnew')")
    @Oplog(title = "物品包类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody CssdGoodsclass cssdGoodsclass) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        cssdGoodsclass.setClassNo(UuidUtils.shortUUID());
        cssdGoodsclass.setCreateBy(loginUser.getUser().getUserNo());
        cssdGoodsclass.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(cssdGoodsclassService.AddNewRecord(loginUser.getUser().getAppCode(),cssdGoodsclass));
    }

    /**
     * 编辑物品包类型
     */
    @PreAuthorize("@ps.hasPermit('process:goodsclass:update')")
    @Oplog(title = "物品包类型", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody CssdGoodsclass cssdGoodsclass) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        cssdGoodsclass.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cssdGoodsclassService.UpdateRecord(loginUser.getUser().getAppCode(),cssdGoodsclass));
        }

    /**
     * 保存物品包类型
     */
    @PreAuthorize("@ps.hasPermit('process:goodsclass:save')")
    @Oplog(title = "物品包类型", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody CssdGoodsclass cssdGoodsclass) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(cssdGoodsclassService.getRecordByNo(loginUser.getUser().getAppCode(),cssdGoodsclass.getClassNo()))) {
            cssdGoodsclass.setClassNo(UuidUtils.shortUUID());
            cssdGoodsclass.setCreateBy(loginUser.getUser().getUserNo());
            cssdGoodsclass.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cssdGoodsclassService.AddNewRecord(loginUser.getUser().getAppCode(),cssdGoodsclass));
        } else {
            cssdGoodsclass.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cssdGoodsclassService.UpdateRecord(loginUser.getUser().getAppCode(),cssdGoodsclass));
        }
    }

    /**
     * 删除物品包类型
     */
    @PreAuthorize("@ps.hasPermit('process:goodsclass:delete')")
    @Oplog(title = "物品包类型", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(cssdGoodsclassService.SoftDeleteByNos(loginUser.getUser().getAppCode(),ids));
    }

    /**
     * 获取物品包类型详细信息
     */
    @PreAuthorize("@ps.hasPermit('process:goodsclass:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(cssdGoodsclassService.getRecordByNo(loginUser.getUser().getAppCode(),id));
    }

    /**
     * 导出物品包类型列表
     */
    @PreAuthorize("@ps.hasPermit('process:goodsclass:export')")
    @Oplog(title = "物品包类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cssdGoodsclassService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());

        List<CssdGoodsclass> list = cssdGoodsclassService.getRecordsByPaging(loginUser.getUser().getAppCode(),1,count,pRequest.getCondition(),"id","Asc");
        ExcelUtils<CssdGoodsclass> util = new ExcelUtils<CssdGoodsclass>(CssdGoodsclass.class);
        return util.exportExcel(list, "CssdGoodsclass");
    }

}
