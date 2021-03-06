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
import com.benet.process.domain.CssdPackclass;
import com.benet.process.service.ICssdPackclassService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;

/**
 * 包装方式Controller
 * 
 * @author yoxking
 * @date 2020-12-13
 */
@Api(value = "process/packclass", tags = "包装方式控制器")
@RestController
@RequestMapping("/process/packclass")
public class CssdPackclassController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ICssdPackclassService cssdPackclassService;
    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('process:packclass:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询包装方式列表
     */
    @PreAuthorize("@ps.hasPermit('process:packclass:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cssdPackclassService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());
        List<CssdPackclass> list = cssdPackclassService.getRecordsByPaging(loginUser.getUser().getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), pRequest.getCondition(), "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 查询内容类型列表
     */
    @PreAuthorize("@ps.hasPermit('process:packclass:list')")
    @GetMapping(value = "/tree")
    public TableDataInfo tree()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cssdPackclassService.getCountByCondition(loginUser.getUser().getAppCode(),"");
        List<ItemObjectVo> list = buildItemTree(loginUser.getUser().getAppCode());
        return getDataTable(list, count);
    }

    private List<ItemObjectVo> buildItemTree(String appCode) {

        List<ItemObjectVo> itemTree = null;
        ItemObjectVo item = null;
        List<CssdPackclass> infoList = cssdPackclassService.getAllRecords(appCode);

        if (infoList != null && infoList.size() > 0) {
            itemTree = new ArrayList<>();
            for (CssdPackclass info : infoList) {
                item = new ItemObjectVo();
                item.setId(info.getPackeNo());
                item.setKey(info.getPackeNo());
                item.setTitle(info.getPackeName());
                item.setValue(info.getPackeNo());
                item.setChildren(null);

                itemTree.add(item);
            }
        }
        return itemTree;
    }

    /**
     * 新增包装方式
     */
    @PreAuthorize("@ps.hasPermit('process:packclass:addnew')")
    @Oplog(title = "包装方式", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody CssdPackclass cssdPackclass) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        cssdPackclass.setPackeNo(UuidUtils.shortUUID());
        cssdPackclass.setCreateBy(loginUser.getUser().getUserNo());
        cssdPackclass.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(cssdPackclassService.AddNewRecord(loginUser.getUser().getAppCode(),cssdPackclass));
    }

    /**
     * 编辑包装方式
     */
    @PreAuthorize("@ps.hasPermit('process:packclass:update')")
    @Oplog(title = "包装方式", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody CssdPackclass cssdPackclass) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        cssdPackclass.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cssdPackclassService.UpdateRecord(loginUser.getUser().getAppCode(),cssdPackclass));
        }

    /**
     * 保存包装方式
     */
    @PreAuthorize("@ps.hasPermit('process:packclass:save')")
    @Oplog(title = "包装方式", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody CssdPackclass cssdPackclass) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(cssdPackclassService.getRecordByNo(loginUser.getUser().getAppCode(),cssdPackclass.getPackeNo()))) {
            cssdPackclass.setPackeNo(UuidUtils.shortUUID());
            cssdPackclass.setCreateBy(loginUser.getUser().getUserNo());
            cssdPackclass.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cssdPackclassService.AddNewRecord(loginUser.getUser().getAppCode(),cssdPackclass));
        } else {
            cssdPackclass.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cssdPackclassService.UpdateRecord(loginUser.getUser().getAppCode(),cssdPackclass));
        }
    }

    /**
     * 删除包装方式
     */
    @PreAuthorize("@ps.hasPermit('process:packclass:delete')")
    @Oplog(title = "包装方式", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(cssdPackclassService.SoftDeleteByNos(loginUser.getUser().getAppCode(),ids));
    }

    /**
     * 获取包装方式详细信息
     */
    @PreAuthorize("@ps.hasPermit('process:packclass:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(cssdPackclassService.getRecordByNo(loginUser.getUser().getAppCode(),id));
    }

    /**
     * 导出包装方式列表
     */
    @PreAuthorize("@ps.hasPermit('process:packclass:export')")
    @Oplog(title = "包装方式", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cssdPackclassService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());

        List<CssdPackclass> list = cssdPackclassService.getRecordsByPaging(loginUser.getUser().getAppCode(),1,count,pRequest.getCondition(),"id","Asc");
        ExcelUtils<CssdPackclass> util = new ExcelUtils<CssdPackclass>(CssdPackclass.class);
        return util.exportExcel(list, "CssdPackclass");
    }

}
