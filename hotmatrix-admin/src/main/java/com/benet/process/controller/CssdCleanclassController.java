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
import com.benet.process.domain.CssdCleanclass;
import com.benet.process.service.ICssdCleanclassService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;

/**
 * 清洗程序类型Controller
 * 
 * @author yoxking
 * @date 2020-12-13
 */
@Api(value = "process/cleanclass", tags = "清洗程序类型控制器")
@RestController
@RequestMapping("/process/cleanclass")
public class CssdCleanclassController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ICssdCleanclassService cssdCleanclassService;
    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('process:cleanclass:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询清洗程序类型列表
     */
    @PreAuthorize("@ps.hasPermit('process:cleanclass:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cssdCleanclassService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());
        List<CssdCleanclass> list = cssdCleanclassService.getRecordsByPaging(loginUser.getUser().getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), pRequest.getCondition(), "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 查询内容类型列表
     */
    @PreAuthorize("@ps.hasPermit('process:cleanclass:list')")
    @GetMapping(value = "/tree")
    public TableDataInfo tree()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cssdCleanclassService.getCountByCondition(loginUser.getUser().getAppCode(),"");
        List<ItemObjectVo> list = buildItemTree(loginUser.getUser().getAppCode());
        return getDataTable(list, count);
    }

    private List<ItemObjectVo> buildItemTree(String appCode) {

        List<ItemObjectVo> itemTree = null;
        ItemObjectVo item = null;
        List<CssdCleanclass> infoList = cssdCleanclassService.getAllRecords(appCode);

        if (infoList != null && infoList.size() > 0) {
            itemTree = new ArrayList<>();
            for (CssdCleanclass info : infoList) {
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
     * 新增清洗程序类型
     */
    @PreAuthorize("@ps.hasPermit('process:cleanclass:addnew')")
    @Oplog(title = "清洗程序类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody CssdCleanclass cssdCleanclass) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        cssdCleanclass.setClassNo(UuidUtils.shortUUID());
        cssdCleanclass.setCreateBy(loginUser.getUser().getUserNo());
        cssdCleanclass.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(cssdCleanclassService.AddNewRecord(loginUser.getUser().getAppCode(),cssdCleanclass));
    }

    /**
     * 编辑清洗程序类型
     */
    @PreAuthorize("@ps.hasPermit('process:cleanclass:update')")
    @Oplog(title = "清洗程序类型", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody CssdCleanclass cssdCleanclass) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        cssdCleanclass.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cssdCleanclassService.UpdateRecord(loginUser.getUser().getAppCode(),cssdCleanclass));
        }

    /**
     * 保存清洗程序类型
     */
    @PreAuthorize("@ps.hasPermit('process:cleanclass:save')")
    @Oplog(title = "清洗程序类型", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody CssdCleanclass cssdCleanclass) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(cssdCleanclassService.getRecordByNo(loginUser.getUser().getAppCode(),cssdCleanclass.getClassNo()))) {
            cssdCleanclass.setClassNo(UuidUtils.shortUUID());
            cssdCleanclass.setCreateBy(loginUser.getUser().getUserNo());
            cssdCleanclass.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cssdCleanclassService.AddNewRecord(loginUser.getUser().getAppCode(),cssdCleanclass));
        } else {
            cssdCleanclass.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cssdCleanclassService.UpdateRecord(loginUser.getUser().getAppCode(),cssdCleanclass));
        }
    }

    /**
     * 删除清洗程序类型
     */
    @PreAuthorize("@ps.hasPermit('process:cleanclass:delete')")
    @Oplog(title = "清洗程序类型", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(cssdCleanclassService.SoftDeleteByNos(loginUser.getUser().getAppCode(),ids));
    }

    /**
     * 获取清洗程序类型详细信息
     */
    @PreAuthorize("@ps.hasPermit('process:cleanclass:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(cssdCleanclassService.getRecordByNo(loginUser.getUser().getAppCode(),id));
    }

    /**
     * 导出清洗程序类型列表
     */
    @PreAuthorize("@ps.hasPermit('process:cleanclass:export')")
    @Oplog(title = "清洗程序类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cssdCleanclassService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());

        List<CssdCleanclass> list = cssdCleanclassService.getRecordsByPaging(loginUser.getUser().getAppCode(),1,count,pRequest.getCondition(),"id","Asc");
        ExcelUtils<CssdCleanclass> util = new ExcelUtils<CssdCleanclass>(CssdCleanclass.class);
        return util.exportExcel(list, "CssdCleanclass");
    }

}
