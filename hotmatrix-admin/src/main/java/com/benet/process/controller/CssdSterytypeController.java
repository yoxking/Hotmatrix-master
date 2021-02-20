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
import com.benet.process.domain.CssdSterytype;
import com.benet.process.service.ICssdSterytypeService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;

/**
 * 灭菌方式Controller
 * 
 * @author yoxking
 * @date 2020-12-19
 */
@Api(value = "process/sterytype", tags = "灭菌方式控制器")
@RestController
@RequestMapping("/process/sterytype")
public class CssdSterytypeController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ICssdSterytypeService cssdSterytypeService;
    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('process:sterytype:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询灭菌方式列表
     */
    @PreAuthorize("@ps.hasPermit('process:sterytype:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cssdSterytypeService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());
        List<CssdSterytype> list = cssdSterytypeService.getRecordsByPaging(loginUser.getUser().getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), pRequest.getCondition(), "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 查询内容类型列表
     */
    @PreAuthorize("@ps.hasPermit('process:sterytype:list')")
    @GetMapping(value = "/tree")
    public TableDataInfo tree()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cssdSterytypeService.getCountByCondition(loginUser.getUser().getAppCode(),"");
        List<ItemObjectVo> list = buildItemTree(loginUser.getUser().getAppCode());
        return getDataTable(list, count);
    }

    private List<ItemObjectVo> buildItemTree(String appCode) {

        List<ItemObjectVo> itemTree = null;
        ItemObjectVo item = null;
        List<CssdSterytype> infoList = cssdSterytypeService.getAllRecords(appCode);

        if (infoList != null && infoList.size() > 0) {
            itemTree = new ArrayList<>();
            for (CssdSterytype info : infoList) {
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
     * 新增灭菌方式
     */
    @PreAuthorize("@ps.hasPermit('process:sterytype:addnew')")
    @Oplog(title = "灭菌方式", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody CssdSterytype cssdSterytype) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        cssdSterytype.setTypeNo(UuidUtils.shortUUID());
        cssdSterytype.setCreateBy(loginUser.getUser().getUserNo());
        cssdSterytype.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(cssdSterytypeService.AddNewRecord(loginUser.getUser().getAppCode(),cssdSterytype));
    }

    /**
     * 编辑灭菌方式
     */
    @PreAuthorize("@ps.hasPermit('process:sterytype:update')")
    @Oplog(title = "灭菌方式", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody CssdSterytype cssdSterytype) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        cssdSterytype.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cssdSterytypeService.UpdateRecord(loginUser.getUser().getAppCode(),cssdSterytype));
        }

    /**
     * 保存灭菌方式
     */
    @PreAuthorize("@ps.hasPermit('process:sterytype:save')")
    @Oplog(title = "灭菌方式", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody CssdSterytype cssdSterytype) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(cssdSterytypeService.getRecordByNo(loginUser.getUser().getAppCode(),cssdSterytype.getTypeNo()))) {
            cssdSterytype.setTypeNo(UuidUtils.shortUUID());
            cssdSterytype.setCreateBy(loginUser.getUser().getUserNo());
            cssdSterytype.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cssdSterytypeService.AddNewRecord(loginUser.getUser().getAppCode(),cssdSterytype));
        } else {
            cssdSterytype.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cssdSterytypeService.UpdateRecord(loginUser.getUser().getAppCode(),cssdSterytype));
        }
    }

    /**
     * 删除灭菌方式
     */
    @PreAuthorize("@ps.hasPermit('process:sterytype:delete')")
    @Oplog(title = "灭菌方式", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(cssdSterytypeService.SoftDeleteByNos(loginUser.getUser().getAppCode(),ids));
    }

    /**
     * 获取灭菌方式详细信息
     */
    @PreAuthorize("@ps.hasPermit('process:sterytype:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(cssdSterytypeService.getRecordByNo(loginUser.getUser().getAppCode(),id));
    }

    /**
     * 导出灭菌方式列表
     */
    @PreAuthorize("@ps.hasPermit('process:sterytype:export')")
    @Oplog(title = "灭菌方式", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cssdSterytypeService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());

        List<CssdSterytype> list = cssdSterytypeService.getRecordsByPaging(loginUser.getUser().getAppCode(),1,count,pRequest.getCondition(),"id","Asc");
        ExcelUtils<CssdSterytype> util = new ExcelUtils<CssdSterytype>(CssdSterytype.class);
        return util.exportExcel(list, "CssdSterytype");
    }

}
