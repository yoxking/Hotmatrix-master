package com.benet.sys.controller;

import java.util.List;
import java.util.Set;

import com.benet.common.core.pager.PageRequest;
import com.benet.common.utils.date.DateUtils;
import com.benet.common.utils.uuid.UuidUtils;
import com.benet.common.utils.web.ServletUtils;
import com.benet.framework.security.LoginUser;
import com.benet.framework.security.service.MyJwtokenService;
import com.benet.framework.utils.SecurityUtils;
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
import com.benet.system.domain.SysSuserinfo;
import com.benet.system.service.ISysSuserinfoService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.core.pager.TableDataInfo;

/**
 * 系统用户信息Controller
 *
 * @author yoxking
 * @date 2020-04-20
 */
@RestController
@RequestMapping("/sys/suserinfo")
public class SysSuserinfoController extends BaseController {
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ISysSuserinfoService sysSuserinfoService;

    /**
     * 首页
     */
    //@PreAuthorize("@ps.hasPermit('system:suserinfo:index')")
    @GetMapping(value = "/index")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

    /**
     * 查询系统用户信息列表
     */
    //@PreAuthorize("@ps.hasPermit('system:suserinfo:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest) {
        int count = sysSuserinfoService.getCountByCondition(pRequest.getCondition());
        List<SysSuserinfo> list = sysSuserinfoService.getRecordsByPaging(pRequest.getPageIndex(), pRequest.getPageSize(), pRequest.getCondition(), "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增系统用户信息
     */
    //@PreAuthorize("@ps.hasPermit('system:suserinfo:insert')")
    @Oplog(title = "系统用户信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody SysSuserinfo sysSuserinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        sysSuserinfo.setUserNo(UuidUtils.shortUUID());
        sysSuserinfo.setCreateBy(loginUser.getUser().getUserNo());
        sysSuserinfo.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(sysSuserinfoService.AddNewRecord(sysSuserinfo));
    }

    /**
     * 编辑系统用户信息
     */
    //@PreAuthorize("@ps.hasPermit('system:suserinfo:update')")
    @Oplog(title = "系统用户信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult update(@RequestBody SysSuserinfo sysSuserinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        sysSuserinfo.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(sysSuserinfoService.UpdateRecord(sysSuserinfo));
    }

    /**
     * 编辑系统用户信息
     */
    //@PreAuthorize("@ps.hasPermit('system:suserinfo:update')")
    @Oplog(title = "修改密码", businessType = BusinessType.OTHER)
    @PutMapping(value = "/password")
    public AjaxResult password(String oldPswd, String newPswd) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        String userNo = loginUser.getUserno();
        String password = loginUser.getPassword();
        if (!SecurityUtils.matchesPassword(oldPswd, password)) {
            return AjaxResult.error("修改密码失败，旧密码错误");
        }
        if (SecurityUtils.matchesPassword(newPswd, password)) {
            return AjaxResult.error("新密码不能与旧密码相同");
        }

        SysSuserinfo info = sysSuserinfoService.getRecordByNo(userNo);
        info.setPassword(SecurityUtils.encryptPassword(newPswd));
        info.setUpdateBy(loginUser.getUsername());
        info.setUpdateTime(DateUtils.getNowDate());

        if (sysSuserinfoService.UpdateRecord(info) > 0) {
            // 更新缓存用户密码
            loginUser.getUser().setPassword(SecurityUtils.encryptPassword(newPswd));
            tokenService.setLoginUser(loginUser);
            return AjaxResult.success();
        }
        return AjaxResult.error("修改密码异常，请联系管理员");
    }

    /**
     * 保存系统用户信息
     */
    //@PreAuthorize("@ps.hasPermit('system:suserinfo:save')")
    @Oplog(title = "系统用户信息", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody SysSuserinfo sysSuserinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(sysSuserinfoService.getRecordByNo(sysSuserinfo.getUserNo()))) {
            sysSuserinfo.setUserNo(UuidUtils.shortUUID());
            sysSuserinfo.setCreateBy(loginUser.getUser().getUserNo());
            sysSuserinfo.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(sysSuserinfoService.AddNewRecord(sysSuserinfo));
        } else {
            sysSuserinfo.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(sysSuserinfoService.UpdateRecord(sysSuserinfo));
        }
    }

    /**
     * 删除系统用户信息
     */
    //@PreAuthorize("@ps.hasPermit('system:suserinfo:delete')")
    @Oplog(title = "系统用户信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids) {
        return toAjax(sysSuserinfoService.SoftDeleteByNos(ids));
    }

    /**
     * 获取系统用户信息详细信息
     */
    //@PreAuthorize("@ps.hasPermit('system:suserinfo:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id) {
        return AjaxResult.success(sysSuserinfoService.getRecordByNo(id));
    }

    /**
     * 获取系统用户信息详细信息
     */
    //@PreAuthorize("@ps.hasPermit('system:suserinfo:profile')")
    @GetMapping(value = "/profile")
    public AjaxResult profile() {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        SysSuserinfo userInfo = loginUser.getUser();
        return AjaxResult.success(userInfo);
    }

    /**
     * 导出系统用户信息列表
     */
    //@PreAuthorize("@ps.hasPermit('system:suserinfo:export')")
    @Oplog(title = "系统用户信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest) {
        int count = sysSuserinfoService.getCountByCondition(pRequest.getCondition());

        List<SysSuserinfo> list = sysSuserinfoService.getRecordsByPaging(1, count, pRequest.getCondition(), "id", "Asc");
        ExcelUtils<SysSuserinfo> util = new ExcelUtils<SysSuserinfo>(SysSuserinfo.class);
        return util.exportExcel(list, "SysSuserinfo");
    }

}
