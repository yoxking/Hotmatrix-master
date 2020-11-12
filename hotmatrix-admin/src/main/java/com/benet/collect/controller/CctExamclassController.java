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
import com.benet.collect.domain.CctExamclass;
import com.benet.collect.service.ICctExamclassService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.core.pager.TableDataInfo;

/**
 * 测评类型Controller
 * 
 * @author yoxking
 * @date 2020-11-10
 */
@Api(value = "collect/examclass", tags = "测评类型控制器")
@RestController
@RequestMapping("/collect/examclass")
public class CctExamclassController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ICctExamclassService cctExamclassService;
    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('collect:examclass:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询测评类型列表
     */
    @PreAuthorize("@ps.hasPermit('collect:examclass:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cctExamclassService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());
        List<CctExamclass> list = cctExamclassService.getRecordsByPaging(loginUser.getUser().getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), pRequest.getCondition(), "id", "Asc");
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
        int count = cctExamclassService.getCountByCondition(loginUser.getUser().getAppCode(),"");
        List<ItemObjectVo> list = buildItemTree(loginUser.getUser().getAppCode(),"0");
        return getDataTable(list, count);
    }

    private List<ItemObjectVo> buildItemTree(String appCode, String parentNo) {

        List<ItemObjectVo> itemTree = null;
        ItemObjectVo item = null;
        List<CctExamclass> infoList = cctExamclassService.getRecordsByClassNo(appCode,parentNo);

        if (infoList != null && infoList.size() > 0) {
            itemTree = new ArrayList<>();
            for (CctExamclass info : infoList) {
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
     * 新增测评类型
     */
    @PreAuthorize("@ps.hasPermit('collect:examclass:addnew')")
    @Oplog(title = "测评类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody CctExamclass cctExamclass) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        cctExamclass.setClassNo(UuidUtils.shortUUID());
        cctExamclass.setCreateBy(loginUser.getUser().getUserNo());
        cctExamclass.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(cctExamclassService.AddNewRecord(loginUser.getUser().getAppCode(),cctExamclass));
    }

    /**
     * 编辑测评类型
     */
    @PreAuthorize("@ps.hasPermit('collect:examclass:update')")
    @Oplog(title = "测评类型", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody CctExamclass cctExamclass) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        cctExamclass.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cctExamclassService.UpdateRecord(loginUser.getUser().getAppCode(),cctExamclass));
        }

    /**
     * 保存测评类型
     */
    @PreAuthorize("@ps.hasPermit('collect:examclass:save')")
    @Oplog(title = "测评类型", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody CctExamclass cctExamclass) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(cctExamclassService.getRecordByNo(loginUser.getUser().getAppCode(),cctExamclass.getClassNo()))) {
            cctExamclass.setClassNo(UuidUtils.shortUUID());
            cctExamclass.setCreateBy(loginUser.getUser().getUserNo());
            cctExamclass.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cctExamclassService.AddNewRecord(loginUser.getUser().getAppCode(),cctExamclass));
        } else {
            cctExamclass.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cctExamclassService.UpdateRecord(loginUser.getUser().getAppCode(),cctExamclass));
        }
    }

    /**
     * 删除测评类型
     */
    @PreAuthorize("@ps.hasPermit('collect:examclass:delete')")
    @Oplog(title = "测评类型", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(cctExamclassService.SoftDeleteByNos(loginUser.getUser().getAppCode(),ids));
    }

    /**
     * 获取测评类型详细信息
     */
    @PreAuthorize("@ps.hasPermit('collect:examclass:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(cctExamclassService.getRecordByNo(loginUser.getUser().getAppCode(),id));
    }

    /**
     * 导出测评类型列表
     */
    @PreAuthorize("@ps.hasPermit('collect:examclass:export')")
    @Oplog(title = "测评类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cctExamclassService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());

        List<CctExamclass> list = cctExamclassService.getRecordsByPaging(loginUser.getUser().getAppCode(),1,count,pRequest.getCondition(),"id","Asc");
        ExcelUtils<CctExamclass> util = new ExcelUtils<CctExamclass>(CctExamclass.class);
        return util.exportExcel(list, "CctExamclass");
    }

}
