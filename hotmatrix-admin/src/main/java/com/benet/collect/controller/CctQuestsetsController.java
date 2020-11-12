package com.benet.collect.controller;

import java.util.List;
import com.benet.common.core.pager.PageRequest;
import com.benet.common.core.pager.TableDataInfo;
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
import com.benet.collect.domain.CctQuestsets;
import com.benet.collect.service.ICctQuestsetsService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.core.pager.TableDataInfo;

/**
 * 题库信息Controller
 * 
 * @author yoxking
 * @date 2020-11-10
 */
@Api(value = "collect/questsets", tags = "题库信息控制器")
@RestController
@RequestMapping("/collect/questsets")
public class CctQuestsetsController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ICctQuestsetsService cctQuestsetsService;
    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('collect:questsets:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询题库信息列表
     */
    @PreAuthorize("@ps.hasPermit('collect:questsets:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cctQuestsetsService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());
        List<CctQuestsets> list = cctQuestsetsService.getRecordsByPaging(loginUser.getUser().getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), pRequest.getCondition(), "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增题库信息
     */
    @PreAuthorize("@ps.hasPermit('collect:questsets:addnew')")
    @Oplog(title = "题库信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody CctQuestsets cctQuestsets) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        cctQuestsets.setSetsNo(UuidUtils.shortUUID());
        cctQuestsets.setCreateBy(loginUser.getUser().getUserNo());
        cctQuestsets.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(cctQuestsetsService.AddNewRecord(loginUser.getUser().getAppCode(),cctQuestsets));
    }

    /**
     * 编辑题库信息
     */
    @PreAuthorize("@ps.hasPermit('collect:questsets:update')")
    @Oplog(title = "题库信息", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody CctQuestsets cctQuestsets) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        cctQuestsets.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cctQuestsetsService.UpdateRecord(loginUser.getUser().getAppCode(),cctQuestsets));
        }

    /**
     * 保存题库信息
     */
    @PreAuthorize("@ps.hasPermit('collect:questsets:save')")
    @Oplog(title = "题库信息", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody CctQuestsets cctQuestsets) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(cctQuestsetsService.getRecordByNo(loginUser.getUser().getAppCode(),cctQuestsets.getSetsNo()))) {
            cctQuestsets.setSetsNo(UuidUtils.shortUUID());
            cctQuestsets.setCreateBy(loginUser.getUser().getUserNo());
            cctQuestsets.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cctQuestsetsService.AddNewRecord(loginUser.getUser().getAppCode(),cctQuestsets));
        } else {
            cctQuestsets.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cctQuestsetsService.UpdateRecord(loginUser.getUser().getAppCode(),cctQuestsets));
        }
    }

    /**
     * 删除题库信息
     */
    @PreAuthorize("@ps.hasPermit('collect:questsets:delete')")
    @Oplog(title = "题库信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(cctQuestsetsService.SoftDeleteByNos(loginUser.getUser().getAppCode(),ids));
    }

    /**
     * 获取题库信息详细信息
     */
    @PreAuthorize("@ps.hasPermit('collect:questsets:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(cctQuestsetsService.getRecordByNo(loginUser.getUser().getAppCode(),id));
    }

    /**
     * 导出题库信息列表
     */
    @PreAuthorize("@ps.hasPermit('collect:questsets:export')")
    @Oplog(title = "题库信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cctQuestsetsService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());

        List<CctQuestsets> list = cctQuestsetsService.getRecordsByPaging(loginUser.getUser().getAppCode(),1,count,pRequest.getCondition(),"id","Asc");
        ExcelUtils<CctQuestsets> util = new ExcelUtils<CctQuestsets>(CctQuestsets.class);
        return util.exportExcel(list, "CctQuestsets");
    }

}
