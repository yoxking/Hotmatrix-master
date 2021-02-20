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
import com.benet.process.domain.CssdEquipclass;
import com.benet.process.service.ICssdEquipclassService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;

/**
 * 器械类型Controller
 * 
 * @author yoxking
 * @date 2020-12-13
 */
@Api(value = "process/equipclass", tags = "器械类型控制器")
@RestController
@RequestMapping("/process/equipclass")
public class CssdEquipclassController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ICssdEquipclassService cssdEquipclassService;
    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('process:equipclass:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询器械类型列表
     */
    @PreAuthorize("@ps.hasPermit('process:equipclass:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cssdEquipclassService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());
        List<CssdEquipclass> list = cssdEquipclassService.getRecordsByPaging(loginUser.getUser().getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), pRequest.getCondition(), "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 查询内容类型列表
     */
    @PreAuthorize("@ps.hasPermit('process:equipclass:list')")
    @GetMapping(value = "/tree")
    public TableDataInfo tree()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cssdEquipclassService.getCountByCondition(loginUser.getUser().getAppCode(),"");
        List<ItemObjectVo> list = buildItemTree(loginUser.getUser().getAppCode());
        return getDataTable(list, count);
    }

    private List<ItemObjectVo> buildItemTree(String appCode) {

        List<ItemObjectVo> itemTree = null;
        ItemObjectVo item = null;
        List<CssdEquipclass> infoList = cssdEquipclassService.getAllRecords(appCode);

        if (infoList != null && infoList.size() > 0) {
            itemTree = new ArrayList<>();
            for (CssdEquipclass info : infoList) {
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
     * 新增器械类型
     */
    @PreAuthorize("@ps.hasPermit('process:equipclass:addnew')")
    @Oplog(title = "器械类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody CssdEquipclass cssdEquipclass) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        cssdEquipclass.setClassNo(UuidUtils.shortUUID());
        cssdEquipclass.setCreateBy(loginUser.getUser().getUserNo());
        cssdEquipclass.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(cssdEquipclassService.AddNewRecord(loginUser.getUser().getAppCode(),cssdEquipclass));
    }

    /**
     * 编辑器械类型
     */
    @PreAuthorize("@ps.hasPermit('process:equipclass:update')")
    @Oplog(title = "器械类型", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody CssdEquipclass cssdEquipclass) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        cssdEquipclass.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cssdEquipclassService.UpdateRecord(loginUser.getUser().getAppCode(),cssdEquipclass));
        }

    /**
     * 保存器械类型
     */
    @PreAuthorize("@ps.hasPermit('process:equipclass:save')")
    @Oplog(title = "器械类型", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody CssdEquipclass cssdEquipclass) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(cssdEquipclassService.getRecordByNo(loginUser.getUser().getAppCode(),cssdEquipclass.getClassNo()))) {
            cssdEquipclass.setClassNo(UuidUtils.shortUUID());
            cssdEquipclass.setCreateBy(loginUser.getUser().getUserNo());
            cssdEquipclass.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cssdEquipclassService.AddNewRecord(loginUser.getUser().getAppCode(),cssdEquipclass));
        } else {
            cssdEquipclass.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cssdEquipclassService.UpdateRecord(loginUser.getUser().getAppCode(),cssdEquipclass));
        }
    }

    /**
     * 删除器械类型
     */
    @PreAuthorize("@ps.hasPermit('process:equipclass:delete')")
    @Oplog(title = "器械类型", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(cssdEquipclassService.SoftDeleteByNos(loginUser.getUser().getAppCode(),ids));
    }

    /**
     * 获取器械类型详细信息
     */
    @PreAuthorize("@ps.hasPermit('process:equipclass:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(cssdEquipclassService.getRecordByNo(loginUser.getUser().getAppCode(),id));
    }

    /**
     * 导出器械类型列表
     */
    @PreAuthorize("@ps.hasPermit('process:equipclass:export')")
    @Oplog(title = "器械类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cssdEquipclassService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());

        List<CssdEquipclass> list = cssdEquipclassService.getRecordsByPaging(loginUser.getUser().getAppCode(),1,count,pRequest.getCondition(),"id","Asc");
        ExcelUtils<CssdEquipclass> util = new ExcelUtils<CssdEquipclass>(CssdEquipclass.class);
        return util.exportExcel(list, "CssdEquipclass");
    }

}
