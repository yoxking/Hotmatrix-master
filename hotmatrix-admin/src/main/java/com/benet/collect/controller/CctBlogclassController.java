package com.benet.collect.controller;

import java.util.ArrayList;
import java.util.List;

import com.benet.collect.domain.CctPaperclass;
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
import com.benet.collect.domain.CctBlogclass;
import com.benet.collect.service.ICctBlogclassService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;

/**
 * 日记类型Controller
 * asas
 * @author yoxking
 * @date 2020-10-14
 */
@Api(value = "collect/blogclass", tags = "日记类型控制器")
@RestController
@RequestMapping("/collect/blogclass")
public class CctBlogclassController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ICctBlogclassService cctBlogclassService;

    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('collect:blogclass:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询日记类型列表
     */
    @PreAuthorize("@ps.hasPermit('collect:blogclass:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cctBlogclassService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());
        List<CctBlogclass> list = cctBlogclassService.getRecordsByPaging(loginUser.getUser().getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), pRequest.getCondition(), "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 查询内容类型列表
     */
    @PreAuthorize("@ps.hasPermit('collect:paperclass:list')")
    @GetMapping(value = "/tree")
    public TableDataInfo tree()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cctBlogclassService.getCountByCondition(loginUser.getUser().getAppCode(),"");
        List<ItemObjectVo> list = buildItemTree(loginUser.getUser().getAppCode(),"0");
        return getDataTable(list, count);
    }

    private List<ItemObjectVo> buildItemTree(String appCode, String parentNo) {

        List<ItemObjectVo> itemTree = null;
        ItemObjectVo item = null;
        List<CctBlogclass> infoList = cctBlogclassService.getRecordsByClassNo(appCode,parentNo);

        if (infoList != null && infoList.size() > 0) {
            itemTree = new ArrayList<>();
            for (CctBlogclass info : infoList) {
                item = new ItemObjectVo();
                item.setId(info.getClassNo());
                item.setKey(info.getClassNo());
                item.setTitle(info.getClassName());
                item.setValue(info.getClassNo());
                item.setChildren(buildItemTree(appCode,info.getClassNo()));

                itemTree.add(item);
            }
        }
        return itemTree;
    }

    /**
     * 新增日记类型
     */
    @PreAuthorize("@ps.hasPermit('collect:blogclass:addnew')")
    @Oplog(title = "日记类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody CctBlogclass cctBlogclass) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        cctBlogclass.setClassNo(UuidUtils.shortUUID());
        cctBlogclass.setCreateBy(loginUser.getUser().getUserNo());
        cctBlogclass.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(cctBlogclassService.AddNewRecord(loginUser.getUser().getAppCode(),cctBlogclass));
    }

    /**
     * 编辑日记类型
     */
    @PreAuthorize("@ps.hasPermit('collect:blogclass:update')")
    @Oplog(title = "日记类型", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody CctBlogclass cctBlogclass) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        cctBlogclass.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cctBlogclassService.UpdateRecord(loginUser.getUser().getAppCode(),cctBlogclass));
        }

    /**
     * 保存日记类型
     */
    @PreAuthorize("@ps.hasPermit('collect:blogclass:save')")
    @Oplog(title = "日记类型", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody CctBlogclass cctBlogclass) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(cctBlogclassService.getRecordByNo(loginUser.getUser().getAppCode(),cctBlogclass.getClassNo()))) {
            cctBlogclass.setClassNo(UuidUtils.shortUUID());
            cctBlogclass.setCreateBy(loginUser.getUser().getUserNo());
            cctBlogclass.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cctBlogclassService.AddNewRecord(loginUser.getUser().getAppCode(),cctBlogclass));
        } else {
            cctBlogclass.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cctBlogclassService.UpdateRecord(loginUser.getUser().getAppCode(),cctBlogclass));
        }
    }

    /**
     * 删除日记类型
     */
    @PreAuthorize("@ps.hasPermit('collect:blogclass:delete')")
    @Oplog(title = "日记类型", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(cctBlogclassService.SoftDeleteByNos(loginUser.getUser().getAppCode(),ids));
    }

    /**
     * 获取日记类型详细信息
     */
    @PreAuthorize("@ps.hasPermit('collect:blogclass:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(cctBlogclassService.getRecordByNo(loginUser.getUser().getAppCode(),id));
    }

    /**
     * 导出日记类型列表
     */
    @PreAuthorize("@ps.hasPermit('collect:blogclass:export')")
    @Oplog(title = "日记类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cctBlogclassService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());

        List<CctBlogclass> list = cctBlogclassService.getRecordsByPaging(loginUser.getUser().getAppCode(),1,count,pRequest.getCondition(),"id","Asc");
        ExcelUtils<CctBlogclass> util = new ExcelUtils<CctBlogclass>(CctBlogclass.class);
        return util.exportExcel(list, "CctBlogclass");
    }

}
