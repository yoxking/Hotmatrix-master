package com.benet.collect.controller;

import java.util.ArrayList;
import java.util.List;

import com.benet.collect.domain.CoctPaperclass;
import com.benet.collect.service.ICoctPaperclassService;
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
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;

/**
 * 问卷类型Controller
 * 
 * @author yoxking
 * @date 2020-08-27
 */
@Api(value = "collect/paperclass", tags = "问卷类型控制器")
@RestController
@RequestMapping("/collect/paperclass")
public class CoctPaperclassController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ICoctPaperclassService coctPaperclassService;
    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('collect:paperclass:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询问卷类型列表
     */
    @PreAuthorize("@ps.hasPermit('collect:paperclass:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = coctPaperclassService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());
        List<CoctPaperclass> list = coctPaperclassService.getRecordsByPaging(loginUser.getUser().getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), pRequest.getCondition(), "id", "Asc");
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
        int count = coctPaperclassService.getCountByCondition(loginUser.getUser().getAppCode(),"");
        List<ItemObjectVo> list = buildItemTree(loginUser.getUser().getAppCode(),"0");
        return getDataTable(list, count);
    }

    private List<ItemObjectVo> buildItemTree(String appCode, String parentNo) {

        List<ItemObjectVo> itemTree = null;
        ItemObjectVo item = null;
        List<CoctPaperclass> infoList = coctPaperclassService.getRecordsByClassNo(appCode,parentNo);

        if (infoList != null && infoList.size() > 0) {
            itemTree = new ArrayList<>();
            for (CoctPaperclass info : infoList) {
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
     * 新增问卷类型
     */
    @PreAuthorize("@ps.hasPermit('collect:paperclass:addnew')")
    @Oplog(title = "问卷类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody CoctPaperclass coctPaperclass) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        coctPaperclass.setClassNo(UuidUtils.shortUUID());
        coctPaperclass.setCreateBy(loginUser.getUser().getUserNo());
        coctPaperclass.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(coctPaperclassService.AddNewRecord(loginUser.getUser().getAppCode(),coctPaperclass));
    }

    /**
     * 编辑问卷类型
     */
    @PreAuthorize("@ps.hasPermit('collect:paperclass:update')")
    @Oplog(title = "问卷类型", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody CoctPaperclass coctPaperclass) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        coctPaperclass.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(coctPaperclassService.UpdateRecord(loginUser.getUser().getAppCode(),coctPaperclass));
        }

    /**
     * 保存问卷类型
     */
    @PreAuthorize("@ps.hasPermit('collect:paperclass:save')")
    @Oplog(title = "问卷类型", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody CoctPaperclass coctPaperclass) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(coctPaperclassService.getRecordByNo(loginUser.getUser().getAppCode(),coctPaperclass.getClassNo()))) {
            coctPaperclass.setClassNo(UuidUtils.shortUUID());
            coctPaperclass.setCreateBy(loginUser.getUser().getUserNo());
            coctPaperclass.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(coctPaperclassService.AddNewRecord(loginUser.getUser().getAppCode(),coctPaperclass));
        } else {
            coctPaperclass.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(coctPaperclassService.UpdateRecord(loginUser.getUser().getAppCode(),coctPaperclass));
        }
    }

    /**
     * 删除问卷类型
     */
    @PreAuthorize("@ps.hasPermit('collect:paperclass:delete')")
    @Oplog(title = "问卷类型", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(coctPaperclassService.SoftDeleteByNos(loginUser.getUser().getAppCode(),ids));
    }

    /**
     * 获取问卷类型详细信息
     */
    @PreAuthorize("@ps.hasPermit('collect:paperclass:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(coctPaperclassService.getRecordByNo(loginUser.getUser().getAppCode(),id));
    }

    /**
     * 导出问卷类型列表
     */
    @PreAuthorize("@ps.hasPermit('collect:paperclass:export')")
    @Oplog(title = "问卷类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = coctPaperclassService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());

        List<CoctPaperclass> list = coctPaperclassService.getRecordsByPaging(loginUser.getUser().getAppCode(),1,count,pRequest.getCondition(),"id","Asc");
        ExcelUtils<CoctPaperclass> util = new ExcelUtils<CoctPaperclass>(CoctPaperclass.class);
        return util.exportExcel(list, "CoctPaperclass");
    }

}
