package com.benet.system.controller;

import java.io.IOException;
import java.util.List;

import com.benet.common.configure.GlobalConfig;
import com.benet.common.core.pager.PageRequest;
import com.benet.common.utils.date.DateUtils;
import com.benet.common.utils.file.FileUploadUtils;
import com.benet.common.utils.uuid.UuidUtils;
import com.benet.common.utils.web.ServletUtils;
import com.benet.framework.security.LoginUser;
import com.benet.framework.security.service.MyJwtokenService;
import com.benet.framework.utils.SecurityUtils;
import io.swagger.annotations.Api;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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
@Api(value = "system/suserinfo", tags = "系统用户信息控制器")
@RestController
@RequestMapping("/system/suserinfo")
public class SysSuserinfoController extends BaseController {
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ISysSuserinfoService sysSuserinfoService;

    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('system:suserinfo:index')")
    @GetMapping(value = "/index")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

    /**
     * 查询系统用户信息列表
     */
    @PreAuthorize("@ps.hasPermit('system:suserinfo:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = sysSuserinfoService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());
        List<SysSuserinfo> list = sysSuserinfoService.getRecordsByPaging(loginUser.getUser().getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), pRequest.getCondition(), "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增系统用户信息
     */
    @PreAuthorize("@ps.hasPermit('system:suserinfo:addnew')")
    @Oplog(title = "系统用户信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody SysSuserinfo sysSuserinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        sysSuserinfo.setUserNo(UuidUtils.shortUUID());
        sysSuserinfo.setCreateBy(loginUser.getUser().getUserNo());
        sysSuserinfo.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(sysSuserinfoService.AddNewRecord(loginUser.getUser().getAppCode(),sysSuserinfo));
    }

    /**
     * 编辑系统用户信息
     */
    @PreAuthorize("@ps.hasPermit('system:suserinfo:update')")
    @Oplog(title = "系统用户信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult update(@RequestBody SysSuserinfo sysSuserinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        sysSuserinfo.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(sysSuserinfoService.UpdateRecord(loginUser.getUser().getAppCode(),sysSuserinfo));
    }

    /**
     * 保存系统用户信息
     */
    @PreAuthorize("@ps.hasPermit('system:suserinfo:save')")
    @Oplog(title = "系统用户信息", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody SysSuserinfo sysSuserinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(sysSuserinfoService.getRecordByNo(loginUser.getUser().getAppCode(),sysSuserinfo.getUserNo()))) {
            sysSuserinfo.setUserNo(UuidUtils.shortUUID());
            sysSuserinfo.setCreateBy(loginUser.getUser().getUserNo());
            sysSuserinfo.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(sysSuserinfoService.AddNewRecord(loginUser.getUser().getAppCode(),sysSuserinfo));
        } else {
            sysSuserinfo.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(sysSuserinfoService.UpdateRecord(loginUser.getUser().getAppCode(),sysSuserinfo));
        }
    }

    /**
     * 删除系统用户信息
     */
    @PreAuthorize("@ps.hasPermit('system:suserinfo:delete')")
    @Oplog(title = "系统用户信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(sysSuserinfoService.SoftDeleteByNos(loginUser.getUser().getAppCode(),ids));
    }

    /**
     * 获取系统用户信息详细信息
     */
    @PreAuthorize("@ps.hasPermit('system:suserinfo:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(sysSuserinfoService.getRecordByNo(loginUser.getUser().getAppCode(),id));
    }

    /**
     * 导出系统用户信息列表
     */
    @PreAuthorize("@ps.hasPermit('system:suserinfo:export')")
    @Oplog(title = "系统用户信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = sysSuserinfoService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());

        List<SysSuserinfo> list = sysSuserinfoService.getRecordsByPaging(loginUser.getUser().getAppCode(),1, count, pRequest.getCondition(), "id", "Asc");
        ExcelUtils<SysSuserinfo> util = new ExcelUtils<SysSuserinfo>(SysSuserinfo.class);
        return util.exportExcel(list, "SysSuserinfo");
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
     * 修改用户密码
     */
    @PreAuthorize("@ps.hasPermit('system:suserinfo:update')")
    @Oplog(title = "修改用户密码", businessType = BusinessType.UPDATE)
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

        SysSuserinfo userInfo = sysSuserinfoService.getRecordByNo(loginUser.getUser().getAppCode(),userNo);
        userInfo.setPassword(SecurityUtils.encryptPassword(newPswd));
        userInfo.setUpdateBy(loginUser.getUsername());
        userInfo.setUpdateTime(DateUtils.getNowDate());

        if (sysSuserinfoService.UpdateRecord(loginUser.getUser().getAppCode(),userInfo) > 0) {
            // 更新缓存用户密码
            loginUser.getUser().setPassword(SecurityUtils.encryptPassword(newPswd));
            tokenService.setLoginUser(loginUser);
            return AjaxResult.success();
        }
        return AjaxResult.error("修改密码异常，请联系管理员");
    }

    /**
     * 头像上传
     */
    @Oplog(title = "更新用户头像", businessType = BusinessType.UPDATE)
    @PostMapping("/uploadAvatar")
    public AjaxResult uploadAvatar(@RequestParam("avatarfile") MultipartFile avatarfile) throws IOException
    {
        if (!avatarfile.isEmpty())
        {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
            SysSuserinfo userInfo = sysSuserinfoService.getRecordByNo(loginUser.getUser().getAppCode(),loginUser.getUserno());
            if(userInfo!=null) {
                String avatar = FileUploadUtils.upload(GlobalConfig.getAvatarPath(), avatarfile);
                userInfo.setAvatar(avatar);
                if (sysSuserinfoService.UpdateRecord(loginUser.getUser().getAppCode(),userInfo)>0) {
                    AjaxResult ajax = AjaxResult.success();
                    ajax.put("imgUrl", avatar);
                    // 更新缓存用户头像
                    loginUser.getUser().setAvatar(avatar);
                    tokenService.setLoginUser(loginUser);
                    return ajax;
                }
            }
        }
        return AjaxResult.error("上传图片异常，请联系管理员");
    }

    /**
     * 校验用户名是否重复
     */
    @GetMapping(value = "checkLoginName/{name}")
    public AjaxResult checkLoginName(@PathVariable("name") String name) {
        return AjaxResult.success(sysSuserinfoService.checkLoginNameUnique(name));
    }

}
