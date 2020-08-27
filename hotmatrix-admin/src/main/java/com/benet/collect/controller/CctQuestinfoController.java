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
import com.benet.collect.domain.CctQuestinfo;
import com.benet.collect.service.ICctQuestinfoService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;

/**
 * 测评题库Controller
 * 
 * @author yoxking
 * @date 2020-08-27
 */
@Api(value = "collect/questinfo", tags = "测评题库控制器")
@RestController
@RequestMapping("/collect/questinfo")
public class CctQuestinfoController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ICctQuestinfoService cctQuestinfoService;
    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('collect:questinfo:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询测评题库列表
     */
    @PreAuthorize("@ps.hasPermit('collect:questinfo:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cctQuestinfoService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());
        List<CctQuestinfo> list = cctQuestinfoService.getRecordsByPaging(loginUser.getUser().getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), pRequest.getCondition(), "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增测评题库
     */
    @PreAuthorize("@ps.hasPermit('collect:questinfo:addnew')")
    @Oplog(title = "测评题库", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody CctQuestinfo cctQuestinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        cctQuestinfo.setQuestNo(UuidUtils.shortUUID());
        cctQuestinfo.setCreateBy(loginUser.getUser().getUserNo());
        cctQuestinfo.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(cctQuestinfoService.AddNewRecord(loginUser.getUser().getAppCode(),cctQuestinfo));
    }

    /**
     * 编辑测评题库
     */
    @PreAuthorize("@ps.hasPermit('collect:questinfo:update')")
    @Oplog(title = "测评题库", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody CctQuestinfo cctQuestinfo) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        cctQuestinfo.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cctQuestinfoService.UpdateRecord(loginUser.getUser().getAppCode(),cctQuestinfo));
        }

    /**
     * 保存测评题库
     */
    @PreAuthorize("@ps.hasPermit('collect:questinfo:save')")
    @Oplog(title = "测评题库", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody CctQuestinfo cctQuestinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(cctQuestinfoService.getRecordByNo(loginUser.getUser().getAppCode(),cctQuestinfo.getQuestNo()))) {
            cctQuestinfo.setQuestNo(UuidUtils.shortUUID());
            cctQuestinfo.setCreateBy(loginUser.getUser().getUserNo());
            cctQuestinfo.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cctQuestinfoService.AddNewRecord(loginUser.getUser().getAppCode(),cctQuestinfo));
        } else {
            cctQuestinfo.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cctQuestinfoService.UpdateRecord(loginUser.getUser().getAppCode(),cctQuestinfo));
        }
    }

    /**
     * 删除测评题库
     */
    @PreAuthorize("@ps.hasPermit('collect:questinfo:delete')")
    @Oplog(title = "测评题库", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(cctQuestinfoService.SoftDeleteByNos(loginUser.getUser().getAppCode(),ids));
    }

    /**
     * 获取测评题库详细信息
     */
    @PreAuthorize("@ps.hasPermit('collect:questinfo:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(cctQuestinfoService.getRecordByNo(loginUser.getUser().getAppCode(),id));
    }

    /**
     * 导出测评题库列表
     */
    @PreAuthorize("@ps.hasPermit('collect:questinfo:export')")
    @Oplog(title = "测评题库", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cctQuestinfoService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());

        List<CctQuestinfo> list = cctQuestinfoService.getRecordsByPaging(loginUser.getUser().getAppCode(),1,count,pRequest.getCondition(),"id","Asc");
        ExcelUtils<CctQuestinfo> util = new ExcelUtils<CctQuestinfo>(CctQuestinfo.class);
        return util.exportExcel(list, "CctQuestinfo");
    }

}
