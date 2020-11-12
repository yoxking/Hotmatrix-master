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
import com.benet.collect.domain.CctQuestopts;
import com.benet.collect.service.ICctQuestoptsService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;

/**
 * 测题选项Controller
 * 
 * @author yoxking
 * @date 2020-08-27
 */
@Api(value = "collect/questopts", tags = "测题选项控制器")
@RestController
@RequestMapping("/collect/questopts")
public class CctQuestoptsController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ICctQuestoptsService cctQuestoptsService;
    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('collect:questopts:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询测题选项列表
     */
    @PreAuthorize("@ps.hasPermit('collect:questopts:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cctQuestoptsService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());
        List<CctQuestopts> list = cctQuestoptsService.getRecordsByPaging(loginUser.getUser().getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), pRequest.getCondition(), "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增测题选项
     */
    @PreAuthorize("@ps.hasPermit('collect:questopts:addnew')")
    @Oplog(title = "测题选项", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody CctQuestopts cctQuestopts) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        cctQuestopts.setOptsNo(UuidUtils.shortUUID());
        cctQuestopts.setCreateBy(loginUser.getUser().getUserNo());
        cctQuestopts.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(cctQuestoptsService.AddNewRecord(loginUser.getUser().getAppCode(),cctQuestopts));
    }

    /**
     * 编辑测题选项
     */
    @PreAuthorize("@ps.hasPermit('collect:questopts:update')")
    @Oplog(title = "测题选项", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody CctQuestopts cctQuestopts) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        cctQuestopts.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cctQuestoptsService.UpdateRecord(loginUser.getUser().getAppCode(),cctQuestopts));
        }

    /**
     * 保存测题选项
     */
    @PreAuthorize("@ps.hasPermit('collect:questopts:save')")
    @Oplog(title = "测题选项", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody CctQuestopts cctQuestopts) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(cctQuestoptsService.getRecordByNo(loginUser.getUser().getAppCode(),cctQuestopts.getOptsNo()))) {
            cctQuestopts.setOptsNo(UuidUtils.shortUUID());
            cctQuestopts.setCreateBy(loginUser.getUser().getUserNo());
            cctQuestopts.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cctQuestoptsService.AddNewRecord(loginUser.getUser().getAppCode(),cctQuestopts));
        } else {
            cctQuestopts.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cctQuestoptsService.UpdateRecord(loginUser.getUser().getAppCode(),cctQuestopts));
        }
    }

    /**
     * 删除测题选项
     */
    @PreAuthorize("@ps.hasPermit('collect:questopts:delete')")
    @Oplog(title = "测题选项", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(cctQuestoptsService.SoftDeleteByNos(loginUser.getUser().getAppCode(),ids));
    }

    /**
     * 获取测题选项详细信息
     */
    @PreAuthorize("@ps.hasPermit('collect:questopts:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(cctQuestoptsService.getRecordByNo(loginUser.getUser().getAppCode(),id));
    }

    /**
     * 导出测题选项列表
     */
    @PreAuthorize("@ps.hasPermit('collect:questopts:export')")
    @Oplog(title = "测题选项", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cctQuestoptsService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());

        List<CctQuestopts> list = cctQuestoptsService.getRecordsByPaging(loginUser.getUser().getAppCode(),1,count,pRequest.getCondition(),"id","Asc");
        ExcelUtils<CctQuestopts> util = new ExcelUtils<CctQuestopts>(CctQuestopts.class);
        return util.exportExcel(list, "CctQuestopts");
    }

}
