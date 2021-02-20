package com.benet.process.controller;

import java.util.ArrayList;
import java.util.List;
import com.benet.common.core.pager.PageRequest;
import com.benet.common.core.pager.TableDataInfo;
import com.benet.common.utils.string.PinyinUtils;
import com.benet.common.utils.uuid.UuidUtils;
import com.benet.common.utils.web.ServletUtils;
import com.benet.framework.security.LoginUser;
import com.benet.framework.security.service.MyJwtokenService;
import com.benet.system.vmodel.ItemObjectVo;
import io.swagger.annotations.Api;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.benet.process.domain.CssdEquipments;
import com.benet.process.service.ICssdEquipmentsService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;

/**
 * 器械信息Controller
 * 
 * @author yoxking
 * @date 2020-12-13
 */
@Api(value = "process/equipments", tags = "器械信息控制器")
@RestController
@RequestMapping("/process/equipments")
public class CssdEquipmentsController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ICssdEquipmentsService cssdEquipmentsService;
    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('process:equipments:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询器械信息列表
     */
    @PreAuthorize("@ps.hasPermit('process:equipments:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        String condition=" equip_type='"+pRequest.getDataParam()+"' ";
        if(StringUtils.isNotEmpty(pRequest.getCondition())){
            condition+=" And "+pRequest.getCondition();
        }

        int count = cssdEquipmentsService.getCountByCondition(loginUser.getUser().getAppCode(),condition);
        List<CssdEquipments> list = cssdEquipmentsService.getRecordsByPaging(loginUser.getUser().getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), condition, "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 查询内容类型列表
     */
    @PreAuthorize("@ps.hasPermit('process:equipments:list')")
    @GetMapping(value = "/tree")
    public TableDataInfo tree(@RequestParam("equipType") String equipType)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        String condition=" equip_type='"+equipType+"'";
        int count = cssdEquipmentsService.getCountByCondition(loginUser.getUser().getAppCode(),condition);
        List<ItemObjectVo> list = buildItemTree(loginUser.getUser().getAppCode(),equipType);
        return getDataTable(list, count);
    }

    private List<ItemObjectVo> buildItemTree(String appCode,String equipType) {

        List<ItemObjectVo> itemTree = null;
        ItemObjectVo item = null;
        String condition=" equip_type='"+equipType+"'";
        List<CssdEquipments> infoList = cssdEquipmentsService.getRecordsByPaging(appCode,1,100,condition,"id","asc");

        if (infoList != null && infoList.size() > 0) {
            itemTree = new ArrayList<>();
            for (CssdEquipments info : infoList) {
                item = new ItemObjectVo();
                item.setId(info.getEquipNo());
                item.setKey(info.getEquipNo());
                item.setTitle(info.getEquipCname());
                item.setValue(info.getEquipNo());
                item.setChildren(null);

                itemTree.add(item);
            }
        }
        return itemTree;
    }

    /**
     * 新增器械信息
     */
    @PreAuthorize("@ps.hasPermit('process:equipments:addnew')")
    @Oplog(title = "器械信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody CssdEquipments cssdEquipments) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        cssdEquipments.setEquipNo(UuidUtils.shortUUID());
        cssdEquipments.setEquipEname(PinyinUtils.getFirstSpell(cssdEquipments.getEquipCname()));
        cssdEquipments.setCreateBy(loginUser.getUser().getUserNo());
        cssdEquipments.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(cssdEquipmentsService.AddNewRecord(loginUser.getUser().getAppCode(),cssdEquipments));
    }

    /**
     * 编辑器械信息
     */
    @PreAuthorize("@ps.hasPermit('process:equipments:update')")
    @Oplog(title = "器械信息", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody CssdEquipments cssdEquipments) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        cssdEquipments.setEquipEname(PinyinUtils.getFirstSpell(cssdEquipments.getEquipCname()));
        cssdEquipments.setUpdateBy(loginUser.getUser().getUserNo());
        return toAjax(cssdEquipmentsService.UpdateRecord(loginUser.getUser().getAppCode(), cssdEquipments));
    }

    /**
     * 保存器械信息
     */
    @PreAuthorize("@ps.hasPermit('process:equipments:save')")
    @Oplog(title = "器械信息", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody CssdEquipments cssdEquipments) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(cssdEquipmentsService.getRecordByNo(loginUser.getUser().getAppCode(),cssdEquipments.getEquipNo()))) {
            cssdEquipments.setEquipNo(UuidUtils.shortUUID());
            cssdEquipments.setCreateBy(loginUser.getUser().getUserNo());
            cssdEquipments.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cssdEquipmentsService.AddNewRecord(loginUser.getUser().getAppCode(),cssdEquipments));
        } else {
            cssdEquipments.setUpdateBy(loginUser.getUser().getUserNo());
            return toAjax(cssdEquipmentsService.UpdateRecord(loginUser.getUser().getAppCode(),cssdEquipments));
        }
    }

    /**
     * 删除器械信息
     */
    @PreAuthorize("@ps.hasPermit('process:equipments:delete')")
    @Oplog(title = "器械信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(cssdEquipmentsService.SoftDeleteByNos(loginUser.getUser().getAppCode(),ids));
    }

    /**
     * 获取器械信息详细信息
     */
    @PreAuthorize("@ps.hasPermit('process:equipments:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(cssdEquipmentsService.getRecordByNo(loginUser.getUser().getAppCode(),id));
    }

    /**
     * 导出器械信息列表
     */
    @PreAuthorize("@ps.hasPermit('process:equipments:export')")
    @Oplog(title = "器械信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cssdEquipmentsService.getCountByCondition(loginUser.getUser().getAppCode(),pRequest.getCondition());

        List<CssdEquipments> list = cssdEquipmentsService.getRecordsByPaging(loginUser.getUser().getAppCode(),1,count,pRequest.getCondition(),"id","Asc");
        ExcelUtils<CssdEquipments> util = new ExcelUtils<CssdEquipments>(CssdEquipments.class);
        return util.exportExcel(list, "CssdEquipments");
    }

}
