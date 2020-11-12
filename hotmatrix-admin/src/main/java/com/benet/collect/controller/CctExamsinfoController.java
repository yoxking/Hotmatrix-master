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
import com.benet.collect.domain.CctExamsinfo;
import com.benet.collect.service.ICctExamsinfoService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.core.pager.TableDataInfo;

/**
 * 测评信息Controller
 * 
 * @author yoxking
 * @date 2020-11-10
 */
@Api(value = "collect/examsinfo", tags = "测评信息控制器")
@RestController
@RequestMapping("/collect/examsinfo")
public class CctExamsinfoController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ICctExamsinfoService cctExamsinfoService;
    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('collect:examsinfo:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询测评信息列表
     */
    @PreAuthorize("@ps.hasPermit('collect:examsinfo:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cctExamsinfoService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());
        List<CctExamsinfo> list = cctExamsinfoService.getRecordsByPaging(loginUser.getUser().getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), pRequest.getCondition(), "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增测评信息
     */
    @PreAuthorize("@ps.hasPermit('collect:examsinfo:addnew')")
    @Oplog(title = "测评信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody CctExamsinfo cctExamsinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        cctExamsinfo.setExamsNo(UuidUtils.shortUUID());
        cctExamsinfo.setCreateBy(loginUser.getUser().getUserNo());
        cctExamsinfo.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(cctExamsinfoService.AddNewRecord(loginUser.getUser().getAppCode(),cctExamsinfo));
    }

    /**
     * 编辑测评信息
     */
    @PreAuthorize("@ps.hasPermit('collect:examsinfo:update')")
    @Oplog(title = "测评信息", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody CctExamsinfo cctExamsinfo) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        cctExamsinfo.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cctExamsinfoService.UpdateRecord(loginUser.getUser().getAppCode(),cctExamsinfo));
        }

    /**
     * 保存测评信息
     */
    @PreAuthorize("@ps.hasPermit('collect:examsinfo:save')")
    @Oplog(title = "测评信息", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody CctExamsinfo cctExamsinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(cctExamsinfoService.getRecordByNo(loginUser.getUser().getAppCode(),cctExamsinfo.getExamsNo()))) {
            cctExamsinfo.setExamsNo(UuidUtils.shortUUID());
            cctExamsinfo.setCreateBy(loginUser.getUser().getUserNo());
            cctExamsinfo.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cctExamsinfoService.AddNewRecord(loginUser.getUser().getAppCode(),cctExamsinfo));
        } else {
            cctExamsinfo.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cctExamsinfoService.UpdateRecord(loginUser.getUser().getAppCode(),cctExamsinfo));
        }
    }

    /**
     * 删除测评信息
     */
    @PreAuthorize("@ps.hasPermit('collect:examsinfo:delete')")
    @Oplog(title = "测评信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(cctExamsinfoService.SoftDeleteByNos(loginUser.getUser().getAppCode(),ids));
    }

    /**
     * 获取测评信息详细信息
     */
    @PreAuthorize("@ps.hasPermit('collect:examsinfo:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(cctExamsinfoService.getRecordByNo(loginUser.getUser().getAppCode(),id));
    }

    /**
     * 导出测评信息列表
     */
    @PreAuthorize("@ps.hasPermit('collect:examsinfo:export')")
    @Oplog(title = "测评信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cctExamsinfoService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());

        List<CctExamsinfo> list = cctExamsinfoService.getRecordsByPaging(loginUser.getUser().getAppCode(),1,count,pRequest.getCondition(),"id","Asc");
        ExcelUtils<CctExamsinfo> util = new ExcelUtils<CctExamsinfo>(CctExamsinfo.class);
        return util.exportExcel(list, "CctExamsinfo");
    }

}
