package com.benet.web.controller;

import java.util.List;

import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.core.pager.TableDataInfo;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;
import com.benet.system.domain.SysMenu;
import com.benet.system.service.ISysMenuService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 菜单权限Controller
 *
 * @author ruoyi
 * @date 2020-03-29
 */
@RestController
@RequestMapping("/system/menu")
public class SysAppinfoController extends BaseController
{
    @Autowired
    private ISysMenuService sysMenuService;

    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('system:menu:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询菜单权限列表
     */
    @PreAuthorize("@ps.hasPermit('system:menu:list')")
    @GetMapping(value="/list")
    public TableDataInfo list()
    {
        startPage();
        List<SysMenu> list = sysMenuService.selectMenuAll(1L);
        return getDataTable(list);
    }

    /**
     * 查询菜单权限列表
     */
    @PreAuthorize("@ps.hasPermit('system:menu:search')")
    @GetMapping(value="/search/{condition}")
    public TableDataInfo search(@PathVariable("condition") String condition)
    {
        List<SysMenu> list = sysMenuService.selectMenuAll(1L);
        return getDataTable(list);
    }

    /**
     * 新增菜单权限
     */
    @PreAuthorize("@ps.hasPermit('system:menu:add')")
    @GetMapping(value="/add")
    public AjaxResult add()
    {
        SysMenu info=new SysMenu();
        return AjaxResult.success(info);
    }

    /**
     * 编辑菜单权限
     */
    @PreAuthorize("@ps.hasPermit('system:menu:edit')")
    @GetMapping(value="/edit/{id}")
    public AjaxResult edit(@PathVariable("id") String id)
    {
        return AjaxResult.success(sysMenuService.selectMenuById(1L));
    }

    /**
     * 保存菜单权限
     */
    @PreAuthorize("@ps.hasPermit('system:menu:save')")
    @Oplog(title = "菜单权限", businessType = BusinessType.UPDATE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody SysMenu sysMenu) {
        if (StringUtils.isNull(sysMenuService.selectMenuById(sysMenu.getMenuId()))) {
            return toAjax(sysMenuService.insertMenu(sysMenu));
        } else {
            return toAjax(sysMenuService.updateMenu(sysMenu));
        }
    }

    /**
     * 删除菜单权限
     */
    @PreAuthorize("@ps.hasPermit('system:menu:delete')")
    @Oplog(title = "菜单权限", businessType = BusinessType.DELETE)
    //@GetMapping("/delete/{ids}")
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") Long[] ids)
    {
        return toAjax(sysMenuService.deleteMenuById(ids[0]));
    }

    /**
     * 获取菜单权限详细信息
     */
    @PreAuthorize("@ps.hasPermit('system:menu:detail')")
    @GetMapping(value = "/detail/{id}")
    public AjaxResult detail(@PathVariable("id") Long id)
    {
        return AjaxResult.success(sysMenuService.selectMenuById(id));
    }

    /**
     * 导出菜单权限列表
     */
    @PreAuthorize("@ps.hasPermit('system:menu:export')")
    @Oplog(title = "菜单权限", businessType = BusinessType.EXPORT)
    @GetMapping("/export/{condition}")
    public AjaxResult export(@PathVariable("condition") String condition)
    {
        List<SysMenu> list = sysMenuService.selectMenuList(null,1L);
        ExcelUtils<SysMenu> util = new ExcelUtils<SysMenu>(SysMenu.class);
        return util.exportExcel(list, "menu");
    }
}