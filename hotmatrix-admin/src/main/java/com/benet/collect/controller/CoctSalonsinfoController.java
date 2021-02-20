package com.benet.collect.controller;

import java.util.List;

import com.benet.collect.domain.CoctSalonsinfo;
import com.benet.collect.service.ICoctSalonsinfoService;
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
public class CoctSalonsinfoController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ICoctSalonsinfoService coctSalonsinfoService;
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
        int count = coctSalonsinfoService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());
        List<CoctSalonsinfo> list = coctSalonsinfoService.getRecordsByPaging(loginUser.getUser().getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), pRequest.getCondition(), "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增活动信息
     */
    @PreAuthorize("@ps.hasPermit('collect:salonsinfo:addnew')")
    @Oplog(title = "活动信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody CoctSalonsinfo coctSalonsinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        coctSalonsinfo.setSalonNo(UuidUtils.shortUUID());
        coctSalonsinfo.setCreateBy(loginUser.getUser().getUserNo());
        coctSalonsinfo.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(coctSalonsinfoService.AddNewRecord(loginUser.getUser().getAppCode(),coctSalonsinfo));
    }

    /**
     * 编辑活动信息
     */
    @PreAuthorize("@ps.hasPermit('collect:salonsinfo:update')")
    @Oplog(title = "活动信息", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody CoctSalonsinfo coctSalonsinfo) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        coctSalonsinfo.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(coctSalonsinfoService.UpdateRecord(loginUser.getUser().getAppCode(),coctSalonsinfo));
        }

    /**
     * 保存活动信息
     */
    @PreAuthorize("@ps.hasPermit('collect:salonsinfo:save')")
    @Oplog(title = "活动信息", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody CoctSalonsinfo coctSalonsinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(coctSalonsinfoService.getRecordByNo(loginUser.getUser().getAppCode(),coctSalonsinfo.getSalonNo()))) {
            coctSalonsinfo.setSalonNo(UuidUtils.shortUUID());
            coctSalonsinfo.setCreateBy(loginUser.getUser().getUserNo());
            coctSalonsinfo.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(coctSalonsinfoService.AddNewRecord(loginUser.getUser().getAppCode(),coctSalonsinfo));
        } else {
            coctSalonsinfo.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(coctSalonsinfoService.UpdateRecord(loginUser.getUser().getAppCode(),coctSalonsinfo));
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
        return toAjax(coctSalonsinfoService.SoftDeleteByNos(loginUser.getUser().getAppCode(),ids));
    }

    /**
     * 获取活动信息详细信息
     */
    @PreAuthorize("@ps.hasPermit('collect:salonsinfo:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(coctSalonsinfoService.getRecordByNo(loginUser.getUser().getAppCode(),id));
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
        int count = coctSalonsinfoService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());

        List<CoctSalonsinfo> list = coctSalonsinfoService.getRecordsByPaging(loginUser.getUser().getAppCode(),1,count,pRequest.getCondition(),"id","Asc");
        ExcelUtils<CoctSalonsinfo> util = new ExcelUtils<CoctSalonsinfo>(CoctSalonsinfo.class);
        return util.exportExcel(list, "CoctSalonsinfo");
    }

}
