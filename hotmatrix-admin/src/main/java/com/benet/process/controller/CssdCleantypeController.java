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
import com.benet.process.domain.CssdCleantype;
import com.benet.process.service.ICssdCleantypeService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;

/**
 * 清洗机次号Controller
 * 
 * @author yoxking
 * @date 2020-12-13
 */
@Api(value = "process/cleantype", tags = "清洗机次号控制器")
@RestController
@RequestMapping("/process/cleantype")
public class CssdCleantypeController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ICssdCleantypeService cssdCleantypeService;
    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('process:cleantype:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询清洗机次号列表
     */
    @PreAuthorize("@ps.hasPermit('process:cleantype:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cssdCleantypeService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());
        List<CssdCleantype> list = cssdCleantypeService.getRecordsByPaging(loginUser.getUser().getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), pRequest.getCondition(), "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 查询内容类型列表
     */
    @PreAuthorize("@ps.hasPermit('process:cleantype:list')")
    @GetMapping(value = "/tree")
    public TableDataInfo tree()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cssdCleantypeService.getCountByCondition(loginUser.getUser().getAppCode(),"");
        List<ItemObjectVo> list = buildItemTree(loginUser.getUser().getAppCode());
        return getDataTable(list, count);
    }

    private List<ItemObjectVo> buildItemTree(String appCode) {

        List<ItemObjectVo> itemTree = null;
        ItemObjectVo item = null;
        List<CssdCleantype> infoList = cssdCleantypeService.getAllRecords(appCode);

        if (infoList != null && infoList.size() > 0) {
            itemTree = new ArrayList<>();
            for (CssdCleantype info : infoList) {
                item = new ItemObjectVo();
                item.setId(info.getTypeNo());
                item.setKey(info.getTypeNo());
                item.setTitle(info.getTypeName());
                item.setValue(info.getTypeNo());
                item.setChildren(null);

                itemTree.add(item);
            }
        }
        return itemTree;
    }

    /**
     * 新增清洗机次号
     */
    @PreAuthorize("@ps.hasPermit('process:cleantype:addnew')")
    @Oplog(title = "清洗机次号", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody CssdCleantype cssdCleantype) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        cssdCleantype.setTypeNo(UuidUtils.shortUUID());
        cssdCleantype.setCreateBy(loginUser.getUser().getUserNo());
        cssdCleantype.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(cssdCleantypeService.AddNewRecord(loginUser.getUser().getAppCode(),cssdCleantype));
    }

    /**
     * 编辑清洗机次号
     */
    @PreAuthorize("@ps.hasPermit('process:cleantype:update')")
    @Oplog(title = "清洗机次号", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody CssdCleantype cssdCleantype) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        cssdCleantype.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cssdCleantypeService.UpdateRecord(loginUser.getUser().getAppCode(),cssdCleantype));
        }

    /**
     * 保存清洗机次号
     */
    @PreAuthorize("@ps.hasPermit('process:cleantype:save')")
    @Oplog(title = "清洗机次号", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody CssdCleantype cssdCleantype) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(cssdCleantypeService.getRecordByNo(loginUser.getUser().getAppCode(),cssdCleantype.getTypeNo()))) {
            cssdCleantype.setTypeNo(UuidUtils.shortUUID());
            cssdCleantype.setCreateBy(loginUser.getUser().getUserNo());
            cssdCleantype.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cssdCleantypeService.AddNewRecord(loginUser.getUser().getAppCode(),cssdCleantype));
        } else {
            cssdCleantype.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cssdCleantypeService.UpdateRecord(loginUser.getUser().getAppCode(),cssdCleantype));
        }
    }

    /**
     * 删除清洗机次号
     */
    @PreAuthorize("@ps.hasPermit('process:cleantype:delete')")
    @Oplog(title = "清洗机次号", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(cssdCleantypeService.SoftDeleteByNos(loginUser.getUser().getAppCode(),ids));
    }

    /**
     * 获取清洗机次号详细信息
     */
    @PreAuthorize("@ps.hasPermit('process:cleantype:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(cssdCleantypeService.getRecordByNo(loginUser.getUser().getAppCode(),id));
    }

    /**
     * 导出清洗机次号列表
     */
    @PreAuthorize("@ps.hasPermit('process:cleantype:export')")
    @Oplog(title = "清洗机次号", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cssdCleantypeService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());

        List<CssdCleantype> list = cssdCleantypeService.getRecordsByPaging(loginUser.getUser().getAppCode(),1,count,pRequest.getCondition(),"id","Asc");
        ExcelUtils<CssdCleantype> util = new ExcelUtils<CssdCleantype>(CssdCleantype.class);
        return util.exportExcel(list, "CssdCleantype");
    }

}
