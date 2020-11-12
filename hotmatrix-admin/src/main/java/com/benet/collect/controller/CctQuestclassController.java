package com.benet.collect.controller;

import java.util.ArrayList;
import java.util.List;
import com.benet.common.core.pager.PageRequest;
import com.benet.common.core.pager.TableDataInfo;
import com.benet.common.utils.uuid.UuidUtils;
import com.benet.common.utils.web.ServletUtils;
import com.benet.framework.security.LoginUser;
import com.benet.framework.security.service.MyJwtokenService;
import com.benet.system.domain.SysConteeclass;
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
import com.benet.collect.domain.CctQuestclass;
import com.benet.collect.service.ICctQuestclassService;
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
public class CctQuestclassController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ICctQuestclassService cctQuestclassService;
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
        int count = cctQuestclassService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());
        List<CctQuestclass> list = cctQuestclassService.getRecordsByPaging(loginUser.getUser().getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), pRequest.getCondition(), "id", "Asc");
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
        int count = cctQuestclassService.getCountByCondition(loginUser.getUser().getAppCode(),"");
        List<ItemObjectVo> list = buildItemTree(loginUser.getUser().getAppCode(),"0");
        return getDataTable(list, count);
    }

    private List<ItemObjectVo> buildItemTree(String appCode, String parentNo) {

        List<ItemObjectVo> itemTree = null;
        ItemObjectVo item = null;
        List<CctQuestclass> infoList = cctQuestclassService.getRecordsByClassNo(appCode,parentNo);

        if (infoList != null && infoList.size() > 0) {
            itemTree = new ArrayList<>();
            for (CctQuestclass info : infoList) {
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
    public AjaxResult insert(@RequestBody CctQuestclass cctQuestclass) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        cctQuestclass.setClassNo(UuidUtils.shortUUID());
        cctQuestclass.setCreateBy(loginUser.getUser().getUserNo());
        cctQuestclass.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(cctQuestclassService.AddNewRecord(loginUser.getUser().getAppCode(),cctQuestclass));
    }

    /**
     * 编辑测题类型
     */
    @PreAuthorize("@ps.hasPermit('collect:questclass:update')")
    @Oplog(title = "测题类型", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody CctQuestclass cctQuestclass) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        cctQuestclass.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cctQuestclassService.UpdateRecord(loginUser.getUser().getAppCode(),cctQuestclass));
        }

    /**
     * 保存测题类型
     */
    @PreAuthorize("@ps.hasPermit('collect:questclass:save')")
    @Oplog(title = "测题类型", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody CctQuestclass cctQuestclass) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(cctQuestclassService.getRecordByNo(loginUser.getUser().getAppCode(),cctQuestclass.getClassNo()))) {
            cctQuestclass.setClassNo(UuidUtils.shortUUID());
            cctQuestclass.setCreateBy(loginUser.getUser().getUserNo());
            cctQuestclass.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cctQuestclassService.AddNewRecord(loginUser.getUser().getAppCode(),cctQuestclass));
        } else {
            cctQuestclass.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cctQuestclassService.UpdateRecord(loginUser.getUser().getAppCode(),cctQuestclass));
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
        return toAjax(cctQuestclassService.SoftDeleteByNos(loginUser.getUser().getAppCode(),ids));
    }

    /**
     * 获取测题类型详细信息
     */
    @PreAuthorize("@ps.hasPermit('collect:questclass:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(cctQuestclassService.getRecordByNo(loginUser.getUser().getAppCode(),id));
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
        int count = cctQuestclassService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());

        List<CctQuestclass> list = cctQuestclassService.getRecordsByPaging(loginUser.getUser().getAppCode(),1,count,pRequest.getCondition(),"id","Asc");
        ExcelUtils<CctQuestclass> util = new ExcelUtils<CctQuestclass>(CctQuestclass.class);
        return util.exportExcel(list, "CctQuestclass");
    }

}
