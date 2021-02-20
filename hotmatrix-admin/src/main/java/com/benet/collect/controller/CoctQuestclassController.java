package com.benet.collect.controller;

import java.util.ArrayList;
import java.util.List;

import com.benet.collect.domain.CoctQuestclass;
import com.benet.collect.service.ICoctQuestclassService;
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
 * 测题类型Controller
 * 
 * @author yoxking
 * @date 2020-08-27
 */
@Api(value = "collect/questclass", tags = "测题类型控制器")
@RestController
@RequestMapping("/collect/questclass")
public class CoctQuestclassController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ICoctQuestclassService coctQuestclassService;
    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('collect:questclass:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询测题类型列表
     */
    @PreAuthorize("@ps.hasPermit('collect:questclass:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = coctQuestclassService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());
        List<CoctQuestclass> list = coctQuestclassService.getRecordsByPaging(loginUser.getUser().getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), pRequest.getCondition(), "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 查询内容类型列表
     */
    @PreAuthorize("@ps.hasPermit('collect:questclass:list')")
    @GetMapping(value = "/tree")
    public TableDataInfo tree()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = coctQuestclassService.getCountByCondition(loginUser.getUser().getAppCode(),"");
        List<ItemObjectVo> list = buildItemTree(loginUser.getUser().getAppCode(),"0");
        return getDataTable(list, count);
    }

    private List<ItemObjectVo> buildItemTree(String appCode, String parentNo) {

        List<ItemObjectVo> itemTree = null;
        ItemObjectVo item = null;
        List<CoctQuestclass> infoList = coctQuestclassService.getRecordsByClassNo(appCode,parentNo);

        if (infoList != null && infoList.size() > 0) {
            itemTree = new ArrayList<>();
            for (CoctQuestclass info : infoList) {
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
     * 新增测题类型
     */
    @PreAuthorize("@ps.hasPermit('collect:questclass:addnew')")
    @Oplog(title = "测题类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody CoctQuestclass coctQuestclass) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        coctQuestclass.setClassNo(UuidUtils.shortUUID());
        coctQuestclass.setCreateBy(loginUser.getUser().getUserNo());
        coctQuestclass.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(coctQuestclassService.AddNewRecord(loginUser.getUser().getAppCode(),coctQuestclass));
    }

    /**
     * 编辑测题类型
     */
    @PreAuthorize("@ps.hasPermit('collect:questclass:update')")
    @Oplog(title = "测题类型", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody CoctQuestclass coctQuestclass) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        coctQuestclass.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(coctQuestclassService.UpdateRecord(loginUser.getUser().getAppCode(),coctQuestclass));
        }

    /**
     * 保存测题类型
     */
    @PreAuthorize("@ps.hasPermit('collect:questclass:save')")
    @Oplog(title = "测题类型", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody CoctQuestclass coctQuestclass) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(coctQuestclassService.getRecordByNo(loginUser.getUser().getAppCode(),coctQuestclass.getClassNo()))) {
            coctQuestclass.setClassNo(UuidUtils.shortUUID());
            coctQuestclass.setCreateBy(loginUser.getUser().getUserNo());
            coctQuestclass.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(coctQuestclassService.AddNewRecord(loginUser.getUser().getAppCode(),coctQuestclass));
        } else {
            coctQuestclass.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(coctQuestclassService.UpdateRecord(loginUser.getUser().getAppCode(),coctQuestclass));
        }
    }

    /**
     * 删除测题类型
     */
    @PreAuthorize("@ps.hasPermit('collect:questclass:delete')")
    @Oplog(title = "测题类型", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(coctQuestclassService.SoftDeleteByNos(loginUser.getUser().getAppCode(),ids));
    }

    /**
     * 获取测题类型详细信息
     */
    @PreAuthorize("@ps.hasPermit('collect:questclass:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(coctQuestclassService.getRecordByNo(loginUser.getUser().getAppCode(),id));
    }

    /**
     * 导出测题类型列表
     */
    @PreAuthorize("@ps.hasPermit('collect:questclass:export')")
    @Oplog(title = "测题类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = coctQuestclassService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());

        List<CoctQuestclass> list = coctQuestclassService.getRecordsByPaging(loginUser.getUser().getAppCode(),1,count,pRequest.getCondition(),"id","Asc");
        ExcelUtils<CoctQuestclass> util = new ExcelUtils<CoctQuestclass>(CoctQuestclass.class);
        return util.exportExcel(list, "CoctQuestclass");
    }

}
