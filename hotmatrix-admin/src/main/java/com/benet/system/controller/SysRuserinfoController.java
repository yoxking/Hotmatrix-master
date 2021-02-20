package com.benet.system.controller;

import java.util.ArrayList;
import java.util.List;
import com.benet.common.core.pager.PageRequest;
import com.benet.common.utils.uuid.UuidUtils;
import com.benet.common.utils.web.ServletUtils;
import com.benet.framework.security.LoginUser;
import com.benet.framework.security.service.MyJwtokenService;
import com.benet.system.vmodel.ItemObjectVo;
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
import com.benet.system.domain.SysRuserinfo;
import com.benet.system.service.ISysRuserinfoService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.core.pager.TableDataInfo;

/**
 * 注册用户信息Controller
 * 
 * @author yoxking
 * @date 2020-04-20
 */
@Api(value = "system/ruserinfo", tags = "注册用户信息控制器")
@RestController
@RequestMapping("/system/ruserinfo")
public class SysRuserinfoController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ISysRuserinfoService sysRuserinfoService;
    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('system:ruserinfo:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询注册用户信息列表
     */
    @PreAuthorize("@ps.hasPermit('system:ruserinfo:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = sysRuserinfoService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());
        List<SysRuserinfo> list = sysRuserinfoService.getRecordsByPaging(loginUser.getUser().getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), pRequest.getCondition(), "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 查询内容类型列表
     */
    @PreAuthorize("@ps.hasPermit('system:ruserinfo:list')")
    @GetMapping(value = "/tree")
    public TableDataInfo tree()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = sysRuserinfoService.getCountByCondition(loginUser.getUser().getAppCode(),"");
        List<ItemObjectVo> list = buildItemTree(loginUser.getUser().getAppCode());
        return getDataTable(list, count);
    }

    private List<ItemObjectVo> buildItemTree(String appCode) {

        List<ItemObjectVo> itemTree = null;
        ItemObjectVo item = null;
        List<SysRuserinfo> infoList = sysRuserinfoService.getAllRecords(appCode);

        if (infoList != null && infoList.size() > 0) {
            itemTree = new ArrayList<>();
            for (SysRuserinfo info : infoList) {
                item = new ItemObjectVo();
                item.setId(info.getUserNo());
                item.setKey(info.getUserNo());
                item.setTitle(info.getUserCnname());
                item.setValue(info.getUserNo());
                item.setChildren(null);

                itemTree.add(item);
            }
        }
        return itemTree;
    }

    /**
     * 新增注册用户信息
     */
    @PreAuthorize("@ps.hasPermit('system:ruserinfo:addnew')")
    @Oplog(title = "注册用户信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody SysRuserinfo sysRuserinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        sysRuserinfo.setUserNo(UuidUtils.shortUUID());
        sysRuserinfo.setCreateBy(loginUser.getUser().getUserNo());
        sysRuserinfo.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(sysRuserinfoService.AddNewRecord(loginUser.getUser().getAppCode(),sysRuserinfo));
    }

    /**
     * 编辑注册用户信息
     */
    @PreAuthorize("@ps.hasPermit('system:ruserinfo:update')")
    @Oplog(title = "注册用户信息", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody SysRuserinfo sysRuserinfo) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        sysRuserinfo.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(sysRuserinfoService.UpdateRecord(loginUser.getUser().getAppCode(),sysRuserinfo));
        }

    /**
     * 保存注册用户信息
     */
    @PreAuthorize("@ps.hasPermit('system:ruserinfo:save')")
    @Oplog(title = "注册用户信息", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody SysRuserinfo sysRuserinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(sysRuserinfoService.getRecordByNo(loginUser.getUser().getAppCode(),sysRuserinfo.getUserNo()))) {
            sysRuserinfo.setUserNo(UuidUtils.shortUUID());
            sysRuserinfo.setCreateBy(loginUser.getUser().getUserNo());
            sysRuserinfo.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(sysRuserinfoService.AddNewRecord(loginUser.getUser().getAppCode(),sysRuserinfo));
        } else {
            sysRuserinfo.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(sysRuserinfoService.UpdateRecord(loginUser.getUser().getAppCode(),sysRuserinfo));
        }
    }

    /**
     * 删除注册用户信息
     */
    @PreAuthorize("@ps.hasPermit('system:ruserinfo:delete')")
    @Oplog(title = "注册用户信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(sysRuserinfoService.SoftDeleteByNos(loginUser.getUser().getAppCode(),ids));
    }

    /**
     * 获取注册用户信息详细信息
     */
    @PreAuthorize("@ps.hasPermit('system:ruserinfo:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(sysRuserinfoService.getRecordByNo(loginUser.getUser().getAppCode(),id));
    }

    /**
     * 导出注册用户信息列表
     */
    @PreAuthorize("@ps.hasPermit('system:ruserinfo:export')")
    @Oplog(title = "注册用户信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = sysRuserinfoService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());

        List<SysRuserinfo> list = sysRuserinfoService.getRecordsByPaging(loginUser.getUser().getAppCode(),1,count,pRequest.getCondition(),"id","Asc");
        ExcelUtils<SysRuserinfo> util = new ExcelUtils<SysRuserinfo>(SysRuserinfo.class);
        return util.exportExcel(list, "SysRuserinfo");
    }

}
