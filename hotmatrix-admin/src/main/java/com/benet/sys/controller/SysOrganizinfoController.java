package com.benet.sys.controller;

import java.util.ArrayList;
import java.util.List;
import com.benet.common.core.pager.PageRequest;
import com.benet.common.utils.uuid.UuidUtils;
import com.benet.common.utils.web.ServletUtils;
import com.benet.framework.security.LoginUser;
import com.benet.framework.security.service.MyJwtokenService;
import com.benet.sys.vmodel.DeptmentVo;
import com.benet.sys.vmodel.OrgnzInfoVo;
import com.benet.system.domain.SysDepartment;
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
import com.benet.system.domain.SysOrganizinfo;
import com.benet.system.service.ISysOrganizinfoService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.core.pager.TableDataInfo;

/**
 * 机构信息Controller
 * 
 * @author yoxking
 * @date 2020-04-20
 */
@RestController
@RequestMapping("/sys/organizinfo")
public class SysOrganizinfoController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ISysOrganizinfoService sysOrganizinfoService;
    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('system:organizinfo:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询机构信息列表
     */
    @PreAuthorize("@ps.hasPermit('system:organizinfo:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        int count = sysOrganizinfoService.getCountByCondition(pRequest.getCondition());
        List<SysOrganizinfo> list = sysOrganizinfoService.getRecordsByPaging(pRequest.getPageIndex(), pRequest.getPageSize(), pRequest.getCondition(), "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 查询机构信息树形列表
     */
    //@PreAuthorize("@ps.hasPermit('system:organizinfo:tree')")
    @GetMapping(value = "/tree")
    public TableDataInfo tree() {
        int count = sysOrganizinfoService.getCountByCondition("");
        List<OrgnzInfoVo> list = buildDeptTree("0");
        return getDataTable(list, count);
    }

    private List<OrgnzInfoVo> buildDeptTree(String parentNo) {

        List<OrgnzInfoVo> orgzTree = null;
        OrgnzInfoVo orgnz = null;
        List<SysOrganizinfo> infoList = sysOrganizinfoService.getRecordsByClassNo(parentNo);

        if (infoList != null && infoList.size() > 0) {
            orgzTree = new ArrayList<>();
            for (SysOrganizinfo info : infoList) {
                orgnz = new OrgnzInfoVo();
                orgnz.setId(info.getOrganizNo());
                orgnz.setLabel(info.getOrganizName());
                orgnz.setOrgnzNo(info.getOrganizNo());
                orgnz.setOrgnzName(info.getOrganizName());
                orgnz.setParentNo(info.getParentNo());
                orgnz.setOrderNo(info.getOrderNo());
                orgnz.setComments(info.getComments());
                orgnz.setChildren(buildDeptTree(info.getOrganizNo()));
                orgzTree.add(orgnz);
            }
        }
        return orgzTree;
    }


    /**
     * 新增机构信息
     */
    @PreAuthorize("@ps.hasPermit('system:organizinfo:insert')")
    @Oplog(title = "机构信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody SysOrganizinfo sysOrganizinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        sysOrganizinfo.setOrganizNo(UuidUtils.shortUUID());
        sysOrganizinfo.setCreateBy(loginUser.getUser().getUserNo());
        sysOrganizinfo.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(sysOrganizinfoService.AddNewRecord(sysOrganizinfo));
    }

    /**
     * 编辑机构信息
     */
    @PreAuthorize("@ps.hasPermit('system:organizinfo:update')")
    @Oplog(title = "机构信息", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody SysOrganizinfo sysOrganizinfo) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        sysOrganizinfo.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(sysOrganizinfoService.UpdateRecord(sysOrganizinfo));
        }

    /**
     * 保存机构信息
     */
    @PreAuthorize("@ps.hasPermit('system:organizinfo:save')")
    @Oplog(title = "机构信息", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody SysOrganizinfo sysOrganizinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(sysOrganizinfoService.getRecordByNo(sysOrganizinfo.getOrganizNo()))) {
            sysOrganizinfo.setOrganizNo(UuidUtils.shortUUID());
            sysOrganizinfo.setCreateBy(loginUser.getUser().getUserNo());
            sysOrganizinfo.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(sysOrganizinfoService.AddNewRecord(sysOrganizinfo));
        } else {
            sysOrganizinfo.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(sysOrganizinfoService.UpdateRecord(sysOrganizinfo));
        }
    }

    /**
     * 删除机构信息
     */
    @PreAuthorize("@ps.hasPermit('system:organizinfo:delete')")
    @Oplog(title = "机构信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        return toAjax(sysOrganizinfoService.SoftDeleteByNos(ids));
    }

    /**
     * 获取机构信息详细信息
     */
    @PreAuthorize("@ps.hasPermit('system:organizinfo:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        return AjaxResult.success(sysOrganizinfoService.getRecordByNo(id));
    }

    /**
     * 导出机构信息列表
     */
    @PreAuthorize("@ps.hasPermit('system:organizinfo:export')")
    @Oplog(title = "机构信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        int count = sysOrganizinfoService.getCountByCondition(pRequest.getCondition());

        List<SysOrganizinfo> list = sysOrganizinfoService.getRecordsByPaging(1,count,pRequest.getCondition(),"id","Asc");
        ExcelUtils<SysOrganizinfo> util = new ExcelUtils<SysOrganizinfo>(SysOrganizinfo.class);
        return util.exportExcel(list, "SysOrganizinfo");
    }

}
