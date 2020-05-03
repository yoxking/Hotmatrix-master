package com.benet.sys.controller;

import java.util.ArrayList;
import java.util.List;
import com.benet.common.core.pager.PageRequest;
import com.benet.common.utils.uuid.UuidUtils;
import com.benet.common.utils.web.ServletUtils;
import com.benet.framework.security.LoginUser;
import com.benet.framework.security.service.MyJwtokenService;
import com.benet.sys.vmodel.ItemObjectVo;
import com.benet.system.domain.SysAppclass;
import com.benet.system.domain.SysDicttype;
import com.benet.system.service.ISysAppclassService;
import com.benet.system.service.ISysDicttypeService;
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
import com.benet.system.domain.SysAppinfo;
import com.benet.system.service.ISysAppinfoService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.core.pager.TableDataInfo;

/**
 * 应用信息Controller
 * 
 * @author yoxking
 * @date 2020-04-20
 */
@RestController
@RequestMapping("/sys/appinfo")
public class SysAppinfoController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ISysAppclassService sysAppclassService;

    @Autowired
    private ISysAppinfoService sysAppinfoService;
    /**
     * 首页
     */
    //@PreAuthorize("@ps.hasPermit('system:appinfo:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询应用信息列表
     */
    //@PreAuthorize("@ps.hasPermit('system:appinfo:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        int count = sysAppinfoService.getCountByCondition(pRequest.getCondition());
        List<SysAppinfo> list = sysAppinfoService.getRecordsByPaging(pRequest.getPageIndex(), pRequest.getPageSize(), pRequest.getCondition(), "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 查询内容信息列表
     */
    //@PreAuthorize("@ps.hasPermit('system:contentinfo:listall')")
    @GetMapping(value = "/classlist")
    public TableDataInfo classlist()
    {
        List<SysAppclass> list = sysAppclassService.getAllRecords();
        return getDataTable(convertList(list), list.size());
    }

    private List<ItemObjectVo> convertList(List<SysAppclass> list){

        List<ItemObjectVo> itemList=new ArrayList<>();
        ItemObjectVo item=null;
        if(list!=null&&list.size()>0){
            for(SysAppclass info:list){
                item=new ItemObjectVo();
                item.setId(info.getClassNo());
                item.setLabel(info.getClassName());
                item.setChildren(null);

                itemList.add(item);
            }
        }
        return itemList;
    }

    /**
     * 新增应用信息
     */
    //@PreAuthorize("@ps.hasPermit('system:appinfo:insert')")
    @Oplog(title = "应用信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody SysAppinfo sysAppinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        sysAppinfo.setAppNo(UuidUtils.shortUUID());
        sysAppinfo.setCreateBy(loginUser.getUser().getUserNo());
        sysAppinfo.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(sysAppinfoService.AddNewRecord(sysAppinfo));
    }

    /**
     * 编辑应用信息
     */
    //@PreAuthorize("@ps.hasPermit('system:appinfo:update')")
    @Oplog(title = "应用信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult update(@RequestBody SysAppinfo sysAppinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        sysAppinfo.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(sysAppinfoService.UpdateRecord(sysAppinfo));
    }

    /**
     * 保存应用信息
     */
    //@PreAuthorize("@ps.hasPermit('system:appinfo:save')")
    @Oplog(title = "应用信息", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody SysAppinfo sysAppinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(sysAppinfoService.getRecordByNo(sysAppinfo.getAppNo()))) {
            sysAppinfo.setAppNo(UuidUtils.shortUUID());
            sysAppinfo.setCreateBy(loginUser.getUser().getUserNo());
            sysAppinfo.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(sysAppinfoService.AddNewRecord(sysAppinfo));
        } else {
            sysAppinfo.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(sysAppinfoService.UpdateRecord(sysAppinfo));
        }
    }

    /**
     * 删除应用信息
     */
    //@PreAuthorize("@ps.hasPermit('system:appinfo:delete')")
    @Oplog(title = "应用信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        return toAjax(sysAppinfoService.SoftDeleteByNos(ids));
    }

    /**
     * 获取应用信息详细信息
     */
    //@PreAuthorize("@ps.hasPermit('system:appinfo:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        return AjaxResult.success(sysAppinfoService.getRecordByNo(id));
    }

    /**
     * 导出应用信息列表
     */
    //@PreAuthorize("@ps.hasPermit('system:appinfo:export')")
    @Oplog(title = "应用信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        int count = sysAppinfoService.getCountByCondition(pRequest.getCondition());

        List<SysAppinfo> list = sysAppinfoService.getRecordsByPaging(1,count,pRequest.getCondition(),"id","Asc");
        ExcelUtils<SysAppinfo> util = new ExcelUtils<SysAppinfo>(SysAppinfo.class);
        return util.exportExcel(list, "SysAppinfo");
    }

}
