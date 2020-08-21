package com.benet.flow.controller;

import java.util.List;
import com.benet.common.core.pager.PageRequest;
import com.benet.common.core.pager.TableDataInfo;
import com.benet.common.utils.uuid.UuidUtils;
import com.benet.common.utils.web.ServletUtils;
import com.benet.framework.security.LoginUser;
import com.benet.framework.security.service.MyJwtokenService;
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
import com.benet.workflow.domain.FlwFlownotes;
import com.benet.workflow.service.IFlwFlownotesService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;

/**
 * 流程处理意见Controller
 * 
 * @author yoxking
 * @date 2020-05-17
 */
@Api(value = "flow/flownotes", tags = "流程处理意见控制器")
@RestController
@RequestMapping("/flow/flownotes")
public class FlwFlownotesController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private IFlwFlownotesService flwFlownotesService;
    /**
     * 首页
     */
    //@PreAuthorize("@ps.hasPermit('system:flownotes:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询流程处理意见列表
     */
    //@PreAuthorize("@ps.hasPermit('system:flownotes:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = flwFlownotesService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());
        List<FlwFlownotes> list = flwFlownotesService.getRecordsByPaging(loginUser.getUser().getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), pRequest.getCondition(), "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增流程处理意见
     */
    //@PreAuthorize("@ps.hasPermit('system:flownotes:insert')")
    @Oplog(title = "流程处理意见", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody FlwFlownotes flwFlownotes) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        flwFlownotes.setNoteNo(UuidUtils.shortUUID());
        flwFlownotes.setCreateBy(loginUser.getUser().getUserNo());
        flwFlownotes.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(flwFlownotesService.AddNewRecord(loginUser.getUser().getAppCode(),flwFlownotes));
    }

    /**
     * 编辑流程处理意见
     */
    //@PreAuthorize("@ps.hasPermit('system:flownotes:update')")
    @Oplog(title = "流程处理意见", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody FlwFlownotes flwFlownotes) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        flwFlownotes.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(flwFlownotesService.UpdateRecord(loginUser.getUser().getAppCode(),flwFlownotes));
        }

    /**
     * 保存流程处理意见
     */
    //@PreAuthorize("@ps.hasPermit('system:flownotes:save')")
    @Oplog(title = "流程处理意见", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody FlwFlownotes flwFlownotes) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(flwFlownotesService.getRecordByNo(loginUser.getUser().getAppCode(),flwFlownotes.getNoteNo()))) {
            flwFlownotes.setNoteNo(UuidUtils.shortUUID());
            flwFlownotes.setCreateBy(loginUser.getUser().getUserNo());
            flwFlownotes.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(flwFlownotesService.AddNewRecord(loginUser.getUser().getAppCode(),flwFlownotes));
        } else {
            flwFlownotes.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(flwFlownotesService.UpdateRecord(loginUser.getUser().getAppCode(),flwFlownotes));
        }
    }

    /**
     * 删除流程处理意见
     */
    //@PreAuthorize("@ps.hasPermit('system:flownotes:delete')")
    @Oplog(title = "流程处理意见", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(flwFlownotesService.SoftDeleteByNos(loginUser.getUser().getAppCode(),ids));
    }

    /**
     * 获取流程处理意见详细信息
     */
    //@PreAuthorize("@ps.hasPermit('system:flownotes:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(flwFlownotesService.getRecordByNo(loginUser.getUser().getAppCode(),id));
    }

    /**
     * 导出流程处理意见列表
     */
    //@PreAuthorize("@ps.hasPermit('system:flownotes:export')")
    @Oplog(title = "流程处理意见", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = flwFlownotesService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());

        List<FlwFlownotes> list = flwFlownotesService.getRecordsByPaging(loginUser.getUser().getAppCode(),1,count,pRequest.getCondition(),"id","Asc");
        ExcelUtils<FlwFlownotes> util = new ExcelUtils<FlwFlownotes>(FlwFlownotes.class);
        return util.exportExcel(list, "FlwFlownotes");
    }

}
