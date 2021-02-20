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
import com.benet.process.domain.CssdDterminfo;
import com.benet.process.service.ICssdDterminfoService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;

/**
 * 有效期天数Controller
 * 
 * @author yoxking
 * @date 2020-12-19
 */
@Api(value = "process/dterminfo", tags = "有效期天数控制器")
@RestController
@RequestMapping("/process/dterminfo")
public class CssdDterminfoController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ICssdDterminfoService cssdDterminfoService;
    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('process:dterminfo:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询有效期天数列表
     */
    @PreAuthorize("@ps.hasPermit('process:dterminfo:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cssdDterminfoService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());
        List<CssdDterminfo> list = cssdDterminfoService.getRecordsByPaging(loginUser.getUser().getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), pRequest.getCondition(), "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 查询内容类型列表
     */
    @PreAuthorize("@ps.hasPermit('process:dterminfo:list')")
    @GetMapping(value = "/tree")
    public TableDataInfo tree()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cssdDterminfoService.getCountByCondition(loginUser.getUser().getAppCode(),"");
        List<ItemObjectVo> list = buildItemTree(loginUser.getUser().getAppCode());
        return getDataTable(list, count);
    }

    private List<ItemObjectVo> buildItemTree(String appCode) {

        List<ItemObjectVo> itemTree = null;
        ItemObjectVo item = null;
        List<CssdDterminfo> infoList = cssdDterminfoService.getAllRecords(appCode);

        if (infoList != null && infoList.size() > 0) {
            itemTree = new ArrayList<>();
            for (CssdDterminfo info : infoList) {
                item = new ItemObjectVo();
                item.setId(info.getTermNo());
                item.setKey(info.getTermNo());
                item.setTitle(info.getTermName());
                item.setValue(info.getTermDays()+"");
                item.setChildren(null);

                itemTree.add(item);
            }
        }
        return itemTree;
    }

    /**
     * 新增有效期天数
     */
    @PreAuthorize("@ps.hasPermit('process:dterminfo:addnew')")
    @Oplog(title = "有效期天数", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody CssdDterminfo cssdDterminfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        cssdDterminfo.setTermNo(UuidUtils.shortUUID());
        cssdDterminfo.setCreateBy(loginUser.getUser().getUserNo());
        cssdDterminfo.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(cssdDterminfoService.AddNewRecord(loginUser.getUser().getAppCode(),cssdDterminfo));
    }

    /**
     * 编辑有效期天数
     */
    @PreAuthorize("@ps.hasPermit('process:dterminfo:update')")
    @Oplog(title = "有效期天数", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody CssdDterminfo cssdDterminfo) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        cssdDterminfo.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cssdDterminfoService.UpdateRecord(loginUser.getUser().getAppCode(),cssdDterminfo));
        }

    /**
     * 保存有效期天数
     */
    @PreAuthorize("@ps.hasPermit('process:dterminfo:save')")
    @Oplog(title = "有效期天数", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody CssdDterminfo cssdDterminfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(cssdDterminfoService.getRecordByNo(loginUser.getUser().getAppCode(),cssdDterminfo.getTermNo()))) {
            cssdDterminfo.setTermNo(UuidUtils.shortUUID());
            cssdDterminfo.setCreateBy(loginUser.getUser().getUserNo());
            cssdDterminfo.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cssdDterminfoService.AddNewRecord(loginUser.getUser().getAppCode(),cssdDterminfo));
        } else {
            cssdDterminfo.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cssdDterminfoService.UpdateRecord(loginUser.getUser().getAppCode(),cssdDterminfo));
        }
    }

    /**
     * 删除有效期天数
     */
    @PreAuthorize("@ps.hasPermit('process:dterminfo:delete')")
    @Oplog(title = "有效期天数", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(cssdDterminfoService.SoftDeleteByNos(loginUser.getUser().getAppCode(),ids));
    }

    /**
     * 获取有效期天数详细信息
     */
    @PreAuthorize("@ps.hasPermit('process:dterminfo:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(cssdDterminfoService.getRecordByNo(loginUser.getUser().getAppCode(),id));
    }

    /**
     * 导出有效期天数列表
     */
    @PreAuthorize("@ps.hasPermit('process:dterminfo:export')")
    @Oplog(title = "有效期天数", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cssdDterminfoService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());

        List<CssdDterminfo> list = cssdDterminfoService.getRecordsByPaging(loginUser.getUser().getAppCode(),1,count,pRequest.getCondition(),"id","Asc");
        ExcelUtils<CssdDterminfo> util = new ExcelUtils<CssdDterminfo>(CssdDterminfo.class);
        return util.exportExcel(list, "CssdDterminfo");
    }

}
