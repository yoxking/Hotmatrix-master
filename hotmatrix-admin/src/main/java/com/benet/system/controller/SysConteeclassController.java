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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.benet.system.domain.SysConteeclass;
import com.benet.system.service.ISysConteeclassService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.core.pager.TableDataInfo;

/**
 * 内容类型Controller
 * 
 * @author yoxking
 * @date 2020-04-20
 */
@Api(value = "system/conteeclass", tags = "内容类型控制器")
@RestController
@RequestMapping("/system/conteeclass")
public class SysConteeclassController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ISysConteeclassService sysConteeclassService;
    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('system:conteeclass:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询内容类型列表
     */
    @PreAuthorize("@ps.hasPermit('system:conteeclass:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = sysConteeclassService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());
        List<SysConteeclass> list = sysConteeclassService.getRecordsByPaging(loginUser.getUser().getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), pRequest.getCondition(), "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 查询内容类型列表
     */
    @PreAuthorize("@ps.hasPermit('system:conteeclass:list')")
    @GetMapping(value = "/tree")
    public TableDataInfo tree()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = sysConteeclassService.getCountByCondition(loginUser.getUser().getAppCode(),"");
        List<ItemObjectVo> list = buildItemTree(loginUser.getUser().getAppCode(),"0");
        return getDataTable(list, count);
    }

    private List<ItemObjectVo> buildItemTree(String appCode, String parentNo) {

        List<ItemObjectVo> itemTree = null;
        ItemObjectVo item = null;
        List<SysConteeclass> infoList = sysConteeclassService.getRecordsByClassNo(appCode,parentNo);

        if (infoList != null && infoList.size() > 0) {
            itemTree = new ArrayList<>();
            for (SysConteeclass info : infoList) {
                item = new ItemObjectVo();
                item.setId(info.getClassNo());
                item.setKey(info.getClassNo());
                item.setTitle(info.getClassName());
                item.setValue(info.getClassNo());
                item.setChildren(buildItemTree(appCode,info.getClassNo()));

                itemTree.add(item);
            }
        }
        return itemTree;
    }

    /**
     * 新增内容类型
     */
    @PreAuthorize("@ps.hasPermit('system:conteeclass:addnew')")
    @Oplog(title = "内容类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody SysConteeclass sysContzclass) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        sysContzclass.setClassNo(UuidUtils.shortUUID());
        sysContzclass.setCreateBy(loginUser.getUser().getUserNo());
        sysContzclass.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(sysConteeclassService.AddNewRecord(loginUser.getUser().getAppCode(),sysContzclass));
    }

    /**
     * 编辑内容类型
     */
    @PreAuthorize("@ps.hasPermit('system:conteeclass:update')")
    @Oplog(title = "内容类型", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody SysConteeclass sysContzclass) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        sysContzclass.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(sysConteeclassService.UpdateRecord(loginUser.getUser().getAppCode(),sysContzclass));
        }

    /**
     * 保存内容类型
     */
    @PreAuthorize("@ps.hasPermit('system:conteeclass:save')")
    @Oplog(title = "内容类型", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody SysConteeclass sysContzclass) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(sysConteeclassService.getRecordByNo(loginUser.getUser().getAppCode(),sysContzclass.getClassNo()))) {
            sysContzclass.setClassNo(UuidUtils.shortUUID());
            sysContzclass.setCreateBy(loginUser.getUser().getUserNo());
            sysContzclass.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(sysConteeclassService.AddNewRecord(loginUser.getUser().getAppCode(),sysContzclass));
        } else {
            sysContzclass.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(sysConteeclassService.UpdateRecord(loginUser.getUser().getAppCode(),sysContzclass));
        }
    }

    /**
     * 删除内容类型
     */
    @PreAuthorize("@ps.hasPermit('system:conteeclass:delete')")
    @Oplog(title = "内容类型", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(sysConteeclassService.SoftDeleteByNos(loginUser.getUser().getAppCode(),ids));
    }

    /**
     * 获取内容类型详细信息
     */
    @PreAuthorize("@ps.hasPermit('system:conteeclass:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(sysConteeclassService.getRecordByNo(loginUser.getUser().getAppCode(),id));
    }

    /**
     * 导出内容类型列表
     */
    @PreAuthorize("@ps.hasPermit('system:conteeclass:export')")
    @Oplog(title = "内容类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = sysConteeclassService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());

        List<SysConteeclass> list = sysConteeclassService.getRecordsByPaging(loginUser.getUser().getAppCode(),1,count,pRequest.getCondition(),"id","Asc");
        ExcelUtils<SysConteeclass> util = new ExcelUtils<SysConteeclass>(SysConteeclass.class);
        return util.exportExcel(list, "SysContzclass");
    }

}
