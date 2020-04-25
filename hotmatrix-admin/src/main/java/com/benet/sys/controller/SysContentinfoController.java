package com.benet.sys.controller;

import java.util.ArrayList;
import java.util.List;
import com.benet.common.core.pager.PageRequest;
import com.benet.common.utils.uuid.UuidUtils;
import com.benet.common.utils.web.ServletUtils;
import com.benet.framework.security.LoginUser;
import com.benet.framework.security.service.MyJwtokenService;
import com.benet.sys.vmodel.ItemObjectVo;
import com.benet.system.domain.SysContzclass;
import com.benet.system.service.ISysContzclassService;
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
import com.benet.system.domain.SysContentinfo;
import com.benet.system.service.ISysContentinfoService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.core.pager.TableDataInfo;

/**
 * 内容信息Controller
 * 
 * @author yoxking
 * @date 2020-04-20
 */
@RestController
@RequestMapping("/sys/contentinfo")
public class SysContentinfoController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ISysContzclassService sysContzclassService;

    @Autowired
    private ISysContentinfoService sysContentinfoService;
    /**
     * 首页
     */
   // @PreAuthorize("@ps.hasPermit('system:contentinfo:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询内容信息列表
     */
    //@PreAuthorize("@ps.hasPermit('system:contentinfo:listall')")
    @GetMapping(value = "/classlist")
    public TableDataInfo classlist()
    {
        List<SysContzclass> list = sysContzclassService.getAllRecords();
        return getDataTable(convertList(list), list.size());
    }

    private List<ItemObjectVo> convertList(List<SysContzclass> list){

        List<ItemObjectVo> itemList=new ArrayList<>();
        ItemObjectVo item=null;
        if(list!=null&&list.size()>0){
            for(SysContzclass info:list){
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
     * 查询内容信息列表
     */
    //@PreAuthorize("@ps.hasPermit('system:contentinfo:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        int count = sysContentinfoService.getCountByCondition(pRequest.getCondition());
        List<SysContentinfo> list = sysContentinfoService.getRecordsByPaging(pRequest.getPageIndex(), pRequest.getPageSize(), pRequest.getCondition(), "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增内容信息
     */
    //@PreAuthorize("@ps.hasPermit('system:contentinfo:insert')")
    @Oplog(title = "内容信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody SysContentinfo sysContentinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        sysContentinfo.setContzNo(UuidUtils.shortUUID());
        sysContentinfo.setCreateBy(loginUser.getUser().getUserNo());
        sysContentinfo.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(sysContentinfoService.AddNewRecord(sysContentinfo));
    }

    /**
     * 编辑内容信息
     */
    //@PreAuthorize("@ps.hasPermit('system:contentinfo:update')")
    @Oplog(title = "内容信息", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody SysContentinfo sysContentinfo) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        sysContentinfo.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(sysContentinfoService.UpdateRecord(sysContentinfo));
        }

    /**
     * 保存内容信息
     */
    //@PreAuthorize("@ps.hasPermit('system:contentinfo:save')")
    @Oplog(title = "内容信息", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody SysContentinfo sysContentinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(sysContentinfoService.getRecordByNo(sysContentinfo.getContzNo()))) {
            sysContentinfo.setContzNo(UuidUtils.shortUUID());
            sysContentinfo.setCreateBy(loginUser.getUser().getUserNo());
            sysContentinfo.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(sysContentinfoService.AddNewRecord(sysContentinfo));
        } else {
            sysContentinfo.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(sysContentinfoService.UpdateRecord(sysContentinfo));
        }
    }

    /**
     * 删除内容信息
     */
    //@PreAuthorize("@ps.hasPermit('system:contentinfo:delete')")
    @Oplog(title = "内容信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        return toAjax(sysContentinfoService.SoftDeleteByNos(ids));
    }

    /**
     * 获取内容信息详细信息
     */
    //@PreAuthorize("@ps.hasPermit('system:contentinfo:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        return AjaxResult.success(sysContentinfoService.getRecordByNo(id));
    }

    /**
     * 导出内容信息列表
     */
    //@PreAuthorize("@ps.hasPermit('system:contentinfo:export')")
    @Oplog(title = "内容信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        int count = sysContentinfoService.getCountByCondition(pRequest.getCondition());

        List<SysContentinfo> list = sysContentinfoService.getRecordsByPaging(1,count,pRequest.getCondition(),"id","Asc");
        ExcelUtils<SysContentinfo> util = new ExcelUtils<SysContentinfo>(SysContentinfo.class);
        return util.exportExcel(list, "SysContentinfo");
    }

}
