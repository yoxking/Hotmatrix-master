package com.benet.process.controller;

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
import com.benet.process.domain.CssdStockflows;
import com.benet.process.service.ICssdStockflowsService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;

/**
 * 出入库操作Controller
 * 
 * @author yoxking
 * @date 2020-12-13
 */
@Api(value = "process/stockflows", tags = "出入库操作控制器")
@RestController
@RequestMapping("/process/stockflows")
public class CssdStockflowsController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ICssdStockflowsService cssdStockflowsService;
    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('process:stockflows:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询出入库操作列表
     */
    @PreAuthorize("@ps.hasPermit('process:stockflows:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        String condition=" equip_type='"+pRequest.getDataParam()+"' ";
        if(StringUtils.isNotEmpty(pRequest.getCondition())){
            condition+=" And "+pRequest.getCondition();
        }

        int count = cssdStockflowsService.getCountByCondition(loginUser.getUser().getAppCode(),condition);
        List<CssdStockflows> list = cssdStockflowsService.getRecordsByPaging(loginUser.getUser().getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), condition, "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增出入库操作
     */
    @PreAuthorize("@ps.hasPermit('process:stockflows:addnew')")
    @Oplog(title = "出入库操作", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody CssdStockflows cssdStockflows) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        cssdStockflows.setSflowNo(UuidUtils.shortUUID());
        cssdStockflows.setCreateBy(loginUser.getUser().getUserNo());
        cssdStockflows.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(cssdStockflowsService.AddNewRecord(loginUser.getUser().getAppCode(),cssdStockflows));
    }

    /**
     * 编辑出入库操作
     */
    @PreAuthorize("@ps.hasPermit('process:stockflows:update')")
    @Oplog(title = "出入库操作", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody CssdStockflows cssdStockflows) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        cssdStockflows.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cssdStockflowsService.UpdateRecord(loginUser.getUser().getAppCode(),cssdStockflows));
        }

    /**
     * 保存出入库操作
     */
    @PreAuthorize("@ps.hasPermit('process:stockflows:save')")
    @Oplog(title = "出入库操作", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody CssdStockflows cssdStockflows) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(cssdStockflowsService.getRecordByNo(loginUser.getUser().getAppCode(),cssdStockflows.getSflowNo()))) {
            cssdStockflows.setSflowNo(UuidUtils.shortUUID());
            cssdStockflows.setCreateBy(loginUser.getUser().getUserNo());
            cssdStockflows.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cssdStockflowsService.AddNewRecord(loginUser.getUser().getAppCode(),cssdStockflows));
        } else {
            cssdStockflows.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cssdStockflowsService.UpdateRecord(loginUser.getUser().getAppCode(),cssdStockflows));
        }
    }

    /**
     * 删除出入库操作
     */
    @PreAuthorize("@ps.hasPermit('process:stockflows:delete')")
    @Oplog(title = "出入库操作", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(cssdStockflowsService.SoftDeleteByNos(loginUser.getUser().getAppCode(),ids));
    }

    /**
     * 获取出入库操作详细信息
     */
    @PreAuthorize("@ps.hasPermit('process:stockflows:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(cssdStockflowsService.getRecordByNo(loginUser.getUser().getAppCode(),id));
    }

    /**
     * 导出出入库操作列表
     */
    @PreAuthorize("@ps.hasPermit('process:stockflows:export')")
    @Oplog(title = "出入库操作", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cssdStockflowsService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());

        List<CssdStockflows> list = cssdStockflowsService.getRecordsByPaging(loginUser.getUser().getAppCode(),1,count,pRequest.getCondition(),"id","Asc");
        ExcelUtils<CssdStockflows> util = new ExcelUtils<CssdStockflows>(CssdStockflows.class);
        return util.exportExcel(list, "CssdStockflows");
    }

}
