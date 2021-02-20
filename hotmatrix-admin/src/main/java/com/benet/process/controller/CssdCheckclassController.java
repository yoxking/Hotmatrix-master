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
import com.benet.process.domain.CssdCheckclass;
import com.benet.process.service.ICssdCheckclassService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;

/**
 * 签到类型Controller
 * 
 * @author yoxking
 * @date 2020-12-13
 */
@Api(value = "process/checkclass", tags = "签到类型控制器")
@RestController
@RequestMapping("/process/checkclass")
public class CssdCheckclassController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ICssdCheckclassService cssdCheckclassService;
    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('process:checkclass:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询签到类型列表
     */
    @PreAuthorize("@ps.hasPermit('process:checkclass:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cssdCheckclassService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());
        List<CssdCheckclass> list = cssdCheckclassService.getRecordsByPaging(loginUser.getUser().getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), pRequest.getCondition(), "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 查询内容类型列表
     */
    @PreAuthorize("@ps.hasPermit('process:checkclass:list')")
    @GetMapping(value = "/tree")
    public TableDataInfo tree()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cssdCheckclassService.getCountByCondition(loginUser.getUser().getAppCode(),"");
        List<ItemObjectVo> list = buildItemTree(loginUser.getUser().getAppCode());
        return getDataTable(list, count);
    }

    private List<ItemObjectVo> buildItemTree(String appCode) {

        List<ItemObjectVo> itemTree = null;
        ItemObjectVo item = null;
        List<CssdCheckclass> infoList = cssdCheckclassService.getAllRecords(appCode);

        if (infoList != null && infoList.size() > 0) {
            itemTree = new ArrayList<>();
            for (CssdCheckclass info : infoList) {
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
     * 新增签到类型
     */
    @PreAuthorize("@ps.hasPermit('process:checkclass:addnew')")
    @Oplog(title = "签到类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody CssdCheckclass cssdCheckclass) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        cssdCheckclass.setClassNo(UuidUtils.shortUUID());
        cssdCheckclass.setCreateBy(loginUser.getUser().getUserNo());
        cssdCheckclass.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(cssdCheckclassService.AddNewRecord(loginUser.getUser().getAppCode(),cssdCheckclass));
    }

    /**
     * 编辑签到类型
     */
    @PreAuthorize("@ps.hasPermit('process:checkclass:update')")
    @Oplog(title = "签到类型", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody CssdCheckclass cssdCheckclass) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        cssdCheckclass.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cssdCheckclassService.UpdateRecord(loginUser.getUser().getAppCode(),cssdCheckclass));
        }

    /**
     * 保存签到类型
     */
    @PreAuthorize("@ps.hasPermit('process:checkclass:save')")
    @Oplog(title = "签到类型", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody CssdCheckclass cssdCheckclass) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(cssdCheckclassService.getRecordByNo(loginUser.getUser().getAppCode(),cssdCheckclass.getClassNo()))) {
            cssdCheckclass.setClassNo(UuidUtils.shortUUID());
            cssdCheckclass.setCreateBy(loginUser.getUser().getUserNo());
            cssdCheckclass.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cssdCheckclassService.AddNewRecord(loginUser.getUser().getAppCode(),cssdCheckclass));
        } else {
            cssdCheckclass.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cssdCheckclassService.UpdateRecord(loginUser.getUser().getAppCode(),cssdCheckclass));
        }
    }

    /**
     * 删除签到类型
     */
    @PreAuthorize("@ps.hasPermit('process:checkclass:delete')")
    @Oplog(title = "签到类型", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(cssdCheckclassService.SoftDeleteByNos(loginUser.getUser().getAppCode(),ids));
    }

    /**
     * 获取签到类型详细信息
     */
    @PreAuthorize("@ps.hasPermit('process:checkclass:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(cssdCheckclassService.getRecordByNo(loginUser.getUser().getAppCode(),id));
    }

    /**
     * 导出签到类型列表
     */
    @PreAuthorize("@ps.hasPermit('process:checkclass:export')")
    @Oplog(title = "签到类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cssdCheckclassService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());

        List<CssdCheckclass> list = cssdCheckclassService.getRecordsByPaging(loginUser.getUser().getAppCode(),1,count,pRequest.getCondition(),"id","Asc");
        ExcelUtils<CssdCheckclass> util = new ExcelUtils<CssdCheckclass>(CssdCheckclass.class);
        return util.exportExcel(list, "CssdCheckclass");
    }

}
