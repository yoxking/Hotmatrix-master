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
import com.benet.system.domain.SysDicttype;
import com.benet.system.service.ISysDicttypeService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.core.pager.TableDataInfo;

/**
 * 字典类型Controller
 * 
 * @author yoxking
 * @date 2020-04-23
 */
@RestController
@RequestMapping("/sys/dicttype")
public class SysDicttypeController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ISysDicttypeService sysDicttypeService;
    /**
     * 首页
     */
    //@PreAuthorize("@ps.hasPermit('system:dicttype:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询字典类型列表
     */
    //@PreAuthorize("@ps.hasPermit('system:dicttype:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        int count = sysDicttypeService.getCountByCondition(pRequest.getCondition());
        List<SysDicttype> list = sysDicttypeService.getRecordsByPaging(pRequest.getPageIndex(), pRequest.getPageSize(), pRequest.getCondition(), "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增字典类型
     */
    //@PreAuthorize("@ps.hasPermit('system:dicttype:addnew')")
    @Oplog(title = "字典类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody SysDicttype sysDicttype) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        sysDicttype.setDictNo(UuidUtils.shortUUID());
        sysDicttype.setCreateBy(loginUser.getUser().getUserNo());
        sysDicttype.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(sysDicttypeService.AddNewRecord(sysDicttype));
    }

    /**
     * 编辑字典类型
     */
    //@PreAuthorize("@ps.hasPermit('system:dicttype:update')")
    @Oplog(title = "字典类型", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody SysDicttype sysDicttype) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        sysDicttype.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(sysDicttypeService.UpdateRecord(sysDicttype));
        }

    /**
     * 保存字典类型
     */
    //@PreAuthorize("@ps.hasPermit('system:dicttype:save')")
    @Oplog(title = "字典类型", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody SysDicttype sysDicttype) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(sysDicttypeService.getRecordByNo(sysDicttype.getDictNo()))) {
            sysDicttype.setDictNo(UuidUtils.shortUUID());
            sysDicttype.setCreateBy(loginUser.getUser().getUserNo());
            sysDicttype.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(sysDicttypeService.AddNewRecord(sysDicttype));
        } else {
            sysDicttype.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(sysDicttypeService.UpdateRecord(sysDicttype));
        }
    }

    /**
     * 删除字典类型
     */
    //@PreAuthorize("@ps.hasPermit('system:dicttype:delete')")
    @Oplog(title = "字典类型", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        return toAjax(sysDicttypeService.SoftDeleteByNos(ids));
    }

    /**
     * 获取字典类型详细信息
     */
    //@PreAuthorize("@ps.hasPermit('system:dicttype:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        return AjaxResult.success(sysDicttypeService.getRecordByNo(id));
    }

    /**
     * 导出字典类型列表
     */
    //@PreAuthorize("@ps.hasPermit('system:dicttype:export')")
    @Oplog(title = "字典类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        int count = sysDicttypeService.getCountByCondition(pRequest.getCondition());

        List<SysDicttype> list = sysDicttypeService.getRecordsByPaging(1,count,pRequest.getCondition(),"id","Asc");
        ExcelUtils<SysDicttype> util = new ExcelUtils<SysDicttype>(SysDicttype.class);
        return util.exportExcel(list, "SysDicttype");
    }

}
