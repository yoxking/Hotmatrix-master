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
import com.benet.process.domain.CssdGoodstate;
import com.benet.process.service.ICssdGoodstateService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;

/**
 * 物品状态Controller
 * 
 * @author yoxking
 * @date 2020-12-13
 */
@Api(value = "process/goodstate", tags = "物品状态控制器")
@RestController
@RequestMapping("/process/goodstate")
public class CssdGoodstateController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ICssdGoodstateService cssdGoodstateService;
    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('process:goodstate:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询物品状态列表
     */
    @PreAuthorize("@ps.hasPermit('process:goodstate:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cssdGoodstateService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());
        List<CssdGoodstate> list = cssdGoodstateService.getRecordsByPaging(loginUser.getUser().getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), pRequest.getCondition(), "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 查询内容类型列表
     */
    @PreAuthorize("@ps.hasPermit('process:goodstate:list')")
    @GetMapping(value = "/tree")
    public TableDataInfo tree()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cssdGoodstateService.getCountByCondition(loginUser.getUser().getAppCode(),"");
        List<ItemObjectVo> list = buildItemTree(loginUser.getUser().getAppCode());
        return getDataTable(list, count);
    }

    private List<ItemObjectVo> buildItemTree(String appCode) {

        List<ItemObjectVo> itemTree = null;
        ItemObjectVo item = null;
        List<CssdGoodstate> infoList = cssdGoodstateService.getAllRecords(appCode);

        if (infoList != null && infoList.size() > 0) {
            itemTree = new ArrayList<>();
            for (CssdGoodstate info : infoList) {
                item = new ItemObjectVo();
                item.setId(info.getStateNo());
                item.setKey(info.getStateNo());
                item.setTitle(info.getStateName());
                item.setValue(info.getStateValue()+"");
                item.setChildren(null);

                itemTree.add(item);
            }
        }
        return itemTree;
    }

    /**
     * 新增物品状态
     */
    @PreAuthorize("@ps.hasPermit('process:goodstate:addnew')")
    @Oplog(title = "物品状态", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody CssdGoodstate cssdGoodstate) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        cssdGoodstate.setStateNo(UuidUtils.shortUUID());
        cssdGoodstate.setCreateBy(loginUser.getUser().getUserNo());
        cssdGoodstate.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(cssdGoodstateService.AddNewRecord(loginUser.getUser().getAppCode(),cssdGoodstate));
    }

    /**
     * 编辑物品状态
     */
    @PreAuthorize("@ps.hasPermit('process:goodstate:update')")
    @Oplog(title = "物品状态", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody CssdGoodstate cssdGoodstate) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        cssdGoodstate.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cssdGoodstateService.UpdateRecord(loginUser.getUser().getAppCode(),cssdGoodstate));
        }

    /**
     * 保存物品状态
     */
    @PreAuthorize("@ps.hasPermit('process:goodstate:save')")
    @Oplog(title = "物品状态", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody CssdGoodstate cssdGoodstate) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(cssdGoodstateService.getRecordByNo(loginUser.getUser().getAppCode(),cssdGoodstate.getStateNo()))) {
            cssdGoodstate.setStateNo(UuidUtils.shortUUID());
            cssdGoodstate.setCreateBy(loginUser.getUser().getUserNo());
            cssdGoodstate.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cssdGoodstateService.AddNewRecord(loginUser.getUser().getAppCode(),cssdGoodstate));
        } else {
            cssdGoodstate.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cssdGoodstateService.UpdateRecord(loginUser.getUser().getAppCode(),cssdGoodstate));
        }
    }

    /**
     * 删除物品状态
     */
    @PreAuthorize("@ps.hasPermit('process:goodstate:delete')")
    @Oplog(title = "物品状态", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(cssdGoodstateService.SoftDeleteByNos(loginUser.getUser().getAppCode(),ids));
    }

    /**
     * 获取物品状态详细信息
     */
    @PreAuthorize("@ps.hasPermit('process:goodstate:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(cssdGoodstateService.getRecordByNo(loginUser.getUser().getAppCode(),id));
    }

    /**
     * 导出物品状态列表
     */
    @PreAuthorize("@ps.hasPermit('process:goodstate:export')")
    @Oplog(title = "物品状态", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cssdGoodstateService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());

        List<CssdGoodstate> list = cssdGoodstateService.getRecordsByPaging(loginUser.getUser().getAppCode(),1,count,pRequest.getCondition(),"id","Asc");
        ExcelUtils<CssdGoodstate> util = new ExcelUtils<CssdGoodstate>(CssdGoodstate.class);
        return util.exportExcel(list, "CssdGoodstate");
    }

}
