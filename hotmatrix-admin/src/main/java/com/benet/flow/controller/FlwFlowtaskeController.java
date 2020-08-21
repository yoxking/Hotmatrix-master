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
import com.benet.workflow.domain.FlwFlowtaske;
import com.benet.workflow.service.IFlwFlowtaskeService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;

/**
 * 流程任务Controller
 * 
 * @author yoxking
 * @date 2020-05-17
 */
@Api(value = "flow/flowtaske", tags = "流程任务控制器")
@RestController
@RequestMapping("/flow/flowtaske")
public class FlwFlowtaskeController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private IFlwFlowtaskeService flwFlowtaskeService;
    /**
     * 首页
     */
    //@PreAuthorize("@ps.hasPermit('system:flowtaske:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询流程任务列表
     */
    //@PreAuthorize("@ps.hasPermit('system:flowtaske:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = flwFlowtaskeService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());
        List<FlwFlowtaske> list = flwFlowtaskeService.getRecordsByPaging(loginUser.getUser().getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), pRequest.getCondition(), "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增流程任务
     */
    //@PreAuthorize("@ps.hasPermit('system:flowtaske:insert')")
    @Oplog(title = "流程任务", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody FlwFlowtaske flwFlowtaske) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        flwFlowtaske.setTaskNo(UuidUtils.shortUUID());
        flwFlowtaske.setCreateBy(loginUser.getUser().getUserNo());
        flwFlowtaske.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(flwFlowtaskeService.AddNewRecord(loginUser.getUser().getAppCode(),flwFlowtaske));
    }

    /**
     * 编辑流程任务
     */
    //@PreAuthorize("@ps.hasPermit('system:flowtaske:update')")
    @Oplog(title = "流程任务", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody FlwFlowtaske flwFlowtaske) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        flwFlowtaske.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(flwFlowtaskeService.UpdateRecord(loginUser.getUser().getAppCode(),flwFlowtaske));
        }

    /**
     * 保存流程任务
     */
    //@PreAuthorize("@ps.hasPermit('system:flowtaske:save')")
    @Oplog(title = "流程任务", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody FlwFlowtaske flwFlowtaske) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(flwFlowtaskeService.getRecordByNo(loginUser.getUser().getAppCode(),flwFlowtaske.getTaskNo()))) {
            flwFlowtaske.setTaskNo(UuidUtils.shortUUID());
            flwFlowtaske.setCreateBy(loginUser.getUser().getUserNo());
            flwFlowtaske.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(flwFlowtaskeService.AddNewRecord(loginUser.getUser().getAppCode(),flwFlowtaske));
        } else {
            flwFlowtaske.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(flwFlowtaskeService.UpdateRecord(loginUser.getUser().getAppCode(),flwFlowtaske));
        }
    }

    /**
     * 删除流程任务
     */
    //@PreAuthorize("@ps.hasPermit('system:flowtaske:delete')")
    @Oplog(title = "流程任务", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(flwFlowtaskeService.SoftDeleteByNos(loginUser.getUser().getAppCode(),ids));
    }

    /**
     * 获取流程任务详细信息
     */
    //@PreAuthorize("@ps.hasPermit('system:flowtaske:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(flwFlowtaskeService.getRecordByNo(loginUser.getUser().getAppCode(),id));
    }

    /**
     * 导出流程任务列表
     */
    //@PreAuthorize("@ps.hasPermit('system:flowtaske:export')")
    @Oplog(title = "流程任务", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = flwFlowtaskeService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());

        List<FlwFlowtaske> list = flwFlowtaskeService.getRecordsByPaging(loginUser.getUser().getAppCode(),1,count,pRequest.getCondition(),"id","Asc");
        ExcelUtils<FlwFlowtaske> util = new ExcelUtils<FlwFlowtaske>(FlwFlowtaske.class);
        return util.exportExcel(list, "FlwFlowtaske");
    }

}
