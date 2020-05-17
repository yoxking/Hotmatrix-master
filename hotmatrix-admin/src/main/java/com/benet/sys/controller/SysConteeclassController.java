package com.benet.sys.controller;

import java.util.List;
import com.benet.common.core.pager.PageRequest;
import com.benet.common.utils.uuid.UuidUtils;
import com.benet.common.utils.web.ServletUtils;
import com.benet.framework.security.LoginUser;
import com.benet.framework.security.service.MyJwtokenService;
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
@RestController
@RequestMapping("/sys/conteeclass")
public class SysConteeclassController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ISysConteeclassService sysConteeclassService;
    /**
     * 首页
     */
    //@PreAuthorize("@ps.hasPermit('system:contzclass:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询内容类型列表
     */
    //@PreAuthorize("@ps.hasPermit('system:contzclass:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        int count = sysConteeclassService.getCountByCondition(pRequest.getCondition());
        List<SysConteeclass> list = sysConteeclassService.getRecordsByPaging(pRequest.getPageIndex(), pRequest.getPageSize(), pRequest.getCondition(), "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增内容类型
     */
    //@PreAuthorize("@ps.hasPermit('system:contzclass:insert')")
    @Oplog(title = "内容类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody SysConteeclass sysContzclass) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        sysContzclass.setClassNo(UuidUtils.shortUUID());
        sysContzclass.setCreateBy(loginUser.getUser().getUserNo());
        sysContzclass.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(sysConteeclassService.AddNewRecord(sysContzclass));
    }

    /**
     * 编辑内容类型
     */
    //@PreAuthorize("@ps.hasPermit('system:contzclass:update')")
    @Oplog(title = "内容类型", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody SysConteeclass sysContzclass) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        sysContzclass.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(sysConteeclassService.UpdateRecord(sysContzclass));
        }

    /**
     * 保存内容类型
     */
    //@PreAuthorize("@ps.hasPermit('system:contzclass:save')")
    @Oplog(title = "内容类型", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody SysConteeclass sysContzclass) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(sysConteeclassService.getRecordByNo(sysContzclass.getClassNo()))) {
            sysContzclass.setClassNo(UuidUtils.shortUUID());
            sysContzclass.setCreateBy(loginUser.getUser().getUserNo());
            sysContzclass.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(sysConteeclassService.AddNewRecord(sysContzclass));
        } else {
            sysContzclass.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(sysConteeclassService.UpdateRecord(sysContzclass));
        }
    }

    /**
     * 删除内容类型
     */
    //@PreAuthorize("@ps.hasPermit('system:contzclass:delete')")
    @Oplog(title = "内容类型", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        return toAjax(sysConteeclassService.SoftDeleteByNos(ids));
    }

    /**
     * 获取内容类型详细信息
     */
    //@PreAuthorize("@ps.hasPermit('system:contzclass:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        return AjaxResult.success(sysConteeclassService.getRecordByNo(id));
    }

    /**
     * 导出内容类型列表
     */
    //@PreAuthorize("@ps.hasPermit('system:contzclass:export')")
    @Oplog(title = "内容类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        int count = sysConteeclassService.getCountByCondition(pRequest.getCondition());

        List<SysConteeclass> list = sysConteeclassService.getRecordsByPaging(1,count,pRequest.getCondition(),"id","Asc");
        ExcelUtils<SysConteeclass> util = new ExcelUtils<SysConteeclass>(SysConteeclass.class);
        return util.exportExcel(list, "SysContzclass");
    }

}
