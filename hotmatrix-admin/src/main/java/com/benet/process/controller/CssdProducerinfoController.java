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
import com.benet.process.domain.CssdProducerinfo;
import com.benet.process.service.ICssdProducerinfoService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;

/**
 * 生产商信息Controller
 * 
 * @author yoxking
 * @date 2020-12-13
 */
@Api(value = "process/producerinfo", tags = "生产商信息控制器")
@RestController
@RequestMapping("/process/producerinfo")
public class CssdProducerinfoController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ICssdProducerinfoService cssdProducerinfoService;
    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('process:producerinfo:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询生产商信息列表
     */
    @PreAuthorize("@ps.hasPermit('process:producerinfo:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cssdProducerinfoService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());
        List<CssdProducerinfo> list = cssdProducerinfoService.getRecordsByPaging(loginUser.getUser().getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), pRequest.getCondition(), "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 查询内容类型列表
     */
    @PreAuthorize("@ps.hasPermit('process:producerinfo:list')")
    @GetMapping(value = "/tree")
    public TableDataInfo tree()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cssdProducerinfoService.getCountByCondition(loginUser.getUser().getAppCode(),"");
        List<ItemObjectVo> list = buildItemTree(loginUser.getUser().getAppCode());
        return getDataTable(list, count);
    }

    private List<ItemObjectVo> buildItemTree(String appCode) {

        List<ItemObjectVo> itemTree = null;
        ItemObjectVo item = null;
        List<CssdProducerinfo> infoList = cssdProducerinfoService.getAllRecords(appCode);

        if (infoList != null && infoList.size() > 0) {
            itemTree = new ArrayList<>();
            for (CssdProducerinfo info : infoList) {
                item = new ItemObjectVo();
                item.setId(info.getProdNo());
                item.setKey(info.getProdNo());
                item.setTitle(info.getProdName());
                item.setValue(info.getProdNo());
                item.setChildren(null);

                itemTree.add(item);
            }
        }
        return itemTree;
    }

    /**
     * 新增生产商信息
     */
    @PreAuthorize("@ps.hasPermit('process:producerinfo:addnew')")
    @Oplog(title = "生产商信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody CssdProducerinfo cssdProducerinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        cssdProducerinfo.setProdNo(UuidUtils.shortUUID());
        cssdProducerinfo.setCreateBy(loginUser.getUser().getUserNo());
        cssdProducerinfo.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(cssdProducerinfoService.AddNewRecord(loginUser.getUser().getAppCode(),cssdProducerinfo));
    }

    /**
     * 编辑生产商信息
     */
    @PreAuthorize("@ps.hasPermit('process:producerinfo:update')")
    @Oplog(title = "生产商信息", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody CssdProducerinfo cssdProducerinfo) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        cssdProducerinfo.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cssdProducerinfoService.UpdateRecord(loginUser.getUser().getAppCode(),cssdProducerinfo));
        }

    /**
     * 保存生产商信息
     */
    @PreAuthorize("@ps.hasPermit('process:producerinfo:save')")
    @Oplog(title = "生产商信息", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody CssdProducerinfo cssdProducerinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(cssdProducerinfoService.getRecordByNo(loginUser.getUser().getAppCode(),cssdProducerinfo.getProdNo()))) {
            cssdProducerinfo.setProdNo(UuidUtils.shortUUID());
            cssdProducerinfo.setCreateBy(loginUser.getUser().getUserNo());
            cssdProducerinfo.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cssdProducerinfoService.AddNewRecord(loginUser.getUser().getAppCode(),cssdProducerinfo));
        } else {
            cssdProducerinfo.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cssdProducerinfoService.UpdateRecord(loginUser.getUser().getAppCode(),cssdProducerinfo));
        }
    }

    /**
     * 删除生产商信息
     */
    @PreAuthorize("@ps.hasPermit('process:producerinfo:delete')")
    @Oplog(title = "生产商信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(cssdProducerinfoService.SoftDeleteByNos(loginUser.getUser().getAppCode(),ids));
    }

    /**
     * 获取生产商信息详细信息
     */
    @PreAuthorize("@ps.hasPermit('process:producerinfo:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(cssdProducerinfoService.getRecordByNo(loginUser.getUser().getAppCode(),id));
    }

    /**
     * 导出生产商信息列表
     */
    @PreAuthorize("@ps.hasPermit('process:producerinfo:export')")
    @Oplog(title = "生产商信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cssdProducerinfoService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());

        List<CssdProducerinfo> list = cssdProducerinfoService.getRecordsByPaging(loginUser.getUser().getAppCode(),1,count,pRequest.getCondition(),"id","Asc");
        ExcelUtils<CssdProducerinfo> util = new ExcelUtils<CssdProducerinfo>(CssdProducerinfo.class);
        return util.exportExcel(list, "CssdProducerinfo");
    }

}
