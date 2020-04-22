package com.benet.sys.controller;

import java.util.List;
import com.benet.common.core.pager.PageRequest;
import com.benet.common.utils.uuid.UuidUtils;
import com.benet.common.utils.web.ServletUtils;
import com.benet.framework.security.LoginUser;
import com.benet.framework.security.service.MyJwtokenService;
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
import com.benet.system.domain.SysDictinfo;
import com.benet.system.service.ISysDictinfoService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.core.pager.TableDataInfo;

/**
 * 字典数据Controller
 * 
 * @author yoxking
 * @date 2020-04-20
 */
@RestController
@RequestMapping("/sys/dictinfo")
public class SysDictinfoController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ISysDictinfoService sysDictinfoService;
    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('system:dictinfo:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询字典数据列表
     */
    @PreAuthorize("@ps.hasPermit('system:dictinfo:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        int count = sysDictinfoService.getCountByCondition(pRequest.getCondition());
        List<SysDictinfo> list = sysDictinfoService.getRecordsByPaging(pRequest.getPageIndex(), pRequest.getPageSize(), pRequest.getCondition(), "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增字典数据
     */
    @PreAuthorize("@ps.hasPermit('system:dictinfo:insert')")
    @Oplog(title = "字典数据", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody SysDictinfo sysDictinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        sysDictinfo.setDictNo(UuidUtils.shortUUID());
        sysDictinfo.setCreateBy(loginUser.getUser().getUserNo());
        sysDictinfo.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(sysDictinfoService.AddNewRecord(sysDictinfo));
    }

    /**
     * 编辑字典数据
     */
    @PreAuthorize("@ps.hasPermit('system:dictinfo:update')")
    @Oplog(title = "字典数据", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody SysDictinfo sysDictinfo) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        sysDictinfo.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(sysDictinfoService.UpdateRecord(sysDictinfo));
        }

    /**
     * 保存字典数据
     */
    @PreAuthorize("@ps.hasPermit('system:dictinfo:save')")
    @Oplog(title = "字典数据", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody SysDictinfo sysDictinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(sysDictinfoService.getRecordByNo(sysDictinfo.getDictNo()))) {
            sysDictinfo.setDictNo(UuidUtils.shortUUID());
            sysDictinfo.setCreateBy(loginUser.getUser().getUserNo());
            sysDictinfo.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(sysDictinfoService.AddNewRecord(sysDictinfo));
        } else {
            sysDictinfo.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(sysDictinfoService.UpdateRecord(sysDictinfo));
        }
    }

    /**
     * 删除字典数据
     */
    @PreAuthorize("@ps.hasPermit('system:dictinfo:delete')")
    @Oplog(title = "字典数据", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        return toAjax(sysDictinfoService.SoftDeleteByNos(ids));
    }

    /**
     * 获取字典数据详细信息
     */
    @PreAuthorize("@ps.hasPermit('system:dictinfo:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        return AjaxResult.success(sysDictinfoService.getRecordByNo(id));
    }

    /**
     * 导出字典数据列表
     */
    @PreAuthorize("@ps.hasPermit('system:dictinfo:export')")
    @Oplog(title = "字典数据", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        int count = sysDictinfoService.getCountByCondition(pRequest.getCondition());

        List<SysDictinfo> list = sysDictinfoService.getRecordsByPaging(1,count,pRequest.getCondition(),"id","Asc");
        ExcelUtils<SysDictinfo> util = new ExcelUtils<SysDictinfo>(SysDictinfo.class);
        return util.exportExcel(list, "SysDictinfo");
    }

}
