package com.benet.sys.controller;

import java.util.ArrayList;
import java.util.List;
import com.benet.common.core.pager.PageRequest;
import com.benet.common.utils.uuid.UuidUtils;
import com.benet.common.utils.web.ServletUtils;
import com.benet.framework.security.LoginUser;
import com.benet.framework.security.service.MyJwtokenService;
import com.benet.sys.vmodel.ItemObjectVo;
import com.benet.system.domain.SysRenteclass;
import com.benet.system.service.ISysRenteclassService;
import io.swagger.annotations.Api;
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
import com.benet.system.domain.SysRenterinfo;
import com.benet.system.service.ISysRenterinfoService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.core.pager.TableDataInfo;

/**
 * 租户信息Controller
 * 
 * @author yoxking
 * @date 2020-04-20
 */
@Api(value = "sys/renterinfo", tags = "租户信息Controller")
@RestController
@RequestMapping("/sys/renterinfo")
public class SysRenterinfoController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ISysRenteclassService sysRenteclassService;

    @Autowired
    private ISysRenterinfoService sysRenterinfoService;
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
     * 查询租户信息列表
     */
    //@PreAuthorize("@ps.hasPermit('system:appinfo:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        int count = sysRenterinfoService.getCountByCondition(pRequest.getCondition());
        List<SysRenterinfo> list = sysRenterinfoService.getRecordsByPaging(pRequest.getPageIndex(), pRequest.getPageSize(), pRequest.getCondition(), "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 查询内容信息列表
     */
    //@PreAuthorize("@ps.hasPermit('system:contentinfo:listall')")
    @GetMapping(value = "/classlist")
    public TableDataInfo classlist()
    {
        List<SysRenteclass> list = sysRenteclassService.getAllRecords();
        return getDataTable(convertList(list), list.size());
    }

    private List<ItemObjectVo> convertList(List<SysRenteclass> list){

        List<ItemObjectVo> itemList=new ArrayList<>();
        ItemObjectVo item=null;
        if(list!=null&&list.size()>0){
            for(SysRenteclass info:list){
                item=new ItemObjectVo();
                item.setId(info.getClassNo());
                item.setKey(info.getClassNo());
                item.setTitle(info.getClassName());
                item.setValue(info.getClassNo());
                item.setChildren(null);

                itemList.add(item);
            }
        }
        return itemList;
    }

    /**
     * 新增租户信息
     */
    //@PreAuthorize("@ps.hasPermit('system:appinfo:insert')")
    @Oplog(title = "租户信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody SysRenterinfo sysAppinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        sysAppinfo.setRentNo(UuidUtils.shortUUID());
        sysAppinfo.setCreateBy(loginUser.getUser().getUserNo());
        sysAppinfo.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(sysRenterinfoService.AddNewRecord(sysAppinfo));
    }

    /**
     * 编辑租户信息
     */
    //@PreAuthorize("@ps.hasPermit('system:appinfo:update')")
    @Oplog(title = "租户信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult update(@RequestBody SysRenterinfo sysAppinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        sysAppinfo.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(sysRenterinfoService.UpdateRecord(sysAppinfo));
    }

    /**
     * 保存租户信息
     */
    //@PreAuthorize("@ps.hasPermit('system:appinfo:save')")
    @Oplog(title = "租户信息", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody SysRenterinfo sysAppinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(sysRenterinfoService.getRecordByNo(sysAppinfo.getRentNo()))) {
            sysAppinfo.setRentNo(UuidUtils.shortUUID());
            sysAppinfo.setCreateBy(loginUser.getUser().getUserNo());
            sysAppinfo.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(sysRenterinfoService.AddNewRecord(sysAppinfo));
        } else {
            sysAppinfo.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(sysRenterinfoService.UpdateRecord(sysAppinfo));
        }
    }

    /**
     * 删除租户信息
     */
    //@PreAuthorize("@ps.hasPermit('system:appinfo:delete')")
    @Oplog(title = "租户信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        return toAjax(sysRenterinfoService.SoftDeleteByNos(ids));
    }

    /**
     * 获取租户信息详细信息
     */
    //@PreAuthorize("@ps.hasPermit('system:appinfo:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        return AjaxResult.success(sysRenterinfoService.getRecordByNo(id));
    }

    /**
     * 导出租户信息列表
     */
    //@PreAuthorize("@ps.hasPermit('system:appinfo:export')")
    @Oplog(title = "租户信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        int count = sysRenterinfoService.getCountByCondition(pRequest.getCondition());

        List<SysRenterinfo> list = sysRenterinfoService.getRecordsByPaging(1,count,pRequest.getCondition(),"id","Asc");
        ExcelUtils<SysRenterinfo> util = new ExcelUtils<SysRenterinfo>(SysRenterinfo.class);
        return util.exportExcel(list, "SysAppinfo");
    }

}
