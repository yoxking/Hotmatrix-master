package com.benet.process.controller;

import java.util.ArrayList;
import java.util.List;
import com.benet.common.core.pager.PageRequest;
import com.benet.common.core.pager.TableDataInfo;
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
import com.benet.process.domain.CssdSteryclass;
import com.benet.process.service.ICssdSteryclassService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;

/**
 * 灭菌程序/类型Controller
 * 
 * @author yoxking
 * @date 2020-12-19
 */
@Api(value = "process/steryclass", tags = "灭菌程序/类型控制器")
@RestController
@RequestMapping("/process/steryclass")
public class CssdSteryclassController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ICssdSteryclassService cssdSteryclassService;
    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('process:steryclass:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询灭菌程序/类型列表
     */
    @PreAuthorize("@ps.hasPermit('process:steryclass:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cssdSteryclassService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());
        List<CssdSteryclass> list = cssdSteryclassService.getRecordsByPaging(loginUser.getUser().getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), pRequest.getCondition(), "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 查询内容类型列表
     */
    @PreAuthorize("@ps.hasPermit('process:steryclass:list')")
    @GetMapping(value = "/tree")
    public TableDataInfo tree()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cssdSteryclassService.getCountByCondition(loginUser.getUser().getAppCode(),"");
        List<ItemObjectVo> list = buildItemTree(loginUser.getUser().getAppCode());
        return getDataTable(list, count);
    }

    private List<ItemObjectVo> buildItemTree(String appCode) {

        List<ItemObjectVo> itemTree = null;
        ItemObjectVo item = null;
        List<CssdSteryclass> infoList = cssdSteryclassService.getAllRecords(appCode);

        if (infoList != null && infoList.size() > 0) {
            itemTree = new ArrayList<>();
            for (CssdSteryclass info : infoList) {
                item = new ItemObjectVo();
                item.setId(info.getClassNo());
                item.setKey(info.getClassNo());
                item.setTitle(info.getClassName());
                item.setValue(info.getClassNo());
                item.setChildren(null);

                itemTree.add(item);
            }
        }
        return itemTree;
    }

    /**
     * 新增灭菌程序/类型
     */
    @PreAuthorize("@ps.hasPermit('process:steryclass:addnew')")
    @Oplog(title = "灭菌程序/类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody CssdSteryclass cssdSteryclass) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        cssdSteryclass.setClassNo(UuidUtils.shortUUID());
        cssdSteryclass.setCreateBy(loginUser.getUser().getUserNo());
        cssdSteryclass.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(cssdSteryclassService.AddNewRecord(loginUser.getUser().getAppCode(),cssdSteryclass));
    }

    /**
     * 编辑灭菌程序/类型
     */
    @PreAuthorize("@ps.hasPermit('process:steryclass:update')")
    @Oplog(title = "灭菌程序/类型", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody CssdSteryclass cssdSteryclass) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        cssdSteryclass.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cssdSteryclassService.UpdateRecord(loginUser.getUser().getAppCode(),cssdSteryclass));
        }

    /**
     * 保存灭菌程序/类型
     */
    @PreAuthorize("@ps.hasPermit('process:steryclass:save')")
    @Oplog(title = "灭菌程序/类型", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody CssdSteryclass cssdSteryclass) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(cssdSteryclassService.getRecordByNo(loginUser.getUser().getAppCode(),cssdSteryclass.getClassNo()))) {
            cssdSteryclass.setClassNo(UuidUtils.shortUUID());
            cssdSteryclass.setCreateBy(loginUser.getUser().getUserNo());
            cssdSteryclass.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cssdSteryclassService.AddNewRecord(loginUser.getUser().getAppCode(),cssdSteryclass));
        } else {
            cssdSteryclass.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cssdSteryclassService.UpdateRecord(loginUser.getUser().getAppCode(),cssdSteryclass));
        }
    }

    /**
     * 删除灭菌程序/类型
     */
    @PreAuthorize("@ps.hasPermit('process:steryclass:delete')")
    @Oplog(title = "灭菌程序/类型", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(cssdSteryclassService.SoftDeleteByNos(loginUser.getUser().getAppCode(),ids));
    }

    /**
     * 获取灭菌程序/类型详细信息
     */
    @PreAuthorize("@ps.hasPermit('process:steryclass:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(cssdSteryclassService.getRecordByNo(loginUser.getUser().getAppCode(),id));
    }

    /**
     * 导出灭菌程序/类型列表
     */
    @PreAuthorize("@ps.hasPermit('process:steryclass:export')")
    @Oplog(title = "灭菌程序/类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cssdSteryclassService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());

        List<CssdSteryclass> list = cssdSteryclassService.getRecordsByPaging(loginUser.getUser().getAppCode(),1,count,pRequest.getCondition(),"id","Asc");
        ExcelUtils<CssdSteryclass> util = new ExcelUtils<CssdSteryclass>(CssdSteryclass.class);
        return util.exportExcel(list, "CssdSteryclass");
    }

}
