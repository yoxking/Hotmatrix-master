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
import com.benet.collect.domain.CctSalonsinfo;
import com.benet.collect.service.ICctSalonsinfoService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;

/**
 * 活动信息Controller
 * 
 * @author yoxking
 * @date 2020-10-14
 */
@Api(value = "collect/salonsinfo", tags = "活动信息控制器")
@RestController
@RequestMapping("/collect/salonsinfo")
public class CctSalonsinfoController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ICctSalonsinfoService cctSalonsinfoService;
    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('collect:salonsinfo:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询活动信息列表
     */
    @PreAuthorize("@ps.hasPermit('collect:salonsinfo:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cctSalonsinfoService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());
        List<CctSalonsinfo> list = cctSalonsinfoService.getRecordsByPaging(loginUser.getUser().getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), pRequest.getCondition(), "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增活动信息
     */
    @PreAuthorize("@ps.hasPermit('collect:salonsinfo:addnew')")
    @Oplog(title = "活动信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody CctSalonsinfo cctSalonsinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        cctSalonsinfo.setSalonNo(UuidUtils.shortUUID());
        cctSalonsinfo.setCreateBy(loginUser.getUser().getUserNo());
        cctSalonsinfo.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(cctSalonsinfoService.AddNewRecord(loginUser.getUser().getAppCode(),cctSalonsinfo));
    }

    /**
     * 编辑活动信息
     */
    @PreAuthorize("@ps.hasPermit('collect:salonsinfo:update')")
    @Oplog(title = "活动信息", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody CctSalonsinfo cctSalonsinfo) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        cctSalonsinfo.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cctSalonsinfoService.UpdateRecord(loginUser.getUser().getAppCode(),cctSalonsinfo));
        }

    /**
     * 保存活动信息
     */
    @PreAuthorize("@ps.hasPermit('collect:salonsinfo:save')")
    @Oplog(title = "活动信息", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody CctSalonsinfo cctSalonsinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(cctSalonsinfoService.getRecordByNo(loginUser.getUser().getAppCode(),cctSalonsinfo.getSalonNo()))) {
            cctSalonsinfo.setSalonNo(UuidUtils.shortUUID());
            cctSalonsinfo.setCreateBy(loginUser.getUser().getUserNo());
            cctSalonsinfo.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cctSalonsinfoService.AddNewRecord(loginUser.getUser().getAppCode(),cctSalonsinfo));
        } else {
            cctSalonsinfo.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cctSalonsinfoService.UpdateRecord(loginUser.getUser().getAppCode(),cctSalonsinfo));
        }
    }

    /**
     * 删除活动信息
     */
    @PreAuthorize("@ps.hasPermit('collect:salonsinfo:delete')")
    @Oplog(title = "活动信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(cctSalonsinfoService.SoftDeleteByNos(loginUser.getUser().getAppCode(),ids));
    }

    /**
     * 获取活动信息详细信息
     */
    @PreAuthorize("@ps.hasPermit('collect:salonsinfo:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(cctSalonsinfoService.getRecordByNo(loginUser.getUser().getAppCode(),id));
    }

    /**
     * 导出活动信息列表
     */
    @PreAuthorize("@ps.hasPermit('collect:salonsinfo:export')")
    @Oplog(title = "活动信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cctSalonsinfoService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());

        List<CctSalonsinfo> list = cctSalonsinfoService.getRecordsByPaging(loginUser.getUser().getAppCode(),1,count,pRequest.getCondition(),"id","Asc");
        ExcelUtils<CctSalonsinfo> util = new ExcelUtils<CctSalonsinfo>(CctSalonsinfo.class);
        return util.exportExcel(list, "CctSalonsinfo");
    }

}
