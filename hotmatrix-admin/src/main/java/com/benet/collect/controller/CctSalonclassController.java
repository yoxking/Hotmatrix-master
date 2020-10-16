package com.benet.collect.controller;

import java.util.ArrayList;
import java.util.List;

import com.benet.collect.domain.CctPaperclass;
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
import com.benet.collect.domain.CctSalonclass;
import com.benet.collect.service.ICctSalonclassService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;

/**
 * 活动类型Controller
 * 
 * @author yoxking
 * @date 2020-10-14
 */
@Api(value = "collect/salonclass", tags = "活动类型控制器")
@RestController
@RequestMapping("/collect/salonclass")
public class CctSalonclassController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ICctSalonclassService cctSalonclassService;
    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('collect:salonclass:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询活动类型列表
     */
    @PreAuthorize("@ps.hasPermit('collect:salonclass:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cctSalonclassService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());
        List<CctSalonclass> list = cctSalonclassService.getRecordsByPaging(loginUser.getUser().getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), pRequest.getCondition(), "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 查询内容类型列表
     */
    @PreAuthorize("@ps.hasPermit('collect:paperclass:list')")
    @GetMapping(value = "/tree")
    public TableDataInfo tree()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cctSalonclassService.getCountByCondition(loginUser.getUser().getAppCode(),"");
        List<ItemObjectVo> list = buildItemTree(loginUser.getUser().getAppCode(),"0");
        return getDataTable(list, count);
    }

    private List<ItemObjectVo> buildItemTree(String appCode, String parentNo) {

        List<ItemObjectVo> itemTree = null;
        ItemObjectVo item = null;
        List<CctSalonclass> infoList = cctSalonclassService.getRecordsByClassNo(appCode,parentNo);

        if (infoList != null && infoList.size() > 0) {
            itemTree = new ArrayList<>();
            for (CctSalonclass info : infoList) {
                item = new ItemObjectVo();
                item.setId(info.getClassNo());
                item.setKey(info.getClassNo());
                item.setTitle(info.getClassName());
                item.setValue(info.getClassNo());
                item.setChildren(buildItemTree(appCode,info.getClassNo()));

                itemTree.add(item);
            }
        }
        return itemTree;
    }

    /**
     * 新增活动类型
     */
    @PreAuthorize("@ps.hasPermit('collect:salonclass:addnew')")
    @Oplog(title = "活动类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody CctSalonclass cctSalonclass) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        cctSalonclass.setClassNo(UuidUtils.shortUUID());
        cctSalonclass.setCreateBy(loginUser.getUser().getUserNo());
        cctSalonclass.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(cctSalonclassService.AddNewRecord(loginUser.getUser().getAppCode(),cctSalonclass));
    }

    /**
     * 编辑活动类型
     */
    @PreAuthorize("@ps.hasPermit('collect:salonclass:update')")
    @Oplog(title = "活动类型", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody CctSalonclass cctSalonclass) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        cctSalonclass.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cctSalonclassService.UpdateRecord(loginUser.getUser().getAppCode(),cctSalonclass));
        }

    /**
     * 保存活动类型
     */
    @PreAuthorize("@ps.hasPermit('collect:salonclass:save')")
    @Oplog(title = "活动类型", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody CctSalonclass cctSalonclass) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(cctSalonclassService.getRecordByNo(loginUser.getUser().getAppCode(),cctSalonclass.getClassNo()))) {
            cctSalonclass.setClassNo(UuidUtils.shortUUID());
            cctSalonclass.setCreateBy(loginUser.getUser().getUserNo());
            cctSalonclass.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cctSalonclassService.AddNewRecord(loginUser.getUser().getAppCode(),cctSalonclass));
        } else {
            cctSalonclass.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cctSalonclassService.UpdateRecord(loginUser.getUser().getAppCode(),cctSalonclass));
        }
    }

    /**
     * 删除活动类型
     */
    @PreAuthorize("@ps.hasPermit('collect:salonclass:delete')")
    @Oplog(title = "活动类型", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(cctSalonclassService.SoftDeleteByNos(loginUser.getUser().getAppCode(),ids));
    }

    /**
     * 获取活动类型详细信息
     */
    @PreAuthorize("@ps.hasPermit('collect:salonclass:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(cctSalonclassService.getRecordByNo(loginUser.getUser().getAppCode(),id));
    }

    /**
     * 导出活动类型列表
     */
    @PreAuthorize("@ps.hasPermit('collect:salonclass:export')")
    @Oplog(title = "活动类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cctSalonclassService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());

        List<CctSalonclass> list = cctSalonclassService.getRecordsByPaging(loginUser.getUser().getAppCode(),1,count,pRequest.getCondition(),"id","Asc");
        ExcelUtils<CctSalonclass> util = new ExcelUtils<CctSalonclass>(CctSalonclass.class);
        return util.exportExcel(list, "CctSalonclass");
    }

}
