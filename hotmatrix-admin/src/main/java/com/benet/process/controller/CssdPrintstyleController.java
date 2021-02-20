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
import com.benet.process.domain.CssdPrintstyle;
import com.benet.process.service.ICssdPrintstyleService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;

/**
 * 打印样式Controller
 * 
 * @author yoxking
 * @date 2020-12-13
 */
@Api(value = "process/printstyle", tags = "打印样式控制器")
@RestController
@RequestMapping("/process/printstyle")
public class CssdPrintstyleController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ICssdPrintstyleService cssdPrintstyleService;
    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('process:printstyle:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询打印样式列表
     */
    @PreAuthorize("@ps.hasPermit('process:printstyle:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cssdPrintstyleService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());
        List<CssdPrintstyle> list = cssdPrintstyleService.getRecordsByPaging(loginUser.getUser().getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), pRequest.getCondition(), "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 查询内容类型列表
     */
    @PreAuthorize("@ps.hasPermit('process:printstyle:list')")
    @GetMapping(value = "/tree")
    public TableDataInfo tree()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cssdPrintstyleService.getCountByCondition(loginUser.getUser().getAppCode(),"");
        List<ItemObjectVo> list = buildItemTree(loginUser.getUser().getAppCode());
        return getDataTable(list, count);
    }

    private List<ItemObjectVo> buildItemTree(String appCode) {

        List<ItemObjectVo> itemTree = null;
        ItemObjectVo item = null;
        List<CssdPrintstyle> infoList = cssdPrintstyleService.getAllRecords(appCode);

        if (infoList != null && infoList.size() > 0) {
            itemTree = new ArrayList<>();
            for (CssdPrintstyle info : infoList) {
                item = new ItemObjectVo();
                item.setId(info.getStyleNo());
                item.setKey(info.getStyleNo());
                item.setTitle(info.getStyleName());
                item.setValue(info.getStyleNo());
                item.setChildren(null);

                itemTree.add(item);
            }
        }
        return itemTree;
    }

    /**
     * 新增打印样式
     */
    @PreAuthorize("@ps.hasPermit('process:printstyle:addnew')")
    @Oplog(title = "打印样式", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody CssdPrintstyle cssdPrintstyle) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        cssdPrintstyle.setStyleNo(UuidUtils.shortUUID());
        cssdPrintstyle.setCreateBy(loginUser.getUser().getUserNo());
        cssdPrintstyle.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(cssdPrintstyleService.AddNewRecord(loginUser.getUser().getAppCode(),cssdPrintstyle));
    }

    /**
     * 编辑打印样式
     */
    @PreAuthorize("@ps.hasPermit('process:printstyle:update')")
    @Oplog(title = "打印样式", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody CssdPrintstyle cssdPrintstyle) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        cssdPrintstyle.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cssdPrintstyleService.UpdateRecord(loginUser.getUser().getAppCode(),cssdPrintstyle));
        }

    /**
     * 保存打印样式
     */
    @PreAuthorize("@ps.hasPermit('process:printstyle:save')")
    @Oplog(title = "打印样式", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody CssdPrintstyle cssdPrintstyle) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(cssdPrintstyleService.getRecordByNo(loginUser.getUser().getAppCode(),cssdPrintstyle.getStyleNo()))) {
            cssdPrintstyle.setStyleNo(UuidUtils.shortUUID());
            cssdPrintstyle.setCreateBy(loginUser.getUser().getUserNo());
            cssdPrintstyle.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cssdPrintstyleService.AddNewRecord(loginUser.getUser().getAppCode(),cssdPrintstyle));
        } else {
            cssdPrintstyle.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cssdPrintstyleService.UpdateRecord(loginUser.getUser().getAppCode(),cssdPrintstyle));
        }
    }

    /**
     * 删除打印样式
     */
    @PreAuthorize("@ps.hasPermit('process:printstyle:delete')")
    @Oplog(title = "打印样式", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(cssdPrintstyleService.SoftDeleteByNos(loginUser.getUser().getAppCode(),ids));
    }

    /**
     * 获取打印样式详细信息
     */
    @PreAuthorize("@ps.hasPermit('process:printstyle:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(cssdPrintstyleService.getRecordByNo(loginUser.getUser().getAppCode(),id));
    }

    /**
     * 导出打印样式列表
     */
    @PreAuthorize("@ps.hasPermit('process:printstyle:export')")
    @Oplog(title = "打印样式", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cssdPrintstyleService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());

        List<CssdPrintstyle> list = cssdPrintstyleService.getRecordsByPaging(loginUser.getUser().getAppCode(),1,count,pRequest.getCondition(),"id","Asc");
        ExcelUtils<CssdPrintstyle> util = new ExcelUtils<CssdPrintstyle>(CssdPrintstyle.class);
        return util.exportExcel(list, "CssdPrintstyle");
    }

}
