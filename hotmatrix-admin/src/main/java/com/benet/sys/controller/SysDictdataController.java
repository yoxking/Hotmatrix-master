package com.benet.sys.controller;

import java.util.ArrayList;
import java.util.List;
import com.benet.common.core.pager.PageRequest;
import com.benet.common.utils.uuid.UuidUtils;
import com.benet.common.utils.web.ServletUtils;
import com.benet.framework.security.LoginUser;
import com.benet.framework.security.service.MyJwtokenService;
import com.benet.sys.vmodel.ItemObjectVo;
import com.benet.system.domain.SysDicttype;
import com.benet.system.service.ISysDicttypeService;
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
import com.benet.system.domain.SysDictdata;
import com.benet.system.service.ISysDictdataService;
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
 * @date 2020-04-23
 */
@RestController
@RequestMapping("/sys/dictdata")
public class SysDictdataController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ISysDicttypeService sysDicttypeService;

    @Autowired
    private ISysDictdataService sysDictdataService;
    /**
     * 首页
     */
    //@PreAuthorize("@ps.hasPermit('system:dictdata:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询字典数据列表
     */
    //@PreAuthorize("@ps.hasPermit('system:dictdata:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        int count = sysDictdataService.getCountByCondition(pRequest.getCondition());
        List<SysDictdata> list = sysDictdataService.getRecordsByPaging(pRequest.getPageIndex(), pRequest.getPageSize(), pRequest.getCondition(), "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 查询内容信息列表
     */
    //@PreAuthorize("@ps.hasPermit('system:contentinfo:listall')")
    @GetMapping(value = "/typelist")
    public TableDataInfo typelist()
    {
        List<SysDicttype> list = sysDicttypeService.getAllRecords();
        return getDataTable(convertList(list), list.size());
    }

    private List<ItemObjectVo> convertList(List<SysDicttype> list){

        List<ItemObjectVo> itemList=new ArrayList<>();
        ItemObjectVo item=null;
        if(list!=null&&list.size()>0){
            for(SysDicttype info:list){
                item=new ItemObjectVo();
                item.setId(info.getDictType());
                item.setKey(info.getDictType());
                item.setTitle(info.getDictName());
                item.setValue(info.getDictType());
                item.setChildren(null);

                itemList.add(item);
            }
        }
        return itemList;
    }

    /**
     * 新增字典数据
     */
    //@PreAuthorize("@ps.hasPermit('system:dictdata:insert')")
    @Oplog(title = "字典数据", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody SysDictdata sysDictdata) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        sysDictdata.setDataNo(UuidUtils.shortUUID());
        sysDictdata.setCreateBy(loginUser.getUser().getUserNo());
        sysDictdata.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(sysDictdataService.AddNewRecord(sysDictdata));
    }

    /**
     * 编辑字典数据
     */
    //@PreAuthorize("@ps.hasPermit('system:dictdata:update')")
    @Oplog(title = "字典数据", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody SysDictdata sysDictdata) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        sysDictdata.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(sysDictdataService.UpdateRecord(sysDictdata));
        }

    /**
     * 保存字典数据
     */
    //@PreAuthorize("@ps.hasPermit('system:dictdata:save')")
    @Oplog(title = "字典数据", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody SysDictdata sysDictdata) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(sysDictdataService.getRecordByNo(sysDictdata.getDataNo()))) {
            sysDictdata.setDataNo(UuidUtils.shortUUID());
            sysDictdata.setCreateBy(loginUser.getUser().getUserNo());
            sysDictdata.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(sysDictdataService.AddNewRecord(sysDictdata));
        } else {
            sysDictdata.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(sysDictdataService.UpdateRecord(sysDictdata));
        }
    }

    /**
     * 删除字典数据
     */
    //@PreAuthorize("@ps.hasPermit('system:dictdata:delete')")
    @Oplog(title = "字典数据", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        return toAjax(sysDictdataService.SoftDeleteByNos(ids));
    }

    /**
     * 获取字典数据详细信息
     */
    //@PreAuthorize("@ps.hasPermit('system:dictdata:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        return AjaxResult.success(sysDictdataService.getRecordByNo(id));
    }

    /**
     * 导出字典数据列表
     */
    //@PreAuthorize("@ps.hasPermit('system:dictdata:export')")
    @Oplog(title = "字典数据", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        int count = sysDictdataService.getCountByCondition(pRequest.getCondition());

        List<SysDictdata> list = sysDictdataService.getRecordsByPaging(1,count,pRequest.getCondition(),"id","Asc");
        ExcelUtils<SysDictdata> util = new ExcelUtils<SysDictdata>(SysDictdata.class);
        return util.exportExcel(list, "SysDictdata");
    }

}
